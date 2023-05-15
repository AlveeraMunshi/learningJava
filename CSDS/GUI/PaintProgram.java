package GUI;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.awt.geom.*;
import java.awt.image.*;

public class PaintProgram extends JPanel implements MouseListener,MouseMotionListener,ActionListener,AdjustmentListener,ChangeListener
{
	JFrame frame;
	ArrayList<Point> points = new ArrayList<Point>(); //points in a line
	//Stack<ArrayList<Point>> lines = new Stack<ArrayList<Point>>(); //lines that exist
	Stack<Object> shapes = new Stack<Object>(); //shapes that exist
	Shape currShape; //current shape
	boolean draw = true; //whether or not the user is drawing a free line
	boolean drawRect = false; //whether or not the user is drawing a rectangle
	boolean drawOval = false; //whether or not the user is drawing an oval
	int currWidth=2; //width of line
	Color currColor = Color.BLACK; //color of line
	Color bgColor = Color.WHITE; //background color
	boolean erase = false; //whether or not the user is erasing

	JScrollBar width;
	JMenuBar menuBar;
	JMenu colorMenu, fileMenu, editMenu;
	Color[] colors;
	JMenuItem[] colorButtons;
	JColorChooser colorChooser;
	JMenuItem save, load, clear, exit, undo, redo;
	JButton rectButton, ovalButton, lineButton, eraseButton;
	ImageIcon saveImg, loadImg, clearImg, exitImg, undoImg, redoImg, eraseImg, rectImg, ovalImg, lineImg;
	JFileChooser chooser;
	BufferedImage loadedImg;

