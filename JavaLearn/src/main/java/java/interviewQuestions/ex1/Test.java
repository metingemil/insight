package java.interviewQuestions.ex1;

public class Test {
	public static void main(String[] args) {
		int x = 50;
		String s = "0";

		minInt(x, 20, 10);
		minInt(s, 10, 20);

		System.out.println("s = " + s);
		System.out.println("x = " + x);
	}

	static void minInt(String s, int a, int b) {
		if (a > b)
			s = new Integer(b).toString();
		else
			s = new Integer(a).toString();
	}

	static void minInt(int min, int a, int b) {
		if (a > b)
			min = b;
		else
			min = a;
	}
}