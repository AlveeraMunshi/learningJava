package GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUITask extends JFrame{
    JPanel kingPanel, babyPanel;
    GridLayout bLayout, mLayout, kLayout;
    JToggleButton north, south, east, west, reset;
    JMenuBar menuBar;
    JMenu font, size, color, bgcolor, olcolor;
    JTextArea textArea;
    //5 ArrayLists: font options, font sizes, text colors, text background colors, button outline colors
    ArrayList<JMenuItem> fontOptions = new ArrayList<JMenuItem>();
    ArrayList<JMenuItem> fontSizes = new ArrayList<JMenuItem>();
    ArrayList<JMenuItem> textColors = new ArrayList<JMenuItem>();
    ArrayList<JMenuItem> textBGColors = new ArrayList<JMenuItem>();
    ArrayList<JMenuItem> buttonOutlineColors = new ArrayList<JMenuItem>();
    //4 String arrays: font names, background color names, text color names, outline color names
    String[] fontNames = {"Arial", "Times New Roman", "Courier New"};
    String[] bgColorNames = {"Red", "Blue", "Random Color"};
    String[] textColorNames = {"Red", "Blue", "Random Color"};
    String[] outlineColorNames = {"No Color", "Red", "Blue", "Random Color"};
    //1 int arrays: font sizes
    int[] fontSizesInt = {10, 18, 24};
    int currentSize = fontSizesInt[0];
    //4 Color arrays: border color, text color, outline color, text background color
    Color[] colors = {Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA, Color.WHITE, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK};
    //1 font array
    Font[] fonts = {new Font("Arial", Font.PLAIN, 18), new Font("Times New Roman", Font.PLAIN, 18), new Font("Courier New", Font.PLAIN, 18)};
    Font currentFont = fonts[0];
    public GUITask()
    {
        this.setSize(1000, 1000); //set size of window
        this.setLayout(new BorderLayout()); //set layout of window
        kingPanel = new JPanel(); //big panel for entire window
        kLayout = new GridLayout(2, 1); //2 rows, 1 column
        kingPanel.setLayout(kLayout);
        babyPanel = new JPanel(); //small panel for buttons/menu
        //buttons
        north = new JToggleButton("North");
        south = new JToggleButton("South");
        east = new JToggleButton("East");
        west = new JToggleButton("West");
        reset = new JToggleButton("Reset");
        menuBar = new JMenuBar();
        //menus
        font = new JMenu("Font");
        size = new JMenu("Size");
        color = new JMenu("Color");
        bgcolor = new JMenu("Background Color");
        olcolor = new JMenu("Outline Color");
        //menu items
        fontOptions.add(new JMenuItem("Arial"));
        fontOptions.add(new JMenuItem("Times New Roman"));
        fontOptions.add(new JMenuItem("Courier New"));
        fontSizes.add(new JMenuItem("10"));
        fontSizes.add(new JMenuItem("18"));
        fontSizes.add(new JMenuItem("24"));
        textColors.add(new JMenuItem("Red"));
        textColors.add(new JMenuItem("Blue"));
        textColors.add(new JMenuItem("Random Color"));
        textBGColors.add(new JMenuItem("Red"));
        textBGColors.add(new JMenuItem("Blue"));
        textBGColors.add(new JMenuItem("Random Color"));
        buttonOutlineColors.add(new JMenuItem("No Color"));
        buttonOutlineColors.add(new JMenuItem("Red"));
        buttonOutlineColors.add(new JMenuItem("Blue"));
        buttonOutlineColors.add(new JMenuItem("Random Color"));
        //action listeners
        fontOptions.get(0).addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                currentFont = fonts[0];
                textArea.setFont(currentFont);
            }
        });
        north.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(north.isSelected())
                {
                    //textArea.append("North");
                    System.out.print("North");
                    kingPanel.remove(babyPanel);
                    bLayout = new GridLayout(1,4);
                    mLayout = new GridLayout(1,6);
                    babyPanel.setLayout(bLayout);
                    kingPanel.add(babyPanel, BorderLayout.NORTH);
                }
            }
        });
        south.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(south.isSelected())
                {
                    //textArea.append("South");
                    System.out.print("South");
                    kingPanel.remove(babyPanel);
                    bLayout = new GridLayout(1,4);
                    mLayout = new GridLayout(1,6);
                    babyPanel.setLayout(bLayout);
                    kingPanel.add(babyPanel, BorderLayout.SOUTH);
                }
            }
        });
        east.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(east.isSelected())
                {
                    //textArea.append("East");
                    System.out.println("East");
                    kingPanel.remove(babyPanel);
                    bLayout = new GridLayout(4,1);
                    mLayout = new GridLayout(6,1);
                    babyPanel.setLayout(bLayout);
                    kingPanel.add(babyPanel, BorderLayout.EAST);
                }
            }
        });
        west.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(west.isSelected())
                {
                    //textArea.append("West");
                    System.out.println("West");
                    kingPanel.remove(babyPanel);
                    bLayout = new GridLayout(4,1);
                    mLayout = new GridLayout(6,1);
                    babyPanel.setLayout(bLayout);
                    kingPanel.add(babyPanel, BorderLayout.WEST);
                }
            }
        });
        reset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(reset.isSelected())
                {
                    //textArea.append("Reset");
                    //kingPanel.remove(babyPanel);
                }
            }
        });


        //setup
        babyPanel.setLayout(new GridLayout(1,10));
        addParts();
        kingPanel.add(babyPanel, BorderLayout.NORTH);
        this.add(kingPanel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.revalidate(); //repaint for GUI
    }
    public void addParts()
    {
        babyPanel.add(north);
        babyPanel.add(south);
        babyPanel.add(east);
        babyPanel.add(west);
        menuBar.add(font);
        menuBar.add(size);
        menuBar.add(color);
        menuBar.add(bgcolor);
        menuBar.add(olcolor);
        menuBar.add(reset);
        babyPanel.add(menuBar);
    }
    public static void main (String[] args)
    {
        GUITask gui = new GUITask();
    }
}
