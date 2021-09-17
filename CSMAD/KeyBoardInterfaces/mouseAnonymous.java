import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class mouseAnonymous extends JPanel
{
	public mouseAnonymous()
	{
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);
		addMouseMotionListener(new myListener());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main (String[]args)
	{
		mouseAnonymous demo = new mouseAnonymous();
		ogxpos = 0;
		ogypos = 0;
		newxpow = 0;
		newypos = 0;
		myListener l = new myListener()
		{
		    public void run()
		    {
				// 1 px = 0.0075 in
				Thread.sleep(1000000);
			}
		};
		Thread t = new Thread(l);
        t.start();
	}
	public class myListener extends JPanel implements MouseMotionListener
	{
		public void mouseMoved(MouseEvent e)
		{
			saySomething("Mouse moved", e);
		}

		public void mouseDragged(MouseEvent e)
		{
			saySomething("Mouse dragged", e);
		}
		void saySomething(String eventDescription, MouseEvent e)
		{
			System.out.println(" (" + e.getX() + "," + e.getY() + ")");
    	}
	}
}