package helloOpenGL;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.nio.DoubleBuffer;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;

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

	/*
	 * Custom variables for mouse drag operations
	 */
	int windowWidth, windowHeight;
	float orthoX = 40;
	float tVal_x, tVal_y, rVal_x, rVal_y, rVal;
	double rtMat[] = new double[16];
	int mouseX0, mouseY0;
	int saveRTnow = 0, mouseDragButton = 0;

	float scale_f = 1f;
	float old_scale_f = 0f;
	float dx = 0f, dy = 0f;
	float oldDx = 0f, oldDy = 0f;
	float px, py;

	float angle = 0f;
	float oldAngle = 0f;

	boolean scale_flag = false;
	boolean trans_flag = false;
	boolean rotation_flag = false;

	float x1, y1, z1, x2, y2, z2, x3, y3, z3;
	float rotated_x1, rotated_x2, rotated_x3, rotated_y1, rotated_y2, rotated_y3;

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
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); // Black Background
		gl.glClearDepth(1.0f); // Depth Buffer Setup
		gl.glEnable(GL.GL_DEPTH_TEST); // Enables Depth Testing
		gl.glDepthFunc(GL.GL_LEQUAL); // The Type Of Depth Testing To Do
		// Really Nice Perspective Calculations
		// gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

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

		gl.glBegin(GL.GL_TRIANGLES); // Drawing Using Triangles

		for (int i = 0; i < 44; i++) {

			float x1 = vertices[(indices[i * 4 + 1]) * 3];
			float x2 = vertices[(indices[i * 4 + 2]) * 3];
			float x3 = vertices[(indices[i * 4 + 3]) * 3];

			float y1 = vertices[(indices[i * 4 + 1]) * 3 + 1];
			float y2 = vertices[(indices[i * 4 + 2]) * 3 + 1];
			float y3 = vertices[(indices[i * 4 + 3]) * 3 + 1];

			gl.glColor3f(0.7f, 0.7f, 0.7f);
			gl.glVertex3f(dx + scale_f * (x1 * (float) Math.cos(angle) - y1 * (float) Math.sin(angle)),
					dy + scale_f * (x1 * (float) Math.sin(angle) + y1 * (float) Math.cos(angle)),
					vertices[(indices[i * 4 + 1]) * 3 + 2]);

			gl.glVertex3f(dx + scale_f * (x2 * (float) Math.cos(angle) - y2 * (float) Math.sin(angle)),
					dy + scale_f * (x2 * (float) Math.sin(angle) + y2 * (float) Math.cos(angle)),
					vertices[(indices[i * 4 + 2]) * 3 + 2]);

			gl.glVertex3f(dx + scale_f * (x3 * (float) Math.cos(angle) - y3 * (float) Math.sin(angle)),
					dy + scale_f * (x3 * (float) Math.sin(angle) + y3 * (float) Math.cos(angle)),
					vertices[(indices[i * 4 + 3]) * 3 + 2]);

		}
		// for (int i = 0; i < 44; i++) {
		// x1 = vertices[(indices[i * 4 + 1]) * 3];
		// y1 = vertices[(indices[i * 4 + 1]) * 3 + 1];
		// z1 = vertices[(indices[i * 4 + 1]) * 3 + 2];
		//
		// x2 = vertices[(indices[i * 4 + 2]) * 3];
		// y2 = vertices[(indices[i * 4 + 2]) * 3 + 1];
		// z2 = vertices[(indices[i * 4 + 2]) * 3 + 2];
		//
		// x3 = vertices[(indices[i * 4 + 3]) * 3];
		// y3 = vertices[(indices[i * 4 + 3]) * 3 + 1];
		// z3 = vertices[(indices[i * 4 + 3]) * 3 + 2];
		// for (int j = 0; j < 44; j++) {
		// if (scale_flag) {
		//
		// float scaled_x1 = px + scale_f * (x1 - px);
		// float scaled_y1 = py + scale_f * (y1 - py);
		//
		// float scaled_x2 = px + scale_f * (x2 - px);
		// float scaled_y2 = py + scale_f * (y2 - py);
		//
		// float scaled_x3 = px + scale_f * (x3 - px);
		// float scaled_y3 = py + scale_f * (y3 - py);
		//
		// gl.glVertex3f(scaled_x1 + dx, scaled_y1 + dy, z1);
		// gl.glVertex3f(scaled_x2 + dx, scaled_y2 + dy, z2);
		// gl.glVertex3f(scaled_x3 + dx, scaled_y3 + dy, z3);
		//
		// vertices[(indices[i * 4 + 1]) * 3] = scaled_x1;
		// vertices[(indices[i * 4 + 2]) * 3] = scaled_x2;
		// vertices[(indices[i * 4 + 3]) * 3] = scaled_x3;
		// vertices[(indices[i * 4 + 1]) * 3 + 1] = scaled_y1;
		// vertices[(indices[i * 4 + 2]) * 3 + 1] = scaled_y2;
		// vertices[(indices[i * 4 + 3]) * 3 + 1] = scaled_y3;
		//
		// }
		//
		// else if (trans_flag) {
		// float trans_x1 = scale_f * x1 + dx;
		// float trans_x2 = scale_f * x2 + dx;
		// float trans_x3 = scale_f * x3 + dx;
		// float trans_y1 = scale_f * y1 + dy;
		// float trans_y2 = scale_f * y2 + dy;
		// float trans_y3 = scale_f * y3 + dy;
		//
		// gl.glVertex3f(trans_x1, trans_y1, z1);
		// gl.glVertex3f(trans_x2, trans_y2, z2);
		// gl.glVertex3f(trans_x3, trans_y3, z3);
		//
		// vertices[(indices[i * 4 + 1]) * 3]= trans_x1;
		// vertices[(indices[i * 4 + 2]) * 3]= trans_x2;
		// vertices[(indices[i * 4 + 3]) * 3] = trans_x3;
		// vertices[(indices[i * 4 + 1]) * 3 + 1] = trans_y1;
		// vertices[(indices[i * 4 + 2]) * 3 + 1]= trans_y2;
		// vertices[(indices[i * 4 + 3]) * 3 + 1] = trans_y3;
		//
		// // gl.glVertex3f(scale_f * x1 + dx, scale_f * y1 + dy, z1);
		// // gl.glVertex3f(scale_f * x2 + dx, scale_f * y2 + dy, z2);
		// // gl.glVertex3f(scale_f * x3 + dx, scale_f * y3 + dy, z3);
		// }
		//
		// else if (rotation_flag) {
		// float cosTheta = (float) Math.cos(angle);
		// float sinTheta = (float) Math.sin(angle);
		//
		// float rotated_x1 = px + (scale_f * x1 - px) * cosTheta - (scale_f *
		// y1 - py) * sinTheta + dx;

		// float rotated_y1 = px + (scale_f * x1 - px) * sinTheta + (scale_f *
		// y1 - py) * cosTheta + dy;
		//
		// float rotated_x2 = px + (scale_f * x2 - px) * cosTheta - (scale_f *
		// y2 - py) * sinTheta + dx;
		// float rotated_y2 = px + (scale_f * x2 - px) * sinTheta + (scale_f *
		// y2 - py) * cosTheta + dy;
		//
		// float rotated_x3 = px + (scale_f * x3 - px) * cosTheta - (scale_f *
		// y3 - py) * sinTheta + dx;
		// float rotated_y3 = px + (scale_f * x3 - px) * sinTheta + (scale_f *
		// y3 - py) * cosTheta + dy;
		//
		// gl.glVertex3f(rotated_x1, rotated_y1, z1);
		//
		// gl.glVertex3f(rotated_x2, rotated_y2, z2);
		//
		// gl.glVertex3f(rotated_x3, rotated_y3, z3);
		//
		// vertices[(indices[i * 4 + 1]) * 3] = rotated_x1;
		// vertices[(indices[i * 4 + 2]) * 3] = rotated_x2;
		// vertices[(indices[i * 4 + 3]) * 3] = rotated_x3;
		// vertices[(indices[i * 4 + 1]) * 3 + 1] = rotated_y1;
		// vertices[(indices[i * 4 + 2]) * 3 + 1]= rotated_y2;
		// vertices[(indices[i * 4 + 3]) * 3 + 1] = rotated_y3;
		// } else {
		//
		// gl.glVertex3f(x1, y1, z1);
		// gl.glVertex3f(x2, y2, z2);
		// gl.glVertex3f(x3, y3, z3);
		//
		// }
		// }
		//
		// }
		gl.glEnd(); // Finished Drawing The Triangle
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

		if (e.getKeyCode() == KeyEvent.VK_0) {
			scale_f = 1f;
			angle = 0f;
			dx = dy = oldDx = oldDy = 0f;
			scale_flag = false;
			trans_flag = false;
			rotation_flag = false;

		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			scale_flag = true;
			trans_flag = false;
			rotation_flag = false;

		} else if (e.getKeyCode() == KeyEvent.VK_T) {
			trans_flag = true;
			scale_flag = false;
			rotation_flag = false;
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			rotation_flag = true;
			scale_flag = false;
			trans_flag = false;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		float XX = (e.getX() - windowWidth * 0.5f) * orthoX / windowWidth;
		float YY = -(e.getY() - windowHeight * 0.5f) * orthoX / windowWidth;
		for (int i = 0; i < 44; i++) {

			if (scale_flag) {

				scale_f = 0.5f*(float) Math.sqrt((XX - px) * (XX - px) + (YY - py) * (YY - py));

			}

			else if (trans_flag) {

				dx = (XX - px) + oldDx;
				dy = (YY - py) + oldDy;

			} else if (rotation_flag) {

				angle = -(XX - px) + oldAngle;

			}
		}

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
		// TODO Auto-generated method stub
		/*
		 * Coordinates printout
		 */
		float XX = (e.getX() - windowWidth * 0.5f) * orthoX / windowWidth;
		float YY = -(e.getY() - windowHeight * 0.5f) * orthoX / windowWidth;
		System.out.printf("Point clicked: (%.3f, %.3f)\n", XX, YY);

		px = XX;
		py = YY;

		oldDx = dx;
		oldDy = dy;
		oldAngle = angle;
		old_scale_f = scale_f;

		// mouseX0 = e.getX();
		// mouseY0 = e.getY();
		if (e.getButton() == MouseEvent.BUTTON1) { // Left button

		} else if (e.getButton() == MouseEvent.BUTTON3) { // Right button
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * public void init(GLDrawable gLDrawable) { final GL gl =
	 * glDrawable.getGL(); final GLU glu = glDrawable.getGLU();
	 * 
	 * gl.glMatrixMode(GL.GL_PROJECTION); gl.glLoadIdentity();
	 * glu.gluOrtho2D(-1.0f, 1.0f, -1.0f, 1.0f); // drawing square
	 * gl.glMatrixMode(GL.GL_MODELVIEW); gl.glLoadIdentity(); }
	 */

}
