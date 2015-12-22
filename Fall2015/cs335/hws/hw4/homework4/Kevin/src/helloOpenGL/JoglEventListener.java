package helloOpenGL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.awt.image.DataBufferByte; 
import java.nio.ByteOrder;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.awt.TextRenderer;





public class JoglEventListener implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	
	// Sphere Sizes
	float sunRadius = 1.0f;
	float earthRadius = 0.333f;
	float moonRadius = 0.111f;
	
	// Orbit Sizes
	float earthOrbitRadius = 5.0f;
	float moonOrbitRadius = 2.333f;
	
	// Orbit tilt angle ( about Z,  in degrees )
	float earthTiltAngle = 12.0f;
	float moonTiltAngle = -12.0f;
	
	// Current orbit angle ( About Y, in degrees)
	float earthOrbitAngle = 0;
	float moonOrbitAngle = 0;
	
	// Sun Coords
	final float sunX = 0;
	final float sunY = 0;
	final float sunZ = 0;
	
	// Earth Coords
	float earthX = 0;
	float earthY = 0;
	float earthZ = 0;
	
	// Moon Coords
	float moonX = 0;
	float moonY = 0;
	float moonZ = 0;
	
	// Camera Coords
	float camX = 3;
	float camY = 0;
	float camZ = 0;
	
	// Camera Lookat
	final float camLookX = 0;
	final float camLookY = 0;
	final float camLookZ = 0;
	
	// Camera Radius
	float camRadius = 3;
	
	// Camera Mouse Difference
	float camDiffX = 0;
	float camDiffY = 0;
	float camDiffXOld = 0;
	float camDiffYOld = 0;
	float camZoom = 4;
	float camZoomOld = 4;
	
	// Animation
	boolean play = true;
	float earthAnimSpeed = 0.05f;
	float moonAnimSpeed = 0.5f;
	boolean switchDir = false;
	
	// Mouse button
	int mButton = 0;
	
	// Mouse
	float mouseStartX = 0;
	float mouseStartY = 0;
	
	// Other Vars
	int windowWidth, windowHeight;
	float orthoX=40;
	float tVal_x, tVal_y, rVal_x, rVal_y, rVal;
	double rtMat[] = new double[16];
	int mouseX0, mouseY0;
	int saveRTnow=0, mouseDragButton=0;
	
	
    private GLU glu = new GLU();
    	
	 public void displayChanged(GLAutoDrawable gLDrawable, 
	            boolean modeChanged, boolean deviceChanged) {
	    }
	    /** Called by the drawable immediately after the OpenGL context is
	     * initialized for the first time. Can be used to perform one-time OpenGL
	     * initialization such as setup of lights and display lists.
	     * @param gLDrawable The GLAutoDrawable object.
	     */
	    public void init(GLAutoDrawable gLDrawable) {
	        GL2 gl = gLDrawable.getGL().getGL2();
	        //gl.glShadeModel(GL.GL_LINE_SMOOTH);              // Enable Smooth Shading
	        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
	        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
	        gl.glEnable(GL.GL_DEPTH_TEST);              // Enables Depth Testing
	        gl.glDepthFunc(GL.GL_LEQUAL);               // The Type Of Depth Testing To Do
	        // Really Nice Perspective Calculations
	        //gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);  
	        
	        gl.glMatrixMode(GL2.GL_MODELVIEW);
	        gl.glLoadIdentity();
	        gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX,  rtMat, 0);
	        
	        
	    }
	    
	    public float getXCoord( float angle, float radius )
	    {
	    	
	    	return radius * (float)Math.cos( Math.toRadians( angle ) );
	    }
	    
	    public float getYCoord( float angle, float radius )
	    {
	    	return 0;
	    }
	    
	    public float getZCoord( float angle, float radius )
	    {
	    	return radius * (float)Math.sin( Math.toRadians( angle ) );
	    }
	    
	    public void drawSphere(final GL2 gl, float color[]){
	    	
            int step = 10;
            int latN = 180/step +1;
            int longN = 360/step;
            Vertex[][] xyz = new Vertex[latN][longN];
            int i = 0;
            int j = 0;
            for (double lat = -90; lat < 90 + step; lat += step, i++){
                double y = Math.sin(lat*Math.PI/180.0);
                j=0;
                for (float longitude = -180; longitude < 180; longitude += step,++j){
                    double r = Math.cos(lat*Math.PI/180.0);
                    double x = Math.cos(longitude*Math.PI/180.0)*r;
                    double z = Math.sin(longitude*Math.PI/180.0)*r;
                    
                    xyz[i][j] = new Vertex(x,y,z);
                }
            }
            
            gl.glPolygonMode(GL.GL_FRONT, GL2.GL_FILL);
            gl.glPolygonMode(GL.GL_BACK, GL2.GL_LINE);
            
            gl.glBegin(GL2.GL_TRIANGLES);
            gl.glColor4f(color[0], color[1], color[2], 1);
            for (i = 0;i<latN-1;++i){
                for(j = 0;j<longN;++j){
                    
                    Vertex p0 = xyz[i][j];
                    Vertex p1 = xyz[i+1][j];
                    Vertex p2 = xyz[i+1][(j+1)%longN];
                    Vertex p3 = xyz[i][(j+1)%longN];
                    
                    gl.glNormal3d(p0.x(), p0.y(), p0.z()); gl.glVertex3d(p0.x(), p0.y(), p0.z());//p0
                    gl.glNormal3d(p1.x(), p1.y(), p1.z()); gl.glVertex3d(p1.x(), p1.y(), p1.z());//p1
                    gl.glNormal3d(p2.x(), p2.y(), p2.z()); gl.glVertex3d(p2.x(), p2.y(), p2.z());//p2
                    
                    gl.glNormal3d(p0.x(), p0.y(), p0.z()); gl.glVertex3d(p0.x(), p0.y(), p0.z());//p0
                    gl.glNormal3d(p2.x(), p2.y(), p2.z()); gl.glVertex3d(p2.x(), p2.y(), p2.z());//p2
                    gl.glNormal3d(p3.x(), p3.y(), p3.z()); gl.glVertex3d(p3.x(), p3.y(), p3.z());//p3
                    
                    

                }
            }
            
            gl.glEnd();
        }
	    
	    
	    
	    public class Vertex{
	        private double[] data= new double[3];
	        public Vertex(){};
	        
	        public Vertex(double x, double y, double z){
	            data[0] = x;
	            data[1] = y;
	            data[2] = z;
	        }
	        
	        public double x(){
	            return data[0];
	        }
	        public double y(){
	            return data[1];
	        }
	        public double z(){
	            return data[2];
	        }
	    }
	    
	    public void setUpLights(GL2 gl){            
	        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	        
	        float SHINE_ALL_DIRECTIONS = 1;         
	        float lightPos []= {0,0,0,SHINE_ALL_DIRECTIONS};        
	        float materialSpecular []= {0f, 0f, 1, 1};
	        float lightColorAmbient []= {0.5f, 0.5f, 0.4f, 1f};
	        float lightColorSpecular []= {0.8f,0.8f,0.8f,1f}; 
	        float DiffuseLight[]  = {0.8f, 0.8f, 0.8f};
	        
	        gl.glEnable(GL2.GL_LIGHTING);
	        gl.glEnable(GL2.GL_LIGHT0);
	        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR,materialSpecular,0);
	        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT,materialSpecular,0);        
	                
	        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos,0);

	        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR,lightColorSpecular ,0);
	        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT,lightColorAmbient,0 );    
	        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, DiffuseLight,0); 

	        gl.glEnable(GL2.GL_COLOR_MATERIAL);

	        gl.glMatrixMode(GL2.GL_MODELVIEW);
	        gl.glLoadIdentity();        
	        //gl.glEnable(GL2.GL_DEPTH_TEST);
	        
	        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	        gl.glShadeModel(GL2.GL_SMOOTH); 
    
	      
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
	        glu.gluPerspective(45.0f, h, 1.0, 200.0);
	    }
		@Override
		public void display(GLAutoDrawable gLDrawable) {
			// TODO Auto-generated method stub
			final GL2 gl = gLDrawable.getGL().getGL2();
			gl.glClearColor(0, 0, 0, 1);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			
			// Animation
			if( play )
			{
				earthOrbitAngle += earthAnimSpeed;
				moonOrbitAngle -= moonAnimSpeed;
			}
			if( earthAnimSpeed < 0 )
				switchDir = true;
			if( earthAnimSpeed >= 0 )
				switchDir = false;
			// End Animation
			
			
			// Camera Zoom
			gl.glPushMatrix();
			glu.gluLookAt( 0, 0, camZoom, 0, 0, -1, 0, 1, 0 );
			
			// Camera Rotate
			gl.glPushMatrix();
			gl.glRotatef( camDiffX, 0, 1, 0 );
			gl.glRotatef( camDiffY, 1, 0, 0);
			
			// Coordinate Lines
			gl.glPushMatrix();
			gl.glBegin( GL2.GL_LINES );
			gl.glColor3f(1, 0, 0);
			gl.glVertex3f( 0, 0, 0 );
			gl.glVertex3f( 1.5f, 0, 0 );
			gl.glColor3f(0, 1, 0);
			gl.glVertex3f( 0, 0, 0 );
			gl.glVertex3f( 0, 1.5f, 0 );
			gl.glColor3f( 0, 0, 1 );
			gl.glVertex3f( 0, 0, 0 );
			gl.glVertex3f( 0, 0, 1.5f );
			gl.glEnd();
			gl.glPopMatrix();
			
			
			float mat_emission[] = {20.3f, 20.2f, 20.2f, 0.0f};
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_EMISSION, mat_emission, 1);
            
			
			// Sun
			float sunColor[] = { 1, 0.8f, 0.01f };
			gl.glPushMatrix();
			drawSphere(gl, sunColor);
			/*gl.glPopMatrix();*/
			// End Sun
			setUpLights(gl);
			gl.glPopMatrix();
			mat_emission[0] = 0.0f;
            mat_emission[1] = 0.0f;
            mat_emission[2] = 0.0f;
            mat_emission[3] = 0.0f;
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, mat_emission, 1);

			
			
			
			// Earth
			float earthColor[] = { 0.2f, 0.5f, 0.5f };
			gl.glPushMatrix();
			gl.glRotatef( earthTiltAngle, 0, 0, 1);
			gl.glTranslatef( getXCoord( earthOrbitAngle, earthOrbitRadius ), 0, getZCoord( earthOrbitAngle, earthOrbitRadius ) );
			gl.glScaled(0.333, 0.333, 0.333);
			drawSphere(gl, earthColor);
			
			// Moon
			float moonColor[] = { 0.5f, 0.5f, 0.45f };
			gl.glPushMatrix();
			gl.glRotatef( moonTiltAngle * 2,  0,  0,  1);
			gl.glTranslatef( getXCoord( moonOrbitAngle, moonOrbitRadius  ), 0 , getZCoord( moonOrbitAngle, moonOrbitRadius ) );
			gl.glScaled(0.333, 0.333, 0.333);
			drawSphere(gl, moonColor);
			gl.glPopMatrix();
			// End Moon
			
			gl.glPopMatrix();
			// End Earth
			
			gl.glPopMatrix();
			// End Camera Zoom
			gl.glPopMatrix();
			// End Camera Rotate
		}
		@Override
		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		    char key= e.getKeyChar();
			System.out.printf("Key typed: %c\n", key);
			
			// If user hits t, play/stop animation.
			if( key == 't' || key == KeyEvent.VK_T)
			{
				play = !play;
			}
			if( key == 's' || key == KeyEvent.VK_S)
			{
				earthAnimSpeed -= 0.05;
				moonAnimSpeed -= 0.5;
			}
			if( key == 'f' || key == KeyEvent.VK_F)
			{
				earthAnimSpeed += 0.05;
				moonAnimSpeed += 0.5;
			}
			if( key == KeyEvent.VK_ESCAPE)
			{
				System.exit(0);
			}
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
			
			float XX = (e.getX()-windowWidth*0.5f)*orthoX/windowWidth;
			float YY = -(e.getY()-windowHeight*0.5f)*orthoX/windowWidth;
			if( mButton == 1) // Right Button for rotation
			{
				camDiffX = ( XX - mouseStartX ) * 4 + camDiffXOld;
				camDiffY = ( mouseStartY - YY ) * 4 + camDiffYOld;
			}
			if( mButton == -1 )
			{
				camZoom = ( mouseStartX - XX ) / 5 + camZoomOld;
				if( camZoom < 0.1f )
					camZoom = 0.1f;
				if( camZoom > 50 )
					camZoom = 50;
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

			float XX = (e.getX()-windowWidth*0.5f)*orthoX/windowWidth;
			float YY = -(e.getY()-windowHeight*0.5f)*orthoX/windowWidth;

			
			mouseStartX = XX;
			mouseStartY = YY;
		
			mouseX0 = e.getX();
			mouseY0 = e.getY();
			if(e.getButton()==MouseEvent.BUTTON1) // Left button
			{
				mButton = -1;
			}
			else if(e.getButton()==MouseEvent.BUTTON3) // Right button
			{
				mButton = 1;
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			mButton = 0;
			camDiffXOld = camDiffX;
			camDiffYOld = camDiffY;
			camZoomOld = camZoom;
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