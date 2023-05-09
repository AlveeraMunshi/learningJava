package GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUITask implements ActionListener{
    JFrame frame;
    JPanel kingPanel, babyPanel;
    GridLayout bLayout, mLayout, kLayout;
    JButton north, south, east, west, reset;
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
    int currSize = fontSizesInt[0];
    //4 Color arrays: border color, text color, outline color, text background color
    Color[] colors = {Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA, Color.WHITE, Color.GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK};
    ArrayList<Font> fontList = new ArrayList<Font>();
    Font currFont = new Font("Arial", Font.PLAIN, 18);
    public GUITask()
    {
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

        }
        catch (Exception e)
        {
        }
        //vars
        fontList.add(new Font("Arial", Font.PLAIN, 18));
        fontList.add(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        fontList.add(new Font("Courier New", Font.PLAIN, 18));
        //window
        frame = new JFrame(); //create window
        frame.setSize(1500, 1000); //set size of window
        frame.setLayout(new BorderLayout()); //set layout of window
        kingPanel = new JPanel(); //big panel for entire window
        babyPanel = new JPanel(); //small panel for buttons/menu
        //buttons
        north = new JButton("North");
        north.addActionListener(this);
        south = new JButton("South");
        south.addActionListener(this);
        east = new JButton("East");
        east.addActionListener(this);
        west = new JButton("West");
        west.addActionListener(this);
        reset = new JButton("Reset");
        reset.addActionListener(this);
        menuBar = new JMenuBar();
        //menus
        font = new JMenu("Font");
        font.setOpaque(false);
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
        //iter through menuitems and add to menu
        for (JMenuItem o : fontOptions)
        {
            JMenuItem temp = o;
            temp.setFont(new Font(temp.getText(), Font.PLAIN, 18));
            temp.addActionListener(this);
            font.add(temp);
        }
        for (JMenuItem o : fontSizes)
        {
            o.addActionListener(this);
            size.add(o);
        }
        for (JMenuItem o : textColors)
        {
            o.addActionListener(this);
            color.add(o);
        }
        for (JMenuItem o : textBGColors)
        {
            o.addActionListener(this);
            bgcolor.add(o);
        }
        for (JMenuItem o : buttonOutlineColors)
        {
            o.addActionListener(this);
            olcolor.add(o);
        }
        //jtextarea
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(textArea, BorderLayout.CENTER);


        //setup
        babyPanel.setLayout(new GridLayout(1,4));
        menuBar.setLayout(new GridLayout(1, 6));
        addParts();
        kingPanel.setLayout(new GridLayout(1,2));
        kingPanel.add(babyPanel);
        kingPanel.add(menuBar);
        frame.add(kingPanel, BorderLayout.NORTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.revalidate(); //repaint for GUI
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
       

    }
    public void updateFont(Font currFont)
    {
        Font buttonFont = new Font(currFont.getName(), Font.PLAIN, 18);
        textArea.setFont(currFont);
        north.setFont(buttonFont);
        south.setFont(buttonFont);
        east.setFont(buttonFont);
        west.setFont(buttonFont);
    }
    public void updateTextColor(Color currColor)
    {
        textArea.setForeground(currColor);
        north.setForeground(currColor);
        south.setForeground(currColor);
        east.setForeground(currColor);
        west.setForeground(currColor);
        font.setForeground(currColor);
        size.setForeground(currColor);
        color.setForeground(currColor);
        bgcolor.setForeground(currColor);
        olcolor.setForeground(currColor);
        reset.setForeground(currColor);
    }
    public static void main (String[] args)
    {
        GUITask gui = new GUITask();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == east)
        {
            System.out.println("East");
            frame.remove(kingPanel);
            kingPanel.remove(babyPanel);
            kingPanel.remove(menuBar);
            kLayout = new GridLayout(2,1);
            bLayout = new GridLayout(4,1);
            mLayout = new GridLayout(6,1);
            babyPanel.setLayout(bLayout);
            kingPanel.setLayout(kLayout);
            menuBar.setLayout(mLayout);
            kingPanel.add(babyPanel, BorderLayout.EAST);
            kingPanel.add(menuBar, BorderLayout.EAST);
            frame.add(kingPanel, BorderLayout.EAST);
        }
        else if (e.getSource() == west)
        {
            System.out.println("West");
            frame.remove(kingPanel);
            kingPanel.remove(babyPanel);
            kingPanel.remove(menuBar);
            kLayout = new GridLayout(2,1);
            bLayout = new GridLayout(4,1);
            mLayout = new GridLayout(6,1);
            babyPanel.setLayout(bLayout);
            kingPanel.setLayout(kLayout);
            menuBar.setLayout(mLayout);
            kingPanel.add(babyPanel, BorderLayout.WEST);
            kingPanel.add(menuBar, BorderLayout.WEST);
            frame.add(kingPanel, BorderLayout.WEST);
        }
        else if (e.getSource() == north)
        {
            System.out.print("North");
            frame.remove(kingPanel);
            kingPanel.remove(babyPanel);
            kingPanel.remove(menuBar);
            kLayout = new GridLayout(1, 2);
            bLayout = new GridLayout(1,4);
            mLayout = new GridLayout(1,6);
            babyPanel.setLayout(bLayout);
            kingPanel.setLayout(kLayout);
            menuBar.setLayout(mLayout);
            kingPanel.add(babyPanel, BorderLayout.NORTH);
            kingPanel.add(menuBar, BorderLayout.NORTH);
            frame.add(kingPanel, BorderLayout.NORTH);
        }
        else if (e.getSource() == south)
        {
            System.out.print("South");
            frame.remove(kingPanel);
            kingPanel.remove(babyPanel);
            kingPanel.remove(menuBar);
            kLayout = new GridLayout(1, 2);
            bLayout = new GridLayout(1,4);
            mLayout = new GridLayout(1,6);
            babyPanel.setLayout(bLayout);
            kingPanel.setLayout(kLayout);
            menuBar.setLayout(mLayout);
            kingPanel.add(babyPanel, BorderLayout.SOUTH);
            kingPanel.add(menuBar, BorderLayout.SOUTH);
            frame.add(kingPanel, BorderLayout.SOUTH);
        }
        else if (e.getSource() == reset)
        {
            currFont = new Font("Arial", Font.PLAIN, 18);
            updateFont(currFont);
            north.setBorderPainted(false);
            south.setBorderPainted(false);
            east.setBorderPainted(false);
            west.setBorderPainted(false);
            reset.setBorderPainted(false);
            updateTextColor(Color.BLACK);
            textArea.setBackground(Color.WHITE);
            frame.remove(kingPanel);
            kingPanel.remove(babyPanel);
            kingPanel.remove(menuBar);
            bLayout = new GridLayout(1,4);
            kLayout = new GridLayout(1, 2);
            mLayout = new GridLayout(1,6);
            babyPanel.setLayout(bLayout);
            kingPanel.setLayout(kLayout);
            kingPanel.add(babyPanel);
            kingPanel.add(menuBar);
            frame.add(kingPanel, BorderLayout.NORTH);

            System.out.print("North");
            frame.remove(kingPanel);
            kingPanel.remove(babyPanel);
            kingPanel.remove(menuBar);
            kLayout = new GridLayout(1, 2);
            bLayout = new GridLayout(1,4);
            mLayout = new GridLayout(1,6);
            babyPanel.setLayout(bLayout);
            kingPanel.setLayout(kLayout);
            menuBar.setLayout(mLayout);
            kingPanel.add(babyPanel, BorderLayout.NORTH);
            kingPanel.add(menuBar, BorderLayout.NORTH);
            frame.add(kingPanel, BorderLayout.NORTH);
        }
        else if (e.getSource() == fontOptions.get(0))
        {
            currFont = new Font(fontNames[0], currFont.getStyle(), currSize);
            updateFont(currFont);
        }
        else if (e.getSource() == fontOptions.get(1))
        {
            currFont = new Font(fontNames[1], currFont.getStyle(), currSize);
            updateFont(currFont);
        }
        else if (e.getSource() == fontOptions.get(2))
        {
            currFont = new Font(fontNames[2], currFont.getStyle(), currSize);
            updateFont(currFont);
        }
        else if (e.getSource() == fontSizes.get(0))
        {
            currSize = fontSizesInt[0];
            currFont = new Font(currFont.getName(), currFont.getStyle(), currSize);
            updateFont(currFont);
        }
        else if (e.getSource() == fontSizes.get(1))
        {
            currSize = fontSizesInt[1];
            currFont = new Font(currFont.getName(), currFont.getStyle(), currSize);
            updateFont(currFont);
        }
        else if (e.getSource() == fontSizes.get(2))
        {
            currSize = fontSizesInt[2];
            currFont = new Font(currFont.getName(), currFont.getStyle(), currSize);
            updateFont(currFont);
        }
        else if (e.getSource() == textColors.get(0))
        {
            updateTextColor(colors[0]);
        }
        else if (e.getSource() == textColors.get(1))
        {
            updateTextColor(colors[1]);
        }
        else if (e.getSource() == textColors.get(2))
        {
            Color rc = colors[(int)(Math.random()*colors.length)];
            updateTextColor(rc);
        }
        else if (e.getSource() == textBGColors.get(0))
        {
            textArea.setBackground(colors[0]);
        }
        else if (e.getSource() == textBGColors.get(1))
        {
            textArea.setBackground(colors[1]);
        }
        else if (e.getSource() == textBGColors.get(2))
        {
            Color rc = colors[(int)(Math.random()*colors.length)];
            textArea.setBackground(rc);
        }
        else if (e.getSource() == north)
        {
            System.out.println("North");
            north.setBorderPainted(true);
            south.setBorderPainted(false);
            east.setBorderPainted(false);
            west.setBorderPainted(false);
            reset.setBorderPainted(false);
        }
        else if (e.getSource() == south)
        {
            System.out.println("South");
            north.setBorderPainted(false);
            south.setBorderPainted(true);
            east.setBorderPainted(false);
            west.setBorderPainted(false);
            reset.setBorderPainted(false);
        }
        else if (e.getSource() == east)
        {
            System.out.println("East");
            north.setBorderPainted(false);
            south.setBorderPainted(false);
            east.setBorderPainted(true);
            west.setBorderPainted(false);
            reset.setBorderPainted(false);
        }
        else if (e.getSource() == west)
        {
            System.out.println("West");
            north.setBorderPainted(false);
            south.setBorderPainted(false);
            east.setBorderPainted(false);
            west.setBorderPainted(true);
            reset.setBorderPainted(false);
        }
        else if (e.getSource() == reset)
        {
            System.out.println("Reset");
            north.setBorderPainted(false);
            south.setBorderPainted(false);
            east.setBorderPainted(false);
            west.setBorderPainted(false);
            reset.setBorderPainted(true);
        }
        else if (e.getSource() == buttonOutlineColors.get(0))
        {
            north.setBorderPainted(false);
            south.setBorderPainted(false);
            east.setBorderPainted(false);
            west.setBorderPainted(false);
            reset.setBorderPainted(false);
        }
        else if (e.getSource() == buttonOutlineColors.get(1))
        {
            north.setBorderPainted(true);
            south.setBorderPainted(true);
            east.setBorderPainted(true);
            west.setBorderPainted(true);
            reset.setBorderPainted(true);
            north.setBorder(new LineBorder(colors[0]));
            south.setBorder(new LineBorder(colors[0]));
            east.setBorder(new LineBorder(colors[0]));
            west.setBorder(new LineBorder(colors[0]));
            reset.setBorder(new LineBorder(colors[0]));
        }
        else if (e.getSource() == buttonOutlineColors.get(2))
        {
            north.setBorderPainted(true);
            south.setBorderPainted(true);
            east.setBorderPainted(true);
            west.setBorderPainted(true);
            reset.setBorderPainted(true);
            north.setBorder(new LineBorder(colors[1]));
            south.setBorder(new LineBorder(colors[1]));
            east.setBorder(new LineBorder(colors[1]));
            west.setBorder(new LineBorder(colors[1]));
            reset.setBorder(new LineBorder(colors[1]));
        }
        else if (e.getSource() == buttonOutlineColors.get(3))
        {
            north.setBorderPainted(true);
            south.setBorderPainted(true);
            east.setBorderPainted(true);
            west.setBorderPainted(true);
            reset.setBorderPainted(true);
            north.setBorder(new LineBorder(colors[(int)(Math.random()*colors.length)]));
            south.setBorder(new LineBorder(colors[(int)(Math.random()*colors.length)]));
            east.setBorder(new LineBorder(colors[(int)(Math.random()*colors.length)]));
            west.setBorder(new LineBorder(colors[(int)(Math.random()*colors.length)]));
            reset.setBorder(new LineBorder(colors[(int)(Math.random()*colors.length)]));
        }
        frame.revalidate();
    }
}
