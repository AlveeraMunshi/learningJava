package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SierpinskiTriangleSimulator extends JPanel implements KeyListener 
{
    Color blue = new Color(176, 247, 230);
    Color pink = new Color(245, 169, 203);
    ArrayList<Point> points = new ArrayList<Point>();
    int[] xvals, yvals;
    Polygon hexagon;
    JFrame frame;
    public SierpinskiTriangleSimulator()
    {
        frame = new JFrame("Sierpinski Triangle Simulator");
        frame.setSize(700, 700);
        frame.add(this);
        frame.addKeyListener(this);
        pinski();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void pinski()
    {
        /*//save x and y vals of the triangle
        xvals=new int[]{frame.getWidth()/2, 100, frame.getWidth() - 100};
        yvals=new int[]{100, frame.getHeight() - 100, frame.getHeight() - 100};*/
        //svae x and y vals of the hexagon
        xvals=new int[]{200, 500, 200, 500, 50, 650};
        yvals=new int[]{90, 90, 610, 610, 350, 350};
        //add the points to the arraylist
        points.add(new Point(xvals[0],yvals[0], pink));
        points.add(new Point(xvals[1],yvals[1], pink));
        points.add(new Point(xvals[2],yvals[2], pink));
        points.add(new Point(xvals[3], yvals[3], pink));
        points.add(new Point(xvals[4], yvals[4], pink));
        points.add(new Point(xvals[5], yvals[5], pink));
        /*//create the triangle
        triangle=new Polygon(xvals,yvals,3);*/
        //create the hexagon
        hexagon=new Polygon(xvals,yvals,6);
        //check if the first random point is in the polygon
        int x;
        int y;
        do
        {
            x = (int)(Math.random()*frame.getWidth());
            y = (int)(Math.random()*frame.getHeight());

        }while(!hexagon.contains(x,y));
        points.add(new Point(x, y, pink));

        addPoint(points.get(points.size() - 1));
    }
    public void addPoint(Point p)
    {
        System.out.println("adding points");
        int random = (int)(Math.random()*6); //randomly choose a corner
        //get x and y vals of the corner
        int cx = xvals[random];
        int cy = yvals[random];
        //find midpoint between point and corner
        int x = (p.getX() + cx)/2;
        int y = (p.getY() + cy)/2;
        //add the point to the arraylist
        points.add(new Point(x, y, p.getColor()));
    }
    public static void main (String[]args)
    {
        SierpinskiTriangleSimulator g = new SierpinskiTriangleSimulator();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int num = e.getKeyCode() - 48;
        System.out.println("pressed " + num);
        for (int x = 0; x < num; x++)
            addPoint(points.get(points.size() - 1));
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        System.out.println("printing!!");
        g.setColor(blue);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        for (Point p : points)
        {
            g.setColor(p.getColor());
            g.fillOval(p.getX(), p.getY(), 2, 2);
        }
    }
    public class Point
    {
        private int x;
        private int y;
        private Color c;
        public Point(int x, int y, Color c)
        {
            this.x = x;
            this.y = y;
            this.c = c;
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
            return c;
        }
    }
}
