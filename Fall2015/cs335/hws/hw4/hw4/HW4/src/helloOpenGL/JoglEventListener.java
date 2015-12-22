 package helloOpenGL;


import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;


import java.nio.ByteOrder;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.awt.TextRenderer;





public class JoglEventListener implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {

  float[] vertices = {5.97994f, -0.085086f, -0.010798f,
        5.97994f, 10.0043f, -0.010798f,
        7.99077f, 10.0043f, -0.010798f,
        7.99077f, 11.3449f, -0.010798f,
        -0.405339f, 11.3449f, -0.010798f,
        -0.405339f, 9.98083f, -0.010798f,
        1.65252f, 9.98083f, -0.010798f,
        1.65252f, 0.549879f, -0.010798f,
        -0.722839f, 0.549879f, -0.010798f,
        -0.722839f, -1.69612f, -0.010798f,
        2.6168f, -1.69612f, -0.010798f,
        -7.24925f, 0.42055f, -0.010798f,
        -9.35415f, 0.42055f, -0.010798f,
        -9.35415f, 10.0043f, -0.010798f,
        -7.37859f, 10.0043f, -0.010798f,
        -7.37859f, 11.3802f, -0.010798f,
        -15.8217f, 11.3802f, -0.010798f,
        -15.8217f, 9.99258f, -0.010798f,
        -13.8109f, 9.99258f, -0.010798f,
        -13.8109f, -0.061591f, -0.010798f,
        -10.2361f, -1.73139f, -0.010798f,
        -7.26099f, -1.73139f, -0.010798f,
        -6.1909f, 0.855631f, -0.010798f,
        -8.11942f, 0.855631f, -0.010798f,
        -8.11942f, 2.31379f, -0.010798f,
        0.217914f, 2.31379f, -0.010798f,
        0.217914f, 0.926204f, -0.010798f,
        -1.73415f, 0.926204f, -0.010798f,
        -1.73415f, -4.10675f, -0.010798f,
        9.23724f, 0.937952f, -0.010798f,
        7.26169f, 0.937952f, -0.010798f,
        7.26169f, 2.38434f, -0.010798f,
        15.6696f, 2.38434f, -0.010798f,
        15.6696f, 1.00851f, -0.010798f,
        14.964f, 1.00851f, -0.010798f,
        7.75558f, -2.44873f, -0.010798f,
        14.4231f, -9.36318f, -0.010798f,
        16.0576f, -9.36318f, -0.010798f,
        16.0576f, -10.6685f, -0.010798f,
        7.62625f, -10.6685f, -0.010798f,
        7.62625f, -9.33965f, -0.010798f,
        9.67236f, -9.33965f, -0.010798f,
        4.49827f, -3.90687f, -0.010798f,
        -1.35784f, -6.59973f, -0.010798f,
        -1.35784f, -9.3279f, -0.010798f,
        0.217914f, -9.3279f, -0.010798f,
        0.217914f, -10.6919f, -0.010798f,
        -8.22526f, -10.6919f, -0.010798f,
        -8.22526f, -9.32786f, -0.010798f,
        -6.20266f, -9.32786f, -0.010798f};
  int[] indices = {3, 2, 3, 1,
        3, 1, 3, 6,
        3, 1, 6, 10,
        3, 10, 6, 7,
        3, 10, 7, 8,
        3, 4, 5, 6,
        3, 4, 6, 3,
        3, 10, 8, 9,
        3, 1, 10, 0,
        3, 13, 14, 15,
        3, 13, 15, 18,
        3, 13, 18, 20,
        3, 13, 20, 12,
        3, 16, 17, 18,
        3, 16, 18, 15,
        3, 12, 20, 21,
        3, 12, 21, 11,
        3, 20, 18, 19,
        3, 49, 22, 44,
        3, 44, 22, 28,
        3, 44, 28, 43,
        3, 43, 28, 29,
        3, 43, 29, 42,
        3, 42, 29, 35,
        3, 42, 35, 41,
        3, 41, 35, 36,
        3, 41, 36, 38,
        3, 38, 36, 37,
        3, 39, 40, 41,
        3, 39, 41, 38,
        3, 29, 30, 32,
        3, 29, 32, 34,
        3, 29, 34, 35,
        3, 46, 47, 49,
        3, 46, 49, 44,
        3, 46, 44, 45,
        3, 22, 23, 25,
        3, 22, 25, 27,
        3, 22, 27, 28,
        3, 25, 23, 24,
        3, 27, 25, 26,
        3, 49, 47, 48,
        3, 32, 30, 31,
        3, 34, 32, 33};
  float backrgb[] = new float[4];
  float rot;
  float rotX;
  int Button;
  boolean Paused = false;
  float modify = 1.0f;

