/*
 * $Id$
 */
package javaLearn.FileLock.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/***************************************************************************
 * This is the class that handles the writes and reads in the file storage riak
 * simulation.
 */
public class FileClusterDataControl
{
    /**
     * The sole instance of the class.
     */
    private static FileClusterDataControl instance;

    /**
     * Write operation code.
     */
    private final String                  OP_WRITE       = "WRITE";                                           //$NON-NLS-1$

    /**
     * Read operation code.
     */
    private final String                  OP_READ        = "READ";                                            //$NON-NLS-1$

    /**
     * Delete operation code.
     */
    private final String                  OP_DELETE      = "DELETE";                                          //$NON-NLS-1$

    /**
     * Extension of the lock files.
     */
    private final String                  LOCK_EXTENSION = ".lock";                                           //$NON-NLS-1$

    /**
     * Synchronized set to hold the files that are read/write upon.
     */
    private Set<String>                   lockedFiles    = Collections.synchronizedSet(new HashSet<String>());

    /***************************************************************************
     * Creates a new FileClusterDataControl.
     *
     */
    private FileClusterDataControl()
    {

    }

    /***************************************************************************
     * @return Get FileClusterDataControl instance
     */
    public static FileClusterDataControl getInstance()
    {
        if (instance == null)
        {
            synchronized (FileClusterDataControl.class)
            {
                if (instance == null)
                {
                    instance = new FileClusterDataControl();
                }
            }
        }

        return instance;
    }

    /***************************************************************************
     * In case of deserialization get the sole instance.
     * 
     * @return FileClusterDataControl instance
     */
    protected Object readResolve()
    {
        return getInstance();
    }

    /***************************************************************************
     * Write the value in a file by making sure no other threads write/read
     * in/from the same file or other process.
     * 
     * @param path : the file path to write to
     * @param value : the value to write in the file
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void write(String path, String value) throws InterruptedException, IOException
    {
        runOperation(OP_WRITE, path, value);
    }

    /***************************************************************************
     * Read the value in a file by making sure no other threads write/read
     * in/from the same file or other process.
     * 
     * @param path : the file path to read from
     * 
     * @return value in the file
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public String read(String path) throws InterruptedException, IOException
    {
        return runOperation(OP_READ, path, null);
    }

    /***************************************************************************
     * Delete the file by making sure no other threads/processes write/read
     * in/from the same file.
     * 
     * @param path : the file path to be deleted
     *
     * @throws InterruptedException
     * @throws IOException
     */
    public void delete(String path) throws InterruptedException, IOException
    {
        runOperation(OP_DELETE, path, null);
    }

    /***************************************************************************
     * Write/Read the value in a file by making sure no other threads write/read
     * in/from the same file or other process.
     * 
     * @param operationCode
     * @param path
     * @param value
     * 
     * @return value in the file
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    private String runOperation(String operationCode, String path, String value) throws InterruptedException,
                                                                                IOException
    {
        String absolutePath = (new File(path)).getPath();

        Main.log("Entering the synchronized block for add");
        synchronized (lockedFiles)
        {
            Main.log("Entered the synchronized block for add");
            Main.log("locked files before wait: " + Main.getSetContents(lockedFiles));
            while (lockedFiles.contains(absolutePath))
            {
                Main.log("start waiting...");
                lockedFiles.wait();
                Main.log("end waiting...");
            }

            Main.log("locked files after wait: " + Main.getSetContents(lockedFiles));
            lockedFiles.add(absolutePath);
            Main.log("locked files after wait 2: " + Main.getSetContents(lockedFiles));
            Main.log("Leaving the synchronized block for add");
        }
        Main.log("Left the synchronized block for add");

        String returnVal = null;

        try
        {
            Main.log("Starting operation....");
            switch (operationCode)
            {
                case OP_READ:
                    returnVal = readSynced(absolutePath);
                    break;
                case OP_WRITE:
                    writeSynced(absolutePath, value);
                    break;
                case OP_DELETE:
                    deleteSynced(absolutePath);
                    break;
                default:
                    Main.log("Operation '" + operationCode + "' not supported."); //$NON-NLS-1$ //$NON-NLS-2$
                    break;
            }
            Main.log("Finishing operation....");
        }
        finally
        {
            Main.log("Entering the synchronized block for delete");
            synchronized (lockedFiles)
            {
                Main.log("Entered the synchronized block for delete");
                Main.log("locked files before removing: " + Main.getSetContents(lockedFiles));
                lockedFiles.remove(absolutePath);
                Main.log("locked files after removing: " + Main.getSetContents(lockedFiles));
                Main.log("notifyAll...");
                lockedFiles.notifyAll();
                Main.log("Leaving the synchronized block for delete");
            }
            Main.log("Left the synchronized block for delete");
        }
        return returnVal;
    }

    /***************************************************************************
     * Write the value in a file by making sure no other process writes/reads
     * in/from the same file.
     * 
     * @param path : the file path to write to
     * @param value : the value to write in the file
     * 
     * @throws IOException
     * 
     */
    private void writeSynced(String path, String value) throws IOException
    {
        final String METHOD = "writeSynced"; //$NON-NLS-1$

        RandomAccessFile accessFile = null;
        FileChannel fileChannel = null;
        FileLock fileLock = null;
        File lockingFile = new File(path + LOCK_EXTENSION);

        FileOutputStream outFileStream = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;

        try
        {
            if (!lockingFile.exists())
            {
                lockingFile.createNewFile();
            }
            accessFile = new RandomAccessFile(lockingFile, "rw"); //$NON-NLS-1$
            fileChannel = accessFile.getChannel();
            fileLock = fileChannel.lock();

            if (fileLock != null && fileLock.isValid())
            {
                File file = new File(path);
                if (!file.exists())
                {
                    file.createNewFile();
                }
                outFileStream = new FileOutputStream(file, true);
                osw = new OutputStreamWriter(outFileStream);
                bw = new BufferedWriter(osw);
                out = new PrintWriter(bw);

                out.write(value);
                out.flush();
            }
            else
            {
                throw new IOException("Cannot aquire FileLock for the desired file."); //$NON-NLS-1$
            }
        }
        finally
        {
            if (outFileStream != null)
            {
                outFileStream.close();
                outFileStream = null;
            }

            if (out != null)
            {
                out.close();
                out = null;
            }

            if (bw != null)
            {
                bw.close();
                bw = null;
            }

            if (osw != null)
            {
                osw.close();
                osw = null;
            }

            if (fileLock != null)
            {
                fileLock.close();
            }

            if (fileChannel != null)
            {
                fileChannel.close();
            }

            if (accessFile != null)
            {
                accessFile.close();
                accessFile = null;
            }

            lockingFile.delete();
        }
    }

