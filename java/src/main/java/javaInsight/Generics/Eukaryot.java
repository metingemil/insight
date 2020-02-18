package javaInsight.Generics;

public class Eukaryot extends LifeForm
{
    @Override
    public void method1()
    {
        System.out.println("method1() in Eukaryot class");
    }

    @Override
    public String me()
    {
        return "Eukaryot";
    }
    
    public void justEukaryot()
    {
        System.out.println("This is just an Eukaryot class method");
    }
}
