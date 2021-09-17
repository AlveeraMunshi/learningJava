import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class KeyBoardInterfaceDemo extends JPanel implements KeyListener
{
	public KeyBoardInterfaceDemo()
	{
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);
		addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main (String[]args)
	{
		KeyBoardInterfaceDemo demo = new KeyBoardInterfaceDemo();
	}
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