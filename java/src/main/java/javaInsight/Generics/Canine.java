package javaLearn.Generics;

public class Canine extends Mammal
{
    @Override
    public void method1()
    {
        System.out.println("method1() in Canine class");
    }

    @Override
    public String me()
    {
        return "Canine";
    }

    public void justCanine()
    {
        System.out.println("This is just an Canine class method");
    }
}
