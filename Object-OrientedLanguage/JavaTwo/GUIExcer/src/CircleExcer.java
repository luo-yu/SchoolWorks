import java.awt.*;

import javax.swing.*;


public class CircleExcer extends JButton {

	public CircleExcer(String text){
		super(text);
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g.drawOval(0, 0, 100, 200);
		
	}

	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setBounds(0, 0, 500, 500);
		j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
		
		j.add(new CircleExcer("Click Me"));
		j.setVisible(true);
	}

}
