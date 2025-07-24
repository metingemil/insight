/*
 * $Id$
 */
package javaInsight.FileLock.test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
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
                    FileClusterDataControl.getInstance().change(filePath1, new Changeble<String, String>()
                    {
                        /**
                         * Methods for transforming the set to string and vice
                         * versa
                         */
                        private final FileSetClusterUtils mSetsUtils = (new Main2()).new FileSetClusterUtils();

                        @Override
                        public String changeTo(String in)
                        {
                            String out = null;
                            Set<String> set = mSetsUtils.getSetFromString(in);
                            set.remove("val2");
                            out = mSetsUtils.getStringFromSet(set);
                            return out;
                        }
                    });
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

    /***************************************************************************
     * Class holding the methods for transforming the set to string and vice
     * versa.
     */
    class FileSetClusterUtils
    {
        /***************************************************************************
         * Returns the set instance from the string representation of the set.
         * 
         * @param stringSet
         * 
         * @return set instance
         */
        public Set<String> getSetFromString(String stringSet)
        {
            Set<String> result = new LinkedHashSet<String>();
            
            if (stringSet.isEmpty()) return result;

            for (String setEntry : stringSet.split(",")) //$NON-NLS-1$
            {
                result.add(setEntry);
            }
            return result;
        }

        /***************************************************************************
         * Returns the string representation of the set.
         * 
         * @param set
         * 
         * @return the string representation of the set
         */
        public String getStringFromSet(Set<String> set)
        {
            if (set == null) return null;

            int index = 0;
            StringBuilder retVal = new StringBuilder();
            for (String setEntry : set)
            {
                index++;
                retVal.append(setEntry);
                if (index < set.size())
                {
                    retVal.append(","); //$NON-NLS-1$
                }
            }

            return retVal.toString();
        }
    }
}
