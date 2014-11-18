import java.lang.reflect.InvocationTargetException;


public interface PoolInterface {
	public void add(Shape a);
	public Shape borrow() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}
