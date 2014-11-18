import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

public class Control {

	static Person p1;
	static Person p2;
	static Shelf s;
	static Pool p;
	public static View v;
	
	public static ArrayList<Class> classList;
	int xPoint[] = { 0, 800 };
	int YPoint[] = { 90, 140 };
	public static Iterator all;
	public static Iterator left;
	public static Iterator right;
	
	public static Iterator p1Stick;
	public static Iterator p2Stick;
	
	
	public static boolean oop = false;
	public static int fix=0;
	public static void run() throws InterruptedException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
	   
		int i = 1 ;  
		while(p1Stick.hasNext())
		{
			p1Stick.getElement().setxPos(Control.p1.getX()+18);
			p1Stick.getElement().setyPos(Control.p1.getY()+p1Stick.getElement().height-(25*i));
			Shape obj = p1Stick.getElement();
			i++;
			p1Stick.getElement().setState(Shape.PERSONONE);
		}
		i=1;
		while(p2Stick.hasNext())
		{
			p2Stick.getElement().setxPos(Control.p2.getX()+18);
			p2Stick.getElement().setyPos(Control.p2.getY()-p2Stick.getElement().height-(25*i));
			Shape obj = p2Stick.getElement(); 
			i++;
			p2Stick.getElement().setState(Shape.PERSONTWO);
		} 
		
		while (left.hasNext()) { 
			if(oop==false)
			{
				fix++;
				oop=true;
			}
			int now = (int) (left.getElement().getxPos() +1); 
			
			if (now >= 560) 
			{
				left.getElement().setDirection();
				left.getElement().setSpeed(); 
				int d = left.getElement().getDirection();
				left.getElement().setyPos(left.getElement().getyPos() );
				left.getElement().setxPos(left.getElement().getxPos()+d); 
				left.getElement().setState(Shape.FALLING);
				all.addShape(left.getElement());
				left.removeShape(left.getElement());
				 
			} else { 
				left.getElement().setxPos(left.getElement().getxPos() + 1);
				left.getElement().setState(Shape.LEFTSHELF);
			}

		} 
		oop=false; 
		while (right.hasNext()) {
			int now = (int) (right.getElement().getxPos() -1);
			
			if (now <= 660) {
				
				right.getElement().setDirection();
				right.getElement().setSpeed();
				//View.contents.add(right.getElement());
				int d = right.getElement().getDirection();
				right.getElement().setyPos(right.getElement().getyPos() + 1);
				right.getElement().setxPos(right.getElement().getxPos()+d); 
				right.getElement().setState(Shape.FALLING);
				all.addShape(right.getElement());
				right.removeShape(right.getElement());
				
				
			} else { 
				right.getElement().setxPos(right.getElement().getxPos() - 1);
				right.getElement().setState(Shape.RIGHTSHELF);
			} 
		} 
		while(all.hasNext())
		{ 
			if(p1.getStickHeight()>300)
			{
				JOptionPane.showMessageDialog(null, "Game Over\nPlayer Two Wins");
				System.exit(0);
			}
			else if(p2.getStickHeight()>300)
			{
				JOptionPane.showMessageDialog(null, "Game Over\nPlayer One Wins");
				System.exit(0);
			}
			all.getElement().setyPos(all.getElement().getyPos() + 1); 
			//View.contents.add(all.getElement());
			all.getElement().setxPos(all.getElement().getxPos()+all.getElement().getDirection());
			int nowY=(int) (all.getElement().getyPos());
			
			int h1 = 500-(p1.getStickHeight());
			int h2 = 500-(p2.getStickHeight());
			 
			if(nowY>=650)
			{ 
				all.getElement().setState(Shape.ONPENDING);
				p.add(all.getElement());
				all.removeShape(all.getElement());
				p1.modifySequence();p2.modifySequence();
				continue;
			} 
			int indexx=(int) (all.getElement().getyPos()+20);
			
			if((indexx+20==h1))
			{  
				int _state=getState1(all.getElement());
				if(_state==1)
				{
						all.getElement().setState(Shape.PERSONONE);
						all.getElement().setxPos(p1.getX());
						p1Stick.addShape(all.getElement());	 
						all.removeShape(all.getElement());
						p1.modifySequence();p2.modifySequence();
				}
			}
			else
			{
				all.getElement().setState(Shape.FALLING);
			}
			 
			if(indexx+20==h2) 
			{
				int _state=getState2(all.getElement());
				if (_state==1)
				{
						all.getElement().setState(Shape.PERSONTWO);
						all.getElement().setxPos(p2.getX());
						p2Stick.addShape(all.getElement());	  
						all.removeShape(all.getElement());
						p1.modifySequence();p2.modifySequence();
				} 
			}
			else
			{
				all.getElement().setState(Shape.FALLING);
			}
		}
		
		boolean res =Control.removeRepetition1(); 
		if(res)
		{
			Shape arr[]=new Shape[200];
			int s1 = p1.leftStick.size();
			Stack<Shape>sta= p1.leftStick;
			p1Stick= new Iterator();
			for(int ii=0;ii<s1;ii++)
			{ 
				arr[ii]=sta.pop();
				p1Stick.addShape( arr[ii]);
			}
			for(int ii=s1-1;ii>=0;ii--)
			{
				sta.push(arr[ii]);
			}
		}
		
		res =Control.removeRepetition2();
		if(res)
		{
			Shape arr[]=new Shape[200];
			int s2 = p2.leftStick.size();   
			Stack<Shape>staa= p2.leftStick;
			p2Stick= new Iterator();
			for(int ii=0;ii<s2;ii++)
			{ 
				arr[ii]=staa.pop();
				p2Stick.addShape(arr[ii]);
			}
			for(int ii=s2-1;ii>=0;ii--)
			{
				staa.push(arr[ii]);
			}
			
		}
		
