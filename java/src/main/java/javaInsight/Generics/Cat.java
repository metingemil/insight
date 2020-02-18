package javaLearn.Generics;

public class Cat extends Feline
{
    @Override
    public void method1()
    {
        System.out.println("method1() in Cat class");
    }

    @Override
    public String me()
    {
        return "Cat";
    }

    public void justCat()
    {
        System.out.println("This is just an Cat class method");
    }
}
