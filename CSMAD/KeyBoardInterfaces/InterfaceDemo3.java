import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class InterfaceDemo3 extends JPanel
{
	public InterfaceDemo3()
	{
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);
		addKeyListener(new KeyListener(){
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
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main (String[]args)
	{
		InterfaceDemo3 demo = new InterfaceDemo3();
	}
}