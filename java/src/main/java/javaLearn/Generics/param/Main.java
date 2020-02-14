package javaLearn.Generics.param;

public class Main {
	public static void main(String[] args) {
		Garage<?> garage = getCarGarage();
		//garage.park(getCar());
		garage.hold(new Bicycle());
	}

	public static Vehicle getCar() {
		return new Car();
	}

	public static Vehicle getPlane() {
		return new Plane();
	}
	
	public static Garage<?> getCarGarage() {
		return new CarGarage();
	}
}
