import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Factory { 
		public static Shape createShape(Class myClass,String kind,double x, double y, Color c) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			if (kind.equals("Plate")){ 
				 
				Constructor cc=myClass.getConstructor(null);
				Shape s = (Shape) cc.newInstance(); 
				Method myMethod3 = myClass.getMethod("i"+kind, new Class[] {
						double.class, double.class, Color.class });
				myMethod3.invoke(s, new Object[] { x,y,c });  
				
				return s; 
			}
			else if (kind.equals("Square"))
			{
				Constructor cc=myClass.getConstructor(null);
				Shape s = (Shape) cc.newInstance(); 
				Method myMethod3 = myClass.getMethod("i"+kind, new Class[] {
						double.class, double.class, Color.class });
				myMethod3.invoke(s, new Object[] { x,y,c });  
				
				return s; 
			}
	        return null; 
	    } 
}
