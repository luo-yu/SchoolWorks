/*
 * Name: Yu Luo
 * Instructor: Mr. Norman J. Krumpe
 * CSE 271, TA
 * Date: June 19, 2013
 * File: ShapeDisplayer.java
 * Description: Display random shapes with random colors, with
 *              the shapes nicely arranged in rows and columns
 */
package shape;

import java.awt.Color;

import javax.swing.*;

/**
 * A class that represents a Diamond shape with a bouding box and
 * color
 * 
 * @author Yu Luo
 * 
 */
public class Diamond extends Shape {

	/**
	 * Constructs a Diamond with the given bounding box and color
	 * 
	 * @param x
	 *            x-coordinate of the bounding box's upper-left corner
	 * @param y
	 *            y-coordinate of the bounding box's upper-left corner
	 * @param width
	 *            width of the bounding box
	 * @param height
	 *            height of the bounding box
	 * @param color
	 *            color of the shape
	 */
	public Diamond(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	/**
	 * Creates a Diamond that is a copy of this Diamond, copying its
	 * color and all the details of its bounding box.
	 * 
	 * @param t
	 *            the Diamond that is to be copied
	 */
	public Diamond(Diamond d) {
		super(d);
	}

	/**
	 * Gets an array of points representing the vertices of this
	 * shape.
	 * 
	 * @return an array of points representing the vertices of this
	 *         shape.
	 */
	@Override
	public Point[] getVertices() {
		Point[] result = new Point[4];

		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();

		result[0] = new Point(x + w / 2, y);
		result[1] = new Point(x, y + h / 2);
		result[2] = new Point(x + w / 2, y + h);
		result[3] = new Point(x + w, y + h / 2);

		return result;
	}

	/**
	 * Returns true if the parameter is the same type of shape, and
	 * everything else matches from the parent class check
	 * 
	 * @see Shape#equals(java.lang.Object)
	 * @param an
	 *            Object
	 * @return true if the object is a Diamond, false otherwise
	 */
	public boolean equals(Object that) {
		if (that instanceof Diamond) {
			return super.equals(that);
		}
		return false;
	}

	/**
	 * Returns a String representation of this shape, by putting the
	 * shape type in front of the inherited bounding box and color
	 * information
	 * 
	 * @see Shape#toString()
	 * @return a string representation of this shape
	 */
	public String toString() {
		return "Diamond " + super.toString();
	}

}
