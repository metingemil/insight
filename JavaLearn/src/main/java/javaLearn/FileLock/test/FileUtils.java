/*
 * $Id$
 */
package javaLearn.FileLock.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileUtils
{
    public static void writeToFileNoLock(String filePath, String data, int sleepSeconds) throws IOException,
                                                                                        InterruptedException
    {
        FileOutputStream outFileStream = null;
        OutputStreamWriter osw = null;
        if (sleepSeconds != 0)
        {
            Main.log("Sleeping for " + sleepSeconds + " seconds");
            Thread.sleep(sleepSeconds * 1000);
            Main.log("End sleep");
        }

        try
        {
            outFileStream = new FileOutputStream(filePath, true);
            osw = new OutputStreamWriter(outFileStream);
            osw.write(data + "\n");
            osw.flush();
        }
        finally
        {
            if (outFileStream != null)
            {
                outFileStream.close();
                outFileStream = null;
            }
            if (osw != null)
            {
                osw.close();
                osw = null;
            }
        }
    }

    public static void writeToFileWithChannelLock(String filePath, String data, int sleepSeconds) throws IOException,
                                                                                                 InterruptedException
    {
        FileOutputStream outFileStream = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        FileChannel fileChannel = null;
        FileLock fileLock = null;

        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                file.createNewFile();
            }
            outFileStream = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(outFileStream);
            bw = new BufferedWriter(osw);
            out = new PrintWriter(bw);
            fileChannel = outFileStream.getChannel();
            fileLock = fileChannel.lock();
            if (fileLock != null && fileLock.isValid())
            {
                Main.log("Got the lock");
            }
            out.write(data + "\n");
            out.flush();
            if (sleepSeconds != 0)
            {
                Main.log("Sleeping for " + sleepSeconds + " seconds");
                Thread.sleep(sleepSeconds * 1000);
                Main.log("End sleep");
            }
        }
        finally
        {
            /*
             * if (fileLock != null) { fileLock.close(); }
             */
            if (fileChannel != null)
            {
                fileChannel.close();
            }
            if (fileLock != null)
            {
                if (!fileLock.isValid())
                {
                    Main.log("Released the lock");
                }
            }

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
}
