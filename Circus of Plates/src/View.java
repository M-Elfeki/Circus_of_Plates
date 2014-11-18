import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
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
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


public class View  extends JFrame implements KeyListener, MouseMotionListener
{

	public static BufferedImage clown, highBar, lowBar, background;
    public static int cordX = 50, cordX2 = 950;
    public static int time=20000, scoreOne, scoreTwo;
    public static JLabel timer ,score_1, score_2;
    private JButton loadClass, saveGame, loadGame;
    public static String url="";
    public static String pathOfFile = "file:/";
    private Iterator left;
    private Iterator right;
    private Iterator all;
    private Iterator print;
    private Iterator pp1;
    private Iterator pp2;
    public static ArrayList<Shape> contents;

	public void setCurrentDrawing(Iterator a2,Iterator a1 ,Iterator a3,Iterator a4,Iterator a5)
	{
		this.left  = a1;
		this.right = a2;
		this.all   = a3;
		this.pp1   = a4;
		this.pp2   = a5;
		
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
		
		while(pp1.hasNext())
		{
			print.addShape(pp1.getElement());  
		}
		
		while(pp2.hasNext())
		{
			print.addShape(pp2.getElement());  
		}
		
		repaint();
	} 
	public View()
	{
		
		contents=new ArrayList<Shape>();
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
    	
		loadImages();
    	setTitle("Cirucs of Plates");
		setLayout(new FlowLayout());
		getContentPane().setBackground(new Color(0, 255, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,700);
		setVisible(true);
//		setBackground(new Color(0, 255, 0, 0));
		    	
    	JFileChooser fc;
		fc = new JFileChooser();
		JMenuBar mbr = new JMenuBar();
		setJMenuBar(mbr);
		JMenu file = new JMenu("FILE");
		JMenuItem p;
		file.add(p = new JMenuItem("open"));
    	loadClass.addActionListener(dynamicLoading);
    	saveGame.addActionListener(saveFile);
    	loadGame.addActionListener(loadFile);
    	KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( new KeyEventDispatcher() {
		      public boolean dispatchKeyEvent(KeyEvent e) {
		    	  switch (e.getKeyCode()) 
		  		{
		  	        case KeyEvent.VK_RIGHT: 
		  	            cordX+=5;
		  	            break;
		  	        case KeyEvent.VK_LEFT: 
		  	            cordX-=5;
		  	            break;
		  		}
		    	  Control.p1.setX(cordX);
					Control.p1.setY(500);
		          return false;
		      }});
 	    addMouseMotionListener(this);
	}
    
    public void loadImages()
	{
	    try 
	    {
	        String path_1 = "./src/clown.png";
	    //    String path_2 = "./src/highBar.png";
	    //    String path_3 = "./src/lowBar.png";
	        clown = ImageIO.read(new File(path_1));
	        background = ImageIO.read(new File("./src/background.jpg"));
	      //  highBar = ImageIO.read(new File(path_2)); 
	      //  lowBar = ImageIO.read(new File(path_3)); 
	    } catch (IOException ex) {}
	   
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
		      while(time>=0)
		      {
		    	  try
		    	  {
		    		  View.timer.setText("     Timer: "+Integer.toString(time)+"        ");
			    	  repaint();
			    	  time--;
			    	  Thread.sleep(50);
		    	  }catch(Exception ex){}
		      }
		      if(time==0)
		      {
		    	  if(scoreOne>scoreTwo)
		    	  {
		    		  JOptionPane.showMessageDialog(null, "Player One Wins");
		    		  System.exit(0);
		    	  }
		    	  else if(scoreOne<scoreTwo)
		    	  {
		    		  JOptionPane.showMessageDialog(null, "Player Two Wins");
		    		  System.exit(0);
		    	  }
		    	  else
		    	  {
		    		  JOptionPane.showMessageDialog(null, "Game Over");
		    		  System.exit(0);
		    	  }
		      }
		   } 

	}
    
    ActionListener dynamicLoading = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try{
			JFileChooser fc = new JFileChooser();
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
			Class c;
			String url = pathOfFile; 
			DynamicLinkage dl = new DynamicLinkage(url);
			c = dl.invokeClass();
			Control.classList.add(c);
			}catch(Exception ex){}

	    	new MoveObject();
		}	
	};
	
	ActionListener saveFile = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			SaveGame game = new SaveGame();
	        try {
				game.saveBoard(View.contents);
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}; 

	ActionListener loadFile = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String path="";
			try{
			JFileChooser fe = new JFileChooser();
			int returnVal = fe.showOpenDialog(View.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
				File fel = fe.getSelectedFile();
				path += fel.getPath();
			}
			SaveGame game = new SaveGame();
			game.loadBoard(path);
			}catch(Exception ex){}
		}	
		private void sysout() {
			// TODO Auto-generated method stub

		}
	}; 


    public void paint(Graphics g)
	{
    	contents.clear();
		super.paint(g); 
		try{
			while(print.hasNext())
			{ 
				contents.add(print.getElement());
				g.setColor   (print.getElement().getColor()); 
				g.fillPolygon(print.getElement().getPoly() );
			}
		}
		catch (Exception e)
		{
			
		}
//		g.drawImage(highBar, 0, 100, 300, 10, this);
//		g.drawImage(highBar, 500, 100, 300, 10, this);
//		g.drawImage(lowBar, 0, 150, 200, 10, this);
//		g.drawImage(lowBar, 600, 150, 200, 10, this);
		g.drawImage(clown, cordX, 500, 100, 200, this);
		g.drawImage(clown, cordX2, 500, 100, 200, this); 
		
	}
	

	@Override
	public void keyPressed(KeyEvent ke) 
	{ 
		if(ke.getKeyCode()==KeyEvent.VK_RIGHT&&cordX>0&&cordX<1200) 
			cordX+=3;
		else if(ke.getKeyCode()==KeyEvent.VK_LEFT&&cordX>0&&cordX<1200)
			cordX-=3;		
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
		if(e.getX()>cordX2&&e.getX()>0&&e.getX()<1200)
			cordX2+=3;
		else if(e.getX()<cordX2&&e.getX()>0&&e.getX()<1200)
			cordX2-=3;
			
		try{
			Control.p2.setX(cordX2);
			Control.p2.setY(500);
		}
		catch (Exception es)
		{
			
		}
	}
    
}
class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}