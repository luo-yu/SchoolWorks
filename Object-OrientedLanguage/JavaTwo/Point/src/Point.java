/*
 * Name: Yu Luo
 *  Instructor: Mr. Krumpe
 *  CSE 271, TA
 *  Date: May 25, 2013
 *  File Name: Point.java
 *  Description: The definition of a Point class that is similar
 *               to the Point class in java.awt
 */
/**
 * @author Yu Luo
 * 
 */
public class Point {

	/**
	 * The X coordinate of this Point. If no X coordinate is set it
	 * will default to 0.
	 */
	public int x = 0;

	/**
	 * The Y coordinate of this Point. If no X coordinate is set it
	 * will default to 0.
	 */
	public int y = 0;

	/**
	 * Constructs and initializes a point at the origin (0,0) of the
	 * coordinate space
	 */
	public Point() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Constructs and initializes a point at the specified (x,y)
	 * location of the coordinate space
	 * 
	 * @param x
	 *            - the X coordinate of the newly constructed Point
	 * @param y
	 *            - the Y coordinate of the newly constructed Point
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructs and initializes a Point with the same location as
	 * the specified Point object
	 * 
	 * @param p
	 *            a point
	 */
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	/**
	 * Returns the location of this point.
	 * 
	 * @return a copy of this point, at the same location
	 */
	public Point getLocation() {
		Point p = new Point(this.x, this.y);
		return p;
	}

	/**
	 * Returns the X coordinate of this Point in double precision.
	 * 
	 * @return the X coordinate of this Point.
	 */
	public double getX() {
		return (double) this.x;
	}

	/**
	 * Returns the Y coordinate of this Point in double precision.
	 * 
	 * @return the Y coordinate of this Point.
	 */
	public double getY() {
		return (double) this.y;
	}

	/**
	 * Determines whether or not two points are equal.Two instances of
	 * Point are equal if the values of their x and y member fields,
	 * representing their position of the coordinate space, are the
	 * same
	 * 
	 * @param obj
	 *            - an object to be compare with this Point
	 * @return true if the object to be compared is an instance Point
	 *         and has the same values; false otherwise.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Point))
			return false;
		else
			return (this.x == ((Point) obj).x && this.y == ((Point) obj).y);

	}

	/**
	 * Moves this point to the specified location in the (x, y)
	 * coordinate plane. This method is identical with
	 * setLocation(int, int).
	 * 
	 * @param x
	 *            - the X coordinate of the new location
	 * @param y
	 *            - the Y coordinate of the new location
	 */
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets the location of this point to the specified double
	 * coordinates. The double values will be rounded to integer
	 * values. Any number smaller than Integer.MIN_VALUE will be reset
	 * to MIN_VALUE, and any number larger than Integer.MAX_VALUE will
	 * be reset to MAX_VALUE.
	 * 
	 * @param x
	 *            - the X coordinate of the new location
	 * @param y
	 *            - the Y coordinate of the new location
	 */
	public void setLocation(double x, double y) {
		move((int) x, (int) y);
	}

	/**
	 * Changes to point to have the specified location. This method's
	 * behavior is identical with move(int, int).
	 * 
	 * @param x
	 *            - the X coordinate of the new location
	 * @param y
	 *            - the Y coordinate of the new location
	 */
	public void setLocation(int x, int y) {
		move(x, y);
	}

	/**
	 * Sets the location of the point to the specified location.
	 * 
	 * @param p
	 *            - a point, the new location for this point
	 */
	public void setLocation(Point p) {
		move(p.x, p.y);
	}

	/**
	 * Returns a string representation of this point and its location
	 * in the (x,y) coordinate space. The returned string may be empty
	 * but may not be null.
	 * 
	 * @return a string representation of this point
	 */
	public String toString() {
		String result = "";
		result = result + this.getClass().getName() + "[x=" + this.x
				+ ", y=" + this.y + "]";

		return result;
	}

	/**
	 * Translates this point, at location (x, y), by dx along the x
	 * axis and dy along the y axis so that it now represents the
	 * point (x+dx, y+dy).
	 * 
	 * @param dx
	 *            - the distance to move this point along the X axis
	 * @param dy
	 *            - the distance to move this point along the Y axis
	 */
	public void translate(int dx, int dy) {
		move(this.x + dx, this.y + dy);
	}

}
