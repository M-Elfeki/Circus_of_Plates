import java.util.Stack;


public class Person {

	
	public Stack<Shape>leftStick ; 
	
	public static int height = 200 ;
	public static int width = 100 ;
	private int x ;
	private int y ; 
	 
	public int  getH = 500 ;
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}
 
	public Person()
	{
		leftStick =new Stack<Shape>(); 
	}
	 
	
	public int getStickHeight()
	{
		int h=0;
		for(int i =0 ;i<leftStick.size();i++)
		{
			h+=20;
		}
		return h;
	}
	
	public void modifySequence()
	{
		for(int i =0;i<leftStick.size();i++)
		{
			leftStick.get(i).setyPos(500-i*20);
		}
			 
	}
	public void pushLeft(Shape s)
	{
		this.leftStick.push(s); 
	}
	
	public Shape popLeft()
	{
		return this.leftStick.pop(); 
	}
	 
	

}
