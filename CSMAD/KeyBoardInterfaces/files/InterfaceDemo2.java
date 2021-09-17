import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class InterfaceDemo2 extends JPanel
{
	public InterfaceDemo2()
	{
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);
		addKeyListener(new KeyInterface());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main (String[]args)
	{
		InterfaceDemo2 demo = new InterfaceDemo2();
	}
	public class KeyInterface implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			System.out.println(e.getKeyChar());
		}
		public void keyReleased(KeyEvent e)
		{
		}
		public void keyTyped(KeyEvent e)
		{
		}
	}
}