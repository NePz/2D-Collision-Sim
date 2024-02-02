
public class SIM_Physics extends gui {

	public SIM_Physics(){
		
	}
	static double angle = Math.asin(b1.getySpeed()/b1.getxSpeed());
	static double momentum;

public static void checkCollision() {

    deltaX = (b1.getX() - b2.getX()); //deltaX is the absolute position of X pos's of b1 - b2
    deltaY = (b1.getY() - b2.getY());
    distance = deltaX * deltaX + deltaY * deltaY;
    //
    if (distance < (b1.getDiametar() / 2 + b2.getDiametar() / 2) * (b1.getDiametar() / 2 + b2.getDiametar() / 2)) {

        double newxSpeed1 = (b1.getxSpeed() * (4 - 7) + (2 * 7 * b2.getxSpeed())) / 11;

        double newxSpeed2 = (b2.getxSpeed() * (7 - 4) + (2 * 4 * b1.getxSpeed())) / 11;

        double newySpeed1 = (b1.getySpeed() * (4 - 7) + (2 * 7 * b2.getySpeed())) / 11;

        double newySpeed2 = (b2.getySpeed() * (7 - 4) + (2 * 4 * b1.getySpeed())) / 11;

        b2.setxSpeed(newxSpeed2);
        b2.setySpeed(newySpeed2);
        b1.setxSpeed(newxSpeed1);
        b1.setySpeed(newySpeed1);

    }

}
public static double calculateMagnitude() {
	magnitude = b1.getySpeed()/angle;
	if (magnitude < 0 ){
		magnitude = magnitude + magnitude + magnitude;
	}
	return magnitude;
}

}



   public void Detect_Collision(){
    	  for (int i = 0 ; i <= gui.ball_obj.length-1;i++ ) {
    		
    		  for (int j = 0 ; j <= gui.ball_obj.length-1;j++ ) {
    			  double deltaX = (gui.ball_obj[i].getX()-gui.ball_obj[j].getX());
    			  double deltaY = (gui.ball_obj[i].getY()-gui.ball_obj[j].getY());
    			  double distance = deltaX * deltaX + deltaY * deltaY;
    			  
    			  if(distance <(gui.ball_obj[i].getDiameter()/2 + gui.ball_obj[j].getDiameter()/2)*(gui.ball_obj[i].getDiameter()/2+gui.ball_obj[j].getDiameter()/2)) {
    				  double NewXspeed1 = (gui.ball_obj[i].getxSpeed() * (4-7)+(2 * 7 * gui.ball_obj[j].getxSpeed()))/11;
    				  double NewXSpeed2 = (gui.ball_obj[j].getxSpeed() * (7-4)+(2* 4 * gui.ball_obj[i].getxSpeed()))/11;
    				  
    				  double NewYspeed1 = (gui.ball_obj[i].getySpeed() * (4-7)+(2 * 7 * gui.ball_obj[j].getySpeed()))/11;
    				  double NewYSpeed2 = (gui.ball_obj[j].getySpeed() * (7-4)+(2* 4 * gui.ball_obj[i].getySpeed()))/11;
 
    				  gui.ball_obj[i].setxSpeed((float)NewXspeed1);
    				  gui.ball_obj[i].setySpeed((float) NewYspeed1);
    				  
    				  gui.ball_obj[j].setxSpeed((float) NewXSpeed2);
    				  gui.ball_obj[j].setySpeed((float) NewYSpeed2);
    			  }