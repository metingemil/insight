package javaInsight.CompleteReferenceNinthEdition.ch28;

//An example of CyclicBarrier.
import java.util.concurrent.*;

class BarDemo {
	public static void main(String args[]) {
		CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
		System.out.println("Starting");
		new MyThread2(cb, "A");
		new MyThread2(cb, "B");
		new MyThread2(cb, "C");
		new MyThread2(cb, "X");
		new MyThread2(cb, "Y");
		new MyThread2(cb, "Z");
		
	}
}

// A thread of execution that uses a CyclicBarrier.
class MyThread2 implements Runnable {
	CyclicBarrier cbar;
	String name;

	MyThread2(CyclicBarrier c, String n) {
		cbar = c;
		name = n;
		new Thread(this).start();
	}

	public void run() {
		System.out.println(name);
		try {
			cbar.await();
		} catch (BrokenBarrierException exc) {
			System.out.println(exc);
		} catch (InterruptedException exc) {
			System.out.println(exc);
		}
	}
}

// An object of this class is called when the
// CyclicBarrier ends.
class BarAction implements Runnable {
	public void run() {
		System.out.println("Barrier Reached!");
	}
}