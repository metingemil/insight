package javaInsight.Generics;

public class Animal extends MulticellularEukaryotic
{
    @Override
    public void method1()
    {
        System.out.println("method1() in Animal class");
    }

    @Override
    public String me()
    {
        return "Animal";
    }
    
    public void justAnimal()
    {
        System.out.println("This is just an Animal class method");
    }
}
