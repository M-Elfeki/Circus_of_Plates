import java.awt.Color;
import java.awt.Polygon;
import java.util.Random;


public class Shape  
{
	public static final int ONPENDING= 0;
	public static final int FALLING = 1;
	public static final int RIGHTSHELF = 2;
	public static final int LEFTSHELF = 3;
	public static final int PERSONONE = 4;
	public static final int PERSONTWO = 5;
	
	public static double width;
	public static double height;
	public double angle ;
	public double xPos;
	public double yPos;
	public Color  color =Color.red;
	public int speed=1;
	public int state = 0; 
	public int direction =-100 ;
	private int om ;
	
	public void setState(int s)
	{
		state = s;
	}
	public double getAngle()
	{
		return angle;
	}
	
	public void setAngle(double r)
	{
		angle = r;
	}
	
	public int observer()
	{
		return state;
	}
	
	public void setDirection()
	{
		 Random randomGenerator = new Random();
		 int i = randomGenerator.nextInt(3);
		if(direction==-100)
		{
			direction=i==2?-1:i;
		}
	}
	
	public void setDirection(int i)
	{
		 direction = i;
	}
	
	public int getDirection()
	{
		return direction;
	}
 
	public double getxPos() {
		return xPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setSpeed()
	{
		if(speed==1)
		{
			Random randomGenerator = new Random();
			 int i = randomGenerator.nextInt(6);
			 speed=i;
		}
	}
	public void setSpeed(int i)
	{
		speed = i;
	}
	public int getSpeed()
	{
		return speed;
	}
	
	public void falldown()
	{
		this.yPos+=speed;
	}
	
	public void moveOnShelf()
	{
		this.xPos+=speed;
	}
	
	public void waiting()
	{
		this.xPos = 20;
		this.yPos = 60;
	}
	//Need collected&&onPending
	
	  
	public Shape()
	{
		 
	}
	
	public Shape(double xPos,double yPos, Color color) {  
		this.xPos   = xPos  ;
		this.yPos   = yPos  ;
		this.color  = color ;
	} 
	 
	public double[][] getVertix ()
	{
		double arr[][]=new double[4][2];
		arr[0][0]=xPos;
		arr[0][1]=yPos;
		
		arr[1][0]=xPos+width;
		arr[1][1]=yPos;
		
		double x=0;
		if(angle!=90)
			x = (double)height/(double)Math.tan(angle);
		
		arr[2][0]=xPos+x;
		arr[2][1]=yPos+height;
		
		arr[3][0]=xPos+width-x;
		arr[3][1]=yPos+height;
		
		return arr;
	}
	
	public Polygon getPoly()
	{
		Polygon p =new Polygon();
		double[][] arr=getVertix();
		p.xpoints[2]=(int) arr[0][0];
		p.xpoints[3]=(int) arr[1][0];
		p.xpoints[1]=(int) arr[2][0];
		p.xpoints[0]=(int) arr[3][0];
		p.ypoints[2]=(int) arr[0][1];
		p.ypoints[3]=(int) arr[1][1];
		p.ypoints[1]=(int) arr[2][1];
		p.ypoints[0]=(int) arr[3][1];
		p.npoints=4;
		return p ;
		
	}
	  
	
	public int getOm() { 
		return om;
	}

} 
