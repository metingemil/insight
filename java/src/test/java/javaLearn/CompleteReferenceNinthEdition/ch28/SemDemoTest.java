package javaLearn.CompleteReferenceNinthEdition.ch28;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SemDemoTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public SemDemoTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(SemDemoTest.class);
	}

	public static void main(String[] args)
    {
        System.out.println("aici");
    }
	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
