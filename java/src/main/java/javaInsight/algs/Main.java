package javaInsight.algs;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        int value = 0;
        try
        {
            value = System.in.read();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }

        Main main = new Main();
        try
        {
            main.method1(value);
        }
        catch (MyException e)
        {
            e.printStackTrace();
        }
    }

    private void method1(int val) throws MyException
    {
        MyException myEx = new MyException();
        if (val > 0)
        {
            throw myEx;
        }
    }
}

class MyException extends Exception
{
}