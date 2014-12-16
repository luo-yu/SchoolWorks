/**
 * 
 * @author Yu Luo
 * 
 *         This simple program iteratively solve a linear system using three
 *         different method
 * 
 *         Jacobi, Gauss-Sedel, and SOR
 */

public class SolveLinearSystems {

	public static void main(String[] args) {

		// The array to hold the 4 solutions
		double[] solutions = new double[4];
		solutions[0] = 0.0;
		solutions[1] = 0.0;
		solutions[2] = 0.0;
		solutions[3] = 0.0;

		System.out
				.println("###############################Jacobi Method##############################");
		System.out.println();

		double x1 = 0, x2 = 0, x3 = 0, x4 = 0, err1 = 0, err2 = 0, err3 = 0, err4 = 0, errNorm = 0;

		// Used to track the number of iterations
		int k = 1;
		// Iterations using Jacobi method.
		for (int i = 0; i < 15; i++) {

			// store solutions in array, each time, a new set of value was
			// applied to the solution
			// simutaneously.
			solutions[0] = (3.0 - x2 + x3 - 2.0 * x4) / 7.0;
			solutions[1] = (-5.0 - x1 + 2.0 * x4) / 8.0;
			solutions[2] = (4.0 + x1 + x4) / 4.0;
			solutions[3] = (-3.0 - 2.0 * x1 + 2.0 * x2 + x3) / 6.0;

			// assign solutions to x1, x2, x3, and x4
			x1 = solutions[0];
			x2 = solutions[1];
			x3 = solutions[2];
			x4 = solutions[3];

			// round x1, x2, x3, x4
			x1 = Math.round(x1 * 10000) / 10000.0;
			x2 = Math.round(x2 * 10000) / 10000.0;
			x3 = Math.round(x3 * 10000) / 10000.0;
			x4 = Math.round(x4 * 10000) / 10000.0;

			// calculate error
			err1 = Math.abs(1.0 - x1);
			err2 = Math.abs(-1.0 - x2);
			err3 = Math.abs(1.0 - x3);
			err4 = Math.abs(-1.0 - x4);

			// calculate Euclidean norms of error
			errNorm = Math.sqrt(Math.pow(err1, 2) + Math.pow(err2, 2)
					+ Math.pow(err3, 2) + Math.pow(err4, 2));

			// round norm of error
			errNorm = Math.round(errNorm * 10000) / 10000.0;

			// output this iteration result
			System.out.print("Iteration " + k);
			System.out.println("  X1 = " + x1 + " X2 = " + x2 + " X3 = " + x3
					+ " X4 = " + x4 + " Euclidean norms of Error: " + errNorm);
			k++;

		}// end iterator for Jacobi

		System.out.println();
		System.out
				.println("######################Gauss-Seidel Method######################");
		// Reset array to test next method
		k = 1;
		System.out.println();
		solutions[0] = 0.0;
		solutions[1] = 0.0;
		solutions[2] = 0.0;
		solutions[3] = 0.0;

		// iteration for Gauss-Seidel
		for (int j = 0; j < 8; j++) {
			solutions[0] = (3.0 - solutions[1] + solutions[2] - 2.0 * solutions[3]) / 7.0;
			solutions[1] = (-5.0 - solutions[0] + 2.0 * solutions[3]) / 8.0;
			solutions[2] = (4.0 + solutions[0] + solutions[3]) / 4.0;
			solutions[3] = (-3.0 - 2.0 * solutions[0] + 2.0 * solutions[1] + solutions[2]) / 6.0;

			printResult(solutions, err1, err2, err3, err4, errNorm, k);

			k++;

		}

		System.out.println();
		System.out
				.println("######################SOR Method######################");
		System.out.println();

		// reset array to test next method
		k = 1;
		System.out.println();
		solutions[0] = 0.0;
		solutions[1] = 0.0;
		solutions[2] = 0.0;
		solutions[3] = 0.0;
		double omega = 1.1;
		for (int i = 0; i < 5; i++) {
			solutions[0] = (1 - omega)
					* solutions[0]
					+ ((3.0 - solutions[1] + solutions[2] - 2.0 * solutions[3]) / 7.0)
					* omega;

			solutions[1] = (1 - omega) * solutions[1]
					+ ((-5.0 - solutions[0] + 2.0 * solutions[3]) / 8.0)
					* omega;

			solutions[2] = (1 - omega) * solutions[2]
					+ ((4.0 + solutions[0] + solutions[3]) / 4.0) * omega;

			solutions[3] = (1 - omega)
					* solutions[3]
					+ ((-3.0 - 2.0 * solutions[0] + 2.0 * solutions[1] + solutions[2]) / 6.0)
					* omega;
			printResult(solutions, err1, err2, err3, err4, errNorm, k);
			k++;
		}

	}

	/**
	 * Print solutions, Euclidean norms of error, and iterations
	 * 
	 * @param solutions
	 *            double array include solutions
	 * @param err1
	 *            calculated error for solution 1
	 * @param err2
	 *            calculated error for solution 2
	 * @param err3
	 *            calculate error for solution 3
	 * @param err4
	 *            calculated error for solution 4
	 * @param errNorm
	 *            calculated Euclidean norms of error
	 * @param k
	 *            Total iterations
	 */
	public static void printResult(double[] solutions, double err1,
			double err2, double err3, double err4, double errNorm, int k) {
		solutions[0] = Math.round(solutions[0] * 10000) / 10000.0;
		solutions[1] = Math.round(solutions[1] * 10000) / 10000.0;
		solutions[2] = Math.round(solutions[2] * 10000) / 10000.0;
		solutions[3] = Math.round(solutions[3] * 10000) / 10000.0;

		err1 = Math.abs(1.0 - solutions[0]);
		err2 = Math.abs(-1.0 - solutions[1]);
		err3 = Math.abs(1.0 - solutions[2]);
		err4 = Math.abs(-1.0 - solutions[3]);

		errNorm = Math.sqrt(Math.pow(err1, 2) + Math.pow(err2, 2)
				+ Math.pow(err3, 2) + Math.pow(err4, 2));

		errNorm = Math.round(errNorm * 10000) / 10000.0;

		System.out.print("Iteration " + k);
		System.out.println("  X1 = " + solutions[0] + " X2 = " + solutions[1]
				+ " X3 = " + solutions[2] + " X4 = " + solutions[3]
				+ " Euclidean norms of Error: " + errNorm);

	}

}
