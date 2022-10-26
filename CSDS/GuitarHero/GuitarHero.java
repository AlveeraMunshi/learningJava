package GuitarHero;

import java.io.*;

public class GuitarHero {
    public GuitarHero()
    {

        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/GuitarHero/GuitarHeroInput.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            int count = 0;
            String[][] outputGrid = null;
            //Listed in order going down, 29 notes
            String[] noteNames = {"G#", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A", "G#", "G", "F#", "F", "E"};
            //Row positions of note names in the fret location
            int[][] helper = {{29,24,19,14,10,5},
                              {28,23,18,13,9,4},
                              {27,22,17,12,8,3},
                              {26,21,16,11,7,2},
                              {25,20,15,10,6,1}};
            int row = 0;
            String[][] song = null;
            boolean first = true;
            int column = 1;
            boolean[] thatB = null;
            while ((text = reader.readLine()) != null)
            {
                String[] pieces = text.split(",");
                if (first)
                {
                    boolean notdone = true;
                    outputGrid = new String[30][pieces.length+1];
                    thatB = new boolean[pieces.length+1];
                    for (int x = 0; x < thatB.length; x++)
                    {
                        thatB[x] = false;
                    }
                    int extra = 1;
                    //initalize row labels with note names
                    for (int r = 1; r <= noteNames.length; r++)
                    {
                        outputGrid[r][0] = noteNames[r-1];
                    }
                    //initialize column labels with measure #s
                    outputGrid[0][0] = "Measure";
                    for (int c = 1; c <= pieces.length; c++)
                    {
                        outputGrid[0][c] = "" + c;
                    }
                    first = false;
                }
                int extra = 0;
                //go through measures
                for (int x = 0; x < pieces.length; x++)
                {
                    //go through columns in each measure
                    for (int y = 0; y < 6; y++)
                    {
                        int fp = helper[row][y];
                        String note = "";
                        if (pieces[x].charAt(y)!= 'x' && pieces[x].charAt(y)!= '-')
                        {
                            note = "o";
                            // when in the third column and there's a star
                            if (row == 4 & y == 3 && pieces[x].charAt(y)== '*')
                                thatB[x+1] = true;
                        }
                        outputGrid[fp][x+1] = note;
                    }
                    //count++;
                }
                //columns
                for (int c = 1; c < outputGrid[0].length; c++)
                {
                    boolean highNote = false;
                    //rows
                    for (int r = 1; r < outputGrid.length; r++)
                    {
                        //identify highnote
                        if (!highNote)
                        {

                            if (outputGrid[r][c]!=null && outputGrid[r][c].equals("o"))
                                highNote = true;
                        }
                        //next time its just empty
                        else
                        {
                            if (outputGrid[r][c]!=null && outputGrid[r][c].equals("o"))
                            outputGrid[r][c]="";
                        }
                        //location of B
                        if (r==10)
                        {
                            //If exists, mark it
                            if (thatB[c])
                            {
                                outputGrid[r][c] = "o";
                            }
                        }
                        //resets every 5 because column ends
                        if ((r<10 && r%5==0) || (r>=10 && r%5==4))
                       // if(r%5==0)
                        {
                            highNote = false;
                        }
                    }
                }
                
                row++;
            }
            //printing
            for (int r = 0; r < outputGrid.length; r++)
            {
                for (int c = 0; c < outputGrid[0].length; c++)
                {
                    if (outputGrid[r][c] == null)
                        System.out.print("\t");
                    else   
                        System.out.print(outputGrid[r][c] + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            // TODO: handle exception

        }
    }
    public static void main(String[]args)
    {
        GuitarHero app = new GuitarHero();
    }
}
