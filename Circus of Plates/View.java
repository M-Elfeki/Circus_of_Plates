
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class View extends JFrame implements KeyListener, MouseMotionListener
{
	
	private BufferedImage clown, highBar, lowBar;
    public static int cordX = 50, cordX2 = 950;
    private int time, scoreOne, scoreTwo;
    public static JLabel timer ,score_1, score_2;
    private JButton loadClass, saveGame, loadGame;
    public static String url="";
    public static String pathOfFile = "file:/";
    public ArrayList<Shape> components;
	
	
	public View()
	{
    	setTitle("Cirucs of Plates");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,700);
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		private Iterator left;
		private Iterator right;
		private Iterator all;
		private Iterator print;
		
		public void setCurrentDrawing(Iterator a2,Iterator a1 ,Iterator a3)
		{
			this.left  = a1;
			this.right = a2;
			this.all   = a3;
			
			print=new Iterator();
			while(right.hasNext())
			{  
				print.addShape(right.getElement()); 
			}
			while(left.hasNext())
			{
				print.addShape(left.getElement());  
			}
				
			while(all.hasNext())
			{
				print.addShape(all.getElement()); 
			}
		}
		
		timer = new JLabel("              Timer :  "+Integer.toString(time)+"       ");
    	score_1 = new JLabel("Player 1 : "+Integer.toString(scoreOne)+"    ");
    	score_2 = new JLabel("Player 2 : "+Integer.toString(scoreTwo)+"    ");
    	loadClass = new JButton("Load Class");
    	saveGame = new JButton("Save Game");
    	loadGame = new JButton("Load Game");
    	
    	JPanel displayPanel = new JPanel(new BorderLayout());
    	JPanel displayPanel_2 = new JPanel(new BorderLayout());
    	JPanel displayPanel_3 = new JPanel(new BorderLayout());
    	displayPanel.add(timer, BorderLayout.WEST);
    	displayPanel.add(score_1, BorderLayout.CENTER);
    	displayPanel.add(score_2, BorderLayout.EAST);
    	displayPanel_2.add(loadClass, BorderLayout.WEST);
    	displayPanel_2.add(loadGame, BorderLayout.CENTER);
    	displayPanel_2.add(saveGame, BorderLayout.EAST);
    	displayPanel_3.add(displayPanel_2, BorderLayout.NORTH);
    	displayPanel_3.add(displayPanel, BorderLayout.SOUTH);
    	add(displayPanel_3);
    	
    	loadClass.addActionListener(dynamicLoading);
    	new MoveObject();
		loadImages();
	}
	
	
	public void loadImages()
	{
	    try 
	    {
	        String path_1 = "./src/clown.png";
	        String path_2 = "./src/highBar.png";
	        String path_3 = "./src/lowBar.png";
	        clown = ImageIO.read(new File(path_1));
//	        highBar = ImageIO.read(new File(path_2)); 
//	        lowBar = ImageIO.read(new File(path_3)); 
	    } catch (IOException ex) {}
	    addKeyListener(this);
	    addMouseMotionListener(this);
	}
    
    
    class MoveObject implements Runnable 
	{
		   Thread t;
		   int i =20000;
		   MoveObject() 
		   {
		      t = new Thread(this, "Demo Thread");
		      t.start(); 
		   }
		   
		   public void run() 
		   {
		      while(i>=0)
		      {
		    	  try
		    	  {
		    		  View.timer.setText("           Timer: "+Integer.toString(i)+"      ");
			    	  repaint();
			    	  i--;
			    	  Thread.sleep(50);
		    	  }catch(Exception ex){}
		      }
		   } 
	}
    
	

    ActionListener dynamicLoading = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JFileChooser fc;
			fc = new JFileChooser();
			JMenuBar mbr = new JMenuBar();
			setJMenuBar(mbr);
			JMenu file = new JMenu("FILE");
			JMenuItem p;
			file.add(p = new JMenuItem("open"));
			int returnVal = fc.showOpenDialog(View.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
							File fle = fc.getSelectedFile();
							pathOfFile += fle.getPath();
			}
		}	
	};
	
	public void paint(Graphics g)
	{
		super.paint(g);
		try
		{
			for(int i =0 ;i<components.size();i++)
			{
				g.setColor(components.get(i).getColor()); 
				g.fillPolygon(components.get(i).getPoly());
			}
		}catch(Exception ex){}
		g.drawImage(highBar, 0, 150, 400, 10, this);
		g.drawImage(highBar, 800, 150, 400, 10, this);
		g.drawImage(lowBar, 0, 250, 200, 10, this);
		g.drawImage(lowBar, 1000, 250, 200, 10, this);
		g.drawImage(clown, cordX, 490, 100, 200, this);
		g.drawImage(clown, cordX2, 490, 100, 200, this);
	}
	

	@Override
	public void keyPressed(KeyEvent ke) 
	{
		switch (ke.getKeyCode()) 
		{
	        case KeyEvent.VK_RIGHT: 
	            cordX+=3;
	            break;
	        case KeyEvent.VK_LEFT: 
	            cordX-=3;
	            break;
		}
				
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}


	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		if(e.getX()>cordX2)
			cordX2+=3;
		else if(e.getX()<cordX2)
			cordX2-=3;
			
	}
}
