import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.nio.DoubleBuffer;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public class JoglEventListener implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {

	float[] vertices = { 5.97994f, -0.085086f, -0.010798f, 5.97994f, 10.0043f, -0.010798f, 7.99077f, 10.0043f,
			-0.010798f, 7.99077f, 11.3449f, -0.010798f, -0.405339f, 11.3449f, -0.010798f, -0.405339f, 9.98083f,
			-0.010798f, 1.65252f, 9.98083f, -0.010798f, 1.65252f, 0.549879f, -0.010798f, -0.722839f, 0.549879f,
			-0.010798f, -0.722839f, -1.69612f, -0.010798f, 2.6168f, -1.69612f, -0.010798f, -7.24925f, 0.42055f,
			-0.010798f, -9.35415f, 0.42055f, -0.010798f, -9.35415f, 10.0043f, -0.010798f, -7.37859f, 10.0043f,
			-0.010798f, -7.37859f, 11.3802f, -0.010798f, -15.8217f, 11.3802f, -0.010798f, -15.8217f, 9.99258f,
			-0.010798f, -13.8109f, 9.99258f, -0.010798f, -13.8109f, -0.061591f, -0.010798f, -10.2361f, -1.73139f,
			-0.010798f, -7.26099f, -1.73139f, -0.010798f, -6.1909f, 0.855631f, -0.010798f, -8.11942f, 0.855631f,
			-0.010798f, -8.11942f, 2.31379f, -0.010798f, 0.217914f, 2.31379f, -0.010798f, 0.217914f, 0.926204f,
			-0.010798f, -1.73415f, 0.926204f, -0.010798f, -1.73415f, -4.10675f, -0.010798f, 9.23724f, 0.937952f,
			-0.010798f, 7.26169f, 0.937952f, -0.010798f, 7.26169f, 2.38434f, -0.010798f, 15.6696f, 2.38434f, -0.010798f,
			15.6696f, 1.00851f, -0.010798f, 14.964f, 1.00851f, -0.010798f, 7.75558f, -2.44873f, -0.010798f, 14.4231f,
			-9.36318f, -0.010798f, 16.0576f, -9.36318f, -0.010798f, 16.0576f, -10.6685f, -0.010798f, 7.62625f,
			-10.6685f, -0.010798f, 7.62625f, -9.33965f, -0.010798f, 9.67236f, -9.33965f, -0.010798f, 4.49827f,
			-3.90687f, -0.010798f, -1.35784f, -6.59973f, -0.010798f, -1.35784f, -9.3279f, -0.010798f, 0.217914f,
			-9.3279f, -0.010798f, 0.217914f, -10.6919f, -0.010798f, -8.22526f, -10.6919f, -0.010798f, -8.22526f,
			-9.32786f, -0.010798f, -6.20266f, -9.32786f, -0.010798f };
	int[] indices = { 3, 2, 3, 1, 3, 1, 3, 6, 3, 1, 6, 10, 3, 10, 6, 7, 3, 10, 7, 8, 3, 4, 5, 6, 3, 4, 6, 3, 3, 10, 8,
			9, 3, 1, 10, 0, 3, 13, 14, 15, 3, 13, 15, 18, 3, 13, 18, 20, 3, 13, 20, 12, 3, 16, 17, 18, 3, 16, 18, 15, 3,
			12, 20, 21, 3, 12, 21, 11, 3, 20, 18, 19, 3, 49, 22, 44, 3, 44, 22, 28, 3, 44, 28, 43, 3, 43, 28, 29, 3, 43,
			29, 42, 3, 42, 29, 35, 3, 42, 35, 41, 3, 41, 35, 36, 3, 41, 36, 38, 3, 38, 36, 37, 3, 39, 40, 41, 3, 39, 41,
			38, 3, 29, 30, 32, 3, 29, 32, 34, 3, 29, 34, 35, 3, 46, 47, 49, 3, 46, 49, 44, 3, 46, 44, 45, 3, 22, 23, 25,
			3, 22, 25, 27, 3, 22, 27, 28, 3, 25, 23, 24, 3, 27, 25, 26, 3, 49, 47, 48, 3, 32, 30, 31, 3, 34, 32, 33 };
	float backrgb[] = new float[4];
	float rot;
	GLUquadric earth;
	/*
	 * Custom variables for mouse drag operations
	 */
	int windowWidth, windowHeight;
	float orthoX = 40;
	float tVal_x, tVal_y, rVal_x, rVal_y, rVal;
	double rtMat[] = new double[16];
	int mouseX0, mouseY0;
	int saveRTnow = 0, mouseDragButton = 0;

	private GLU glu = new GLU();

	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) {
	}

	/**
	 * Called by the drawable immediately after the OpenGL context is
	 * initialized for the first time. Can be used to perform one-time OpenGL
	 * initialization such as setup of lights and display lists.
	 * 
	 * @param gLDrawable
	 *            The GLAutoDrawable object.
	 */
	public void init(GLAutoDrawable gLDrawable) {
		GL2 gl = gLDrawable.getGL().getGL2();
		// gl.glShadeModel(GL.GL_LINE_SMOOTH); // Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Black Background
		gl.glClearDepth(1.0f); // Depth Buffer Setup
		gl.glEnable(GL.GL_DEPTH_TEST); // Enables Depth Testing
		gl.glDepthFunc(GL.GL_LEQUAL); // The Type Of Depth Testing To Do
		// Really Nice Perspective Calculations
		// gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		//initialize earth
		earth = glu.gluNewQuadric();
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, rtMat, 0);
	}

	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
		windowWidth = width;
		windowHeight = height;
		final GL2 gl = gLDrawable.getGL().getGL2();

		if (height <= 0) // avoid a divide by zero error!
			height = 1;
		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(-orthoX * 0.5, orthoX * 0.5, -orthoX * 0.5 * height / width, orthoX * 0.5 * height / width, -100,
				100);
		// glu.gluPerspective(45.0f, h, 1.0, 200.0);
	}

	@Override
	public void display(GLAutoDrawable gLDrawable) {
		// TODO Auto-generated method stub
		final GL2 gl = gLDrawable.getGL().getGL2();

		gl.glClearColor(backrgb[0], 0, 1, 1);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		// backrgb[0]+=0.0005;
		// if (backrgb[0]> 1) backrgb[0] = 0;
		
		setCamera(gl, glu, 30);
		
	
		
        // Prepare light parameters.
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {-25, 0, 30, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 1f};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 1f};

        // Set light parameters.
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular, 0);

        // Enable lighting in GL.
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHTING);

        // Set material properties.
        float[] rgba = {1f, 0.0f, 0.0f};
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, 0.5f);
		
		//draw sphere
		glu.gluQuadricDrawStyle(earth, GLU.GLU_LINE);
		glu.gluQuadricNormals(earth, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(earth, GLU.GLU_OUTSIDE);
		final float radius = 6.378f;
		final int slices = 16;
		final int stacks = 16;
		glu.gluSphere(earth, radius, slices, stacks);
		glu.gluDeleteQuadric(earth);
	}
	
	private void setCamera(GL2 gl, GLU glu, float distance){
		//change to projection matrix
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		//perspective
		glu.gluPerspective(60, 1.00,1.0,100.0);
		glu.gluLookAt(0, 0, distance,//<-eye 
				0, 0, 0,//<-----center of coordinate system
				0, 1, 0); //<-----up, along the Y coordinate
		
		
		//change back to model view matrix
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char key = e.getKeyChar();
		System.out.printf("Key typed: %c\n", key);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub


	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}