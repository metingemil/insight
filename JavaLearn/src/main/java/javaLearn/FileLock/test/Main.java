/*
 * $Id$
 */
package javaLearn.FileLock.test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main
{
    public static void main(String[] args)
    {
        log("Started process");
        String file1 = "C:\\Users\\metgemil\\Desktop\\file_1.txt";
        String file2 = "C:\\Users\\metgemil\\Desktop\\file_2.txt";
        String message1 = "message 1";
        String message2 = "message 2";

        Thread th1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                log("Start thread");
                try
                {
                    FileUtils.writeToFileWithChannelLock(file1, getPID() + " " + message1, 6);
                }
                catch (IOException | InterruptedException e)
                {
                    log("error : " + e.getMessage());
                }
                catch (RuntimeException re)
                {
                    log("error : " + re.getClass().getName() + " " + re.getMessage());
                }
                log("End thread");
            }
        }, "Thread-1");
        th1.start();

        Thread th2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                log("Start thread");
                try
                {
                    FileUtils.writeToFileWithChannelLock(file1, getPID() + " " + message2, 3);
                }
                catch (IOException | InterruptedException e)
                {
                    log("error : " + e.getMessage());
                }
                catch (RuntimeException re)
                {
                    log("error : " + re.getClass().getName() + " " + re.getMessage());
                }

                log("End thread");
            }
        }, "Thread-2");
        th2.start();

        try
        {
            th1.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        try
        {
            th2.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        log("end process");
    }

    public static long getPID()
    {
        String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        return Long.parseLong(processName.split("@")[0]);
    }

    public static void log(String message)
    {
        System.out.println("PID= " + getPID() + " | thread=" + Thread.currentThread().getName() + " | time= "
                + getCurrentTime() + " | " + message);
    }

    public static String getCurrentTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return dateFormat.format(System.currentTimeMillis());
    }
}
