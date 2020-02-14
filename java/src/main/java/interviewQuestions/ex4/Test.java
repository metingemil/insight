package interviewQuestions.ex4;

import java.io.IOException;

abstract class A {
	public void test() {
	    System.out.println();
	}
}

//enum B extends A {
//	VAL1,
//    VAL2;
//}

public class Test {
	public static void main(String args[]) {
		Test test = new Test();
		test.tryStart();
	}

	private int tryStart() {
		try {
		//	A a = new A();
	//		a.start();
		} catch (RuntimeException re) {
			System.out.println(re.toString());
			return 1;
		} catch (Exception ex) {
			return 0;
		} finally {
			System.out.println("Doing some cleaning");
		}
		return 3;
	}
}