import java.net.URL;
import java.net.URLClassLoader;

public class DynamicLinkage implements DynamicLinkageInterface {

	private String pathOfFile = "";
	private String className;

	
	public DynamicLinkage(String url) {
		this.pathOfFile = url;
	}
	
	@Override
	public 	Class invokeClass() { 
		try 
		{
			int inn=getClassName();
			URL classUrl;
			String uurl = "";
			for (int i = 0; i <= inn; i++) {
				uurl += pathOfFile.charAt(i);
			}
			classUrl = new URL(uurl);
			URL[] classUrls = { classUrl };
			URLClassLoader ucl = new URLClassLoader(classUrls); 
			return ucl.loadClass(this.className);
		} 
		catch (Exception e)
		{
			System.out.println("Class not found *");
		}
		return null;
	}
	

	private int getClassName() {
		String value = "";
		int inn = 0;
		for (int i = pathOfFile.length() - 1; i >= 0; i--) 
		{
			if (pathOfFile.charAt(i) == '\\')
			{
				inn = i;
				break;
			} 
			else if (pathOfFile.charAt(i) == '.')
			{
				value = "";
				pathOfFile = pathOfFile.trim();
			} 
			else 
			{
				value = pathOfFile.charAt(i) + value;
				pathOfFile = pathOfFile.trim();
			}
		}
		this.className= value;
		return inn ;
	}
	 
	

}