		//View.contents.add(right.getElement());
		
		if(fix>60)
		{ 
			fix=0; 
			Shape a = p.borrow();
			Shape b = p.borrow();
			a.setxPos(0);
			b.setxPos(1200);
			left.addShape(a); 
			right.addShape(b);
		}				
	}
	
	public static int getState1(Shape obj) throws InterruptedException
	{  
		if(p1.leftStick.size()==0)
		 {
			if((obj.getxPos()+obj.getOm())>=p1.getX()&&((obj.getxPos()+10))<=(p1.getX()+100) )
			{  
				p1.pushLeft(obj);
				return 1;
			} 
		}
		else
		{  
			
			Shape aa =p1.popLeft();
			p1.pushLeft(aa);
			int x1 = (int) aa.getxPos();
			int x2 = (int) (aa.getxPos()+60);
			int x  = (int) (obj.getxPos()+10); 
			int xx = (int) (obj.getxPos()+50);
			if(x>=x1&&x<=x2)
			{ 
				p1.pushLeft(obj);
				return 1;
			}
			if(xx>=x1&&xx<=x2)
			{
				p1.pushLeft(obj);
				return 1;
			}
		} 
		 return 0;
	}
	
	public static int getState2(Shape obj) throws InterruptedException
	{  
		 
		 if(p2.leftStick.size()==0)
		 {
			if((obj.getxPos()+obj.getOm())>=p2.getX()&&((obj.getxPos()+10))<=(p2.getX()+100) )
			{  
				p2.pushLeft(obj);
				return 1;
			} 
		}
		else
		{  
			
			Shape aa =p2.popLeft();
			
			p2.pushLeft(aa);
			int x1 = (int) aa.getxPos();
			int x2 = (int) (aa.getxPos()+60);
			int x  = (int) (obj.getxPos()+10); 
			int xx = (int) (obj.getxPos()+50);
			if(x>=x1&&x<=x2)
			{   
				p2.pushLeft(obj);
				return 1;
			}
			if(xx>=x1&&xx<=x2)
			{
				p2.pushLeft(obj);
				return 1;
			}
		} 
		 return 0;
	}
	
	public static boolean removeRepetition1() throws InterruptedException
	{ 
		if(p1.leftStick.size()>2)
		{  
			Shape a=p1.leftStick.pop();
			Shape b=p1.leftStick.pop();
			Shape c=p1.leftStick.peek();
			if(a.getColor().equals(b.getColor())&&b.getColor().equals(c.getColor()))
			{
				p1.leftStick.pop();
				p.add(a);
				p.add(b);
				p.add(c);
				View.scoreOne++;
				View.score_1.setText("Player 1 : "+Integer.toString(View.scoreOne)+"    ");
				return true;
			}
			else
			{
				p1.leftStick.push(b);
				p1.leftStick.push(a); 
			}
		}
		return false;
	}
	
	public static boolean removeRepetition2() throws InterruptedException
	{ 
		if(p2.leftStick.size()>2)
		{ 
			Shape a=p2.leftStick.pop();
			Shape b=p2.leftStick.pop();
			Shape c=p2.leftStick.peek();
			if(a.getColor().equals(b.getColor())&&b.getColor().equals(c.getColor()))
			{ 
				p2.leftStick.pop();
				p.add(a);
				p.add(b);
				p.add(c);
				View.scoreTwo++;
				View.score_2.setText("Player 2 : "+Integer.toString(View.scoreTwo)+"    ");
				return true;
			}
			else
			{
				p2.leftStick.push(b);
				p2.leftStick.push(a);
			} 
		}
		return false;
	}
	   
	
	public Control() throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InterruptedException {
		classList = new ArrayList<Class>();
		p = new Pool();
//		GraphicsEnvironment ge = 
//	            GraphicsEnvironment.getLocalGraphicsEnvironment();
//	        GraphicsDevice gd = ge.getDefaultScreenDevice();
//	        
//	        JFrame.setDefaultLookAndFeelDecorated(true);
//
//	        SwingUtilities.invokeLater(new Runnable() {
//	            @Override
//	            public void run() {
//	            	v = new View();	
//	                v.setOpacity(0.1000f);
//	                v.setVisible(true);
//	            }
//	        });
		v = new View();
		p1 = new Person();
		p2 = new Person();
		s = Shelf.getInstance();
		all = new Iterator();
		right = new Iterator();
		left = new Iterator(); 
		
		p1Stick=new Iterator();
		p2Stick=new Iterator();
		
		while(classList.size()==0)
		{ 
			Thread.sleep(15);
		} 
		
		Shape a1,b1;
		a1=p.borrow();
		b1=p.borrow();
		a1.setxPos(0);
		b1.setxPos(1200);
		left.addShape(a1); 
		right.addShape(b1); 
		v.setCurrentDrawing(left,right,all,p1Stick,p2Stick); 
		Thread.sleep(15);
		new moveObject();
	}

	public static void main(String[] args) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InterruptedException {

		new Control();
	}
}

class moveObject implements Runnable {
	Thread t;
	int i = 100000;

	moveObject() {
		t = new Thread(this, "Demo Thread");
		t.start();
	}

	public void run() {
		while (i >= 0) {
			try {
				Control.run();  
				Control.left.position=0;
				Control.right.position=0;
				Control.v.setCurrentDrawing(Control.left,Control.right,Control.all,Control.p1Stick,Control.p2Stick); 
				i--;
				Thread.sleep(9);
			} catch (Exception ex) {
			}
		}
	}
}