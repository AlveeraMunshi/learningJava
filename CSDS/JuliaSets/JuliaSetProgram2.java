package JuliaSets;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JuliaSetProgram2 extends JPanel implements AdjustmentListener, ActionListener,MouseListener
{
    JFrame frame;
    JScrollBar aBar, bBar, zoomBar, briBar, satBar, huefBar, huebBar, sizeBar, shapeBar, iterationBar;
    JLabel aLabel, bLabel, zoomLabel, briLabel, satLabel, huefLabel, huebLabel, sizeLabel, shapeLabel, iterationLabel;
    JButton save, reset;
    double a, b;
    float zoom, bri, sat, huef, hueb, size, shape, maxIter;
    JPanel buttonPanel, scrollPanel, labelPanel, kingPanel;
    BufferedImage image;
    String currDir=System.getProperty("user.dir");
    JFileChooser fileChooser=new JFileChooser(currDir);
    int pixelCount=1;
    public JuliaSetProgram2()
    {
      /*  try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        }
        catch (Exception e)
        {
        }
        */
        //Main JFrame
        frame = new JFrame("Julia Set Program");
        frame.setSize(1000, 1000);

        //panel for scroll bars
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new GridLayout(10, 1)); //rows for each scroll bar
        //scrollbars
        aBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
        aBar.addAdjustmentListener(this);
        aBar.addMouseListener(this);
        bBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -2000, 2000);
        bBar.addAdjustmentListener(this);
        zoomBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
        zoomBar.addAdjustmentListener(this);
        briBar = new JScrollBar(JScrollBar.HORIZONTAL, 500, 0, 0, 1000);
        briBar.addAdjustmentListener(this);
        satBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
        satBar.addAdjustmentListener(this);
        huefBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
        huefBar.addAdjustmentListener(this);
        huebBar = new JScrollBar(JScrollBar.HORIZONTAL, 1000, 0, 0, 1000);
        huebBar.addAdjustmentListener(this);
        sizeBar = new JScrollBar(JScrollBar.HORIZONTAL, 6000, 0, 0, 6000);
        sizeBar.addAdjustmentListener(this);
        shapeBar = new JScrollBar(JScrollBar.HORIZONTAL, 2000, 0, 0, 2000);
        shapeBar.addAdjustmentListener(this);
        iterationBar = new JScrollBar(JScrollBar.HORIZONTAL, 300, 0, 0, 300);
        //add scrollbars to panel
        scrollPanel.add(aBar);
        scrollPanel.add(bBar);
        scrollPanel.add(zoomBar);
        scrollPanel.add(briBar);
        scrollPanel.add(satBar);
        scrollPanel.add(huefBar);
        scrollPanel.add(huebBar);
        scrollPanel.add(sizeBar);
        scrollPanel.add(shapeBar);
        scrollPanel.add(iterationBar);
        //panel for labels
        labelPanel = new JPanel();
        labelPanel.setPreferredSize(new Dimension(100, 50));
        labelPanel.setLayout(new GridLayout(10, 1)); //rows for each label
        //labels for each val
        a = aBar.getValue()/1000.0;
        aLabel = new JLabel("A: " + a);
        b = bBar.getValue()/1000.0;
        bLabel = new JLabel("B: " + b);
        zoom = zoomBar.getValue()/1000.0f;
        zoomLabel = new JLabel("Zoom: " + zoom);
        bri = briBar.getValue()/1000.0f;
        briLabel = new JLabel("Brightness: " + bri);
        sat = satBar.getValue()/1000.0f;
        satLabel = new JLabel("Saturation: " + sat);
        huef = huefBar.getValue()/1000.0f;
        huefLabel = new JLabel("Hue: " + huef);
        hueb = huebBar.getValue()/1000.0f;
        huebLabel = new JLabel("Hue: " + hueb);
        size = sizeBar.getValue()/1000.0f;
        sizeLabel = new JLabel("Size: " + size);
        shape = shapeBar.getValue()/1000.0f;
        shapeLabel = new JLabel("Shape: " + shape);
        maxIter = iterationBar.getValue();
        iterationLabel = new JLabel("Iterations: " + maxIter);
        //add labels to panel
        labelPanel.add(aLabel);
        labelPanel.add(bLabel);
        labelPanel.add(zoomLabel);
        labelPanel.add(briLabel);
        labelPanel.add(satLabel);
        labelPanel.add(huefLabel);
        labelPanel.add(huebLabel);
        labelPanel.add(sizeLabel);
        labelPanel.add(shapeLabel);
        labelPanel.add(iterationLabel);
        //panel for buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2)); //cols for each button
        //buttons
        save = new JButton("Save");
        save.addActionListener(this);
        reset = new JButton("Reset");
        reset.addActionListener(this);
        //add buttons to panel
        buttonPanel.add(save);
        buttonPanel.add(reset);
        //big panel
        kingPanel = new JPanel();
        kingPanel.setLayout(new BorderLayout());
        kingPanel.add(labelPanel, BorderLayout.WEST);
        kingPanel.add(scrollPanel, BorderLayout.CENTER);
        kingPanel.add(buttonPanel, BorderLayout.EAST);

        frame.add(this);
        frame.add(kingPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        //g.setColor(color);
        //g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        g.drawImage(drawJulia(), 0, 0, null);
    }
    public BufferedImage drawJulia()
    {
        int w = frame.getWidth();
        int h = frame.getHeight();
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < w; x+=pixelCount)
        {
            for (int y = 0; y < h; y+=pixelCount)
            {
                double zx = 1.5*(x - w/2.0)/(0.5*zoom*w);
                double zy = (y - h/2.0)/(0.5*zoom*h);
                float i = maxIter;
                while ((zx*zx + zy*zy < size) && (i > 0)) //shape
                {
                    double tmp = zx*zx - zy*zy + a;
                    zy = shape*zx*zy + b;
                    zx = tmp;
                    i--;
                }
                int c;
                if(i>0)
                    c = Color.HSBtoRGB(hueb*(i/200.0f), sat, bri); //outside colors
                else 
                    c = Color.HSBtoRGB(huef, sat, bri); //eye color
                image.setRGB(x, y, c);
            }
        }
        return image;
    }
    @Override
    public void adjustmentvalChanged(AdjustmentEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == aBar)
        {
            a = aBar.getValue()/1000.0;
            aLabel.setText("A: " + a);
        }
        else if (e.getSource() == bBar)
        {
            b = bBar.getValue()/1000.0;
            bLabel.setText("B: " + b);
        }
        else if (e.getSource() == zoomBar)
        {
            zoom = zoomBar.getValue()/1000.0f;
            zoomLabel.setText("Zoom: " + zoom);
        }
        else if (e.getSource() == briBar)
        {
            bri = briBar.getValue()/1000.0f;
            briLabel.setText("Brightness: " + bri);
        }
        else if (e.getSource() == satBar)
        {
            sat = satBar.getValue()/1000.0f;
            satLabel.setText("Saturation: " + sat);
        }
        else if (e.getSource() == huefBar)
        {
            huef = huefBar.getValue()/1000.0f;
            huefLabel.setText("Hue: " + huef);
        }
        else if (e.getSource() == huebBar)
        {
            hueb = huebBar.getValue()/1000.0f;
            huebLabel.setText("Hue: " + hueb);
        }
        else if (e.getSource() == sizeBar)
        {
            size = sizeBar.getValue()/1000.0f;
            sizeLabel.setText("Size: " + size);
        }
        else if (e.getSource() == shapeBar)
        {
            shape = shapeBar.getValue()/1000.0f;
            shapeLabel.setText("Shape: " + shape);
        }
        else if (e.getSource() == iterationBar)
        {
            maxIter = iterationBar.getValue();
            iterationLabel.setText("Iteration: " + maxIter);
        }
        repaint();
        
    }
    public void saveImage()
    {
        if(image!=null) //juliaImage is the BufferedImage I declared globally (and used in
        //the drawJulia method)
        {
            FileFilter filter=new FileNameExtensionFilter("*.png","png");
            fileChooser.setFileFilter(filter);
            if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
            {
                File file=fileChooser.getSelectedFile();
                try
                {
                    String st=file.getAbsolutePath();
                    if(st.indexOf(".png")>=0)
                        st=st.substring(0,st.length()-4);
                    ImageIO.write(image,"png",new File(st+".png"));
                }catch(IOException e)
                {
                }

            }
        }
    }
    public static void main(String[]args)
    {
        JuliaSetProgram2 jsp = new JuliaSetProgram2();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == save)
        {
            saveImage();
        }
        else if (e.getSource() == reset)
        {
            aBar.setval(0);
            bBar.setval(0);
            zoomBar.setval(1000);
            briBar.setval(1000);
            satBar.setval(1000);
            huefBar.setval(0);
            huebBar.setval(0);
            sizeBar.setval(1000);
            shapeBar.setval(1000);
            iterationBar.setval(1000);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        pixelCount=3;    
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        pixelCount=1;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
