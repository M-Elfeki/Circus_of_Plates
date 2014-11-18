import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;


public class Shelf extends JPanel {
	
	
	public static int upperHeight  =600-200;//25er el frame- 150
	public static int bottomHeight =600-150;
	
	public static int upperWidth=400;
	public static int bottonWidth=300;
	
	private Queue<Shape>leftUpper  ;
	private Queue<Shape>leftbotton ;

	private Queue<Shape>rightUpper ;
	private Queue<Shape>rightbotton;
	
	private Shelf()
	{
		leftbotton=new LinkedList<Shape>();
		leftUpper=new LinkedList<Shape>();
		rightbotton=new LinkedList<Shape>();;
		rightUpper=new LinkedList<Shape>(); 
		this.repaint();
	}
	
	private  volatile static Shelf instance = null;


	//singleton method
	public static  Shelf getInstance() {
		if (instance == null) {
			synchronized (Shelf.class) {
				if (instance == null) {
					instance = new Shelf();
				}
			}
		}
		return instance;
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
		System.out.println("hii");
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawLine(10, 150, 300, 150);  
		g.drawLine(800, 150, 400, 150);
		g.drawLine(0, 200, 200, 200);
		g.drawLine(800, 200, 500, 200);

    }
	
	public void pushLeftUpper(Shape s)
	{
		this.leftUpper.add(s);
	}
	
	public void pushLeftBotton(Shape s)
	{
		this.leftbotton.add(s);
	}
	
	public void pushRightUpper(Shape s)
	{
		this.rightUpper.add(s);
	}
	
	public void pushRightBotton(Shape s)
	{
		this.rightbotton.add(s);
	} 
	
	
	
}