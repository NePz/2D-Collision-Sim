
public class Game_Obj {
private int Size;
private int Mass;
private String Material;

public Game_Obj(int Size,int Mass,String Material){
this.setSize(Size);
}
public int getSize(){//Retrieves value of var Size
	return Size; //Does the retrieving
}
public void setSize(int Size){//Setting the size of ball
	this.Size = 10; 
}

	
}
