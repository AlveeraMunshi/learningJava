package Maze;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.security.cert.PolicyQualifierInfo;

public class MazeProgram extends JPanel implements KeyListener {
    JFrame frame;
    String[][] maze;
    Hero hero;
    boolean[][] visited = new boolean[25][60];
    private int endR, endC;
    boolean in2D = true;
    ArrayList<Wall> lwalls, rwalls, cwalls, bwalls, fwalls;
    public MazeProgram()
    {
        frame = new JFrame();
        frame.add(this);
        frame.setSize(1000,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        setMaze();
        frame.setVisible(true);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        if (in2D)
        {
            g2.setColor(Color.RED);
            for (int r = 0; r < maze.length; r++)
            {
                for (int c = 0; c < maze[0].length; c++)
                {
                    if (maze[r][c].equalsIgnoreCase("*"))
                        g2.fillRect(c*10 +20, r*10 + 20, 10, 10);
                }
            }
            g2.setColor(Color.WHITE);
            g2.fillOval(hero.getC() *10 + 20, hero.getR() *10 + 20, 10, 10);
        }
        else{
            for (Wall wall : bwalls)
            {
                if (wall.getBreadCrumb()!=null)
                    g2.setColor(wall.getBreadCrumb());
                else
                    g2.setPaint(wall.getPaint());
                g2.fillPolygon(wall.getPoly());
                g2.setColor(Color.WHITE);
                g2.drawPolygon(wall.getPoly());

            }
            for (Wall wall : lwalls)
            {
                g2.setPaint(wall.getPaint());
                g2.fillPolygon(wall.getPoly());
                g2.setColor(Color.WHITE);
                g2.drawPolygon(wall.getPoly());

            }
            for (Wall wall : rwalls)
            {
                g2.setPaint(wall.getPaint());
                g2.fillPolygon(wall.getPoly());
                g2.setColor(Color.WHITE);
                g2.drawPolygon(wall.getPoly());

            }
            for (Wall wall : cwalls)
            {
                g2.setPaint(wall.getPaint());
                g2.fillPolygon(wall.getPoly());
                g2.setColor(Color.WHITE);
                g2.drawPolygon(wall.getPoly());
            }
            for (Wall wall : fwalls)
            {
                g2.setPaint(Color.GRAY);
                g2.fillPolygon(wall.getPoly());
                g2.setColor(Color.WHITE);
                g2.drawPolygon(wall.getPoly());
            }
            g2.setColor(Color.ORANGE);
            g2.fillRect(0, 0, 100, 100);
            g2.setColor(Color.white);
            g2.drawRect(0, 0, 100, 100);
            g2.setColor(Color.black);
            try 
            {
                for (int r = 0; r < 9; r++)
                {
                    for (int c = 0; c < 9; c++)
                    {
                        try 
                        {
                            if (maze[r+hero.getR()-4][c+hero.getC()-4].equalsIgnoreCase("*"))
                                g2.fillRect(c*10+10, r*10+10, 10, 10);
                        }
                        catch (ArrayIndexOutOfBoundsException e)
                        {

                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            g2.setColor(Color.WHITE);
            g2.fillOval(50, 50, 10, 10);
        }
    }
    public void setMaze()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Maze/maze1.txt");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            maze = new String[25][60];
            int r = 0;
            boolean first = true;
            while ((text = reader.readLine()) != null)
            {
                if (first)
                {
                    String[] pieces = text.split(" ");
                    int row = Integer.parseInt(pieces[0]);
                    int col = Integer.parseInt(pieces[1]);
                    hero = new Hero(row, col, pieces[2].charAt(0));
                    endR = Integer.parseInt(pieces[3]);
                    endC = Integer.parseInt(pieces[4]);
                    first=false;
                }
                else
                {
                    String[] pieces = text.split("");
                    maze[r] = pieces;
                    r++;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void set3D()
    {
        lwalls = new ArrayList<>();
        rwalls = new ArrayList<>();
        cwalls = new ArrayList<>();
        bwalls = new ArrayList<>();
        fwalls = new ArrayList<>();
        for (int a = 0; a <= 3; a++)
        {
            int r = hero.getR();
            int c = hero.getC();
            char d = hero.getD();
            bottomWall(a); //bottom trapezoids
            ceilingWall(a);  //ceiling trapezoids
            leftWall(a);  //left trapezoids
            rightWall(a); //right trapezoids
            leftPath(a);
            rightPath(a);

        }
        
        //distance from wall
        for (int a = 3; a >= 0; a--)
        {
            int r = hero.getR();
            int c = hero.getC();
            char d = hero.getD();
            ceilingWall(a);
            bottomWall(a);
            try
            {
                if ((d == 'N' && visited[r-a][c] == true) || (d == 'S' && visited[r+a][c] == true) || (d == 'E' && visited[r][c+a] == true) || (d == 'W' && visited[r][c-a] == true))
                    bwalls.get(bwalls.size()-1).setColor(Color.YELLOW);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {

            }
            switch(d)
            {
                //wall on left in east is row index decrease (row above) and columns index increase (columns on right)
                case 'E':
                    try
                    {   if (maze[r-1][c+a].equals("*")) 
                            leftWall(a);
                        if (maze[r+1][c+a].equals("*")) 
                            rightWall(a);
                        if (maze[r][c+1+a].equals("*"))
                            front(a); //front wall square
                        break;
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        front(a);
                    }
                //wall on left in west is row index increase (row below) and columns index decrease (columns on left)
                case 'W':
                    try
                    {
                        if (maze[r+1][c-a].equals("*"))
                            leftWall(a);
                        if (maze[r-1][c-a].equals("*")) 
                            rightWall(a);
                        if (maze[r][c-1-a].equals("*"))
                            front(a); //front wall square
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        front(a);
                    }
                    break;
                //wall on left in north is rows decrease (row above) and columns index decrease (columns on left)
                case 'N':
                    try
                    {    
                        if (maze[r-a][c-1].equals("*"))
                            leftWall(a);
                        if (maze[r-a][c+1].equals("*"))
                            rightWall(a);
                        if (maze[r-1-a][c].equals("*"))
                            front(a); //front wall square
                        break;
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        front(a);
                    }
                //wall on left in south is row index increase (row below) and column index increase (columns on right)
                case 'S':
                    try
                    {   
                        if (maze[r+a][c+1].equals("*"))
                            leftWall(a);
                        if (maze[r+a][c-1].equals("*"))
                            rightWall(a);
                        if (maze[r+1+a][c].equals("*"))
                            front(a); //front wall square
                        break;
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        front(a);
                    }
                    break;
            }
        }

    }




    public void leftPath(int a)
    {
        //rectangles initialize
        int[] xl = {100 + 50*a, 150+50*a, 150 + 50*a, 100 + 50*a};
        int[] yl = {100 + 50*a, 100 + 50*a, 500 - 50*a, 500 - 50*a};
        //rectangles paint
        lwalls.add(new Wall(xl, yl, 255-50*a, 255-50*a, 255-50*a, "Left", a, null));
    }


    public void leftWall(int a)
    {
        //trapezoids
        int[] x = {100 + 50*a, 150+50*a, 150 + 50*a, 100 + 50*a};
        int[] y = {50 + 50*a, 100 + 50*a, 500 - 50*a, 550 - 50*a};
        lwalls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "Left", a, null));
    }

    public void rightPath(int a)
    {
        int[] xr = {900 - 50*a, 850-50*a, 850 - 50*a, 900 - 50*a};
        int[] yr = {100 + 50*a, 100 + 50*a, 500 - 50*a, 500 - 50*a};
        rwalls.add(new Wall(xr, yr, 255-50*a, 255-50*a, 255-50*a, "Right", a, null));    
    }


    public void rightWall(int a)
    {
        //trapezoids
        int[] x = {900 - 50*a, 850-50*a, 850 - 50*a, 900 - 50*a};
        int[] y = {50 + 50*a, 100 + 50*a, 500 - 50*a, 550 - 50*a};
        rwalls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "Right", a, null));
    }
    public void ceilingWall(int a)
    {
        //trapezoids
        int[] x = {100 + 50*a, 900 - 50*a, 850 - 50*a, 150 + 50*a};
        int[] y = {50 + 50*a, 50 + 50*a, 100 + 50*a, 100 + 50*a};
        cwalls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "Ceil", a, null));
    }
    public void bottomWall(int a)
    {
        //trapezoids
        int[] x = {150 + 50*a, 850 - 50*a, 900 - 50*a, 100 + 50*a};
        int[] y = {500 - 50*a, 500 - 50*a, 550 - 50*a, 550 - 50*a};
        bwalls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "Floor", a, null));
    }
    public void front(int a)
    {
        a++;
        //trapezoids
        int[] x = {100 + 50*a, 900 - 50*a, 900 - 50*a, 100 + 50*a};
        int[] y = {50 + 50*a, 50 + 50*a, 550 - 50*a, 550 - 50*a};
        fwalls.add(new Wall(x, y, 255-50*a, 255-50*a, 255-50*a, "Front", a, null));
    }
    public class Wall
    {
        int[] x;
        int[] y;
        private int dist;
        private Color color;
        private String type;
        int r, g, b;
        public Wall(int[] x, int[] y, int r, int g, int b, String type, int dist, Color color)
        {
            this.r = r;
            this.g = g;
            this.b = b;
            this.x = x;
            this.y = y;
            this.type = type;
            this.dist = dist;
            this.color = color;
        }
        public void setColor(Color color)
        {
            this.color = color;
        }
        public Color getBreadCrumb()
        {
            return color;
        }
        public Polygon getPoly()
        {
            return new Polygon(x,y,4);
        }
        public GradientPaint getPaint()
        {
            int endRed = r-50;
            int endG = g-50;
            int endB = b-50;
            if (r<0)
                r = 0;
            if (g<0)
                g = 0;
            if (b<0)
                b=0;
            if (endRed<0)
                endR = 0;
            if (endG<0)
                endG = 0;
            if (endB<0)
                endB = 0;
            Color startColor = new Color(r,g,b);
            Color endColor = new Color(endRed, endG, endB);
            switch (type)
            {
                case "Left":
                case "Right":
                    return new GradientPaint(x[0], y[0],startColor,x[1], y[0], endColor);
                case "Floor":
                    return new GradientPaint(x[3], y[3],startColor,x[3], y[0], endColor);
            }
            return new GradientPaint(x[0], y[0],startColor,x[0], y[2], endColor);
        }
    }
    public class Hero
    {
        int r, c;
        char d;
        public Hero()
        {
            r = 0;
            c = 0;
            d = 0;
        }
        public Hero(int r, int c, char d)
        {
            this.r = r;
            this.c = c;
            this.d = d;
        }
        public int getR()
        {
            return r;
        }
        public int getC()
        {
            return c;
        }
        public char getD()
        {
            return d;
        }
        public void move(int keycode)
        {
            System.out.println(getD());
            switch(keycode)
            {
                //a for left
                case 65:
                    switch (d)
                    {
                        //east left turns north
                        case 'E':
                            d = 'N';
                            break;
                        //north left turns west
                        case 'N':
                            d = 'W';
                            break;
                        //west left turns south
                        case 'W':
                            d = 'S';
                            break;
                        //south left turns east
                        case 'S':
                            d = 'E';
                            break;
                    }
                    break;
                //d for right
                case 68:
                    switch (d)
                    {
                        //east right turns south
                        case 'E':
                            d = 'S';
                            break;
                        //south right turns west
                        case 'S':
                            d = 'W';
                            break;
                        //west right turns north
                        case 'W':
                            d = 'N';
                            break;
                        //north right turns east
                        case 'N':
                            d = 'E';
                            break;
                    }
                    break;
                //w for forward
                case 87:
                    switch(d)
                    {
                        //going north 
                        case 'N':
                            try
                            {
                                if (!maze[r-1][c].equals("*"))
                                    r--;
                            }
                            catch (ArrayIndexOutOfBoundsException e)
                            {
                                //none
                            }
                            break;
                        case 'S':
                            try
                            {
                                if (!maze[r+1][c].equals("*"))
                                    r++;
                            }
                            catch (ArrayIndexOutOfBoundsException e)
                            {
                                //none
                            }
                            break;
                        case 'E':
                            try
                            {
                                if (!maze[r][c+1].equals("*"))
                                    c++;
                            }
                            catch (ArrayIndexOutOfBoundsException e)
                            {
                                //none
                            }
                            break;
                        case 'W':
                            try
                            {
                                if (!maze[r][c-1].equals("*"))
                                    c--;
                            }
                            catch (ArrayIndexOutOfBoundsException e)
                            {
                                //none
                            }
                            break;
                    }
                    visited[r][c] = true;
                    break;
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        //space bar to switch view
        if (e.getKeyCode()==32)
        {
            in2D = !in2D;
        }
        hero.move(e.getKeyCode());
        if (!in2D)
            set3D();
       
        System.out.println(e.getKeyCode());
        frame.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    public static void main(String[]args)
    {
        MazeProgram app = new MazeProgram();
    }
}
