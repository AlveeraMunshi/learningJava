package GUI;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

public class SpriteAnimation extends JPanel implements Runnable {
    BufferedImage[] images = new BufferedImage[12];
    JFrame frame;
    Thread timingThread;
    int currentImage = 0;
    public SpriteAnimation()
    {
        // create a new window
        frame = new JFrame();
        frame.add(this);
        // set the size of the window
        frame.setSize(1000, 1000);
        // set the title of the window
        frame.setTitle("Sprite Animation");
        // create a new sprite
        for (int i = 1; i < 13; i++)
        {
            // create a new BufferedImage
            try {
                BufferedImage image = ImageIO.read(new File("GUI/Animation/Walk" + i + ".png")); // load each image in the animation
                // resize the BufferedImage
                image = resize(image, 100, 100);
                // add the BufferedImage to the array
                images[i-1] = image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // create a new thread
        timingThread = new Thread(this);
        // default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // show the frame
        frame.setVisible(true);
    }
    public BufferedImage resize(BufferedImage image, int width, int height)
    {
        //Images can be scaled but BufferedImages cannot
        Image temp=image.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        // Turn the BufferedImage into Image so that it can be scaled
        BufferedImage scaledVersion = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //Scaled the Image and then casted it into a BufferedImage
        Graphics2D g2=scaledVersion.createGraphics();
        //render it and then return the image
        g2.drawImage(temp,0,0,null);
        g2.dispose();
        return scaledVersion;
    }
    public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        // draw the image
        g2.fillRect(0, 0, 1000, 1000);
        g2.drawImage(images[currentImage], 0, 0, null);

    }
    public static void main(String[] args) {
        SpriteAnimation sa = new SpriteAnimation();
    }
    @Override
    public void run() {
        while (true)
        {
            try
            {
                // sleep for 100 milliseconds
                Thread.sleep(300);
                // increment the current image
                currentImage++;
                // if the current image is greater than the number of images
                if (currentImage >= images.length)
                {
                    // reset the current image
                    currentImage = 0;
                }
                // repaint the frame
                frame.repaint();
            }
            catch (InterruptedException e)
            {

            }
        }
    }
}
