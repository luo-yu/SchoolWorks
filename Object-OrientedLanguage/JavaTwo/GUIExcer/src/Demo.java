import javax.swing.*;
import java.awt.*;


public class Demo extends JPanel{


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval(0, 0, getWidth()/2, getHeight()/2);
		g.setColor(Color.GREEN);
		g.fillOval(getWidth()/2, 0, getWidth()/2, getHeight()/2);
		g.setColor(Color.BLUE);
		g.fillOval(0, getHeight()/2, getWidth()/2, getHeight()/2);
		
		
		g.setColor(Color.BLACK);
		g.fillOval(getWidth()/2, getHeight()/2, getWidth()/2, getHeight()/2);
		
		g.setColor(new Color(0, 255, 0, 10));
		g.fillRect(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
	}
	
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.add(new Demo());
		jf.setBounds(0,0,500,500);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setVisible(true);

	}

}
