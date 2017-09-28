package java.interviewQuestions.ex3;

class Animal {
	public static void method() {
		System.out.println("Animal");
	}
}

class Cat extends Animal {
	public static void method() {
		System.out.println("Cat");
	}
}

public class Test {
	public static void main(String[] args) {
		Animal animal = new Animal();
		Animal cat = new Cat();

		animal.method();
		cat.method();
	}
}
