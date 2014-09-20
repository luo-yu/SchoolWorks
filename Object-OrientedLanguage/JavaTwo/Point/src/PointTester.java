/*
 *  Name: Yu Luo
 *  Instructor: Mr. Krumpe
 *  CSE 271, TA
 *  Date: May 25, 2013
 *  File Name: PointTester.java
 *  Description: The main method used to test all
 *               the definitions in the Point class
 */
import java.util.Scanner;

public class PointTester {

	public static void main(String[] args) {

		/*
		 * demonstration of no-parameter constructor and copy
		 * constructor produces points.
		 */
		Point p1 = new Point();
		System.out.println("no-parameter constructor p1: "
				+ p1.toString());
		System.out.println("P1 coordinate: " + p1.x + " " + p1.y);

		Point p2 = new Point(1, 3);
		System.out.println("Point p2: " + p2.toString());
		System.out.println("P2 coordinates: " + p2.x + " " + p2.y);
		Point p3 = new Point(p2);
		System.out.println("P3 copying p2: " + p3.toString());

		System.out.println("P3 coordinates: " + p3.x + " " + p3.y);

		/*
		 * create a point using user supplied data
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter x and y coordinates of a point: ");
		Point p4 = new Point(keyboard.nextInt(), keyboard.nextInt());

		/*
		 * demonstration of toString() method, access Point class's
		 * public data, and the use of getX(), getY() methods. getX()
		 * and getY() methods returns doubles.
		 */
		System.out.println("Display p4: " + p4.toString());
		System.out.println("p4 x = " + p4.x + "; p4 y = " + p4.y);
		System.out.println("p4 x = " + p4.getX() + "; p4 y = "
				+ p4.getY());

		/*
		 * demonstration of getLocation() method
		 */
		System.out.println("p1 location: " + p1.getLocation()
				+ "; \np2 location: " + p2.getLocation()
				+ "; \np3 location: " + p3.getLocation()
				+ "; \np4 location: " + p4.getLocation());

		/*
		 * demonstration of move(int x, int y) method
		 */
		System.out.println("Before move p4: " + p4);
		p4.move(2, 3);
		System.out.println("After move p4 to (2,3): " + p4);

		/*
		 * demonstration of setLocation(double x, double y) method.
		 * the double values will be rounded to integers
		 */
		System.out.println("Before set p4 location: " + p4);
		p4.setLocation(1.0, 4.0);
		System.out.println("After set p4 location to (1.0, 4.0): "
				+ p4);

		/*
		 * demonstration of setLocation(int x, int y) method.
		 */

		System.out.println("Before set p4 location: " + p4);
		p4.setLocation(5, 10);
		System.out.println("After set p4 location to (5, 10): " + p4);

		/*
		 * demonstration of setLocation(Point p) method.
		 */
		System.out.println("Before set p4 location: " + p4);
		p4.setLocation(p1);
		System.out.println("After set p4 location to p1: " + p4);

		/*
		 * demonstration of translate(int dx, int dy) method
		 */

		System.out.println("Before translate p4 location: " + p4);
		p4.translate(2, 2);
		System.out.println("After tranlate p4 location by (2,2): "
				+ p4);

		/*
		 * demonstration of equals(Object obj) method. create a Point
		 * has a value null to compare with p4. create a String object
		 * to compare to p4. create two Points with the same values as
		 * p4. create a Point with different values as p4. compare
		 * each of them with p4.
		 */

		Point p5 = null;
		System.out.println("p4 equals to p5 = " + p4.equals(p5));
		String s = "This is a String object";
		System.out.println("p4 equals to s = " + p4.equals(s));
		Point p6 = new Point(2, 2);
		System.out.println("p4 equals to p6 = " + p4.equals(p6));
		Point p7 = p4;
		System.out.println("p4 equals to p7 = " + p4.equals(p7));
		Point p8 = new Point(5, 7);
		System.out.println("p4 equals to p8 = " + p4.equals(p8));

	}

}
