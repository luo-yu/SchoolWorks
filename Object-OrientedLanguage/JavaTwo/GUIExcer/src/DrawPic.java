import java.awt.*;

import javax.swing.*;


public class DrawPic extends JPanel {
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.fillOval(50, 50, 100, 100);
		g.drawOval(150, 50, 100, 100);
		

	}
	
	public static void main(String[] args){
		DrawPic dp = new DrawPic();
		JFrame jf = new JFrame();
		jf.setBounds(0, 0, 500, 500);
		jf.add(dp);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		
	}

}
