package javaLearn.Generics.param;

public class CarGarage implements Garage<Car> {

	@Override
	public void park(Car vehicle) {
		// TODO Auto-generated method stub
		System.out.println("CarGarage - park");
	}

	@Override
	public Car hold(Cycle c) {
		// TODO Auto-generated method stub
		return new Car();
	}
}
