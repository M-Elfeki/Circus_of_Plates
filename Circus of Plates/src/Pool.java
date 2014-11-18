import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Pool implements PoolInterface{
	
	 public  int con = 0;
	 private static Queue<Shape>q;
	 private static Color array[]={Color.black,Color.blue,Color.green,Color.yellow,Color.CYAN,Color.orange,Color.GRAY};
	 
	 public Pool ()
	 {
		 q=new LinkedList<Shape>();
	 }
	 @Override
	 public Shape borrow() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	 {
		if(q.size()>0)
		{ 
			Shape a = q.poll();
			int nx =con; 
			int ny = 80; 
			Random randomGenerator = new Random();
			int i = randomGenerator.nextInt(array.length);
			Color co=array[i];
			a.setxPos(nx);
			a.setyPos(ny);
			a.setColor(co);
			return a;
		}
		return createShape();
		
	 }
	 private Shape createShape() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	 {
		 
		 Random randomGenerator = new Random();
		 int i = randomGenerator.nextInt(array.length);
		 Color co=array[i];
		  
		 int i2 = randomGenerator.nextInt(Control.classList.size());
		 
		 String name = Control.classList.get(i2).getName(); 
		 
		 int nx =0; 
		 int ny = 80; 
		 return Factory.createShape(Control.classList.get(i2), Control.classList.get(i2).getName(), nx,ny , co);
	 }
	 @Override
	 public void add (Shape a)
	 {
		 q.add(a);
	 }

}
