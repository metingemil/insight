/*
 * $Id$
 */
package javaInsight.concurrency;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class HoldLock
{
    private File mFile = new File(".");

    public File getFile()
    {
        return mFile;
    }

    public void setFile(File file)
    {
        mFile = null;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        HoldLock ref = new HoldLock();

        Thread th1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                File lockObj = ref.getFile();

                log("before entering the synchronized block");
                synchronized (lockObj)
                {
                    log("entered the synchronized block");
                    try
                    {
                        log("sleeping for 10 secs - 1st");
                        Thread.sleep(10 * 1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    ref.setFile(null);
                    try
                    {
                        log("sleeping for 10 secs - 2nd");
                        Thread.sleep(10 * 1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    log("leaving the synchronized block");
                }
                log("left the synchronized block");
                log("finishing thread");
            }
        }, "Thread-1");
        th1.start();

        Thread th2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                File lockObj = ref.getFile();
                try
                {
                    log("sleeping for 5 secs");
                    Thread.sleep(5 * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                log("before entering the synchronized block");
                synchronized (lockObj)
                {
                    log("entered the synchronized block");
                    try
                    {
                        log("sleeping for 10 secs");
                        Thread.sleep(10 * 1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                log("finishing thread");
            }
        }, "Thread-2");
        th2.start();
    }

    public static long getPID()
    {
        //String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        return 0L;//Long.parseLong(processName.split("@")[0]);
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
