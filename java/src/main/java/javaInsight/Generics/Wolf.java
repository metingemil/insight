package javaLearn.Generics;

public class Wolf extends Canine
{
    @Override
    public void method1()
    {
        System.out.println("method1() in Wolf class");
    }

    @Override
    public String me()
    {
        return "Wolf";
    }

    public void justWolf()
    {
        System.out.println("This is just an Wolf class method");
    }
}
