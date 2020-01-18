import java.util.Arrays;

/*
 * Avem un rand de sticle de vin cu preturi diferite in orice ordine. Putem
 * vinde sticle de vin ori din fata(stanga) ori din spate(dreapta) randului de
 * sticle. Dupa fiecare sticla vanduta, pretul tuturor sticlelor ramase creste
 * cu 10%.
 * 
 * In ce ordine trebuiesc vandute sticlele astfel ca la sfarsit sa avem profitul
 * maxim posibil.
 */
public class WineBottles {

	WineBottles(int[] bottlePrices) {
		mBottlePrices = bottlePrices;
	}

	private int[] mBottlePrices;

	int[] mOrder;

	double[][] mComputedVals;

	double[] maxForIteration;

	public static void main(String[] args) {
		// int[] x = new int[] {70,70,250,1,74};
		// int[] x = new int[] { 100, 100, 100, 1000, 1000, 1 };
		int[] x = new int[] { 1, 2, 3, 4, 5 };
		(new WineBottles(x)).computeBestPrice();
	}

	public void computeBestPrice() {
		int n = mBottlePrices.length;
		mOrder = new int[n];
		maxForIteration = new double[n];
		mComputedVals = new double[n][n];

		double optimumSum = bestMatch(1, n);

		System.out.println("Optimum profit is " + optimumSum + " for order " + Arrays.toString(mOrder));
	}

	private double bestMatch(int i, int j) {
		if (mComputedVals[i - 1][j - 1] != 0) {
			return mComputedVals[i - 1][j - 1];
		}

		int n = mBottlePrices.length;

		int iteration = i - 1 + n - j;

		double max = 0;

		if (i == j) {
			max = power(1.1, iteration) * mBottlePrices[i - 1];

			if (maxForIteration[iteration] < max) {
				maxForIteration[iteration] = max;
				mOrder[iteration] = i - 1;
			}
		} else {
			double leftPick = power(1.1, iteration) * mBottlePrices[i - 1] + bestMatch(i + 1, j);
			double rightPick = power(1.1, iteration) * mBottlePrices[j - 1] + bestMatch(i, j - 1);

			if (leftPick <= rightPick) {
				max = rightPick;

				if (maxForIteration[iteration] < max) {
					maxForIteration[iteration] = max;
					mOrder[iteration] = j - 1;
				}
			} else {
				max = leftPick;

				if (maxForIteration[iteration] < max) {
					maxForIteration[iteration] = max;
					mOrder[iteration] = i - 1;
				}
			}
		}

		mComputedVals[i - 1][j - 1] = max;
		return max;
	}

	private double power(double val, int exp) {
		double result = 1;
		for (int i = 0; i < exp; i++) {
			result *= val;
		}

		return result;
	}
}