    /***************************************************************************
     * Read the value in a file by making sure no other process writes/reads
     * in/from the same file.
     * 
     * @param path : the file path to read from
     * 
     * @return value in the file
     * 
     * @throws IOException
     */
    private String readSynced(String path) throws IOException
    {
        final String METHOD = "readSynced"; //$NON-NLS-1$

        RandomAccessFile accessFile = null;
        FileChannel fileChannel = null;
        FileLock fileLock = null;
        File lockingFile = new File(path + LOCK_EXTENSION);

        String returnVal = null;
        FileInputStream inFileStream = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try
        {
            if (!lockingFile.exists())
            {
                lockingFile.createNewFile();
            }
            accessFile = new RandomAccessFile(lockingFile, "rw");
            fileChannel = accessFile.getChannel();
            fileLock = fileChannel.lock();

            if (fileLock != null && fileLock.isValid())
            {
                File file = new File(path);
                if (!file.exists())
                {
                    return null;
                }

                inFileStream = new FileInputStream(file);
                isr = new InputStreamReader(inFileStream);
                br = new BufferedReader(isr);

                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null)
                {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
                returnVal = sb.toString();
            }
            else
            {
                throw new IOException("Cannot aquire FileLock for the desired file.");
            }
        }
        finally
        {
            if (inFileStream != null)
            {
                inFileStream.close();
                inFileStream = null;
            }

            if (isr != null)
            {
                isr.close();
                isr = null;
            }

            if (br != null)
            {
                br.close();
                br = null;
            }

            if (fileLock != null)
            {
                fileLock.close();
            }

            if (fileChannel != null)
            {
                fileChannel.close();
            }

            if (accessFile != null)
            {
                accessFile.close();
                accessFile = null;
            }

            lockingFile.delete();
        }

        return returnVal;
    }

    /***************************************************************************
     * Delete the file by making sure no other process writes/reads in/from the
     * same file.
     * 
     * @param path : the file path to be deleted
     *
     * @throws IOException
     */
    private void deleteSynced(String path) throws IOException
    {
        final String METHOD = "deleteSynced"; //$NON-NLS-1$

        RandomAccessFile accessFile = null;
        FileChannel fileChannel = null;
        FileLock fileLock = null;
        File lockingFile = new File(path + LOCK_EXTENSION);

        try
        {
            if (!lockingFile.exists())
            {
                lockingFile.createNewFile();
            }
            accessFile = new RandomAccessFile(lockingFile, "rw"); //$NON-NLS-1$
            fileChannel = accessFile.getChannel();
            fileLock = fileChannel.lock();

            if (fileLock != null && fileLock.isValid())
            {
                File file = new File(path);
                if (!file.exists())
                {
                    return;
                }

                if (!file.delete())
                {
                    throw new IOException("Cannot delete the file."); //$NON-NLS-1$
                }
            }
            else
            {
                throw new IOException("Cannot aquire FileLock for the desired file."); //$NON-NLS-1$
            }
        }
        finally
        {
            if (fileLock != null)
            {
                fileLock.close();
            }

            if (fileChannel != null)
            {
                fileChannel.close();
            }

            if (accessFile != null)
            {
                accessFile.close();
                accessFile = null;
            }

            lockingFile.delete();
        }
    }
}
