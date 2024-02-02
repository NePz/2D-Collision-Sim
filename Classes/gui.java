
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.EventQueue;
import java.awt.Font;
import java.lang.Iterable;
import java.text.DecimalFormat;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;




// This class contains anything that needs to be drawn using the graphics method 
public class gui extends JPanel implements Runnable {
//static int OBJ_Count = CollisionSimulator.get_OBJ_Count();
	 
 static ArrayList<ball> ball_obj = new ArrayList<>(); //Creates a array list of ball of dynamic size i.e. length set to var OBJ_Count

    static boolean isRunning = true; //Sim is running

    Thread animator;
// variables used / shared by this class
    static double deltaX;
    static double deltaY;
    static double distance;
    static double magnitude;
    static double Ek;
    static double TotalEK;
    private long lastTime;
    private double fps;
    public static boolean ghostParticles;
    static double kinetic_energy;
   
	 static int rand_X_Pos= 100 + (int)(Math.random() * ((800  - 100) + 1)); //Min + (int)(Math.random() * ((Max - Min) + 1)) RNG range
   	 static int rand_Y_Pos= 100 + (int)(Math.random() * ((100 - 100) + 1));
 // End of variables //   
   	 

    public gui() {
       	Random rand_X_Pos=new Random();
       	Random rand_Y_Pos=new Random();
        animator = new Thread(this);
        animator.start();

    }
    
    public void LineGraphics(Graphics g) { // graphics method imported from the java library, used to draw lines
    	Graphics2D g2d = (Graphics2D) g;
    	float[] dashingPattern1 = {10, 10}; 
    	Stroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND,
    	        BasicStroke.JOIN_ROUND, 1.0f, null , 2.0f);
    	g2d.setStroke(stroke);
 
    }
   
    
   
           
           
    @Override
    // this section of code below is responsible for all the moving graphics in the simulation screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image Vel_P_Img = Toolkit.getDefaultToolkit().getImage("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Velocity_Pointer.pg");
        g.drawImage(Vel_P_Img, getMousePositionX() + 50, getMousePositionY() -25,50,50, this);
        
    // Background image location // 
       Image Background = Toolkit.getDefaultToolkit().getImage("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Background\\Plain sky.jpg");
       // draws the background image
       g.drawImage(Background, 0, 0,925,515, this); // inserts the image specified in the file location above (x, y, width, height)
  
    
        for (ball b: ball_obj) { //For each loop to draw the ball obj from array ball[]
        	g.setColor(Color.DARK_GRAY); //color of the ball_obj
            b.draw(g); // draws|renders the actual ball object(s) when instantiated

        g.setColor(Color.GREEN);
        g.fillOval((int) ball_obj.get(0).getX() - 5, (int) ball_obj.get(0).getY() - 5, 10, 10); // marks which ball object is ball [0]
        
    
       if (CollisionSimulator.Show_info == true){ // if display stats is toggled ON...
    		g.setColor(Color.MAGENTA); // Momentum vector arrow representation
    		for (int i = 0 ; i <= ball_obj.size()-1;i++){
    			int x1 = (int) ball_obj.get(i).getX();
    			int y1 = (int) ball_obj.get(i).getY();
    			int x2 = x1 + ((int)ball_obj.get(i).getxVelocity()* (int)ball_obj.get(i).getMass());
    			int y2 = y1 + ((int)ball_obj.get(i).getyVelocity()* (int)ball_obj.get(i).getMass());
    	    	   g.drawLine(x1, y1, x2, y2);
    	           LineGraphics(g);
    	    	   	}
    	   g.setColor(Color.GREEN);
    	   	for (int i = 0 ; i <= ball_obj.size()-1;i++){ // loops through all the ball object in the array list
    	   		// draw the green resultant velocity due to x, y vector components
    	   g.drawLine((int) ball_obj.get(i).getX(), (int) ball_obj.get(i).getY(), (int)ball_obj.get(i).getX()+ (int)ball_obj.get(i).getxVelocity() * 10,(int) ball_obj.get(i).getY()+(int)ball_obj.get(i).getyVelocity()*10);
    	   	
    	   LineGraphics(g);// Visually represents the X,Y vector component's magnitude 
    	   for (int j = 0 ; j <= ball_obj.size()-1;j++){
     		   g.setColor(Color.YELLOW);
     		   // draws the visual representation of the Y vector component(s) of ball object(s)
     		   g.drawLine((int) ball_obj.get(j).getX(), (int) ball_obj.get(j).getY(),  (int)ball_obj.get(j).getX() , (int)ball_obj.get(j).getY()+ (int)ball_obj.get(j).getyVelocity() * 10);
     		   g.setColor(Color.blue);
     		  // draws the visual representation of the X vector component(s) of ball object(s)
     		   g.drawLine((int) ball_obj.get(j).getX(), (int) ball_obj.get(j).getY(),  (int)ball_obj.get(j).getX()+(int)ball_obj.get(j).getxVelocity()*10, (int)ball_obj.get(j).getY() );
     		   LineGraphics(g);}
    	   g.setColor(Color.GREEN);
     	   	}
    	   	
    	   
           g.setColor(Color.white);
       g.drawString("mouse: (" + getRelativeMousePositionX() + ", " + getRelativeMousePositionY() + ")", 20, 25);
       
       g.drawString("∑Ek: " + (int) getTotalEk() + " J", 20, 40); // displays the sum of kinetic energy
       g.setColor(Color.GREEN);
 
       g.setColor(Color.WHITE);
       DecimalFormat df = new DecimalFormat("####0.00"); // makes the program display only up to 2SF
       g.drawString("Time eclapsed: " + df.format(SimClock.runClock()) + " s" , 20, 50);
        	}
        }
       
        g.setFont(new Font("default", Font.BOLD, 15));
       g.setColor(Color.green);
       if(CollisionSimulator.OBJ_Count >0 && CollisionSimulator.Show_ADD_Vector == true){
      //g.drawLine((int) ball_obj.get(0).getX(), (int) ball_obj.get(0).getY(),  (int)getMousePositionX()-100, (int)getMousePositionY() -120);
       }
       g.setColor(Color.red);
       if (CollisionSimulator.Show_info == true){
       for (int i=0; i <= ball_obj.size()-1;i++){
    	   // Labels each ball object by inserting a string above marking OBJ and their position in their array VV
       g.drawString("OBJ ["+i+"]", (int) ball_obj.get(i).getX() - 20, (int) ((int) ball_obj.get(i).getY() - (ball_obj.get(i).getDiameter()/1.5))); //places OBJ name right on top
       g.setColor(Color.BLACK);
       g.setFont(new Font("default", Font.PLAIN, 15));
       g.drawString("Ek: ["+ ball_obj.get(i).get_EK() +"J]", (int) ball_obj.get(i).getX() -35, (int) ((int) ball_obj.get(i).getY() + (ball_obj.get(i).getDiameter()/1.5)));
      // ^^ draws a string text under each ball object when display stats is toggled ON, which has the calculated kinetic energy (claculated using the get_EK() method) value displayed concatenated with "J"
       g.setColor(Color.red);
       g.setFont(new Font("default", Font.BOLD, 15));
       }}
    
    }
    // End of graphics code //
    
