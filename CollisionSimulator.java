import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.print.DocFlavor.STRING;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import javax.swing.border.*;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;


import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import java.awt.Panel;
import javax.swing.JTextPane;
import javax.swing.JDesktopPane;
import javax.swing.JSeparator;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Label;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JEditorPane;




public class CollisionSimulator {
	// Variables for methods
	public static int OBJ_Count; // stores the no. of objects curretly instantiated
	public static int obj_1_size;
	public static int SIM_Speed = 1;
	public static boolean Pause_State = false; // Ensures sim isn't paused when initialised 
	public static double Sim_timer_parameter = 0;
	public static boolean Show_info = false;
	public static boolean Gravity_Enabled; // used to determine if gravity is enabled
	public static boolean Elasticity_Enabled; // used to detemrine if collisions are ealstic of inelastic
	public static double Elasticity_Constant; // ererewerwe
	public static int Frame_Height;
	public static int Frame_Width;
	public static int SelectedIndex;
	public static boolean touching_mouse;
	public static boolean Show_ADD_Vector;
	// End of variables for methods
	public static JFrame frmCollisionSimulator; // Instantiating a new JFrame object
	public final static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); // Instantiating a new JTab object

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CollisionSimulator window = new CollisionSimulator(); // Instantiates a new Collision simulator window object
					window.frmCollisionSimulator.setVisible(true); // Makes the window visible when the program is executed / ran
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CollisionSimulator() {
		initialize(); //Initialises the contents of the frame
	
		}
	

	/**
	 * Initialize the contents of the frame.
	 */

	public static void initialize() {
		
		frmCollisionSimulator = new JFrame(); // Instantiates a new JFrame inside the window
		frmCollisionSimulator.setTitle("Collision Simulator "); 
		frmCollisionSimulator.setBounds(0, 0, 1100, 750); // setBounds method defines the dimensions & position of the window when initialised
		frmCollisionSimulator.setResizable(false); 
		frmCollisionSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCollisionSimulator.getContentPane().setLayout(null);
		
		 
	
		JPanel T_R_Panel_Dock = new JPanel(); // Instantiates a new JPanel object 
		T_R_Panel_Dock.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null)); // definies the style of the borders
		T_R_Panel_Dock.setBackground(SystemColor.activeCaption); 
		T_R_Panel_Dock.setBounds(927, 0, 167, 50); // defines the dimensions & position of the JPanel inside the window
		frmCollisionSimulator.getContentPane().add(T_R_Panel_Dock); // actually inserts it into the window to be visible
		
		JLabel lblSettings = new JLabel("SETTINGS"); // Instantiates new JLabel object
		T_R_Panel_Dock.add(lblSettings); // Inserts it into the JPanel T_R_Panel_Dock so it can actually display somewhere
		lblSettings.setForeground(Color.DARK_GRAY);
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel Settings_Decal = new JLabel(""); 
		T_R_Panel_Dock.add(Settings_Decal);
		Settings_Decal.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Cog.png")); // image is inserted into JLabel via filepath to be displayed in the jpanel
		
		JPanel R_Panel_Dock = new JPanel();
		R_Panel_Dock.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		R_Panel_Dock.setBackground(SystemColor.activeCaption);
		R_Panel_Dock.setBounds(927, 44, 167, 513);
		frmCollisionSimulator.getContentPane().add(R_Panel_Dock);
		R_Panel_Dock.setLayout(null);
		
		// Gravity toggle button code // 
		JToggleButton Gravity_btn = new JToggleButton(""); // Instantiates a new JToggleButton object
		
		Gravity_btn.setToolTipText("Toggle gravity"); // The text pops up when you hover over it
		Gravity_btn.addMouseListener(new MouseAdapter() { // Adds a new event listener into the gravity button
			@Override
			public void mouseClicked(MouseEvent e) { // If a mouse click event is detected run the codes inside the brackets:
				if (Gravity_btn.isSelected() == true) {System.out.println("GRAVITY: ON"); // If gravity button is enabled then
					Gravity_Enabled = true; // Set the boolean variable Gravity_Enabled to true
					
					}
				if (Gravity_btn.isSelected() == false) {System.out.println("GRAVITY: OFF"); // If gravity button isn't enabled...
				Gravity_Enabled = false;} // Set the bool var Gravity_Enabled to false
			}
		});
		Gravity_btn.setSelectedIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Gravity_Btn_ON.jpg"));
		// Adds picture to the gravity_btn if it's toggled ON
		
		Gravity_btn.setBounds(10, 54, 150, 30); 
		Gravity_btn.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Gravity_Btn.jpg"));
		System.out.println("GRAVITY: OFF");
		R_Panel_Dock.add(Gravity_btn);
		
		// Pause toggle button code // 
		JToggleButton Pause_btn = new JToggleButton("");
		
		Pause_btn.setToolTipText("Pause / Run Simulation");
		Pause_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Pause_btn.isSelected() == true) {System.out.println("SIMULATION: PAUSED"); Pause_State = true; Pause_Sim(Pause_State);}
				if (Pause_btn.isSelected() == false) {System.out.println("SIMULATION: RUNNING");Pause_State = false; Resume_Sim(Pause_State);}
				System.out.println("Collision sim VAR Pause_State :" + Pause_State);
				
			}
		});
		Pause_btn.setSelectedIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Play_btn.jpg"));
		Pause_btn.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Paused_btn.jpg"));
		Pause_btn.setBounds(66, 11, 32, 32);
		R_Panel_Dock.add(Pause_btn);
		
		// Display stats toggle button code // 
		JToggleButton Display_Stats_Btn = new JToggleButton("");
		Display_Stats_Btn.setToolTipText("Toggle Obj stats");
		Display_Stats_Btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Display_Stats_Btn.isSelected() == true) {System.out.println("DISPLAY STATS: ON"); Show_info = true; }
				if (Display_Stats_Btn.isSelected() == false) {System.out.println("DISPLAY STATS: OFF"); Show_info = false;}
			}
		});
		Display_Stats_Btn.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Display_Stats_Btn_OFF.jpg"));
		Display_Stats_Btn.setSelectedIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Display_Stats_Btn_ON.jpg"));
		Display_Stats_Btn.setBounds(10, 95, 150, 30);
		R_Panel_Dock.add(Display_Stats_Btn);
		
		// Increase Simulation Speed button code //
		JButton Increase_Sim_Speed_btn = new JButton("");
		Increase_Sim_Speed_btn.setToolTipText("Increase simulation speed");
		Increase_Sim_Speed_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // when mouse is clicked on this button
			//run these block of code below
				System.out.println("Simulation speed "+ SIM_Speed);
				if (SIM_Speed < 32){ // Checks if the variable SIM_SPEED is less than 32
					SIM_Speed = SIM_Speed * 2; // If it is then double it
				} else
					SIM_Speed = 32; // other wise set it to 32
			}
		});
		Increase_Sim_Speed_btn.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Sim_Speed_Increase_btn.jpg"));
		Increase_Sim_Speed_btn.setBounds(108, 11, 32, 32);
		R_Panel_Dock.add(Increase_Sim_Speed_btn);
		
		// Decrease Simulation Speed button code // 
		JButton Decrease_Sim_Speed_btn = new JButton("");
		Decrease_Sim_Speed_btn.setToolTipText("Decrease simulation speed");
		Decrease_Sim_Speed_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Simulation speed decreased!");
			}
		});
		Decrease_Sim_Speed_btn.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Sim_Speed_Decrease_btn.jpg"));
		Decrease_Sim_Speed_btn.setBounds(24, 11, 32, 32);
		R_Panel_Dock.add(Decrease_Sim_Speed_btn);
		

		
		JLabel Elasticity = new JLabel("Elasticity:");
		Elasticity.setBounds(10, 177, 150, 14);
		R_Panel_Dock.add(Elasticity);
		
		// G_Constant slider code //
		JSlider G_Constant_Slider = new JSlider(); // Instantiates a new JSlider object
		G_Constant_Slider.setBounds(13, 263, 147, 21);
		R_Panel_Dock.add(G_Constant_Slider); // puts it into the R_Panel_Dock
		G_Constant_Slider.setValue(0);
		G_Constant_Slider.setSnapToTicks(true);
		G_Constant_Slider.setPaintLabels(true);
		G_Constant_Slider.setMinorTickSpacing(10); // sets the smallest possible interval value of the slider
		G_Constant_Slider.setMinimum(1); // sets the smallest value of the slider to 1
		G_Constant_Slider.setForeground(Color.BLACK);
		G_Constant_Slider.setBackground(SystemColor.activeCaption);
		
		
		JLabel Label = new JLabel("1                                        10");
		Label.setBounds(16, 240, 144, 14);
		Label.setLabelFor(G_Constant_Slider);
		R_Panel_Dock.add(Label);
		
		JLabel G_Const_Label = new JLabel("G Constant:");
		G_Const_Label.setBounds(10, 227, 150, 14);
		R_Panel_Dock.add(G_Const_Label);
		
		//SLIDER FOR ELASTICITY
		JSlider Elasticity_Slider = new JSlider();
		Elasticity_Slider.setPaintTicks(true);
		Elasticity_Slider.setPaintLabels(true);
		Elasticity_Slider.setBackground(SystemColor.activeCaption);
		Elasticity_Slider.setForeground(SystemColor.desktop);
		Elasticity_Slider.setBounds(10, 207, 150, 23);
		Elasticity_Slider.setValue(100); // Initial value of the Elasticity constant slider set to 100 i.e. 100% elastic collisions (no Ek lost)

		System.out.println("Elasticityy: " + Elasticity_Enabled);
		System.out.println("E-const: " + Elasticity_Constant);
		
		R_Panel_Dock.add(Elasticity_Slider); // inserts the elasticity slider into the panel on the right hand side
		// End of Elasticity slider //
		
		
		
		// Elasticity button toggle //
		JToggleButton Elasticity_btn = new JToggleButton("");
		Elasticity_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Elasticity_btn.isSelected() == true){
					Elasticity_Enabled = false;
					Elasticity_Constant = getElasticConst();
					System.out.println("Elastic collisions [OFF] " + Elasticity_Constant*100 + "%");
				System.out.println("Current E-const: " + Elasticity_Constant);}
				else if (Elasticity_btn.isSelected() == false){
					Elasticity_Enabled = true; // Tells the simulation program that the Elasticity button is toggled: ON
					Elasticity_Constant = 1; // when it's toggled ON, set the var elasticity constant to 1
						}
					}
						
				}
			
		);
		Elasticity_btn.setSelectedIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Elastic_btn_OFF.jpg")); // show this image on the Elasticity_btn when it's toggled: OFF
		Elasticity_btn.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Elastic_btn_ON.jpg"));  // show this image on the Elasticity_btn when it's toggled: ON
		Elasticity_btn.setToolTipText("Toggle Obj stats");
		Elasticity_btn.setBounds(10, 136, 150, 30);
		
		R_Panel_Dock.add(Elasticity_btn);
		
		if (Elasticity_btn.isSelected() == false){
			Elasticity_Enabled = true;
			Elasticity_Constant = 1;
			System.out.println("Elastic collisions [ON] 100%");
			System.out.println("Current e-cost = " + Elasticity_Constant);	
		}
		// End of Elasticity button toggle //
			
		JLabel label_1 = new JLabel("0                                  100%");
		label_1.setBounds(16, 191, 144, 14);
		R_Panel_Dock.add(label_1);
		
		//APPLY SETTINGS BUTTON
		JButton Apply_btn = new JButton("| Apply settings |");
		Apply_btn.setBounds(9, 288, 151, 30);
		R_Panel_Dock.add(Apply_btn);
		
		// Positive gravity field toggle button
		JToggleButton TogglePGrav = new JToggleButton("Pos Grav field( + )"); // Positive gravity pulls ball objects towards the point mouse is clicked
		TogglePGrav.setBounds(10, 339, 147, 23);
		R_Panel_Dock.add(TogglePGrav);
	
		// Negative gravity field toggle button
		JToggleButton ToggleNGrav = new JToggleButton("Neg Grav field( - )"); // Positive gravity pushes away ball objects from the point mouse is clicked
		ToggleNGrav.setBounds(10, 362, 147, 23);
		R_Panel_Dock.add(ToggleNGrav);
		
		// Add vector to ball object toggle button
		JToggleButton tglbtnAddVectorxy = new JToggleButton("Add Vector [x,y]"); 
		tglbtnAddVectorxy.setBounds(10, 385, 147, 23);
		R_Panel_Dock.add(tglbtnAddVectorxy);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Draggable");
		chckbxNewCheckBox.setBackground(SystemColor.activeCaption);
		chckbxNewCheckBox.setBounds(10, 412, 130, 23);
		R_Panel_Dock.add(chckbxNewCheckBox);
		
		// line separating the settings i.e. toggle gravity, stats, elasticity & Apply button, from the extra features such as toggle gravity fields, vectors, etc...
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.desktop);
		separator.setBounds(0, 329, 167, 1);
		R_Panel_Dock.add(separator);
		
		// Apply button code
		Apply_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // when apply button is pressed
				System.out.println("Applying settings...");
				if (Gravity_Enabled == true){ // if gravity is enabled when the button is pressed
					ball.gravity = G_Constant_Slider.getValue(); // update the current g constant with the g constant slider's value
					System.out.println("G-CONST: " + ball.gravity);
				}
			
				
				
				if (Elasticity_btn.isSelected() == true){//Apply elasticity
					Elasticity_Constant = Elasticity_Slider.getValue() * 0.01; // set the elasticity constant to the value of the elasticity slider multiplied by 0.01
					// it's multiplied by 0.01 to calculate the %elasticity e.g. 100 * 0.01 = 1, which is fully elastic, where as 50 * 0.01 = 0.5 which is 50% inelastic which means 50% energy is lost on every bounce
					System.out.println("E-CONST: " + Elasticity_Constant);
			}
				
				}
			
		});
		Apply_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// End of Apply button //
		
		// Bottom panel // 
		JPanel Btm_Panel_Dock = new JPanel();
		Btm_Panel_Dock.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Btm_Panel_Dock.setBackground(SystemColor.activeCaption);
		Btm_Panel_Dock.setBounds(0, 554, 1094, 167);
		frmCollisionSimulator.getContentPane().add(Btm_Panel_Dock);
		Btm_Panel_Dock.setLayout(null);
	   
		
		// OBJ size slider // 
		// These are the sliders for adjusting the sizes of all the 5 objects the user is allowed to instantiate
		
		// Slider for ball object[0]'s size
		JSlider Obj0_size_slider = new JSlider();
		Obj0_size_slider.setPaintLabels(true);
		Obj0_size_slider.setMinorTickSpacing(1);
		Obj0_size_slider.setBounds(263, 16, 200, 21);
		Obj0_size_slider.setMaximum(150);
		Obj0_size_slider.setMinimum(50);
		Obj0_size_slider.setValue(1);
		Obj0_size_slider.setSnapToTicks(true);
		Obj0_size_slider.setBackground(SystemColor.activeCaption);
		Obj0_size_slider.setForeground(SystemColor.desktop);
		obj_1_size = Obj0_size_slider.getValue();
		Btm_Panel_Dock.add(Obj0_size_slider);
		
		// Slider for ball object[1]'s size
		JSlider Obj1_size_slider = new JSlider();
		Obj1_size_slider.setPaintLabels(true);
		Obj1_size_slider.setValue(1);
		Obj1_size_slider.setSnapToTicks(true);
		Obj1_size_slider.setMinorTickSpacing(1);
		Obj1_size_slider.setMinimum(50);
		Obj1_size_slider.setMaximum(150);
		Obj1_size_slider.setForeground(Color.BLACK);
		Obj1_size_slider.setBackground(SystemColor.activeCaption);
		Obj1_size_slider.setBounds(263, 40, 200, 21);
		Btm_Panel_Dock.add(Obj1_size_slider);
		
		// Slider for ball object[2]'s size
		JSlider Obj2_size_slider = new JSlider();
		Obj2_size_slider.setPaintLabels(true);
		Obj2_size_slider.setValue(1);
		Obj2_size_slider.setSnapToTicks(true);
		Obj2_size_slider.setMinorTickSpacing(1);
		Obj2_size_slider.setMinimum(50);
		Obj2_size_slider.setMaximum(150);
		Obj2_size_slider.setForeground(Color.BLACK);
		Obj2_size_slider.setBackground(SystemColor.activeCaption);
		Obj2_size_slider.setBounds(263, 67, 200, 21);
		Btm_Panel_Dock.add(Obj2_size_slider);
		
		// Slider for ball object[3]'s size
		JSlider Obj3_size_slider = new JSlider();
		Obj3_size_slider.setPaintLabels(true);
		Obj3_size_slider.setValue(1);
		Obj3_size_slider.setSnapToTicks(true);
		Obj3_size_slider.setMinorTickSpacing(1);
		Obj3_size_slider.setMinimum(50);
		Obj3_size_slider.setMaximum(150);
		Obj3_size_slider.setForeground(Color.BLACK);
		Obj3_size_slider.setBackground(SystemColor.activeCaption);
		Obj3_size_slider.setBounds(263, 90, 200, 21);
		Btm_Panel_Dock.add(Obj3_size_slider);
		
		// Slider for ball object[4]'s size
		JSlider Obj4_size_slider = new JSlider();
		Obj4_size_slider.setPaintLabels(true);
		Obj4_size_slider.setValue(1);
		Obj4_size_slider.setSnapToTicks(true);
		Obj4_size_slider.setMinorTickSpacing(1);
		Obj4_size_slider.setMinimum(50);
		Obj4_size_slider.setMaximum(150);
		Obj4_size_slider.setForeground(Color.BLACK);
		Obj4_size_slider.setBackground(SystemColor.activeCaption);
		Obj4_size_slider.setBounds(263, 114, 200, 21);
		Btm_Panel_Dock.add(Obj4_size_slider);
		// End of object sliders//
		
		// // ADD OBJ button code //
		JButton ADD_Obj = new JButton("");
		ADD_Obj.setBounds(10, 16, 135, 30);
		ADD_Obj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (get_OBJ_Count() < 5) { // When button is pressed & if the var obj_count is less than 5 
					 gui.ball_obj.add(new ball("OBJ_1",(int) gui.RandX(),(int) gui.RandY(),(int) gui.RandDiameter(),1,gui.Ek,Color.red)); // if count < 5 it will instantiate a new ball object upon pressing
					System.out.println(get_OBJ_Count() + " Object has been added to the simulation!");
					
					if(get_OBJ_Count() >=5){ // However if there are 5 or more objects already instantiated
						System.out.println("THE MAX OBJECT LIMIT HAS BEEN REACHED");
						 JOptionPane.showMessageDialog(null, "MAXIMUN NUMBER OF OBJECT HAS BEEN REACHED", "Error Message", // this will display error message if object limit (5 objects) is reached and if user is trying to instantiate more
		                         JOptionPane.ERROR_MESSAGE);
						}
				}
				
