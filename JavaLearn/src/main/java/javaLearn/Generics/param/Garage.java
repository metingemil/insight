package javaLearn.Generics.param;

public interface Garage<V extends Vehicle> {

	void park(V vehicle);
	
	void method1(Number num);
	
	void method1(Integer num);
	
	V hold(Cycle c);
}
