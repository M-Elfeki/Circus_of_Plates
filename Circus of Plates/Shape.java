import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Shape {

	public static  double width ;
	public static  double height;
	public static  double angle ;
	private double xPos  ;
	private double yPos  ;
	private Color  color ;
	 
	
 
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
	 
	/*
	public Shape(double width, double height, double angle, double xPos,
			double yPos, Color color) {
		super();
		this.width  = width ;
		this.height = height;
		this.angle  = angle ;
		this.xPos   = xPos  ;
		this.yPos   = yPos  ;
		this.color  = color ;
	}
	*/
	
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
	
	public void paintShape(Graphics g){ 
		
		Polygon p =new Polygon();
		
		double[][] arr=getVertix();
		
		p.xpoints[0]=(int) arr[0][0];
		p.xpoints[1]=(int) arr[1][0];
		p.xpoints[2]=(int) arr[2][0];
		p.xpoints[3]=(int) arr[3][0];

		p.ypoints[0]=(int) arr[0][1];
		p.ypoints[1]=(int) arr[1][1];
		p.ypoints[2]=(int) arr[2][1];
		p.ypoints[3]=(int) arr[3][1];
		p.npoints=4;
		
		g.setColor(this.color); 
		g.fillPolygon(p) ; 
		
	}
	
	
} 
