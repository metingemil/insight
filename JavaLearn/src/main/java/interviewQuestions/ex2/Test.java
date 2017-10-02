package interviewQuestions.ex2;

class A1 {
	void method(int i) {
		System.out.println("A1 int");
	}
}

class B1 extends A1 {
	void method(Integer i) {
		System.out.println("B1 Integer");
	}
}

public class Test extends A1 {
	public static void main(String[] args) {		
		A1 a = new B1();
		a.method(new Integer(10));
		a.method(10);

		B1 b = new B1();
		b.method(new Integer(10));
		b.method(10);
	}
}