if (get_OBJ_Count() > 1 ) { // when add object is pressed & if there are already more than 1 objects
	 for (int i = 0 ; i <= gui.ball_obj.size()-1;i++ ) {
 		
		  for (int j = 1 ; j <= gui.ball_obj.size()-1;j++ ) {
			  double deltaX = (gui.ball_obj.get(i).getX()-gui.ball_obj.get(j).getX()); // Calculate X distance between the ball objects
			  double deltaY = (gui.ball_obj.get(i).getY()-gui.ball_obj.get(j).getY()); // Calculate Y distance between the ball objects
			 double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);  // calculate distance between ball objects by implementing the pythagora's theorem
		
			 if(gui.ball_obj.get(i) == gui.ball_obj.get(j)){ // When going through the loop, if the object being scanned in 1 is same in the other then...
				 j++; // skip 1 iteration in the nested for loop in order to prevent comparing x, y distance between the SAME ball object
			 } else
				 // code for checking if newly instantiate object's spawn point overlaps already existing one's x, y position if spawned
			 if(distance <= gui.ball_obj.get(i).getDiameter()/2 + gui.ball_obj.get(j).getDiameter()/2 || distance <= gui.ball_obj.get(j).getDiameter()/2 + gui.ball_obj.get(i).getDiameter()/2) { // Compares the distance between the ball objects
				 System.out.println("OBJ SPAWNED ON TOP, RE-POSITIONING..."); // if the condition above is satisfied then program knows ball object has spawned on top of each other
			gui.ball_obj.get(j).setX(gui.RandX()); // regenerates a new X co-ordinate(s) using the randX() method for the spawn point of the object to be instantiated
			gui.ball_obj.get(j).setY(gui.RandY()); // regenerates a new Y co-ordinate(s) using the randY() method for the spawn point of the object to be instantiated
			i--; // this will make the program go back to the top of loop & check if the newly generated X, Y co-ordinates spawn point is still overlapping
			// It will do a recursive loop until the generate x, y co-ordinates aren't overlapping
				 
			 }
		  }
	 }

					}
				
					
		
			}
		});
			

		ADD_Obj.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\ADD_Obj_btn.jpg"));
		Btm_Panel_Dock.add(ADD_Obj);
		// End of add object button code
		
		// Remove OBJ button code //
		JButton Remove_Obj = new JButton("");
		Remove_Obj.setBounds(10, 48, 135, 30);
		Remove_Obj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( get_OBJ_Count() > 0) { // if there are more than 1 instantiated object(s)
					
					System.out.println("ball_obj[" +get_OBJ_Count() + "] has been removed from the simulation!");
					 gui.ball_obj.remove(0); // removes ball object in array position 0 from the simulation
					} else {
						System.out.println("NO OBJECTS LEFT TO REMOVE!"); // display this error message if there are 0 object(s) in the simulation 
						 JOptionPane.showMessageDialog(null, "NO MORE OBJECT LEFT TO REMOVE!", "Error Message",
	                              JOptionPane.ERROR_MESSAGE);
					}
			
				
				
				}
			
		});
		Remove_Obj.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\REMOVE_Obj_btn.jpg"));
		Btm_Panel_Dock.add(Remove_Obj);
		//End of remove obj button code //
		
		// OBJECT SIZE LABEL//
		JLabel lblObjSize = new JLabel("Obj[0]| Size:");
		lblObjSize.setLabelFor(Obj0_size_slider);
		lblObjSize.setForeground(Color.BLACK);
		lblObjSize.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblObjSize.setBounds(179, 16, 84, 21);
		Btm_Panel_Dock.add(lblObjSize);
		
		// ADD | Remove button toggle(s)
		JToggleButton ADD_OBJ_TOGGLE = new JToggleButton("");
		ADD_OBJ_TOGGLE.setSelectedIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\ADD_Obj_btn_ON.jpg"));
		ADD_OBJ_TOGGLE.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\ADD_Obj_btn_OFF.jpg"));						
						//gui.Add_OBJ();
		ADD_OBJ_TOGGLE.setBounds(10, 81, 150, 30);
		Btm_Panel_Dock.add(ADD_OBJ_TOGGLE);
		
		JToggleButton REMOVE_OBJ_TOGGLE = new JToggleButton("");
		REMOVE_OBJ_TOGGLE.setSelectedIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\REMOVE_Obj_btn_ON.jpg"));
		REMOVE_OBJ_TOGGLE.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\REMOVE_Obj_btn_OFF.jpg"));
		REMOVE_OBJ_TOGGLE.setBounds(10, 114, 150, 30);
		Btm_Panel_Dock.add(REMOVE_OBJ_TOGGLE);
		// End of remove object button code
		
		// Code for the apply ball size settings
		JButton ApplyBallSize_btn = new JButton("Apply size");
		ApplyBallSize_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // when the button is pressed
				// set the diameter to the value of the ball object to its corresponding slider's value
				gui.ball_obj.get(0).setDiameter(Obj0_size_slider.getValue());
				gui.ball_obj.get(1).setDiameter(Obj1_size_slider.getValue());
				gui.ball_obj.get(2).setDiameter(Obj2_size_slider.getValue());
				gui.ball_obj.get(3).setDiameter(Obj3_size_slider.getValue());
				gui.ball_obj.get(4).setDiameter(Obj4_size_slider.getValue());
				
			}
		});
		ApplyBallSize_btn.setBounds(263, 137, 84, 25);
		Btm_Panel_Dock.add(ApplyBallSize_btn); // adds the button into the bottom panel
		
		// Labels for the object size sliders
		JLabel label_2 = new JLabel("Obj[1]| Size:");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(179, 40, 84, 21);
		Btm_Panel_Dock.add(label_2);
		
		JLabel lblObjSize_1 = new JLabel("Obj[2]| Size:");
		lblObjSize_1.setForeground(Color.BLACK);
		lblObjSize_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblObjSize_1.setBounds(179, 67, 84, 21);
		Btm_Panel_Dock.add(lblObjSize_1);
		
		JLabel lblObjSize_2 = new JLabel("Obj[3]| Size:");
		lblObjSize_2.setForeground(Color.BLACK);
		lblObjSize_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblObjSize_2.setBounds(179, 90, 84, 21);
		Btm_Panel_Dock.add(lblObjSize_2);
		
		JLabel lblObjSize_3 = new JLabel("Obj[4]| Size:");
		lblObjSize_3.setForeground(Color.BLACK);
		lblObjSize_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblObjSize_3.setBounds(179, 114, 84, 21);
		Btm_Panel_Dock.add(lblObjSize_3);
		// End of labels... //
	
		
		JLabel lblXVelocity = new JLabel("X Velocity:");
		lblXVelocity.setHorizontalAlignment(SwingConstants.CENTER);
		lblXVelocity.setBounds(473, 5, 59, 14);
		Btm_Panel_Dock.add(lblXVelocity);
		

		
		JLabel lblYVelocity = new JLabel("Y Velocity:");
		lblYVelocity.setHorizontalAlignment(SwingConstants.CENTER);
		lblYVelocity.setBounds(554, 5, 59, 14);
		Btm_Panel_Dock.add(lblYVelocity);
		
		
		
		JLabel lblX = new JLabel("X:");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(623, 5, 59, 14);
		Btm_Panel_Dock.add(lblX);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setHorizontalAlignment(SwingConstants.CENTER);
		lblY.setBounds(692, 5, 59, 14);
		Btm_Panel_Dock.add(lblY);
		
		JLabel lblMonentump = new JLabel("Monentum (P):");
		lblMonentump.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonentump.setBounds(761, 5, 84, 14);
		Btm_Panel_Dock.add(lblMonentump);
		
		JLabel lblEk = new JLabel("Ek:");
		lblEk.setHorizontalAlignment(SwingConstants.CENTER);
		lblEk.setBounds(855, 5, 65, 14);
		Btm_Panel_Dock.add(lblEk);
	// End of labels for ball obj stats
	
		// code for displaying values of the variables of ball objects
		JEditorPane BallOBJ_0_xVel = new JEditorPane();
		BallOBJ_0_xVel.setText("0");
		BallOBJ_0_xVel.setBounds(473, 30, 51, 20);
		if(OBJ_Count > 0){
			BallOBJ_0_xVel.setText("" + gui.ball_obj.get(0).getxVelocity()); // display ball object[0]'s x Velocity
			}
		Btm_Panel_Dock.add(BallOBJ_0_xVel);
		
		JEditorPane BallOBJ_0_yVel = new JEditorPane();
		BallOBJ_0_yVel.setText("0");
		BallOBJ_0_yVel.setBounds(554, 30, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_0_yVel); // display ball object[0]'s Y Velocity
		
		JEditorPane BallOBJ_0_xPos = new JEditorPane();
		BallOBJ_0_xPos.setText("0");
		BallOBJ_0_xPos.setBounds(631, 30, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_0_xPos); // display ball object[0]'s x Position
		
		JEditorPane BallOBJ_0_yPos = new JEditorPane();
		BallOBJ_0_yPos.setText("0");
		BallOBJ_0_yPos.setBounds(702, 30, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_0_yPos); // display ball object[0]'s y Velocity
		
		JEditorPane BallOBJ_0_Momentum = new JEditorPane();
		BallOBJ_0_Momentum.setText("0");
		BallOBJ_0_Momentum.setBounds(781, 30, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_0_Momentum); // display ball object[0]'s Momentum
		
		JEditorPane BallOBJ_0_Ek = new JEditorPane();
		BallOBJ_0_Ek.setText("0");
		BallOBJ_0_Ek.setBounds(853, 30, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_0_Ek); // display ball object[0]'s Kinetic energy,
		
		JEditorPane BallOBJ_1_xVel = new JEditorPane();
		BallOBJ_1_xVel.setText("0");
		BallOBJ_1_xVel.setBounds(473, 54, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_1_xVel);
		
		JEditorPane BallOBJ_1_yVel = new JEditorPane();
		BallOBJ_1_yVel.setText("0");
		BallOBJ_1_yVel.setBounds(554, 54, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_1_yVel);
		
		JEditorPane BallOBJ_1_xPos = new JEditorPane();
		BallOBJ_1_xPos.setText("0");
		BallOBJ_1_xPos.setBounds(631, 54, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_1_xPos);
		
		JEditorPane BallOBJ_1_yPos = new JEditorPane();
		BallOBJ_1_yPos.setText("0");
		BallOBJ_1_yPos.setBounds(702, 54, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_1_yPos);
		
		JEditorPane BallOBJ_1_Momentum = new JEditorPane();
		BallOBJ_1_Momentum.setText("0");
		BallOBJ_1_Momentum.setBounds(781, 54, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_1_Momentum);
		
		JEditorPane editorPane_5 = new JEditorPane();
		editorPane_5.setText("0");
		editorPane_5.setBounds(853, 54, 51, 20);
		Btm_Panel_Dock.add(editorPane_5);
		
		JEditorPane BallOBJ_2_Momentum = new JEditorPane();
		BallOBJ_2_Momentum.setText("0");
		BallOBJ_2_Momentum.setBounds(781, 79, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_2_Momentum);
		
		JEditorPane BallOBJ_2_Ek = new JEditorPane();
		BallOBJ_2_Ek.setText("0");
		BallOBJ_2_Ek.setBounds(853, 79, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_2_Ek);
		
		JEditorPane BallOBJ_2_yPos = new JEditorPane();
		BallOBJ_2_yPos.setText("0");
		BallOBJ_2_yPos.setBounds(702, 79, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_2_yPos);
		
		JEditorPane BallOBJ_2_xPos = new JEditorPane();
		BallOBJ_2_xPos.setText("0");
		BallOBJ_2_xPos.setBounds(631, 79, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_2_xPos);
		
		JEditorPane BallOBJ_2_yVel = new JEditorPane();
		BallOBJ_2_yVel.setText("0");
		BallOBJ_2_yVel.setBounds(554, 79, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_2_yVel);
		
		JEditorPane BallOBJ_2_xVel = new JEditorPane();
		BallOBJ_2_xVel.setText("0");
		BallOBJ_2_xVel.setBounds(473, 79, 51, 20);
		Btm_Panel_Dock.add(BallOBJ_2_xVel);
		
		
		
		System.out.println("Height of frame: "+ Frame_Height +"px, Width: "+ Frame_Width +"px");
		double Sim_timer = set_Time( Sim_timer_parameter );
		tabbedPane.setBounds(0, 0, 929, 557);
		frmCollisionSimulator.getContentPane().add(tabbedPane);
		
		// Introduction page/tab labels & panel //
		JPanel Intro = new JPanel();
		Intro.setBackground(SystemColor.textHighlightText);
		tabbedPane.addTab("Introduction", null, Intro, "Introductory message to users");
		tabbedPane.setBackgroundAt(0, SystemColor.activeCaption);
		Intro.setLayout(null);
		
		Label label = new Label("This is a computing project created for a A-Level Computer science coursework at Highdown School & Sixth Form Center");
		label.setAlignment(Label.CENTER);
		label.setBounds(138, 43, 712, 13);
		Intro.add(label);
		
		Label Intro_title = new Label("Welcome to Collision Simulator");
		Intro_title.setBounds(295, 5, 400, 41);
		Intro_title.setFont(new Font("Century Gothic", Font.PLAIN, 28));
		Intro.add(Intro_title);
		
		JLabel IntroPhoto = new JLabel("");
		IntroPhoto.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Intro photo.png"));
		IntroPhoto.setBounds(300, 70, 310, 310);
			Intro.add(IntroPhoto); // Adds the introduction picture into the intro tab
			
					// Add the actual simulation code // 
		//	Stats stats = new Stats();
					gui balls = new gui(); //Instantiates class gui & adds into JPanel
			    
					balls.setForeground(new Color(0, 0, 0));
					
					tabbedPane.addTab("Collision Simulation", new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Collision sim logo.png"), balls, "View the collision simulation");
					tabbedPane.setBackgroundAt(1, new Color(124, 252, 0));
					
						balls.setFocusable(true);
						balls.requestFocus(true);
						
									balls.addKeyListener(new KeyAdapter() {
										@Override
										public void keyTyped(KeyEvent e) {
										
											  if(gui.isRunning == true && e.getKeyCode() == KeyEvent.VK_P){
											 System.out.println("Pausing sim. . .");
											  }
										}
									});
									balls.addMouseListener(new MouseAdapter() { // adds a mouse event listener to the simulation frame panel
								// all mouse related events inside the simulation frame panel is included here
										@Override
										public void mousePressed(MouseEvent e) {
											if(touching_mouse == true){
												gui.ball_obj.get(ball.touching_ball).setX(e.getX());
												gui.ball_obj.get(ball.touching_ball).setY(e.getY());
											}
											if(chckbxNewCheckBox.isSelected() == true){
											 for (int i = 0 ; i <= gui.ball_obj.size()-1;i++ ) {
										    	 double deltaX = (gui.ball_obj.get(i).getX()+gui.ball_obj.get(i).getDiameter()-gui.getMousePositionX());
										    	 double deltaY = (gui.ball_obj.get(i).getY()+gui.ball_obj.get(i).getDiameter()-gui.getMousePositionY());
												 // double deltaY = (gui.getMousePositionY()-gui.ball_obj.get(0).getY());
												  double distance_frm_mouse = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
												  System.out.println("dist between mouse & ball_obj["+ i +"]: " + distance_frm_mouse +"px");
												  while(distance_frm_mouse <= 0 + gui.ball_obj.get(i).getDiameter()/2 && chckbxNewCheckBox.isSelected() == true)  {
													 System.out.println("touching ball [" + i + "]");
													 gui.ball_obj.get(i).setX(gui.getMousePositionX() -120);
													 gui.ball_obj.get(i).setY(gui.getMousePositionY() - 120);
													CollisionSimulator.SelectedIndex = i;
												  }
										}
											}
									
										
										
										}
										
										// Code for all the extra features such as toggle add vector, gravity fields, etc... //
										public void mouseClicked(MouseEvent e) { // When mouse is clicked
											
											
											if( ADD_OBJ_TOGGLE.isSelected()==true  && get_OBJ_Count() <=4){ // only instantiate a new object if there are less than or equal to 5 objects already in game
												
												 gui.ball_obj.add(new ball("OBJ_1",e.getX(),e.getY(),(int) gui.RandDiameter(),1,gui.Ek,Color.red)); // instantiates new ball object with its parameters defined
													// x, y position is set to mouse pointer so it will spawn where user clicks
													System.out.println(OBJ_Count + " Object has been added to the simulation!");
											}else {
												if(get_OBJ_Count() >=5 && ADD_OBJ_TOGGLE.isSelected()==true){ // if toggle add button is enabled & mouse is clicked
												System.out.println("THE MAX OBJECT LIMIT HAS BEEN REACHED"); // gives error message saying max object limit has beenr eached
												  JOptionPane.showMessageDialog(null, "MAX NUMBER OF OBJECT HAS BEEN REACHED!", "Error Message",
                              JOptionPane.ERROR_MESSAGE);
												}
												}
											if(tglbtnAddVectorxy.isSelected()==true){ 
												Show_ADD_Vector = true;
												} else
													Show_ADD_Vector = false;
											System.out.println("Calculating new VECTOR component..."); //Changes X,Y vector component of ball obj such that it heads towards mouse click origin
											if(TogglePGrav.isSelected()==false && ToggleNGrav.isSelected()==false && get_OBJ_Count() > 0 && ADD_OBJ_TOGGLE.isSelected()==false && tglbtnAddVectorxy.isSelected()==true ){
											
												// code for calculating the new x, y vector component for the ball object to head towards the mouse click
											gui.ball_obj.get(0).setyVelocity((e.getY() - gui.ball_obj.get(0).getY())/10); // calculates the difference in x position between mouse & the ball object (0) & sets it to the x velocity
											gui.ball_obj.get(0).setxVelocity((e.getX() - gui.ball_obj.get(0).getX())/10); // calculates the difference in y position between mouse & the ball object (0) & sets it to the y velocity
											}
	if (TogglePGrav.isSelected()==true && ToggleNGrav.isSelected()==false && OBJ_Count > 0  && ADD_OBJ_TOGGLE.isSelected()==false && tglbtnAddVectorxy.isSelected()==false ){
									// if positive gravity is gravity toggle is the ONLY one enabled then...
									for (int i = 0; i<= gui.ball_obj.size()-1; i++){
										if((e.getY() - gui.ball_obj.get(i).getY()>150)){//Sets range of Positive gravity field e.g. if distance is more than 150, it won't pull in the ball objects
									// if out of range, keep the x, y component of the ball objects outside the range the same
											gui.ball_obj.get(i).setyVelocity(gui.ball_obj.get(i).getyVelocity());
											gui.ball_obj.get(i).setxVelocity(gui.ball_obj.get(i).getxVelocity());
										} else{
											// Same code as addVectorXY code but this will set the new x, y component to ALL of the ball objects currenrtly in the simulation
										gui.ball_obj.get(i).setyVelocity((e.getY() - gui.ball_obj.get(i).getY())/10);
										gui.ball_obj.get(i).setxVelocity((e.getX() - gui.ball_obj.get(i).getX())/10);
										}
										}
									} 
									if (ToggleNGrav.isSelected()==true && TogglePGrav.isSelected()==false && OBJ_Count > 0  && ADD_OBJ_TOGGLE.isSelected()==false && tglbtnAddVectorxy.isSelected()==false ){
										for (int i = 0; i<= gui.ball_obj.size()-1; i++){
											if((e.getY() - gui.ball_obj.get(i).getY()>150)){//Sets range of negative gravity field
												gui.ball_obj.get(i).setyVelocity(gui.ball_obj.get(i).getyVelocity());
												gui.ball_obj.get(i).setxVelocity(gui.ball_obj.get(i).getxVelocity());
											} else{
												// same code as positive gravity but negative sign is added so that the objects will head the opposite direciton from the mouse click
											gui.ball_obj.get(i).setyVelocity(-(e.getY() - gui.ball_obj.get(i).getY())/10);
											gui.ball_obj.get(i).setxVelocity(-(e.getX() - gui.ball_obj.get(i).getX())/10);
											}
											}
									}
											
											
										}
									
									});
									balls.setBackground(new Color(153, 255, 255));
									
									balls.setLayout(null);
									Frame_Width = balls.getWidth();
									Frame_Height = balls.getHeight();
									
									JLabel lblInfo = new JLabel("");
									//	lblInfo.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\INFO_BTN.png"));
										lblInfo.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent e) {
												Show_info = true;
												System.out.println("Showing Info");
												
											}
										});
										lblInfo.setBounds(885, 11, 32, 30);
										balls.add(lblInfo);
										
						
										
								
			
	
			
			JPanel InformationPanelTab = new JPanel(); // Creates a new tab called InformationPanelTab
			InformationPanelTab.setBackground(SystemColor.window);
			tabbedPane.addTab("Info (for physics geeks)", new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\INFO_BTN.png"), InformationPanelTab, "Learn about the theory"); // Sets the title of the tab & it's background design
			
			JLabel Info_info = new JLabel("");
			Info_info.setLabelFor(InformationPanelTab);
			Info_info.setIcon(new ImageIcon("D:\\A-LEVEL FOLDER\\COMPUTING\\Dr. Stephenson\\Coursework\\Assets\\Information Info.jpg"));
			Info_info.setBounds(0, 0, 310, 310);
				InformationPanelTab.add(Info_info); // Add it into the InformationalPanelTab so it's only visible when that specific tab is active
			tabbedPane.setBackgroundAt(2, SystemColor.inactiveCaption);
			
			JPanel panel = new JPanel(); // Instantiates the JPanel that contains the simulation code
			panel.setLayout(null);
			panel.setBackground(Color.WHITE);
			tabbedPane.addTab("New tab", null, panel, null);
			
			Label label_3 = new Label("This is a computing project created for a A-Level Computer science coursework at Highdown School & Sixth Form Center");
			label_3.setAlignment(Label.LEFT);
			
			JCheckBox GhostParticleCheckBox = new JCheckBox("Ghost particles"); // Instantiates a new JCheckBox object 
			GhostParticleCheckBox.addMouseListener(new MouseAdapter() {
				@Override
				
				public void mouseClicked(MouseEvent e) {
					if(GhostParticleCheckBox.isSelected() == true){ // If this check box is clicked on & is ticked
						gui.ghostParticles = true; // Set the bool var ghost particles in the class gui 
					} else {
						gui.ghostParticles = false; // if not ticked, then set the bool var to false
					}
				}
			});
			GhostParticleCheckBox.setBackground(SystemColor.activeCaption);
			GhostParticleCheckBox.setBounds(10, 439, 150, 23);
			R_Panel_Dock.add(GhostParticleCheckBox);
			label_3.setBounds(138, 43, 712, 13);
			panel.add(label_3);
			
			Label label_4 = new Label("Welcome to Collision Simulator"); // Introduction message to the user in the Introduction tab
			label_4.setFont(new Font("Century Gothic", Font.PLAIN, 28));
			label_4.setBounds(295, 5, 400, 41);
			panel.add(label_4);
			
			JLabel label_5 = new JLabel("");
			label_5.setBounds(300, 70, 310, 310);
			panel.add(label_5);

		
		
	}
	
