/**
 * A class designed to take an array of Point objects and display
 * it.  DON'T MODIFY THIS FILE IN ANY WAY.
 * @author Norm Krumpe
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PointDisplay extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Point> points;
	private int diameter;
	private boolean connectTheDots;

	/**
	 * Constructs a PointDisplay with a given array of points and a
	 * diameter to be used for displaying those points. Add this
	 * display to a JFrame object and it will automatically show its
	 * points.
	 * 
	 * @param points An array of Point objects to be displayed
	 * @param diameter The diameter, in pixels, of each point to be
	 *           displayed
	 */
	public PointDisplay(ArrayList<Point> points, int diameter) {
		this.setPreferredSize(new Dimension(300, 300));
		this.points = points;
		this.diameter = diameter;
		this.connectTheDots = true;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		double brightness = 0.0;

		if (points != null)
			for (int i = 0; i < points.size(); i++) {
				if (points.get(i) != null) {

					g.setColor(new Color((int) brightness, 0,
							255 - (int) brightness));
					brightness += 256.0 / points.size();

					// Draw the point
					g.fillOval(points.get(i).x - diameter / 2,
							points.get(i).y - diameter / 2, diameter,
							diameter);

					// Connect the dots, if desired:
					if (connectTheDots) {
						if (i > 0 && points.get(i - 1) != null) {
							g.drawLine(points.get(i - 1).x,
									points.get(i - 1).y, points.get(i).x,
									points.get(i).y);
						}
					}

				}

			}
	}

}
