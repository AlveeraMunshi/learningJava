package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends JFrame implements MouseListener
{
    JPanel buttonPanel;
    //JFrame frame;
    JToggleButton[][] buttons;
    int numMines = 10;
    boolean firstClick = true;
    int clickR, clickC;
    public Minesweeper() 
    {
        //frame = new JFrame("Minesweeper - The Game");
        this.setSize(1000, 1000);
        buttonPanel = new JPanel();
        setGrid(9,9);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void setGrid(int rows, int cols)
    {
        if (buttonPanel != null)
            this.remove(buttonPanel);
        this.setSize(cols*40, rows*40);
        buttons = new JToggleButton[rows][cols];
        buttonPanel.setLayout(new GridLayout(rows, cols));
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < cols; c++)
            {
                buttons[r][c] = new JToggleButton();
                buttons[r][c].addMouseListener(this);
                buttons[r][c].putClientProperty("r", r);
                buttons[r][c].putClientProperty("c", c);
                buttons[r][c].putClientProperty("mineVal", -1);
                buttonPanel.add(buttons[r][c]);
            }
        }
        this.add(buttonPanel);
        this.revalidate(); //repaint for GUI
    }
    public void dropMines(int rows, int cols)
    {
        int mines = 0;
        while (mines < numMines)
        {
            int r = (int)(Math.random()*rows);
            int c = (int)(Math.random()*cols);
            if (r != clickR && c != clickC && (int)buttons[r][c].getClientProperty("mineVal") == -1)
            {
                buttons[r][c].putClientProperty("mineVal", 0);
                mines++;
            }
        }
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < cols; c++)
            {
                int state = (int)buttons[r][c].getClientProperty("mineVal");
                buttons[r][c].setText("" + state);
                if (state!=-1)
                {
                    buttons[r][c].setEnabled(false);
                    int count = 0;
                    for (int rr = r-1; rr<=r+1; rr++)
                    {
                        for (int cc = c-1; cc<=c+1; cc++)
                        {
                            try
                            {
                                if ((int)buttons[rr][cc].getClientProperty("mineVal") == -1)
                                    count++;
                            }
                            catch (ArrayIndexOutOfBoundsException e)
                            {
                                
                            }
                        }
                    }
                    buttons[r][c].putClientProperty("mineVal", count);
                }
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.print(e.getSource());
        clickR=(int)((JToggleButton)e.getComponent()).getClientProperty("r");
        clickC=(int)((JToggleButton)e.getComponent()).getClientProperty("c");
        if (firstClick)
        {
            //do stuff
            dropMines(buttons.length, buttons[0].length);
            firstClick = false;
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
}