// Methods used in this class 
// Pause sim signal, Like the broadcast block used in scratch 
	private static void Pause_Sim (boolean Pause_State){
		if (Pause_State == true) {
			System.out.println("PAUSING SIM...");
			gui.PauseSim();
		
		}
	}
	//Resume sim signal
	private static void Resume_Sim (boolean Pause_State){
		if (Pause_State == false) {
			System.out.println("RESUMING SIM...");
			gui.ResumeSim();
		}
	}	
			
	boolean Return_Sim_State (boolean Pause_State){
		return Pause_State;
	}
	
	
	private static double set_Time  (double Sim_timer_parameter) {
		if (Pause_State == false) {
				try {
					Sim_timer_parameter = Sim_timer_parameter + 0.01;
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return Sim_timer_parameter;
	}

	
	private double get_Time() {
		
		return 0;
	}

	public void Set_Sim_Speed(int SIM_Speed) {
		 this.SIM_Speed = SIM_Speed;
	}

	public static int get_Sim_Speed() {
		return SIM_Speed;
	}
	public static int get_OBJ_Count(){
		OBJ_Count = gui.ball_obj.size();
		return OBJ_Count;
	}
	public static int get_OBJ_1_size(){
		return obj_1_size-1;
		
	}
	
	public int getMousePositionX() {
		int mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX(); // retrieves the x posititon of the mouse

		return mouseX;

		}

	public static double getElasticConst(){
		return Elasticity_Constant;

	}
	public static void updateBallOBJ_0_xVel(){

	}
}


