package javaLearn.Generics;

public class Lion extends Feline
{
    @Override
    public void method1()
    {
        System.out.println("method1() in Lion class");
    }

    @Override
    public String me()
    {
        return "Lion";
    }

    public void justLion()
    {
        System.out.println("This is just an Lion class method");
    }
}
