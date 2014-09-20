import java.awt.*;

import javax.swing.*;


public class Circle extends JPanel {
	
	
	

	public static void main(String[] args) {
		Circle c = new Circle();
		c.setLayout(new GridLayout(2, 0));
		JFrame jf = new JFrame();
		c.add(new JButton("1"));
		c.add(new JButton("1"));
		c.add(new JButton("1"));
		c.add(new JButton("1"));
		c.add(new JButton("1"));
		c.add(new JButton("1"));
		c.add(new JButton("1"));
		c.add(new JButton("1"));
		jf.setBounds(0, 0, 500, 500);
		jf.add(c);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setVisible(true);

	}

}
