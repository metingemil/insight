/*
 * $Id$
 */
package javaInsight.FileLock.test;

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
     * Supported operation types.
     */
    enum OperationType
    {
        /**
         * Change file contents operation type.
         */
        CHANGE,

        /**
         * Delete file operation type.
         */
        DELETE,

        /**
         * Read from file operation type.
         */
        READ,

        /**
         * Write to file operation type.
         */
        WRITE
    } //$NON-NLS-1$

    /**
     * Extension of the lock files.
     */
    private final String LOCK_EXTENSION = ".lock";                                           //$NON-NLS-1$

    /**
     * Synchronized set to hold the files that are read/write upon.
     */
    private Set<String>  lockedFiles    = Collections.synchronizedSet(new HashSet<String>());

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
     * in/from the same file.
     * 
     * @param path : the file path to write to
     * @param value : the value to write in the file
     * @param append : if <code>true</code> the value will be written to the end
     *            of the file, if <code>false</code> the value will clear the
     *            existing value in the file.
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void write(String path, String value, Boolean append) throws InterruptedException, IOException
    {
        runOperation(OperationType.WRITE, path, value, append, null);
    }

    /***************************************************************************
     * Read the value in a file by making sure no other threads write/read
     * in/from the same file.
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
        return runOperation(OperationType.READ, path, null, null, null);
    }

    /***************************************************************************
     * Changes the contents of a file by making sure no other threads/processes
     * write/read in/from the same file.
     * 
     * @param path : the file path
     * @param changeOp : the change operation applied to the file
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public void change(String path, Changeble<String, String> changeOp) throws InterruptedException, IOException
    {
        runOperation(OperationType.CHANGE, path, null, null, changeOp);
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
        runOperation(OperationType.DELETE, path, null, null, null);
    }

    /***************************************************************************
     * Write/Read the value in a file by making sure no other threads write/read
     * in/from the same file or other process.
     * 
     * @param operationCode
     * @param path
     * @param value
     * @param append
     * 
     * @return value in the file
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    private String runOperation(OperationType operationType,
                                String path,
                                String value,
                                Boolean append,
                                Changeble<String, String> changeOp) throws InterruptedException, IOException
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
                /*
                 * Wait on other threads that want to operate on a file that
                 * other threads are operating on
                 */
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
        File lockingFile = null;
        RandomAccessFile accessFile = null;
        FileChannel fileChannel = null;
        FileLock fileLock = null;

        try
        {
            Main.log("Starting operation....");

            /* Lock the file for other processes trying to access it */
            lockingFile = new File(path + LOCK_EXTENSION);
            if (!lockingFile.exists())
            {
                lockingFile.createNewFile();
            }
            accessFile = new RandomAccessFile(lockingFile, "rw"); //$NON-NLS-1$;
            fileChannel = accessFile.getChannel();
            fileLock = fileChannel.lock();

            if (fileLock != null && fileLock.isValid())
            {
                switch (operationType)
                {
                    case CHANGE:
                        writeInFile(absolutePath, changeOp.changeTo(readFromFile(absolutePath)), false);
                        break;

                    case DELETE:
                        File file = new File(path);
                        if (file.exists() && !file.delete())
                        {
                            throw new IOException("Cannot delete the file."); //$NON-NLS-1$
                        }
                        break;

                    case READ:
                        returnVal = readFromFile(absolutePath);
                        break;

                    case WRITE:
                        writeInFile(absolutePath, value, append);
                        break;
                }
            }
            else
            {
                throw new IOException("Cannot aquire FileLock for the desired file."); //$NON-NLS-1$
            }
            Main.log("Finishing operation....");
        }
        finally
        {
            if (fileLock != null)
            {
                fileLock.close();
                fileLock = null;
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

            if (!lockingFile.delete())
            {
                Main.log("Cannot delete the lock file."); //$NON-NLS-1$
            }

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
     * Write the value in a file.
     * 
     * @param path : the file path to write to
     * @param value : the value to write in the file
     * @param append : if <code>true</code> the value will be written to the end
     *            of the file, if <code>false</code> the value will clear the
     *            existing value in the file.
     * 
     * @throws IOException
     * 
     */
    private void writeInFile(String path, String value, Boolean append) throws IOException
    {
        FileOutputStream outFileStream = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;

        try
        {
            File file = new File(path);
            if (!file.exists())
            {
                file.createNewFile();
            }
            outFileStream = new FileOutputStream(file, append);
            osw = new OutputStreamWriter(outFileStream);
            bw = new BufferedWriter(osw);
            out = new PrintWriter(bw);
            out.write(value);
            out.flush();
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
        }
    }

    /***************************************************************************
     * Read the value from a file.
     * 
     * @param path : the file path to read from
     * 
     * @return value in the file
     * 
     * @throws IOException
     */
    private String readFromFile(String path) throws IOException
    {
        String returnVal = null;
        FileInputStream inFileStream = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try
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
            returnVal = sb.toString().trim();
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
        }

        return returnVal;
    }
}