	public PaintProgram()
	{
		//menubar
		menuBar = new JMenuBar();

		//frame
		frame=new JFrame("The Best Paint Program Ever Constructed - by me....");
		frame.add(this);
		frame.setSize(1400,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		//file menu
		fileMenu = new JMenu("File");
		fileMenu.setLayout(new GridLayout(4,1));
		//filechooser
		String currDir = System.getProperty("user.dir");
		chooser = new JFileChooser(currDir);

		//save, load, clear, exit JMenuItem
		save = new JMenuItem("Save", KeyEvent.VK_S);
		save.addActionListener(this);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)); //ctrl+s
		load = new JMenuItem("Load", KeyEvent.VK_L);
		load.addActionListener(this);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK)); //ctrl+l
		clear = new JMenuItem("Clear", KeyEvent.VK_C);
		clear.addActionListener(this);
		clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK)); //ctrl+c
		exit = new JMenuItem("Exit", KeyEvent.VK_E);
		exit.addActionListener(this);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK)); //ctrl+e
		//save, load, clear, exit ImageIcon
		saveImg = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/saveImg.png");
		saveImg = new ImageIcon(saveImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		loadImg = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/loadImg.png");
		loadImg = new ImageIcon(loadImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		clearImg = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/eraserImg.png");
		clearImg = new ImageIcon(clearImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		//set menu item icons
		save.setIcon(saveImg);
		load.setIcon(loadImg);
		clear.setIcon(clearImg);
		//exit.setIcon(exitImg);
		//add to file menu
		fileMenu.add(save);
		fileMenu.add(load);
		fileMenu.add(clear);
		fileMenu.add(exit);

		//edit menu
		editMenu = new JMenu("Edit");
		editMenu.setLayout(new GridLayout(2,1));

		//undo, redo JMenuItem
		undo = new JMenuItem("Undo", KeyEvent.VK_Z);
		undo.addActionListener(this);
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK)); //ctrl+z
		redo = new JMenuItem("Redo", KeyEvent.VK_Y);
		redo.addActionListener(this);
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK)); //ctrl+y
		//undo, redo ImageIcon
		undoImg = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/undoImg.png");
		undoImg = new ImageIcon(undoImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		redoImg = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/redoImg.png");
		redoImg = new ImageIcon(redoImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		//add to edit menu
		editMenu.add(undo);
		editMenu.add(redo);

		//color menu
		colorMenu = new JMenu("Color Options");
		colors = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(75, 0, 130), new Color(143, 0, 255), Color.MAGENTA, Color.PINK, Color.BLACK, Color.WHITE};
		colorButtons = new JMenuItem[colors.length];
		colorMenu.setLayout(new GridLayout(colors.length, 1));
		//color JMenuItems
		for (int i = 0; i < colors.length; i++)
		{
			colorButtons[i] = new JMenuItem();
			colorButtons[i].putClientProperty("colorIndex", i);
			colorButtons[i].setOpaque(true);
			colorButtons[i].setBackground(colors[i]);
			colorButtons[i].addActionListener(this);
			//add to color menu
			colorMenu.add(colorButtons[i]);
		}
		//color chooser
		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(this);
		colorMenu.add(colorChooser);

		//buttons
		rectButton = new JButton("Rectangle");
		rectButton.addActionListener(this);
		ovalButton = new JButton("Oval");
		ovalButton.addActionListener(this);
		lineButton = new JButton("Line");
		lineButton.addActionListener(this);
		eraseButton = new JButton("Erase");
		eraseButton.addActionListener(this);
		//buttons images
		rectImg = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/rectImg.png");
		rectImg = new ImageIcon(rectImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		ovalImg = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/ovalImg.png");
		ovalImg = new ImageIcon(ovalImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		lineImg = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/freeLineImg.png");
		lineImg = new ImageIcon(lineImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		eraseImg = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/eraserImg.png");
		eraseImg = new ImageIcon(eraseImg.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		//set button icons
		rectButton.setIcon(rectImg);
		ovalButton.setIcon(ovalImg);
		lineButton.setIcon(lineImg);
		eraseButton.setIcon(eraseImg);
		//set button sizes
		/*rectButton.setFocusable(false);
		ovalButton.setFocusable(false);
		lineButton.setFocusable(false);
		eraseButton.setFocusable(false);*/

		//width scrollbar
		width = new JScrollBar(JScrollBar.HORIZONTAL, 2, 1, 1, 20);
		width.addAdjustmentListener(this);

		//add to menu and frame
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(colorMenu);
		menuBar.add(rectButton);
		menuBar.add(ovalButton);
		menuBar.add(lineButton);
		menuBar.add(eraseButton);
		menuBar.add(width);
		frame.setJMenuBar(menuBar);

		frame.setVisible(true); //do last
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		//draw old lines
		//Iterator it = lines.iterator(); //go through stack without removing values
		Iterator it = shapes.iterator(); //go through stack without removing values
		while (it.hasNext()) //while there are still lines to draw
		{
			Object o = it.next();
			if (o instanceof BufferedImage)
			{
				//draw loaded image
				if (loadedImg != null)
				{
					g.drawImage(loadedImg, 0, 0, null);
				}
			}
			else if (o instanceof Rectangle)
			{
				Rectangle r = (Rectangle)o;
				g2.setStroke(new BasicStroke(r.getPenWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				g.setColor(r.getColor());
				g2.draw(r.getRect());
			}
			else if (o instanceof Oval)
			{
				Oval o1 = (Oval)o;
				g2.setStroke(new BasicStroke(o1.getPenWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				g.setColor(o1.getColor());
				g2.draw(o1.getOval());
			}
			else if (o instanceof ArrayList<?>)
			{
				ArrayList<Point> p = (ArrayList<Point>)(it.next());
				g2.setStroke(new BasicStroke(p.get(0).getWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				//go through each point in the line
				for (int i=0;i<p.size()-1;i++)
				{
					g.setColor(p.get(i).getColor());
					Point p1 = p.get(i);
					Point p2 = p.get(i+1);
					g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
				}
			}
		}
		//draw new line
		if (draw)
		{
			g2.setStroke(new BasicStroke(points.get(0).getWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.setColor(points.get(0).getColor());
			//go through each point in the line
			for (int i=0;i<points.size()-1;i++)
			{
				Point p1 = points.get(i);
				Point p2 = points.get(i+1);
				g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
			}
		}
	}
	//used implemented methods
	public BufferedImage createImage()
	{
		BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = img.createGraphics();
		this.paint(g2);
		return img;
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == clear)
		{
			//lines = new Stack<ArrayList<Point>>(); //clear lines
			shapes = new Stack<Object>(); //clear shapes
			loadedImg = null;
			repaint();
		}
		else if (e.getSource() == exit)
		{
			System.exit(0);
		}
		else if (e.getSource() == save)
		{
			FileFilter filter = new FileNameExtensionFilter("*.png", "png"); //only allow png files
			chooser.setFileFilter(filter);
			int result = chooser.showSaveDialog(null); //show save dialog
			if (result == JFileChooser.APPROVE_OPTION) //if user selects a file
			{
				File file = chooser.getSelectedFile();
				try
				{
					String path = file.getAbsolutePath();
					if (path.endsWith(".png")) //if user enters .png at the end of the file name
					{
						path.substring(0, path.length()-4); //remove .png
					}
					{
						path.substring(0, path.length()-4);
					}
					file = new File(path);
					ImageIO.write(createImage(), "png", new File(path+".png")); //save image as png
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		else if (e.getSource() == load)
		{
			chooser.showOpenDialog(null); //show open dialog
			File imgFile = chooser.getSelectedFile();
			if (imgFile!=null && imgFile.toString().indexOf(".png") >= 0) //if user selects a png file
			{
				try
				{
					loadedImg = ImageIO.read(imgFile); //load image
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
				//lines = new Stack<ArrayList<Point>>(); //clear lines
				shapes = new Stack<Object>(); //clear shapes
				shapes.push(loadedImg); //add image to shapes stack
				repaint();
			}
			else if (imgFile!=null) //if user selects a non-png file
			{
				JOptionPane.showMessageDialog(null, "Please select a .png file");
			}
			
		}
		else if (e.getSource() == undo)
		{
			/*if (lines.size() > 0)
			{
				lines.pop();
				repaint();
			}*/
			if (shapes.size() > 0)
			{
				shapes.pop();
				repaint();
			}
		}
		else if (e.getSource() == redo)
		{
			/*if (lines.size() > 0)
			{
				lines.pop();
				repaint();
			}*/
			if (shapes.size() > 0)
			{
				shapes.pop();
				repaint();
			}
		}
		else if (e.getSource() == eraseButton)
		{
			drawRect = false;
			drawOval = false;
			draw = false;
			erase = true;
			//currColor = bgColor;
		}
		else if (e.getSource() == rectButton)
		{
			drawRect = true;
			drawOval = false;
			draw = false;
			erase = false;
			//currColor = shapes.peek().getColor();
		}
		else if (e.getSource() == ovalButton)
		{
			drawRect = false;
			drawOval = true;
			draw = false;
			erase = false;
			//currColor = Color.BLACK;
		}
		else if (e.getSource() == lineButton)
		{
			drawRect = false;
			drawOval = false;
			draw = true;
			erase = false;
			//currColor = Color.BLACK;
		}
		else
		{
			int index = (int)((JMenuItem)e.getSource()).getClientProperty("colorIndex");
			currColor = colors[index];
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		draw = false;
		if (points.size() > 0)
		{
			//lines.push(points);
			shapes.push(points);
			points=new ArrayList<Point>();
		}
	}
	public void mouseDragged(MouseEvent e)
	{
		Color c = currColor;
		if (erase)
			c = bgColor;
		points.add(new Point(e.getX(),e.getY(),c,currWidth));
		repaint();
	}
	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		currWidth = width.getValue();
	}
	public void stateChanged(ChangeEvent e)
	{
		currColor = colorChooser.getColor();
	}
	public static void main(String[] args)
	{
		PaintProgram app=new PaintProgram();
	}
	//unused implemented methods
	public void mousePressed(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	//point class
	public class Point
	{
		int x,y, width;
		Color color;
		public Point(int x,int y, Color color, int width)
		{
			this.x=x;
			this.y=y;
			this.color=color;
			this.width=width;
		}
		public int getX()
		{
			return x;
		}
		public int getY()
		{
			return y;
		}
		public Color getColor()
		{
			return color;
		}
		public int getWidth()
		{
			return width;
		}
	}
	//shape class
	public class Shape
	{
		int x,y, penWidth, width, height;
		Color color;
		public Shape(int x,int y, Color color, int penWidth, int width, int height)
		{
			this.x=x;
			this.y=y;
			this.color=color;
			this.penWidth=penWidth;
			this.width=width;
			this.height=height;
		}
		public int getX()
		{
			return x;
		}
		public int getY()
		{
			return y;
		}
		public Color getColor()
		{
			return color;
		}
		public int getPenWidth()
		{
			return penWidth;
		}
		public int getWidth()
		{
			return width;
		}
		public int getHeight()
		{
			return height;
		}
		public void setX(int x)
		{
			this.x=x;
		}
		public void setY(int y)
		{
			this.y=y;
		}
		public void setColor(Color color)
		{
			this.color=color;
		}
		public void setPenWidth(int penWidth)
		{
			this.penWidth=penWidth;
		}
		public void setWidth(int width)
		{
			this.width=width;
		}
		public void setHeight(int height)
		{
			this.height=height;
		}
	}
	//rectangle class
	public class Rectangle extends Shape
	{
		public Rectangle(int x,int y, Color color, int penWidth, int width, int height)
		{
			super(x,y,color,penWidth,width,height);
		}
		public Rectangle2D.Double getRect()
		{
			return new Rectangle2D.Double(getX(),getY(),getWidth(),getHeight());
		}
	}
	//oval class
	public class Oval extends Shape
	{
		public Oval(int x,int y, Color color, int penWidth, int width, int height)
		{
			super(x,y,color,penWidth,width,height);
		}
		public Ellipse2D.Double getOval()
		{
			return new Ellipse2D.Double(getX(),getY(),getWidth(),getHeight());
		}
	}
	//line class
	/*public class Line extends Shape
	{
		public Line(int x,int y, Color color, int penWidth, int width, int height)
		{
			super(x,y,color,penWidth,width,height);
		}
		public Line2D.Double getLine()
		{
			return new Line2D.Double(getX(),getY(),getWidth(),getHeight());
		}
	}*/
}