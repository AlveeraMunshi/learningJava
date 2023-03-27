package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Minesweeper extends JFrame implements MouseListener
{
    JPanel buttonPanel;
    //JFrame frame;
    JToggleButton[][] buttons;
    int numMines = 10;
    boolean firstClick = true;
    int clickR, clickC;
    ImageIcon[] numbers = new ImageIcon[8];
    ImageIcon flag, smile, win, dead, wait, mine;
    boolean gameOver = false;
    int clickCount = 0;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem beginner, intermediate, expert;
    JButton reset;
    Timer timer;
    int timePassed = 0;
    JTextField timeField;
    GraphicsEnvironment ge;
    Font clockFont;

    public Minesweeper() 
    {
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

        }
        catch (Exception e)
        {
        }
        //frame = new JFrame("Minesweeper - The Game");
        for (int x = 1; x < 8; x++)
        {
            numbers[x-1] = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/MineSweeperImages/" + x + ".png");
            numbers[x-1] = new ImageIcon(numbers[x-1].getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        }
        flag = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/MineSweeperImages/flag0.png");
        flag = new ImageIcon(flag.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        smile = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/MineSweeperImages/smile0.png");
        smile = new ImageIcon(smile.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        win = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/MineSweeperImages/win0.png");
        win = new ImageIcon(win.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        dead = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/MineSweeperImages/dead0.png");
        dead = new ImageIcon(dead.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        wait = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/MineSweeperImages/wait0.png");
        wait = new ImageIcon(wait.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        mine = new ImageIcon("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI/MineSweeperImages/mine0.png");
        mine = new ImageIcon(mine.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        //setup screen
        this.setSize(1000, 1000);
        setGrid(9,9); //default size
        //reset button
        reset = new JButton();
        reset.setIcon(smile);
        reset.setFocusable(false);
        reset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                firstClick = true;
                gameOver = false;
                clickCount = 0;
                timePassed = 0;
                timeField.setText("   0:00");
                setGrid(buttons.length, buttons[0].length);
                if(timer!=null)
		            timer.cancel();
            }
        });
        menuBar = new JMenuBar();
        menu = new JMenu("Difficulty");
        beginner = new JMenuItem("Beginner");
        beginner.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                numMines = 10;
                setGrid(9,9);
                if(timer!=null)
		            timer.cancel();
            }
        });
        intermediate = new JMenuItem("Intermediate");
        intermediate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                numMines = 40;
                setGrid(16,16);
                if(timer!=null)
		            timer.cancel();
            }
        });
        expert = new JMenuItem("Expert");
        expert.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                numMines = 99;
                setGrid(16,30);
                if(timer!=null)
		            timer.cancel();
            }
        });
        //timer
        try 
        {
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String st = "/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GUI";
            clockFont=Font.createFont(Font.TRUETYPE_FONT, new File(st+"/digital-7.ttf"));
            ge.registerFont(clockFont);
        } 
        catch (IOException|FontFormatException e) 
        {
        }
        timeField = new JTextField("   0:00");
        timeField.setFont(clockFont.deriveFont(18f));
        timeField.setBackground(Color.BLACK);
	    timeField.setForeground(Color.GREEN);
        //add to menu
        menu.add(beginner);
        menu.add(intermediate);
        menu.add(expert);
        //add to menu bar
        menuBar.add(menu);
        menuBar.add(reset);
        menuBar.add(timeField);
        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void setGrid(int rows, int cols)
    {
        if (buttonPanel != null)
            this.remove(buttonPanel);
        buttonPanel = new JPanel();
        buttons = new JToggleButton[rows][cols];
        buttonPanel.setLayout(new GridLayout(rows, cols));
        //init buttons
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < cols; c++)
            {
                buttons[r][c] = new JToggleButton();
                buttons[r][c].addMouseListener(this);
                buttons[r][c].putClientProperty("r", r);
                buttons[r][c].putClientProperty("c", c);
                buttons[r][c].putClientProperty("mineVal", 0); //0 = no mine, -1 = mine
                buttonPanel.add(buttons[r][c]);
                buttons[r][c].setContentAreaFilled(false);
                buttons[r][c].setOpaque(true);

            }
        }
        this.add(buttonPanel);
        this.setSize(cols*60, rows*60);
        this.revalidate(); //repaint for GUI
    }
    public void dropMines(int rows, int cols)
    {
        int mines = 0;
        //set random mines
        while (mines < numMines)
        {
            int r = (int)(Math.random()*rows); //random row
            int c = (int)(Math.random()*cols); //random col
            if (r != clickR && c != clickC && (int)buttons[r][c].getClientProperty("mineVal") == 0) //if not clicked (first) and not a mine
            {
                buttons[r][c].putClientProperty("mineVal", -1); //set to -1 to indicate mine
                mines++;
            }
        }
        //iter through all buttons and set number of mines around it
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < cols; c++)
            {
                int state = (int)buttons[r][c].getClientProperty("mineVal");
                if (state!=-1) //if not a mine
                {
                    int count = 0;
                    for (int rr = r-1; rr<=r+1; rr++)
                    {
                        for (int cc = c-1; cc<=c+1; cc++)
                        {
                            try
                            {
                                if ((int)buttons[rr][cc].getClientProperty("mineVal") == -1) //if mine
                                    count++; //add to count
                            }
                            catch (ArrayIndexOutOfBoundsException e)
                            {
                                
                            }
                        }
                    }
                    buttons[r][c].putClientProperty("mineVal", count); //set to number of mines around it
                }
                //buttons[r][c].setText("" + state); //set to number of mines around it
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.print(e.getSource());
        clickR=(int)((JToggleButton)e.getComponent()).getClientProperty("r"); //get row
        clickC=(int)((JToggleButton)e.getComponent()).getClientProperty("c"); //get col
        if (!gameOver)
        {
            if (e.getButton() == MouseEvent.BUTTON1) //left click
            {
                if (firstClick) //if first click
                {
                    //do stuff
                    dropMines(buttons.length, buttons[0].length); //drop mines
                    firstClick = false;
                    timer= new Timer();
                    timer.schedule(new UpdateTimer(),0,1000);
                }
                if ((int)buttons[clickR][clickC].getClientProperty("mineVal") != -1) //if not lose
                {
                    expand(clickR, clickC);
                    clickCount++;
                    if (clickCount==buttons.length*buttons[0].length-numMines) //if win
                    {
                        //JOptionPane.showMessageDialog(null, "You are a winner! :)");
                        reset.setIcon(win);
                        timer.cancel();
                    }
                }
                else //if lose
                {
                    //disable board
                    gameOver = true;
                    disableBoard();
                    //JOptionPane.showMessageDialog(null, "You are a loser! :(");
                    reset.setIcon(dead);
                    timer.cancel();
                }
            }
            if (!firstClick && e.getButton() == MouseEvent.BUTTON3) //right click
            {
                System.out.println("sldjkhfksjdhf");
                if (buttons[clickR][clickC].isSelected()) //if already selected
                {
                    if (buttons[clickR][clickC].getIcon() == null) //if no flag
                    {
                        buttons[clickR][clickC].setIcon(flag); //set flag
                        buttons[clickR][clickC].setDisabledIcon(flag);
                        buttons[clickC][clickR].setEnabled(false);
                    }
                    else
                    {
                        buttons[clickR][clickC].setIcon(null);
                        buttons[clickR][clickC].setDisabledIcon(null);
                        buttons[clickC][clickR].setEnabled(true);
                    }
                }
            }
        }
        
    }
    public void expand(int row, int col)
    {
        buttons[row][col].setBackground(Color.GRAY);

        if (!buttons[row][col].isSelected()) //if not already selected
        {
            buttons[row][col].setSelected(true); //select it
            clickCount++;
        }
        if ((int)buttons[row][col].getClientProperty("mineVal") > 0) //if not mine
        {
            //buttons[row][col].setText("" + buttons[row][col].getClientProperty("mineVal"));
            buttons[row][col].setIcon(numbers[(int)buttons[row][col].getClientProperty("mineVal")-1]); //set to number of mines around it
            buttons[row][col].setDisabledIcon(numbers[(int)buttons[row][col].getClientProperty("mineVal")-1]);
            buttons[row][col].setEnabled(false);
        }
        else
        {
            for (int r = row-1; r<=row+1; r++) //go through all surrounding buttons (row)
            {
                for (int c = col-1; c<=col+1; c++) //go through all surrounding buttons (col)
                {
                    try
                    {
                        if (!buttons[r][c].isSelected()) //if not already selected
                            expand(r,c); //expand
                    }
                    catch (ArrayIndexOutOfBoundsException e) //if out of bounds
                    {
                        
                    }
                }
            }
        }
    }
    public void disableBoard()
    {
        for(int r = 0; r < buttons.length; r++)
        {
            for(int c = 0; c < buttons[0].length; c++)
            {
                ImageIcon icon = (ImageIcon) buttons[r][c].getIcon();
                //buttons[r][c].setDisabledIcon(icon); //set disabled icon to current icon
                //buttons[r][c].setText("" + buttons[r][c].getClientProperty("mineVal"));
                if (buttons[r][c].getClientProperty("mineVal").equals(-1))
                {
                    buttons[r][c].setIcon(mine);
                    buttons[r][c].setDisabledIcon(mine);
                    //buttons[r][c].setText("" + -1);
                }
                buttons[r][c].setEnabled(false); //disable all buttons
            }
        }
    }
    public static void main(String[] args)
    {
        Minesweeper m = new Minesweeper();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    public class UpdateTimer extends TimerTask {
		public void run() {
			if(!gameOver){
				timePassed++;
                int seconds = timePassed%60;
                int minutes = timePassed/60;
                if (seconds < 10)
                    timeField.setText("  "+minutes+":0"+seconds);
                else
                    timeField.setText("  "+minutes+":"+seconds);
			}
		}
	}
}
