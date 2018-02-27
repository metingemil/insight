package javaLearn.Generics;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Mammal> onlyMammals = null;
		onlyMammals = new ArrayList<Mammal>(); // nothing else allowed
		onlyMammals.add(new Mammal());
		onlyMammals.add(new Feline());
		onlyMammals.add(new Cat());
		onlyMammals.add(new Lion());
		onlyMammals.add(new Canine());
		//onlyMammals.add(new Animal()); not allowed - only subtypes
		
		
		List<? extends Mammal> exMammals = null;
		exMammals = new ArrayList<Mammal>();
		exMammals = new ArrayList<Feline>();
		exMammals = new ArrayList<Cat>();
		//exMammals = new ArrayList<Animal>(); not allowed - only subtypes
		//exMammals.add(new Mammal());
		//exMammals.add(new Feline());
		//exMammals.add(new Animal());
		//exMammals.add(new MulticellularEukaryotic());
	
		List<? super Mammal> suMammals = null;
		suMammals = new ArrayList<Mammal>();
		suMammals = new ArrayList<Animal>();
		suMammals = new ArrayList<MulticellularEukaryotic>();
		//suMammals = new ArrayList<Feline>();
		//suMammals = new ArrayList<Cat>();
		suMammals.add(new Mammal());
		//suMammals.add(new Animal());
		//suMammals.add(new MulticellularEukaryotic());
		suMammals.add(new Feline());
		suMammals.add(new Cat());

		List<?> unknown = null;
		unknown = new ArrayList<MulticellularEukaryotic>();
		unknown = new ArrayList<Animal>();
		unknown = new ArrayList<Mammal>();
		//unknown.add(new Object());
		
		System.out.println("end program");
	}
}
