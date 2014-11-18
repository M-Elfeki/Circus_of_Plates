import java.util.ArrayList;
import java.util.List;
  

 class Iterator implements ShapeIterator {
	
	public  int position;
	private List<Shape>list;
	
	public Iterator()
	{
		list=new ArrayList<Shape>();
		position=0;
	}
	
	public void addShape(Shape s) {
		this.list.add(s);
	}
	
	public void removeShape(Shape c) {  
		this.list.remove(c);
	}
	
	@Override
	public boolean hasNext()
	{
		
		try{
			list.get(position);
		}
		catch(Exception e)
		{
			position=0;
			return false;
		}
		position ++ ;
		return true;
	}
	
	@Override
	public Shape getElement()
	{
		return list.get(position-1);
	}
	  
	
}
  