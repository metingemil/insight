/*
 * $Id$
 */
package javaLearn.FileLock.test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

public class Main2
{
    public static void main(String[] args)
    {
        log("Started process");
        String filePath1 = "C:\\Users\\metgemil\\Desktop\\file_1.txt";
        String filePath2 = "C:\\Users\\metgemil\\Desktop\\file_2.txt";
        String message1 = "message 1\n";
        String message2 = "message 2\n";

        Thread th2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                log("Start thread");
                try
                {
                    FileClusterDataControl.getInstance().delete(filePath1);
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

    public static String getSetContents(Set<String> set)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = set.size();
        for (String s : set)
        {
            sb.append(s);
            if (size > 1)
            {
                sb.append(", ");
                size--;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
