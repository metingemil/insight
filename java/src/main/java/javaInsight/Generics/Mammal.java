package javaLearn.Generics;

public class Mammal extends Animal
{
    @Override
    public void method1()
    {
        System.out.println("method1() in Mammal class");
    }

    @Override
    public String me()
    {
        return "Mammal";
    }

    public void justMammal()
    {
        System.out.println("This is just an Mammal class method");
    }
}
