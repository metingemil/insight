package javaLearn.Generics.param;

public class Main {
	public static void main(String[] args) {
		Garage<Car> garage = getCarGarage();
		garage.park(getCar());
		garage.hold(new Bicycle());
	}

	public static Car getCar() {
		return new Car();
	}

	public static Vehicle getPlane() {
		return new Plane();
	}
	
	public static Garage<Car> getCarGarage() {
		return new CarGarage();
	}
}
