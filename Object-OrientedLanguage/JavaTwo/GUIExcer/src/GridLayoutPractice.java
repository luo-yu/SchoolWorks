import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridLayoutPractice extends JFrame {

	public GridLayoutPractice(){
	JFrame frame = new JFrame();
	frame.setBounds(0, 0, 300, 300);
	JPanel left = new JPanel();
	left.setLayout(new GridLayout(0, 1));
	left.add(new JButton("Button1"));
	left.add(new JButton("Button2"));
	
	JButton btnQuit = new JButton("Quit");
	left.add(btnQuit);
	
	buttonClickListener bl = new buttonClickListener();
	
	btnQuit.addActionListener(bl);
	
	JPanel right = new JPanel();
	right.setLayout(new BorderLayout());
	JButton clear = new JButton("Clear");
	right.add(clear);
	
	frame.setLayout(new GridLayout(1, 2));
	
	frame.add(left);
	frame.add(right);
	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	frame.setVisible(true);
	}
	public static void main(String[] ags){
/*		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.add(new JButton("Big Button"), BorderLayout.CENTER);
	
		JPanel button = new JPanel();
		button.add(new JButton("OK"));
		button.add(new JButton("Cancel"));
		
		frame.add(top, BorderLayout.CENTER);
		frame.add(button, BorderLayout.SOUTH);
	*/
		
	GridLayoutPractice gl = new GridLayoutPractice();
	
		
	}
	
	public class buttonClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {
			
			if(ae.getActionCommand().equals("Quit"));{
				System.exit(0);
				
			}
					
			
			
		}
		
	}
}
