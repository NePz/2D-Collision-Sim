
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
/**
 Ball Class
 */

public class ball  {
	// variables used by this class //
	String label; 
    private double x;
    private double y;
    private  int diameter;
    Color color;
    private  double xVelocity;
    private  double yVelocity;
    private double mass;
    private  double Ek;
    public static int gravity;
    public static int Pause_Stat;
    public int Simulation_Speed;
    public static double Elasticity_Const;
    public static boolean SpawnedOnTop;
    public static double totalEK;
    public static int touching_ball;
// End of variables //
    public ball(String label, int x, int y, int diameter,double mass, double Ek, Color color) { // Constructor / blueprint for ball object
        this.x = x; 
        this.y = y;
        this.diameter = diameter;
        this.mass = mass;
        this.Ek = Ek;
        this.color = color;
    }
// End of constructor for ball object // 
    public void draw(Graphics g) { // Graphics method from java library to draw the instantiated ball object(s)
        Graphics2D g2d = (Graphics2D) g;
        // draw the outline of the ball object to be instantiated
        Ellipse2D.Double circle = new Ellipse2D.Double(getX() - getDiameter() / 2, getY() - getDiameter() / 2, getDiameter(), getDiameter()); // arguments passed to this draw ellipse method which will define the dimensions of the ball object upon instantiation
        g2d.fill(circle); // Fills in the drawn ellipse called "circle" to draw a solid ball object 
       ((Graphics2D) g).setStroke(new BasicStroke(0.0f));

    }
    public void get_SIM_Speed(){
 Simulation_Speed = CollisionSimulator.SIM_Speed;
    }

   
    public static void get_Pause_Stat(){
    	if (CollisionSimulator.Pause_State == true){ //Updates gravity state 1||0
    	Pause_Stat = 0;
    	gravity = 0;
    		/*while(CollisionSimulator.Pause_State == true){
    		
    		}*/
    	} else {
    	Pause_Stat = 1; 
    	gravity = 1;
    	
    	}}
   
    // Gravity method, sets gravity to 1 if gravitiy is enabled & 0 if it's disabled
    public void Gravity(){
if (CollisionSimulator.Gravity_Enabled == true){ //Updates gravity state 1||0
	gravity = gravity; // sets gravity const to 1
}
else {
	gravity = 0; // gravity off at 0 i.e. ball objects will float
}



    Elasticity_Const = CollisionSimulator.Elasticity_Constant; //CollisionSimulator.getElasticConst();

        if (getY() + getDiameter()/2 > 523) { //If ball obj impacts the floor
        	setyVelocity((int) ((-getyVelocity() )* Elasticity_Const ));//Flip velocity 
        	setxVelocity((int) ((getxVelocity() )* Elasticity_Const ));
        	setY((523 -getDiameter()/2 - gravity/2 )); //stops the ball from getting eaten up by floor lo
        	//System.out.println("FLOOR HIT X");
        	//setxVelocity((int) ((getxVelocity()* Elasticity_Const *Pause_Stat /*friction*/) ));// loses x speed upon impact
        }  

        if (getY() - getDiameter()/2 <=0) {
        	setyVelocity((int) ((-getyVelocity() )* Elasticity_Const /*elasticity*/) );//Flip velocity 
        	setxVelocity((int) ((getxVelocity() )* Elasticity_Const ));
        	 setY((0 + getDiameter()/2)); //stops the ball from getting eaten up by celing	 
        	// System.out.println("CEILING HIT");
        }
        else {//apply GRAVITY (constant velocity downward i.e. gravity effect)
        	setyVelocity((int) ((getyVelocity() + gravity ) /*g_const*/) );
        		
	      
        	}
        
        if (getX() + getDiameter()/2 > 927) { //If ball obj impacts the right border
        	setxVelocity( ((-getxVelocity()* Elasticity_Const   ) ));
        	setX( (927 - getDiameter()/2));
        }
        if (getX() - getDiameter()/2 < 0) { //If ball obj impacts the left border
        	setxVelocity( ((-getxVelocity()* Elasticity_Const  ) ));
        	setX((float) (0 + getDiameter()/2));
        } 
        
        //Update XY pos of ball by adding value of current XY pos to XY speed value
     	if(Pause_Stat == 1){
        setY((getY() + getyVelocity())); //update y_position of the ball
    	setX( (getX() + getxVelocity() ));
     	if(Pause_Stat == 0){
     	   setY((getY() )); //update y_position of the ball
     	   setX( (getX()  ));}
     	}

   	 }
    // End of gravity code
   
