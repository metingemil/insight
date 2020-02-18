/*
 * $Id$
 */
package javaLearn.FileLock.test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Main3
{
    public static void main(String[] args)
    {
        File lockingFile = null;
        RandomAccessFile accessFile = null;
        FileChannel fileChannel = null;
        FileLock fileLock = null;

        try
        {
            /* Lock the file for other processes trying to access it */
            lockingFile = new File("c:\\riakSimulDir\\riak_simul\\CH_test_lock");
            if (!lockingFile.exists())
            {
                lockingFile.getParentFile().mkdirs();
                lockingFile.createNewFile();
            }
            accessFile = new RandomAccessFile(lockingFile, "rw"); //$NON-NLS-1$;
            fileChannel = accessFile.getChannel();
            fileLock = fileChannel.lock();

            if (fileLock != null && fileLock.isValid())
            {
                System.out.println("File lock aquired");
            }
            else
            {
                throw new IOException("Cannot acquire file lock"); //$NON-NLS-1$
            }
        }
        catch (IOException ioex)
        {
            System.out.println(ioex.getMessage());
        }
        finally
        {
            if (fileLock != null)
            {
                try
                {
                    fileLock.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                fileLock = null;
            }
            if (fileChannel != null)
            {
                try
                {
                    fileChannel.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (accessFile != null)
            {
                try
                {
                    accessFile.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                accessFile = null;
            }

            if (lockingFile.delete())
            {
                System.out.println("Deleted the lock file.");
            }
            else
            {
                System.out.println("Failed to delete the lock file.");
            }
        }
    }
}
