/*
 * Name: Yu Luo
 * Instructor: Mr. Norman J. Krumpe
 * CSE 271, TA
 * Date: June 05, 2013
 * File: DemoFrame.java
 * Description: Create an DemoFrame that
 *              extends JFrame. The DemoFrame 
 *              displays letters on top panel
 *              and images on the bottom panel
 */

/**
 * Implements a DemoFrame with all the definitions from
 * JFrame class, and utilize JButton, JLabel, and JPanel
 * to display text and images
 * @author Yu Luo
 */
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DemoFrame extends JFrame {

	/**
	 * Constructs a DemoFrame that displays each letter of a String in
	 * a row of button on the top, and displays five images on the
	 * bottom.
	 * 
	 * @param text
	 *            the specified string text
	 */
	public DemoFrame(String text) {
		super(text);
		this.setBounds(0, 0, 600, 400);
		this.setLayout(new GridLayout(2, 1));
		JPanel topPanel = new JPanel();
		JPanel buttomPanel = new JPanel();
		this.add(topPanel);
		this.add(buttomPanel);

		/*
		 * loop through each letter of the text and add each of them
		 * to a JButton
		 */
		for (int i = 0; i < text.length(); i++) {
			topPanel.add(new JButton("" + text.charAt(i)));
		}

		/*
		 * set five images to an icon, add the icon to the JLabel, and
		 * add JLabel to the JPanel, and finally, add the JPanel to
		 * the JFrame
		 */
		for (int i = 1; i <= 5; i++) {
			JLabel jl = new JLabel();
			ImageIcon image = new ImageIcon("images/" + i + ".gif");
			jl.setIcon(image);
			buttomPanel.add(jl);
			this.add(buttomPanel);
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		DemoFrame df = new DemoFrame("Alaska");
	}

}
