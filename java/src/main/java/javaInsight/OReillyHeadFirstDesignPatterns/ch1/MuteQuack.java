package javaLearn.OReillyHeadFirstDesignPatterns.ch1;

public class MuteQuack implements QuackBehavior {
	public void quack() {
		System.out.println("<< Silence >>");
	}
}