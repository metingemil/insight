package javaInsight.Generics.param;

public interface Garage<V extends Vehicle> {

	void park(V vehicle);
	
	V hold(Cycle c);
}
