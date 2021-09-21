import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.util.*;

public class mouseAnonymous extends JPanel
{
	public mouseAnonymous()
	{
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);
		addMouseMotionListener(new myListener());
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main (String[]args)
	{
		mouseAnonymous demo = new mouseAnonymous();
		/* myListener l = new myListener()
		{
		    public void run()
		    {

			}
		};
		Thread t = new Thread(l);
        t.start(); */
	}
	public class myListener extends JPanel implements MouseMotionListener
	{
		private int distance = 0;
		public void mouseMoved(MouseEvent e)
		{
			saySomething("Mouse moved", e);
		}

		public void mouseDragged(MouseEvent e)
		{
			saySomething("Mouse dragged", e);
		}
		public int mouseGetX (MouseEvent e)
		{
			return e.getX();
		}
		public int mouseGetY (MouseEvent e)
		{
			return e.getY();
		}
		//Part 1
		void saySomething(String eventDescription, MouseEvent e)
		{
			System.out.println(" (" + e.getX() + "," + e.getY() + ")");
    	}
    	//Part 2
    	void distanceMoved(MouseEvent e)
    	{
			Point p1 = new Point(e.getX(), e.getY());
			try
			{
			    Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			Point p2 = new Point(e.getX(), e.getY());
			double a = (p1.getX() - p2.getX())/177;
			double b = (p1.getY() - p2.getY())/128;
			double c = Math.sqrt(a*a + b*b);
			distance+=c;
			System.out.println(distance + " feet moved");
		}
	}
}