package interviewQuestions.ex4;

import java.io.IOException;

/*public*/ class A {
	public void start() throws IOException, RuntimeException {
		throw new RuntimeException("Not able to Start A");
	}
}

class B extends A {
	public void start() throws RuntimeException {
		throw new RuntimeException("Not able to Start B");
	}
}

public class Test {
	public static void main(String args[]) {
		Test test = new Test();
		test.tryStart();
	}

	private int tryStart() {
		try {
			A a = new A();
			a.start();
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