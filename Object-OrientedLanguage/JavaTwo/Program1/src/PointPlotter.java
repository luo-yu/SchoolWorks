// Name: Yu Luo
// Instructor: Mr. Krumpe
// CSE 271, TA
// Date: May 21, 2013
// Filename: PointPlotter.java
// Description: Plotting points and display points 
//              (Review on working with objects, ArrayList, 
//               methods, and files)

/**
 * A program that displays a set of points on the screen.
 * Begin with an array of Point objects.  Send that array
 * to a PointDisplay object.  The PointDisplay object will
 * handle all the necessary drawing for you.
 * 
 * @author Norm Krumpe
 * @version 1.0
 */
import java.awt.Point;
import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class PointPlotter {

	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		ArrayList<Point> myPoints = null;
		myPoints = new ArrayList<Point>();
		writeUserPointsToFile();
		myPoints = readPointsFromFile();
		showStats(myPoints);
		System.out.println(myPoints.get(0).toString());

		// Feel free to adjust this variable to make
		// changes to the graphics output.
		int pointDiameter = 10;

		// ******************* DON'T CHANGE THIS:
		// Don't change the rest of this method
		// For now, how JFrames work is unimportant. Our
		// focus is on using a PointDisplay object,
		// which requires an array of Point objects in order
		// to work.
		JFrame frame = new JFrame("Show Some Points");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Notice that the next line sends the myPoints variable
		// to the PointDisplay object. Don't change this...just
		// understand that your job is to create and modify an
		// array of Points named myPoints, and then the code below
		// takes care of drawing it.
		PointDisplay display = new PointDisplay(myPoints,
				pointDiameter);
		frame.add(display);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	} // end main method

	// Your methods belong below. Each should be public and static.

	// Your methods belong below. Each should be public and static.

	// Collect information from user and write to a file
	// information include number of points, coordinates of
	// points, and the name of the file.
	public static void writeUserPointsToFile() throws Exception {
		System.out.print("How many points will you enter?");
		int numPoints = keyboard.nextInt();
		ArrayList<Point> myPoints = new ArrayList<Point>();

		// when then number of points the user entered is
		// bigger than 0, ask user for coordinates for
		// each point and put the data into an ArrayList
		// of Point.
		while (numPoints > 0) {
			System.out.print("x y: ");
			int x = keyboard.nextInt();
			int y = keyboard.nextInt();
			myPoints.add(new Point(x, y));
			numPoints--;
		}// end while

		// ask user for name of the file, and write data in
		// the ArrayList to the file
		System.out.print("Enter a filename for storing the points: ");
		File outputFile = new File(keyboard.next());
		PrintWriter output = new PrintWriter(outputFile);

		for (int i = 0; i < myPoints.size(); i++) {
			output.println(myPoints.get(i).x + " "
					+ myPoints.get(i).y);
		}// end for loop

		output.close();

	}// end method writeUserPointsToFile

	// Read data from a file and arrange the data in the file
	// into an ArrayList of Point
	public static ArrayList<Point> readPointsFromFile()
			throws Exception {
		System.out.print("Enter name of file you want to read: ");
		Scanner input = new Scanner(new File(keyboard.next()));
		ArrayList<Point> a = new ArrayList<Point>();
		while (input.hasNext()) {
			a.add(new Point(input.nextInt(), input.nextInt()));
		}// end while loop
		input.close();
		return a;

	}// end method readPointsFromFile

	// Show statistics for the points in an ArrayList of
	// Point.
	public static void showStats(ArrayList<Point> a) {

		// if the ArrayList is empty
		if (a.isEmpty())
			System.out.println("No points found!");

		// if the ArrayList if not empty
		else {

			// find the minimum and maximum x values
			int minx = a.get(0).x;
			int maxx = a.get(0).x;
			for (int i = 0; i < a.size(); i++) {
				minx = Math.min(minx, a.get(i).x);
				maxx = Math.max(maxx, a.get(i).x);
			}

			// find the minimum and maximum y values
			int miny = a.get(0).y;
			int maxy = a.get(0).y;
			for (int i = 0; i < a.size(); i++) {
				miny = Math.min(miny, a.get(i).y);
				maxy = Math.max(maxy, a.get(i).y);
			}

			// print out number of points, x-value ranges
			// and y-value ranges
			System.out.println("There are " + a.size() + " points");
			System.out.println("x values range from " + minx + " to "
					+ maxx);
			System.out.println("y values range from " + miny + " to "
					+ maxy);

			// find the total segment length of all the points
			// call Point2D's method public static double
			// distance(double x1,
			// double y1,double x2,double y2) to accomplish the
			// calculation of total segment length
			double length = 0.0;
			if (a.size() > 1) {
				for (int i = 0; i < a.size() - 1; i++) {
					length = length
							+ Point.distance(a.get(i).x, a.get(i).y,
									a.get(i + 1).x, a.get(i + 1).y);
				}// end for loop
			}// end if clause

			// format the decimal of length so that is will always
			// display to the 2nd place after the decimal
			DecimalFormat df = new DecimalFormat("0.00");
			System.out.println("Total segment length = "
					+ df.format(length));
		}// end else

	}// end showStats method

}// end class