    // Code for calculating new velocity upon collision
    public void ResolveCollisions() {
    	double xDist, yDist; // variables used
    	// Nested for loop to run through & compare the ball objects from a single array list
    	for(int i=0; i < gui.ball_obj.size();i++){ 
    		ball b1 = gui.ball_obj.get(i);
    		for(int j=0; j<gui.ball_obj.size();j++){
    			ball b2 = gui.ball_obj.get(j);
    			xDist = b1.getX() - b2.getX(); // difference in x position between ball objects
    			yDist = b1.getY() - b2.getY(); // difference in y position between ball objects
    			double distSquared = xDist * xDist + yDist*yDist; 
    			if(distSquared <=(b1.getDiameter()/2 + b2.getDiameter()/2)*(b1.getDiameter()/2 + b2.getDiameter()/2)){
    				double xVelocity = b2.getxVelocity() - b1.getxVelocity();
    				double yVelocity = b2.getyVelocity() - b1.getyVelocity();
    				double dotProduct = xDist * xVelocity + yDist * yVelocity; // calculates the dot product of xyDist & xyVelocity
    				if(dotProduct >0 && CollisionSimulator.Elasticity_Enabled == false){ // check if ball objects have collided using the dot product
    					System.out.println("COLLIDED");
    					double collisionScale = dotProduct/distSquared;
    					double xCollision = xDist * collisionScale;
    					double yCollision = yDist * collisionScale;
    					double combinedMass = b1.getMass() + b2.getDiameter()/10;
    					double collisionWeightA = 2 * b2.getMass()/combinedMass;
    					double collisionWeightB = 2 * b1.getMass()/combinedMass;
    				  b1.setxVelocity(collisionWeightA * xCollision);
       				  b1.setyVelocity(collisionWeightA * yCollision);
       				  
       				  b2.setxVelocity(-collisionWeightB * xCollision);
       				  b2.setyVelocity(-collisionWeightB * yCollision);
    				} else if(dotProduct >0 && CollisionSimulator.Elasticity_Enabled == true){ // if any 2 ball objects are colliding in 100% elastic collision mode
    					System.out.println("COLLIDED WITH 100% ELASTICITY");
    					double collisionScale = dotProduct/distSquared;
    					double xCollision = xDist * collisionScale;
    					double yCollision = yDist * collisionScale;
    					double combinedMass = b1.getMass() + b2.getDiameter()/10;
    					double collisionWeightA = 2 * b2.getMass()/combinedMass;
    					double collisionWeightB = 2 * b1.getMass()/combinedMass;
    				  b1.setxVelocity( xCollision);
       				  b1.setyVelocity( yCollision);
       				  
       				  b2.setxVelocity(-xCollision);
       				  b2.setyVelocity(-yCollision);
    				}
    					
    			}
    		}
    	}
    		
    }
// End of collision resolve code

    public void DetectMouse(){
    	// detects whether the mouse is touching a ball objects
    	
    	double xDist, yDist; 
    	for(int i=0; i < gui.ball_obj.size();i++){
    		ball b1 = gui.ball_obj.get(i);
    		for(int j=0; j<gui.ball_obj.size();j++){
    			ball b2 = gui.ball_obj.get(j);
    			xDist = b1.getX() - gui.getMousePositionX();
    			yDist = b1.getY() - gui.getMousePositionY();
    			double distSquared = xDist * xDist + yDist*yDist;
    			if(distSquared <=(b1.getDiameter()/2 + b2.getDiameter()/2)*(b1.getDiameter()/2 + b2.getDiameter()/2)){
    				double xVelocity = b2.getxVelocity() - gui.getMousePositionX();
    				double yVelocity = b2.getyVelocity() - gui.getMousePositionY();
    				double dotProduct = xDist * xVelocity + yDist * yVelocity;
    				if(dotProduct >0){
    					System.out.println("TOUCHING MOUSE");
    					//CollisionSimulator.touching_mouse = true; 
    					//int touching_ball = j;
    				} else {
    					CollisionSimulator.touching_mouse = false;
    				}
    				// implement this later to write code to make objects draggable
    					
    			}
    		}
    	}
    		

  	  }
		  
  	  

// Pythagoras' theorem implemented distance calculator
    public static double CalcDist(double x1, double y1, double x2, double y2){
   	 double xDist = x2 - x1; // the difference in x position
   	 double yDist = y2 - y1; // difference in y position
   	return Math.sqrt(xDist * xDist + yDist * yDist); // in Pythagoras' theorem format sqrt(a^2 + b^2) which returns the distance between 2 centre points of given ball objects
   	
   }
   
    // To return xVelocity of badd & also pass into other classes e.g. calling this class will return the xVelocity into the other class
    public  double getxVelocity() {
        return xVelocity;
    }

    
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity ;
    }

    public  double getyVelocity() {
        return yVelocity;
    }

   
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }


    public double getX() {
        return x;
    }

    
    public void setX(double x) {
        this.x = x;
    }

  
    public double getY() {
        return y;
    }

  
    public void setY(double y) {
        this.y = y;
    }

  
    public double getDiameter() {
        return diameter;
    }

   
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
    
public double getMass(){
	// p = m/v <-- momentum equation
	double radius_ball = diameter/2;
	double volume = ((4/3)*Math.PI*Math.pow(radius_ball, 3)); // implementation of sphere volume equation to calculate volume of ball object(s)
	double density = volume*2;
		
	mass = density / volume *10;
	return mass;
}
    public void setMass(double mass){
    	this.mass = getDiameter()/10;
    }
    
	public  double get_EK() {
		  DecimalFormat df = new DecimalFormat("####0.00");
		  return Ek;
	}

	public  void set_EK(double Ek ) {
		this.Ek = Math.round(Ek);
	}
	   public void setGravity(int gravity) {
	
	        this.gravity = gravity;
	    }
	    public double getGravity() {
	        return gravity;
	    }
public void setElasticConst(double Elasticity_Const){
	this.Elasticity_Const = Elasticity_Const;
}
 
public double get_Elasticity_Const(){
	return Elasticity_Const;
}

}
