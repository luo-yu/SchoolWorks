import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;



/*
 * How OpengGL works: Procedural. You start a thread that repeatedly calls a display() method at your fps(frames per second) rate
 * In this method, you clear the screen and rebuild the entire scene. You do this by issuing primitive commands: Begin vertex, this is the 
 * 1st point, the 2nd point, the 3rd, end vertex, and so on.
 * 
 * It gets more complicated for textures, lighting and the like, but the bottom line is that there is one GL object which has 
 * like 1000 glWhatever() methods that you will all over and over again. Yes it is primitive but near to the hardware, and this means fast
 * and flexible. To integrate with Java, there have to be some concessions: Namely, the AWT component (a GLCanvs extending Component) is integrated
 * into the AWT event queue, and you need to implement a listener(GLEventListenr) for life cycle call backs (not so procedural here)
 * 
 */
public class JOGLT extends Frame {


	static Animator anim = null;
	private void setupJOGL(){
	    GLCapabilities caps = new GLCapabilities(null);
	    caps.setDoubleBuffered(true);
	    caps.setHardwareAccelerated(true);
	    
	    GLCanvas canvas = new GLCanvas(caps); 
        add(canvas);

        JoglEventListener jgl = new JoglEventListener();
        canvas.addGLEventListener(jgl); 
        canvas.addKeyListener(jgl); 
        canvas.addMouseListener(jgl);
        canvas.addMouseMotionListener(jgl);

        anim = new Animator(canvas);
        anim.start();

	}
	
    public JOGLT() {
        super("Yu Luo's JOGL");

        setLayout(new BorderLayout());
        
        
        
     
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        
        setSize(600, 600);
        setLocation(40, 40);

        setVisible(true);

        setupJOGL();
    }

    public static void main(String[] args) {
        JOGLT demo = new JOGLT();
        

        demo.setVisible(true);
    }
}


class MyWin extends WindowAdapter {
	 public void windowClosing(WindowEvent e)
   {
       System.exit(0);
   }

}
