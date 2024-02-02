import java.text.DecimalFormat;

public class SimClock extends gui{
static double tick = 0.00;
	public SimClock(){
		
		while (isRunning == true && CollisionSimulator.Pause_State == false) {
						runClock();
			//return_tick();
			
		}  
			
	}
	public static double runClock(){
			 //tick = tick + 0.01;
			
			 try {
				 tick = tick + 0.01;
				 tick = tick + 0.01;
				
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 DecimalFormat df = new DecimalFormat("####0.00");
			 return tick;
		}
	
	
	public void stopClock(){
		
		
	}
	//public static double return_tick(){
		//return tick;
	//}
}
 