public double getTotalEk() {
		 for (int i=0; i <= ball_obj.size()-1;i++){
			TotalEK = 0 + ball_obj.get(i).get_EK();
		}
		return TotalEK;
	}

// Code for mouse pointer
public static int getMousePositionX() {
	int mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
	return mouseX;

	}

public static int getMousePositionY() {
	int mouseY =  (int) (MouseInfo.getPointerInfo().getLocation().getY());
	return mouseY;

	}


public static int getRelativeMousePositionX() { // gets the mouse position inside the window instead of the entire screen i.e. 0,0 at centre of collision simulation window
	int mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX() -1100/2;
	return mouseX;

	}

public static int getRelativeMousePositionY() {
	int mouseY =  (int) (750/2 - MouseInfo.getPointerInfo().getLocation().getY());
	return mouseY;

	}
// end of mouse pointer code

public static void PauseSim() {
	System.out.println("isRunning = "+ isRunning + ", SIM PAUSED");
}

public static void ResumeSim() {
	isRunning = true;
	System.out.println("isRunning = "+ isRunning +" , SIM RUNNING");
	
}

public static double RandX(){//Random generation for the X coordinate of the ball
	 double rand_X_Pos= 100 + (Math.random() * ((857  - 100) + 1)); // Min 100 maximum 927 generated
	 for (int i = 0 ; i <= ball_obj.size()-1-1;i++)
		 if (rand_X_Pos > 553 - ball_obj.get(i).getDiameter()/2 || rand_X_Pos <=0 + ball_obj.get(i).getDiameter()/2){ //Regenerate X position if the spawned object is out of bound upon being spawned
			 System.out.println("OUT OF BOUNDS X, Regenerating new X Co-ordinate...");
			 RandY();
		 } else
			 
	return rand_X_Pos;
	return rand_X_Pos;
}
public static double RandY(){// Random generation for the X coordinate of the ball
	 double rand_Y_Pos= 100 + (Math.random() * ((503  - 100) + 1)); // Min 100 maximum 503 generated
	 for (int i = 0 ; i <= ball_obj.size()-1-1;i++)
	 if (rand_Y_Pos > 553 - ball_obj.get(i).getDiameter()/2 || rand_Y_Pos <=0 + ball_obj.get(i).getDiameter()/2){
		 System.out.println("OUT OF BOUNDS Y, Regenerating new Y Co-ordinate...");
		 RandY();
	 } else
	return rand_Y_Pos;
	return rand_Y_Pos;
}

public static double RandDiameter(){ // Method to generate random diamater sizes for newly instantiated ball objects within the range of 50 - 100
	 double Diameter= 50 + (Math.random() * ((100  - 50) + 1));
	return Diameter; 
}




    public void run()   { //This method is to update the frame, when the program is running
        while (isRunning == true) {
        	
        	        for (ball b : ball_obj) { 
        	           b.Gravity(); // continually run this method inside the ball class for each ball object
        	           b.get_Pause_Stat();
        	           b.get_SIM_Speed();
        	           if(ghostParticles == false){
        	           b.ResolveCollisions();
        	           }
        	           b.DetectMouse();
        	           b.set_EK((b.getDiameter() * (Math.sqrt(b.getxVelocity()*b.getxVelocity() + b.getyVelocity() * b.getyVelocity()))*0.5));           
        	            for (int i = 0 ; i <= ball_obj.size()-1-1;i++){
        	            	
        	            	ball_obj.get(i).set_EK((ball_obj.get(i).getDiameter() * (Math.sqrt(ball_obj.get(i).getxVelocity()*ball_obj.get(i).getxVelocity() + ball_obj.get(i).getyVelocity() * ball_obj.get(i).getyVelocity()))*0.5));
        	            }
        	        }
      
           repaint(); // Updates / refreshes graphics so that the drawn ball objects actually move
         
   
         
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
            }
        }
    }
 
   
}


