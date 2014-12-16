/**
 * 
 * @author Yu Luo This simple program iteratively solve a linear systems using
 *         three different method
 * 
 *         Jacobi, Gauss-Sedel, and SOR
 *
 */
public class SolveLinearSytem {

	public static int ITERATION_LIMIT = 16;
	public static double DELTA = Math.pow(10, -10);
	public static double EPISLON = 0.5 * Math.pow(10, -4);
	public static double W = 1.1;

	public static void main(String[] args) {

		double[] element = { 7, 1, -1, 2, 1, 8, 0, -2, -1, 0, 4, -1, 2, -2, -1,
				6 };

		double[][] A = new double[4][4];
		double[] b = new double[] { 3, -5, 4, -3 };

		int k = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				A[i][j] = element[k];
				k++;
			
			}
	
		}

		
		// using Jacobi iteration
		System.out.println("##################################################Jacobi Method#############################################");
		jacobi(A, b);

		System.out.println("##################################################Gauss-Seidel Method#############################################");
		ITERATION_LIMIT=9;
		// Using Gauss_Seidel iteratiaon
		gauss_seidel(A, b);

		ITERATION_LIMIT=7;
		// Using SOR iterations
		System.out.println("##################################################SOR Method#############################################");
		SOR(A, b, W);

	}// end main

	/**
	 * Calculate the error for an array of elements
	 * 
	 * @param estimate
	 *            the estimated solutions
	 * @return an double array of calculated error
	 */
	public static double[] calculateError(double[] estimate) {

		double[] exact = { 1, -1, 1, -1 };
		double[] error = new double[estimate.length];

		for (int i = 0; i < estimate.length; i++) {
			error[i] = Math.abs(estimate[i] - exact[i]);
		}

		return error;
	}

	/**
	 * Calculate the Euclidean norms of the errors
	 * 
	 * @param error
	 *            an array of error values
	 * @return a Euclidean norms of error value
	 */
	public static double calculateNorm(double[] error) {
		double norm = 0.0;

		for (int i = 0; i < error.length; i++) {
			norm = norm + Math.pow(error[i], 2);
		}
		return Math.sqrt(norm);
	}

	/**
	 * Solve the equation using Jacobi method
	 * 
	 * @param A
	 *            double array representing a matrix (coefficients)
	 * @param b
	 *            an array representing a matrix(right hand side)
	 */
	public static void jacobi(double[][] A, double[] b) {

		double sum, diag;

		// contains the old iterate values
		double[] y = new double[4];

		for (int k = 0; k < y.length; k++) {
			y[k] = 0;
		}

		// containt new iterate values
		double[] x = new double[4];

		for (int k = 0; k < x.length; k++) {
			x[k] = 0;
		}

		// Jacobi iteration
		for (int it_count = 1; it_count < ITERATION_LIMIT; it_count++) {

			System.out.print("Iteratin " + it_count + " ");
			updateXtoY(y, x);

			for (int i = 0; i < 4; i++) {
				sum = b[i];
				diag = A[i][i];

				if (Math.abs(diag) < DELTA) {
					System.out.println("Diagonal elment too small");
					break;
				}

				for (int j = 0; j < 4; j++) {
					if (j != i) {
						sum = sum - A[i][j] * y[j];
					}
				}

				x[i] = Math.round((sum / diag) * 10000) / 10000.0;

			}

			double[] error = calculateError(x);
			double norm = Math.round(calculateNorm(error) * 10000) / 10000.0;
			printArray(x);

			System.out.print(" Euclidean norms of Error: " + norm + "\n");

		}

		
	}

	/**
	 * Solve the linear systems using Gauss_Seidel Method
	 * 
	 * @param A
	 *            Double array representing coefficient matrix
	 * @param b
	 *            An array representing matrix on the right hand side
	 */
	public static void gauss_seidel(double[][] A, double[] b) {

		double sum, diag;

		// contains the old iterate values
		double[] y = new double[4];

		for (int k = 0; k < y.length; k++) {
			y[k] = 0;
		}

		// containt new iterate values
		double[] x = new double[4];

		for (int k = 0; k < x.length; k++) {
			x[k] = 0;
		}

		for (int it_count = 1; it_count < ITERATION_LIMIT; it_count++) {

			System.out.print("Iteration: " + it_count + " ");
			updateXtoY(y, x);
			for (int i = 0; i < 4; i++) {
				sum = b[i];
				diag = A[i][i];

				if (Math.abs(diag) < DELTA) {
					System.out.println("Diagonal elment too small");
					break;
				}

				for (int j = 0; j <= i - 1; j++) {
					sum = sum - A[i][j] * x[j];
				}

				for (int j = i + 1; j < 4; j++) {
					sum = sum - A[i][j] * x[j];
				}

				x[i] = Math.round((sum / diag) * 10000) / 10000.0;

			}


			double[] error = calculateError(x);
			double norm = Math.round((calculateNorm(error)) * 10000) / 10000.0;
			printArray(x);

			System.out.print(" Euclidean norms of Error: " + norm + "\n");
		

		}

	

	}

	/**
	 * Solve linear systems using SOR method
	 * 
	 * @param A
	 *            double array representing coefficients matrix A
	 * @param b
	 *            an array representing right hand side matrix b
	 * @param w
	 *            omega value
	 */
	public static void SOR(double[][] A, double[] b, double w) {

		double sum, diag;

		// contains the old iterate values
		double[] y = new double[4];

		for (int k = 0; k < y.length; k++) {
			y[k] = 0;
		}

		// containt new iterate values
		double[] x = new double[4];

		for (int k = 0; k < x.length; k++) {
			x[k] = 0;
		}

		for (int it_count = 1; it_count < ITERATION_LIMIT; it_count++) {
			System.out.print("Iteration " + it_count + " ");

			updateXtoY(y, x);
			for (int i = 0; i < 4; i++) {
				sum = b[i];
				diag = A[i][i];

				if (Math.abs(diag) < DELTA) {
					System.out.println("Diagonal elment too small");
					break;
				}

				for (int j = 0; j <= i - 1; j++) {
					sum = sum - A[i][j] * x[j];
				}

				for (int j = i+1; j < 4; j++) {
					sum = sum - A[i][j] * x[j];
				}

				x[i] = sum / diag;
				x[i] = Math.round((w * x[i] + (1 - w) * y[i])*10000)/10000.0;
				

			}


			double[] error = calculateError(x);
			double norm = Math.round((calculateNorm(error))*10000)/10000.0;
			printArray(x);

			System.out.print(" Euclidean norms of Error: " + norm + "\n");
		
		}

		

	}

	/**
	 * Update the new values from x to y
	 * 
	 * @param y
	 *            array contains old values
	 * @param x
	 *            array contains new values
	 */
	public static void updateXtoY(double[] y, double[] x) {
		for (int i = 0; i < y.length; i++) {
			y[i] = x[i];
		}
	}

	/**
	 * Print a array
	 * 
	 * @param x
	 *            an array of solutions
	 */
	public static void printArray(double[] x) {

		for (int i = 0; i < x.length; i++) {
			double value = x[i];
			double rounded = Math.round(value * 10000) / 10000.0;
			System.out.print("x" + (i + 1) + " = " + rounded + " ");

		}
	}

}// end class