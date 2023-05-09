package GUI;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class PaintProgram extends JPanel implements MouseListener,MouseMotionListener,ActionListener,AdjustmentListener,ChangeListener
{
	JFrame frame;
	ArrayList<Point> points = new ArrayList<Point>(); //points in a line
	Stack<ArrayList<Point>> lines; //lines that exist
	boolean draw = false; //whether or not the user is drawing a free line
	int currWidth=2; //width of line
	Color currColor = Color.BLACK; //color of line

	JScrollBar width;
	JMenuBar menuBar;
	JMenu colorMenu;
	Color[] colors;
	JButton[] colorButtons;
	public PaintProgram()
	{
		frame=new JFrame("The Best Paint Program Ever Constructed - by me....");
		frame.add(this);
		frame.setSize(1400,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		//frame.addActionListener(this);
		//width.addAdjustmentListener(this);
		//frame.addChangeListener(this);
		lines = new Stack<ArrayList<Point>>();

		// color menu
		menuBar = new JMenuBar();
		colorMenu = new JMenu("Color Options");
		colors = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(75, 0, 130), new Color(143, 0, 255), Color.MAGENTA, Color.PINK, Color.BLACK, Color.WHITE};
		colorButtons = new JButton[colors.length];
		colorMenu.setLayout(new GridLayout(colors.length, 1));
		for (int i = 0; i < colors.length; i++)
		{
			colorButtons[i] = new JButton();
			colorButtons[i].putClientProperty("colorIndex", i);
			colorButtons[i].setOpaque(true);
			colorButtons[i].setBackground(colors[i]);
			colorButtons[i].addActionListener(this);
			colorMenu.add(colorButtons[i]);
		}
		menuBar.add(colorMenu);
		frame.setJMenuBar(menuBar);

		frame.setVisible(true); // do last
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		//draw old lines
		Iterator it = lines.iterator(); //go through stack without removing values
		while (it.hasNext()) //while there are still lines to draw
		{
			ArrayList<Point> p = (ArrayList<Point>)it.next();
			g2.setStroke(new BasicStroke(p.get(0).getWidth()));
			g.setColor(p.get(0).getColor());
			//go through each point in the line
			for (int i=0;i<p.size()-1;i++)
			{
				Point p1 = p.get(i);
				Point p2 = p.get(i+1);
				g2.drawLine(p1.getX(),p1.getY(),p2.getX(),p2.getY());
			}
		}
		//draw new line
		if (draw)
		{
			g2.setStroke(new BasicStroke(points.get(0).getWidth()));
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
	public void actionPerformed(ActionEvent e)
	{
		int index = (int)((JButton)e.getSource()).getClientProperty("colorIndex");
		currColor = colors[index];
	}
	public void mouseReleased(MouseEvent e)
	{
		draw = false;
		lines.push(points);
		points=new ArrayList<Point>();
	}
	public void mouseDragged(MouseEvent e)
	{
		draw = true;
		points.add(new Point(e.getX(),e.getY(),currColor,currWidth));
		repaint();
	}
	public void adjustmentValueChanged(AdjustmentEvent e)
	{

	}
	public void stateChanged(ChangeEvent e){}
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
}