  /*
   * Custom variables for mouse drag operations
   */
  int windowWidth, windowHeight;
  float orthoX = 40;
  float tVal_x, tVal_y, rVal_x, rVal_y, rVal;
  double rtMat[] = new double[16];
  int mouseX0, mouseY0;
  int saveRTnow = 0, mouseDragButton = 0;
  GLUquadric earth;
  int texID[] = new int[3];

  float[] lightPos = {0, 0, 0, 1};        // light position

  private GLU glu = new GLU();
  float DaysPerYear = 5f;
  float year = 0.0f;
  float day = 0.0f;
  float moon = 0.0f;
  float moonSelf = 0.0f;
  float earthRadius = 5f;
  float moonRadius = 5f;
  float daySpeed = 0.0f*modify;
  float yearSpeed = DaysPerYear/360.0f*modify ;
  float moonAES = 0.75f*modify;
  float moonSpeed = 1.0f*modify;
  float zoom = -4f;

  public void displayChanged(GLAutoDrawable gLDrawable,
                       boolean modeChanged, boolean deviceChanged) {
  }

  /**
   * Called by the drawable immediately after the OpenGL context is
   * initialized for the first time. Can be used to perform one-time OpenGL
   * initialization such as setup of lights and display lists.
   *
   * @param gLDrawable The GLAutoDrawable object.
   */
  public void init(GLAutoDrawable gLDrawable) {
     GL2 gl = gLDrawable.getGL().getGL2();
     //gl.glShadeModel(GL.GL_LINE_SMOOTH);              // Enable Smooth Shading
     gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
     //gl.glClearDepth(1.0f);                      // Depth Buffer Setup
     gl.glEnable(GL.GL_DEPTH_TEST);              // Enables Depth Testing
     //gl.glDepthFunc(GL.GL_LEQUAL);               // The Type Of Depth Testing To Do
     // Really Nice Perspective Calculations
     //gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

     earth = glu.gluNewQuadric();

     gl.glMatrixMode(GL2.GL_MODELVIEW);
     gl.glLoadIdentity();
     gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, rtMat, 0);


     float[] noAmbient = {1.0f, 1.0f, 1.0f, 1f};     // low ambient light
     float[] diffuse = {1.0f, 1.0f, 1.0f, 1f};        // full diffuse color

