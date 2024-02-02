//package Collision_Sim;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;



public class Gameplay extends JPanel implements KeyListener, ActionListener{ //Class Game play inherits JPanel's settings
private boolean play = true;


private Timer timer;
private int delay = 8;
private int ObjPosX = 0;
private int ObjPosY = 0;
private int ObjXdir = -0;
private int ObjYdir = -10;

public Gameplay() {
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
	timer = new Timer(delay, this);
	timer.start();
	
	
}

//The code below stylizes the window/ defines what its properties will be like i.e. background fill etc...
public void paint(Graphics g) {//Because Gameplay inherits JPanel, cmds such as paint,graphics,g.(method())(parameters), can now be usedas they are all methods inside the class JPanel
	Color SkyBlue = new Color(150, 206, 255); // custom color palatte creation
	//BG
	g.setColor(SkyBlue);
	g.fillRect(1, 1, 1100, 750); //fills background of the window created in Main.java
	//borders 
	g.setColor(Color.WHITE);
	g.fillRect(0, 0, 3, 550); //Left border
	g.fillRect(950, 0, 3, 550); //Right border
	g.fillRect(0, 0, 1100, 3); //top border
	g.fillRect(0, 550, 1100, 3); //Bottom border
	
	g.setColor(Color.GRAY);
	//g.fillRect(0, 0, 100, 592);
	//Ball
	g.setColor(Color.RED);
	g.fillOval(ObjPosX, ObjPosY, 50, 50);
	//Menu back layer
	g.setColor(Color.GRAY);
	g.fillRect(953, 0, 247, 548);
	g.fillRect(0, 553, 1100, 167);
	
}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(play){
			ObjPosX += ObjXdir;
			ObjPosY += ObjYdir;
			if(ObjPosX < 0){ //Left-side collision detection/Re-bound
				ObjXdir = - ObjXdir;
			}
			if(ObjPosY < 0){
				ObjYdir = - ObjYdir;
			}
			if(ObjPosY > 500){//Floor collision detection/Re-bound
				ObjYdir = - ObjYdir;
			}
			if(ObjPosX > 912){
				ObjXdir = - ObjXdir;
			}
		}
		
		
		repaint();//Updates X,Y position of the ball
	}
	//Menu Graphics
	

	@Override
	public void keyPressed(KeyEvent arg0) {// If key pressed
		if(arg0.getKeyCode() == KeyEvent.VK_M) {

		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}