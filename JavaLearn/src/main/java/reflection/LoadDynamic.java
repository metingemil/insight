package reflection;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LoadDynamic
{
    public static void main(String[] args)
    {
        LoadDynamic load = new LoadDynamic();
        try
        {
            load.validate();
        }
        catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    public void validate() throws ClassNotFoundException,
                          MalformedURLException,
                          NoSuchMethodException,
                          SecurityException,
                          InstantiationException,
                          IllegalAccessException,
                          IllegalArgumentException,
                          InvocationTargetException
    {
        String pathToJar = "c:\\tmp\\UpgradeValidator.jar";
        File file = new File(pathToJar);
        JarFile jarFile = null;
        try
        {
            jarFile = new JarFile(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        URL[] urls = new URL[]{new URL("file:///c:\\tmp\\UpgradeValidator.jar")};
        URLClassLoader childClassLoader = new URLClassLoader(urls, this.getClass().getClassLoader());
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        
        /*
        while (jarEntries.hasMoreElements())
        {
            JarEntry jarEntry = jarEntries.nextElement();
            if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class"))
            {
                continue;
            }

            String className = jarEntry.getName();
            boolean callingClass = false;
            if (className.equals("reflection.dynamic.Validator"))
            {
                callingClass = true;
            }

            className = className.replace('/', '.');

            Class tempClass = childClassLoader.loadClass(className);

            if (callingClass)
            {
                entryClass = tempClass;
            }
        }
         */
        childClassLoader.loadClass("reflection.dynamic.subpackage1.GuidGenerator");
        Class entryClass =childClassLoader.loadClass("reflection.dynamic.Validator");
        
        Object instance = entryClass.newInstance();
        Method method = entryClass.getDeclaredMethod("enterMethod", String.class);
        ReturnVal returnVal = (ReturnVal)method.invoke(instance, "Nume1");
    }
}
