
public class Vector extends gui{
static double X_vel; //X component of vector of ball obj
static double Y_vel;
static double magnitude;



public double reslove_Vx(){ //Finds/Sets the vector magnitude for X axis
	X_vel = Math.tan(xSpeed/Yspeed);
	return X_vel; // Returns the magnitude claculated
	
}

public double reslove_Vy(){
	return Y_vel;
	
}

public double Calc_VectorMagnitude(){
	
	return magnitude;
	
}
}