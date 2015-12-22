package labGL1;


import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;

public class HelloGL implements GLEventListener {

    private double theta = 0;
    private double s = 0;
    private double c = 0;

    public static void main(String[] args) {
    	//The system's default OpenGL profile can be selected as well, 
    	//which represents the profile which is "Best for the running platform"
        GLProfile glp = GLProfile.getDefault();
        
        /*
         * This object describes some specific capabilities a rendering context should 
         * support. The default settings are usually fine, but you can play with some of the 
         * capabilities for achieving certain effects such as full-scene anti-aliasing, stereo rendering
         * and sof forth. This object takes an instance of GLProfile as a parameter
         */
        GLCapabilities caps = new GLCapabilities(glp);
        
        
        
        /*
         * To work with AWT, JOGL provides a class GLCanvas which extends the AWT Component class,
         *  so it can be added to an AWT/Swing GUI. There are a few steps involved in creating a window 
         *  that is prepared for OpenGL rendering:
         *  Choose GLProfile Configure GLCapabilities Create GLCanvas 
         *  (implementation of GLAutoDrawable), which automatically creates our GLContext
         *  Add GLCanvas to Frame (the AWT Frame is our window)
         */
        GLCanvas canvas = new GLCanvas(caps);

        Frame frame = new Frame("AWT Window Test");
        frame.setSize(300, 300);
        frame.add(canvas);
        frame.setVisible(true);

        
     // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        canvas.addGLEventListener(new HelloGL());

        
        //Animator animator = new Animator(canvas); 
        //animator.start();
        
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        update();
        render(drawable);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    	 drawable.getGL().setSwapInterval(1);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

    private void update() {
        theta += 0.05;
    	//theta =Math.PI/4;
        s = Math.sin(theta);
        c = Math.cos(theta);
    }

    private void render(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // draw a triangle filling the window
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor4f(1, 0, 0, 1);
        gl.glVertex3d(-c, -c, 0);
        gl.glColor3f(0, 1, 0);
        gl.glVertex2d(0, c);
        gl.glColor3f(0, 0, 1);
        gl.glVertex2d(s, -s);
        gl.glEnd();
        
        
        gl.glBegin(GL.GL_LINE_LOOP);
        gl.glColor4f(1, 0, 0, 1);
        gl.glVertex3d(1, 0, 0);
        gl.glVertex3d(0, 0, 0);
        
        gl.glColor4f(0, 1, 0, 1);
        gl.glVertex3d(0, 1, 0);
        gl.glVertex3d(0, 0, 0);
        
        gl.glColor4f(0, 0, 1, 1);
        gl.glVertex3d(0, 0, 1);
        gl.glVertex3d(0, 0, 0);
        gl.glEnd();
        
        
    }
}