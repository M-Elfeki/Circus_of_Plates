import java.awt.Color;
import java.awt.Polygon;


public class Square extends Shape   {
	

	public static  double width = 60  ;
	public static  double height= 20 ;
	public static  double angle = 90 ;
	private double xPos  ;
	private double yPos  ;
	private Color  color ;
	  

	
	@Override
	public double getxPos() {
		return xPos;
	}
	
	@Override
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	
	@Override
	public double getyPos() {
		return yPos;
	}
	
	@Override
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	
	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	public Square()
	{ 
		
	}
	
	public void iSquare(double xPos,double yPos, Color color) { 
		this.xPos   = xPos  ;
		this.yPos   = yPos  ;
		this.color  = color ;  
		
	}
	
	@Override
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
	 
	
	
	
} 
