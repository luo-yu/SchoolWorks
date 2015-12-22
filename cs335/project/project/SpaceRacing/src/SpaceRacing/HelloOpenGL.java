package SpaceRacing;






import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.awt.BorderLayout;
import java.awt.Frame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas; 
import com.jogamp.opengl.util.Animator;

import objload.com.momchil_atanasov.data.front.parser.OBJFace;
import objload.com.momchil_atanasov.data.front.parser.OBJMesh;
import objload.com.momchil_atanasov.data.front.parser.OBJModel;
import objload.com.momchil_atanasov.data.front.parser.OBJObject;
import objload.com.momchil_atanasov.data.front.parser.OBJParser;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;





public class HelloOpenGL extends Frame {

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
	
    public HelloOpenGL() {
        super("Basic JOGL Demo");

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

    
    public static void main(String[] args)  throws  IOException{
    	// Open a stream to your OBJ resource
    	
    	
    	/*
    	//final InputStream in = new FileInputStream("C:\\Users\\Lankford\\Desktop\\cube2.obj");
    	final InputStream in = new FileInputStream( "C:\\Users\\Lankford\\Desktop\\335proj\\project\\skybox_textures\\SpaceShip.obj");
    	// Create an OBJParser and parse the resource
    	final OBJParser parser = new OBJParser();
    	
    	final OBJModel model = parser.parse(in);
    	// Use the model representation to get some basic info
    	System.out.println(MessageFormat.format(
    	        "OBJ model has {0} vertices, {1} normals, {2} texture coordinates, and {3} objects.",
    	        model.getVertices().size(),
    	        model.getNormals().size(),
    	        model.getTexCoords().size(),
    	        model.getObjects().size()));
    	System.out.print((model.getVertices().get(0).x));
    	System.out.print((model.getVertices().get(0).y));
    	System.out.print((model.getVertices().get(0).z));
    	for (OBJObject object : model.getObjects()) {
    	    for (OBJMesh mesh : object.getMeshes()) {
    	        for (OBJFace face : mesh.getFaces()) {
    	        	
    	        	System.out.println("\n");
    	        	System.out.println(face.getReferences().size());
    	        	System.out.println("\n");
    	            System.out.println(face.getReferences().get(0).normalIndex);
    	            System.out.println(face.getReferences().get(1).normalIndex);
    	            System.out.println(face.getReferences().get(2).normalIndex);
    	            System.out.println(face.getReferences().get(3).normalIndex);
    	            System.out.println("\n\n\n\n");
    	            
    	        	int i=0;
    	        	System.out.println("\n");
    	        	//System.out.println(mesh.getFaces().size());
    	        	System.out.println("\n");
    	        	//System.out.println(face.getReferences().size());
    	        	
    	            for ( i= 0; i < face.getReferences().size(); i++){
    	            	System.out.println("\n");
    	            	System.out.println(face.getReferences().get(i).vertexIndex);
    	            	System.out.println("\n");
    	            	System.out.print(model.getVertices().get(face.getReferences().get(i).vertexIndex).x);
    	            	
    	            	System.out.print(model.getVertices().get(face.getReferences().get(i).normalIndex).x);
    	            	System.out.println("\n");
   	        	   		System.out.print(model.getVertices().get(face.getReferences().get(i).normalIndex).y);
   	        	   		System.out.println("\n");
 	        	   		System.out.print(model.getVertices().get(face.getReferences().get(i).normalIndex).z);
 	        	   		
 	        	   		
    	            	System.out.println("\n\n\n\n");
    	            	}
    	            }  	            
    	        }
    	    }
    	}
    	*/
        HelloOpenGL demo = new HelloOpenGL();
        demo.setVisible(true);
    }
    
}


class MyWin extends WindowAdapter {
	 public void windowClosing(WindowEvent e)
   {
       System.exit(0);
   }
}