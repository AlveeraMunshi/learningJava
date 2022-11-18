package Matrix;

import java.io.*;
import java.util.ArrayList;

public class Matrix {
    public Matrix()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Matrix/matrixinput.txt");
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
            {
                String[] matrixes = text.split(" "); //{{1,2},{3,4}} {{4,5},{6,7}}
                //cut parentheses on beginning and end {{ and }} of each matrix
                //indexes refer to matrixes from input
                int mi1 = 0;
                int mi2 = 1;
                String matrix1 = matrixes[mi1].substring(2, matrixes[mi1].length()-2); //1,2},{3,4
                String matrix2 = matrixes[mi2].substring(2, matrixes[mi2].length()-2); //4,5},{6,7
                matrix1 = matrix1.replaceAll("\\{", ""); //1,2},3,4
                matrix2 = matrix2.replaceAll("\\{", ""); //4,5},6,7
                
                System.out.println("Matrix 1");
                printMatrix(matrix1);
                System.out.println("\nMatrix 2");
                printMatrix(matrix2);

                String[][] m1 = stringto2D(matrix1);
                String[][] m2 = stringto2D(matrix2);

                //Functions
                int[][] addition = new int[m1.length][m1[0].length];
                addsub(m1, m2, "A", addition);
                int[][] subtraction = new int[m1.length][m1[0].length];
                addsub(m1, m2, "S", subtraction);
                int[][] product = new int[m1.length][m2[0].length];
                System.out.println("Dimensions: " + m1[0].length + "x" + m2.length);
                product(m1, m2, product);
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
    public void printMatrix(String m)
    {
        for (int x = 0; x < m.length(); x++)
        {
            if (m.substring(x, x+1).equals("}"))
                System.out.println();
            else if (m.substring(x, x+1).equals(","))
                System.out.print("");
            else
                System.out.print(m.substring(x, x+1) + " ");
        }
        System.out.println();
    }
    public String[][] stringto2D(String m)
    {
        String[] oneD = m.split("},"); //1,2  3,4 separate rows
        int col = oneD[0].split(",").length; 
        String[][] twoD = new String[oneD.length][col];
        for (int x = 0; x < oneD.length; x++)
        {
            String[] row = oneD[x].split(",");
            for (int y = 0; y < col; y++)
            {
                twoD[x][y] = row[y];
            }
        }
        return twoD;
    }
    public void addsub(String[][] m1, String[][] m2, String letter, int[][] ans)
    {
        if (letter.equalsIgnoreCase("A"))
        {
            System.out.println("Addition");
        }
        else
        {
            System.out.println("Subtraction");
        }
        if (m1.length == m2.length && m1[0].length == m2[0].length)
        {
            
            for (int x = 0; x < m1.length; x++)
            {
                for (int y = 0; y < m1[0].length; y++)
                {
                    if (letter.equalsIgnoreCase("A"))
                    {
                        ans[x][y] = Integer.parseInt(m1[x][y]) + Integer.parseInt(m2[x][y]);
                    }
                    else
                    {
                        ans[x][y] = Integer.parseInt(m1[x][y]) - Integer.parseInt(m2[x][y]);
                    }
                    System.out.print(ans[x][y] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        else
        {
            System.out.println("Canot conduct function");
        }
    }
    public void product(String[][] m1, String[][] m2, int[][] ans)
    {
        System.out.println("Multiplication");
        try
        {
            if (m1[0].length == m2.length)
            {
                for (int x1 = 0; x1 < m1.length; x1++) {
                    for (int y2 = 0; y2 < m2[0].length; y2++) {
                        //ans[x1][y2] = 0;
                        for (int x2 = 0; x2 < m2.length; x2++)
                            ans[x1][y2] += Integer.parseInt(m1[x1][x2]) * Integer.parseInt(m2[x2][y2]);
                        System.out.print(ans[x1][y2] + " ");
                    }
                    System.out.println("");
                }
            }
            else
            {
                System.out.println("Canot conduct function");
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            
        }
        
    }
    public static void main(String[]args)
    {
        Matrix app = new Matrix();
    }
}
