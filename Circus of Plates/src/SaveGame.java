import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class SaveGame
{
	public void saveBoard(ArrayList<Shape> ob) throws ParserConfigurationException, TransformerException, IOException 
	{
        String path = JOptionPane.showInputDialog("Please Enter the File Name");
		
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		
		Element rootElement = doc.createElement("Game");
		doc.appendChild(rootElement);
		
		Element generalData = doc.createElement("generalData");
		rootElement.appendChild(generalData);
		
		Element score_1 = doc.createElement("score1");
		score_1.appendChild(doc.createTextNode(Integer.toString(View.scoreOne)));
		generalData.appendChild(score_1);
		
		Element score_2 = doc.createElement("score2");
		score_2.appendChild(doc.createTextNode(Integer.toString(View.contents.size())));
		generalData.appendChild(score_2);
		
		Element time = doc.createElement("time");
		time.appendChild(doc.createTextNode(Integer.toString(View.time)));
		generalData.appendChild(time);
		
		
		Element cordX = doc.createElement("cordX");
		cordX.appendChild(doc.createTextNode(Integer.toString(View.cordX)));
		generalData.appendChild(cordX);
		
		Element cordX2 = doc.createElement("cordX2");
		cordX2.appendChild(doc.createTextNode(Integer.toString(View.cordX2)));
		generalData.appendChild(cordX2);
		
		Element numberOfShapes = doc.createElement("shapesNo");
		System.out.println(View.contents.size());
		numberOfShapes.appendChild(doc.createTextNode(Integer.toString(View.contents.size())));
		generalData.appendChild(numberOfShapes);
		
		Element shapes = doc.createElement("shapes");
		rootElement.appendChild(shapes);
		
		for(int i =0;i<View.contents.size();i++)
		{
			Element shape = doc.createElement("shape"+Integer.toString(i));
			
			Element angle = doc.createElement("angle");
			angle.appendChild(doc.createTextNode(Double.toString(View.contents.get(i).getAngle())));
			shape.appendChild(angle);
			
			Element xPos = doc.createElement("xPos");
			xPos.appendChild(doc.createTextNode(Double.toString(View.contents.get(i).getxPos())));
			shape.appendChild(xPos);
			
			Element yPos = doc.createElement("yPos");
			yPos.appendChild(doc.createTextNode(Double.toString(View.contents.get(i).getyPos())));
			shape.appendChild(yPos);
			
			Element speed = doc.createElement("speed");
			speed.appendChild(doc.createTextNode(Double.toString(View.contents.get(i).getSpeed())));
			shape.appendChild(speed);
			
			Element state = doc.createElement("state");
			state.appendChild(doc.createTextNode(Double.toString(View.contents.get(i).observer())));
			shape.appendChild(state);
			
			Element direction = doc.createElement("direction");
			direction.appendChild(doc.createTextNode(Double.toString(View.contents.get(i).getDirection())));
			shape.appendChild(direction);
			
			Element color = doc.createElement("color");
			color.appendChild(doc.createTextNode((View.contents.get(i).getColor()).toString()));
			shape.appendChild(color);

			shapes.appendChild(shape);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
		JOptionPane.showMessageDialog(null, "Your File has been successfully Saved");
	}
	
	public void loadBoard(String path) throws ParserConfigurationException, SAXException, IOException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException
	{ 
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		
		int numberOfShapes =0;
		NodeList rList = doc.getElementsByTagName("generalData");
		System.out.println( rList.getLength());
		for (int temp = 0; temp < rList.getLength(); temp++) 
		{	 
			Node nNode = rList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element eElement = (Element) nNode;
				View.score_1.setText("Player 1 : "+eElement.getElementsByTagName("score1").item(0).getTextContent());
				View.score_2.setText("Player 2 : "+eElement.getElementsByTagName("score2").item(0).getTextContent());
				View.time = Integer.parseInt(eElement.getElementsByTagName("time").item(0).getTextContent());
				View.timer.setText("Time   :  "+eElement.getElementsByTagName("time").item(0).getTextContent()+"    ");
				View.cordX = Integer.parseInt(eElement.getElementsByTagName("cordX").item(0).getTextContent());
				View.cordX2 = Integer.parseInt(eElement.getElementsByTagName("cordX2").item(0).getTextContent());
				Control.p1=new Person();
				Control.p2=new Person();
				Control.p1.setX(View.cordX);
				Control.p2.setX(View.cordX2);
				
				try{numberOfShapes = Integer.parseInt(eElement.getElementsByTagName("shapesNo").item(0).getTextContent());}catch(Exception ex){}
			}
		}
		System.out.println(numberOfShapes);
		View.contents.clear();
		for(int k = 0; k<numberOfShapes;k++)
		{
			NodeList nList = doc.getElementsByTagName("shape"+Integer.toString(k));
		    if(nList.getLength()>0){
		    for (int temp = 0; temp < nList.getLength(); temp++) 
		    {	 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					
					Element eElement = (Element) nNode;
					
					int x = 0;try{x=(int) Double.parseDouble(eElement.getElementsByTagName("xPos").item(0).getTextContent());}catch(Exception ex){}
					
					int y = 0;try{y=(int) Double.parseDouble(eElement.getElementsByTagName("yPos").item(0).getTextContent());}catch(Exception ex){}
					int angle = 0;try{angle=(int) Double.parseDouble(eElement.getElementsByTagName("angle").item(0).getTextContent());}catch(Exception ex){}
					int speed = 0;try{speed =(int) Double.parseDouble(eElement.getElementsByTagName("speed").item(0).getTextContent());}catch(Exception ex){}
					int state = 0;try{state = (int) Double.parseDouble(eElement.getElementsByTagName("state").item(0).getTextContent());}catch(Exception ex){System.out.println(eElement.getElementsByTagName("state").item(0).getTextContent());}
					int direction = 0;try{direction = (int) Double.parseDouble(eElement.getElementsByTagName("direction").item(0).getTextContent());}catch(Exception ex){}
					String color="";try{color = eElement.getElementsByTagName("Color").item(0).getTextContent();}catch(Exception ex){}
					if(color!="")
					{
						String colors = color.substring(17);
				    	String colors1=colors.replaceAll("g=", "");
				    	String colors2=colors1.replaceAll("b=", "");
				    	String colors3=colors2.replaceAll("]", "");
				    	String [] z = colors3.split(",");
				    	int[] colorsArray = new int[3];
				    	for(int j=0;j<z.length;j++)colorsArray[j] = Integer.parseInt(z[j]);
				    	Color u = new Color(colorsArray[0], colorsArray[1], colorsArray[2]);
					}
										
			    	try{
			    		Shape s = new Shape();
			    		s.setxPos(x);
			    		s.setyPos(y);
			    		s.setAngle(angle);
			    		s.setSpeed(speed);
			    		s.setState(state);
			    		s.setDirection(direction);
			    		View.contents.add(s);
			    		
		    		}catch(Exception ex){}			

				}
			}
		}
		}
		for(int i=0;i<View.contents.size();i++)
    	{
			System.out.println(View.contents.get(i).observer());
    		if(View.contents.get(i).state==Shape.RIGHTSHELF){
    			Control.right.addShape(View.contents.get(i));
    		}
    		
    		if(View.contents.get(i).state==Shape.LEFTSHELF)
    			Control.left.addShape(View.contents.get(i));
    		
    		if(View.contents.get(i).state==Shape.FALLING)
    			Control.all.addShape(View.contents.get(i));
    		
    		if(View.contents.get(i).state==Shape.PERSONONE)
    			Control.p1Stick.addShape(View.contents.get(i));
    		
    		if(View.contents.get(i).state==Shape.PERSONTWO)
    			Control.p2Stick.addShape(View.contents.get(i));
    	}
		new moveObject();
//		Control.run();
//		Control.v.setCurrentDrawing(Control.left,Control.right,Control.all,Control.p1Stick,Control.p2Stick); 
	}
}