     gl.glEnable(GL2.GL_LIGHTING);
     gl.glEnable(GL2.GL_LIGHT0);
     gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, noAmbient, 1);
     gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 1);
     gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos, 1);
     gl.glEnable(GL2.GL_RESCALE_NORMAL);

  }


  public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
                 int height) {
     windowWidth = width;
     windowHeight = height;
     final GL2 gl = gLDrawable.getGL().getGL2();

     if (height <= 0) // avoid a divide by zero error!
        height = 1;
     final float h = (float) width / (float) height;
     gl.glViewport(0, 0, width, height);
     gl.glMatrixMode(GL2.GL_PROJECTION);
     gl.glLoadIdentity();
     //gl.glOrtho(-orthoX*0.5, orthoX*0.5, -orthoX*0.5*height/width, orthoX*0.5*height/width, -100, 100);
     glu.gluPerspective(45.0f, h, 1.0, 200.0);

     gl.glMatrixMode(GL2.GL_MODELVIEW);
     gl.glLoadIdentity();
     glu.gluLookAt(0, 0, 6, 0, 0, 0, 0, 1, 0);
  }


  public void drawSphere(GL2 gl) {

     int step = 10;
     int latN = 180 / step + 1;
     int longtiN = 360 / step;
     Vector3[][] xyz = new Vector3[latN][longtiN];
     int i = 0, j = 0;

     for (double lat = -90; lat < 90 + step; lat += step, i++) {
        double y = Math.sin(lat * Math.PI / 180);
        j = 0;
        for (float longti = -180; longti < 180; longti += step, j++) {
           double r = Math.cos(lat * Math.PI / 180);
           double x = Math.cos(longti * Math.PI / 180) * r;
           double z = Math.sin(longti * Math.PI / 180) * r;

           xyz[i][j] = new Vector3(x, y, z);
        }
     }
     gl.glPolygonMode(GL2.GL_FRONT, GL2.GL_FILL);
     gl.glPolygonMode(GL2.GL_BACK, GL2.GL_LINE);

     gl.glBegin(GL2.GL_TRIANGLES);

     for (i = 0; i < latN - 1; i++) {
        for (j = 0; j < longtiN; j++) {
           Vector3 p0 = xyz[i][j];
           Vector3 p1 = xyz[i + 1][j];
           Vector3 p2 = xyz[i + 1][(j + 1) % longtiN];
           Vector3 p3 = xyz[i][(j + 1) % longtiN];

           gl.glVertex3d(p0.getX(), p0.getY(), p0.getZ());
           gl.glVertex3d(p1.getX(), p1.getY(), p1.getZ());
           gl.glVertex3d(p2.getX(), p2.getY(), p2.getZ());

           gl.glVertex3d(p0.getX(), p0.getY(), p0.getZ());
           gl.glVertex3d(p2.getX(), p2.getY(), p2.getZ());
           gl.glVertex3d(p3.getX(), p3.getY(), p3.getZ());

        }
     }
     gl.glEnd();
  }

  public void bindTexture(GL2 gl, String fileName) {
     try {
        BufferedImage aImage = ImageIO.read(new File(fileName));
        //URL url = new URL("http:\\")
        ByteBuffer buf = convertImageData(aImage);

        gl.glGenTextures(3, texID, 0);
        gl.glBindTexture(GL.GL_TEXTURE_2D, texID[0]);

        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

        gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, aImage.getWidth(),
              aImage.getHeight(), 0, GL2.GL_BGR, GL.GL_UNSIGNED_BYTE, buf);


        gl.glEnable(GL.GL_TEXTURE_2D);

        gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
        gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);

     } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
     }

  }

  @Override
  public void display(GLAutoDrawable gLDrawable) {
     final GL2 gl = gLDrawable.getGL().getGL2();

     //gl.glClearColor(backrgb[0], 0, 1, 1);
     gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
     gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
     gl.glPushMatrix();

     gl.glRotatef(rotX, 0.0f, 1.0f, 0.0f);
     gl.glRotatef(rot, 1.0f, 0.0f, 0.0f);
     glu.gluLookAt(0.0, 0.0, zoom,
           2.0, 0.0, 1.0,
           0.0, -3.0, 0.0);

     gl.glBegin(GL2.GL_LINES);

     float d1[] = {1.0f, 0.0f, 0.0f, 1.0f};
     float d2[] = {0.0f, 0.0f, 1.0f, 1.0f};
     float d3[] = {0.0f, 1.0f, 0.0f, 1.0f};
     gl.glEnable(GL2.GL_COLOR_MATERIAL);
     gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, d1, 0);
     gl.glVertex3f(-20, 0, 0);
     gl.glVertex3f(20, 0, 0);


     gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, d2, 0);
     gl.glVertex3f(0, -20, 0);
     gl.glVertex3f(0, 20, 0);



     gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, d3, 0);
     gl.glVertex3f(0, 0, -20);
     gl.glVertex3f(0, 0, 20);


     gl.glEnd();

     day += daySpeed;
     year += yearSpeed;
     moonSelf += moonSpeed;
     moon += moonAES;


     float sunColor[] = {1.0f, 1.0f, 0.0f, 1.0f};
     gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, sunColor, 0);
     drawSphere(gl);

     gl.glRotatef( 12, 0.0f, 0.0f, 1.0f);
     gl.glRotatef(year, 0.0f, 1.0f, 0.f);
     gl.glTranslatef(earthRadius, 0.0f, 0.0f);

     gl.glPushMatrix();

     float  materialSpecular[] = {0,0,1,1};
     float shininess = 20;
     gl.glRotatef(day, 0.25f, 1.0f, 0.0f);
     gl.glScaled(0.3f, 0.3f, 0.3f);

     float earthColor[] = {0.0f, 0.0f, 1.0f, 1.0f};
     gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, earthColor, 0);
     gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materialSpecular, 0);
     gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
     drawSphere(gl);

     gl.glPopMatrix();
     gl.glRotatef(24, 0.0f, 0.0f, 1.0f);
     gl.glRotatef(moon, 0.0f, 1.0f, 0.0f);
     gl.glScaled(0.1f, 0.1f, 0.1f);
     gl.glTranslatef(moonRadius, 0.0f, 0.0f);
     gl.glRotatef(moonSelf, 0.0f, 1.0f, 0.0f);

     float moonColor[] = {1.0f, 1.0f, 1.0f, 1.0f};
     gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, moonColor, 0);
     drawSphere(gl);
     gl.glPopMatrix();

  }

  private ByteBuffer convertImageData(BufferedImage bufferedImage) {
     ByteBuffer imageBuffer;
     WritableRaster raster;
     BufferedImage texImage;


          ColorModel glAlphaColorModel = new ComponentColorModel(ColorSpace
                  .getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8, 8 },
                  true, false, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE);

          raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,
                  bufferedImage.getWidth(), bufferedImage.getHeight(), 4, null);
          texImage = new BufferedImage(glAlphaColorModel, raster, true,
                  new Hashtable());

          // copy the source image into the produced image
          Graphics g = texImage.getGraphics();
          g.setColor(new Color(0f, 0f, 0f, 0f));
          g.fillRect(0, 0, 256, 256);
          g.drawImage(bufferedImage, 0, 0, null);

     // build a byte buffer from the temporary image
     // that be used by OpenGL to produce a texture.

     DataBuffer buf = bufferedImage.getRaster().getDataBuffer();


     final byte[] data = ((DataBufferByte) buf).getData();

     //byte[] data = ((DataBufferByte) texImage.getRaster().getDataBuffer()).getData();

     //System.out.printf("%d %d, %d\n", bufferedImage.getWidth(), bufferedImage.getHeight(), data.length);
     //imageBuffer = ByteBuffer.allocateDirect(data.length);
     //imageBuffer.order(ByteOrder.nativeOrder());
     //imageBuffer.put(data, 0, data.length);
     //imageBuffer.flip();

     //return imageBuffer;

     return (ByteBuffer.wrap(data));
  }

  @Override
  public void dispose(GLAutoDrawable arg0) {
     // TODO Auto-generated method stub

  }

  @Override
  public void keyTyped(KeyEvent e) {
     // TODO Auto-generated method stub
  }

  @Override
  public void keyPressed(KeyEvent e) {
     // TODO Auto-generated method stub
     char key = e.getKeyChar();
     switch(key){
        case 't': Paused = true ^ Paused;
           break;
        case KeyEvent.VK_ESCAPE: System.exit(0);
           break;
     }

     if(Paused)
     {modify = 0.0f;}
     else{modify = 1.0f;}
     daySpeed = 0.0f*modify;
     yearSpeed = DaysPerYear/360.0f*modify ;
     moonAES = 0.75f*modify;
     moonSpeed = 1.0f*modify;
  }

  @Override
  public void keyReleased(KeyEvent e) {
     // TODO Auto-generated method stub

  }

  @Override
  public void mouseDragged(MouseEvent e) {

     if(Button == 1) {
        float XX = (e.getX() - windowWidth * 0.5f) * orthoX / windowWidth;
        float YY = -(e.getY() - windowHeight * 0.5f) * orthoX / windowWidth;

        rotX += (e.getX() - mouseX0) * 0.5f;
        rot += (e.getY() - mouseY0) * 0.5f;
     }else if(Button == 2)
     {
        float XX = (e.getX() - windowWidth * 0.5f) * orthoX / windowWidth;
        zoom += (e.getX()-mouseX0)*0.5f;
     }
     mouseX0 = e.getX();
     mouseY0 = e.getY();
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
     mouseX0 = e.getX();
     mouseY0 = e.getY();
     int ButtonPressed = e.getButton();
     switch(ButtonPressed){
        case MouseEvent.BUTTON1: Button = 1;
           break;
        case MouseEvent.BUTTON3: Button = 2;
           break;
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
 public void init(GLDrawable gLDrawable) {
     final GL gl = glDrawable.getGL();
     final GLU glu = glDrawable.getGLU();

     gl.glMatrixMode(GL.GL_PROJECTION);
     gl.glLoadIdentity();
     glu.gluOrtho2D(-1.0f, 1.0f, -1.0f, 1.0f); // drawing square
     gl.glMatrixMode(GL.GL_MODELVIEW);
     gl.glLoadIdentity();
 }*/
  private class Vector3 {
     private double data[];
     public Vector3() {}
     public Vector3(double x, double y, double z) {
        data = new double[3];
        data[0] = x;
        data[1] = y;
        data[2] = z;
     }
     public double getX() {
        return data[0];
     }
     public void setX(double x) {
        data[0] = x;
     }
     public double getY() {
        return data[1];
     }
     public void setY(double y) {
        data[1] = y;
     }
     public double getZ() {
        return data[2];
     }
     public void setZ(double z) {
        data[2] = z;
     }
  }
}








