package javaLearn.Generics;

public class Dog extends Canine
{
    @Override
    public void method1()
    {
        System.out.println("method1() in Dog class");
    }

    @Override
    public String me()
    {
        return "Dog";
    }

    public void justDog()
    {
        System.out.println("This is just an Dog class method");
    }
}
