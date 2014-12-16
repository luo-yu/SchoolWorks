/**
 * 
 * @author Yu luo This is a simple demonstration of 
 * find the root of the
 *         function f(x) = x^3-x^2-x-1 
 *         with initial value x= 1.5
 */

public class NewtonsMethod {

	public static void main(String[] args) {

		int nMax = 5;

		// intial value
		double x = 1.5;

		for (int i = 1; i <= 5; i++) {
			if (x >= 1 && x <= 3) {
				x = x - f(x) / fprime(x);
				System.out.println("Step: " + i + " x = " 
				+ x + " Value = "
						+ f(x));
			}
		}
	}

	// the function
	static double f(double x) {
		return x * x * x - x * x - x - 1;
	}

	// the derivative
	static double fprime(double x) {
		return 3 * x * x - 2 * x - 1;
	}

}
