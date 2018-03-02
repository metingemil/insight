package javaLearn.Generics;

public class Feline extends Mammal
{
    @Override
    public void method1()
    {
        System.out.println("method1() in Feline class");
    }

    @Override
    public String me()
    {
        return "Feline";
    }

    public void justFeline()
    {
        System.out.println("This is just an Feline class method");
    }
}
