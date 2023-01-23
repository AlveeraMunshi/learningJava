package JuliaSets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JuliaSetProgram extends JPanel implements AdjustmentListener
{
    JFrame frame;
    Color color;
    int r, g, b;
    JScrollBar red, green, blue;
    JPanel scrollPanel, labelPanel, kingPanel;
    JLabel redLabel, greenLabel, blueLabel;
    public JuliaSetProgram()
    {
        //big panel
        kingPanel = new JPanel();
        kingPanel.setLayout(new BorderLayout());

        //Main JFrame
        frame = new JFrame("Julia Set Program");
        frame.add(this);
        frame.setSize(1000, 1000);
        frame.add(kingPanel, BorderLayout.SOUTH);

        //panel for scroll bars
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new GridLayout(3, 1)); //3 rows for 3 scrollbars
        //scrollbars for each color
        red = new JScrollBar(JScrollBar.HORIZONTAL, 255, 0, 0, 256);
        red.addAdjustmentListener(this);
        green = new JScrollBar(JScrollBar.HORIZONTAL, 255, 0, 0, 256);
        green.addAdjustmentListener(this);
        blue = new JScrollBar(JScrollBar.HORIZONTAL, 255, 0, 0, 256);
        blue.addAdjustmentListener(this);
        //add scrollbars to panel
        scrollPanel.add(red);
        scrollPanel.add(blue);
        scrollPanel.add(green);
        //add panel to big panel
        kingPanel.add(scrollPanel, BorderLayout.CENTER);

        //start colors
        r = red.getValue();
        g = green.getValue();
        b = blue.getValue();

        //panel for labels
        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(3, 1)); //3 rows for 3 labels
        //labels for each color
        redLabel = new JLabel("Red: " + red.getValue());
        greenLabel = new JLabel("Green: " + green.getValue());
        blueLabel = new JLabel("Blue: " + blue.getValue());
        labelPanel.add(redLabel);
        labelPanel.add(greenLabel);
        labelPanel.add(blueLabel);
        //add labels to panel
        kingPanel.add(labelPanel, BorderLayout.WEST);

        color = new Color(r, g, b);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
    }
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == red)
        {
            r = red.getValue();
            redLabel.setText("Red: " + r);
        }
        else if (e.getSource() == green)
        {
            g = green.getValue();
            greenLabel.setText("Green: " + g);
        }
        else if (e.getSource() == blue)
        {
            b = blue.getValue();
            blueLabel.setText("Blue: " + b);
        }
        color = new Color(r, g, b);
        repaint();
        
    }
    public static void main(String[]args)
    {
        JuliaSetProgram jsp = new JuliaSetProgram();
    }
}
