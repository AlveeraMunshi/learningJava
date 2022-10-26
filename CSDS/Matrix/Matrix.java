package Matrix;

import java.io.*;
import java.util.ArrayList;

public class Matrix {
    public Matrix()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Matrix/matrixinput.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
            {
                String[] matrixes = text.split("\t"); //{{1,2},{3,4}} {{4,5},{6,7}}
                String matrix1 = matrixes[0].substring(2, matrixes[0].length()-2); //1,2},{3,4
                String matrix2 = matrixes[1].substring(2, matrixes[1].length()-2); //4,5},{6,7
                matrix1 = matrix1.replaceAll("\\{", ""); //1,2},3,4
                matrix2 = matrix2.replaceAll("\\{", ""); //4,5},6,7
                String[] a1 = matrix1.split("},"); //1,2  3,4
                int al = a1[0].split(",").length; 
                String[][] a2 = new String[a1.length][al];
                for (int x = 0; x < a1.length; x++)
                {
                    String[] row = a1[x].split(",");
                    for (int y = 0; y < al; y++)
                    {
                        a2[x][y] = row[y];
                    }
                }
                String[] b1 = matrix2.split("},"); //4,5  6,7
                int bl = b1[0].split(",").length; 
                String[][] b2 = new String[b1.length][bl];
                for (int x = 0; x < b1.length; x++)
                {
                    String[] row = b1[x].split(",");
                    for (int y = 0; y < al; y++)
                    {
                        b2[x][y] = row[y];
                    }
                }
                int[][] addition = new int[b1.length][bl];
                addsub(a2, b2, "A", addition);
                int[][] subtraction = new int[b1.length][bl];
                addsub(a2, b2, "S", subtraction);
                int[][] product = new int[a2.length][b2[0].length];
                product(a2, b2, product);
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
    public void addsub(String[][] m1, String[][] m2, String letter, int[][] ans)
    {
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
        for (int x1 = 0; x1 < m1.length; x1++) {
            for (int y2 = 0; y2 < m2[0].length; y2++) {
                ans[x1][y2] = 0;
                for (int x2 = 0; x2 < m2.length; x2++)
                    ans[x1][y2] += Integer.parseInt(m1[x1][x2]) * Integer.parseInt(m2[x2][y2]);
                System.out.print(ans[x1][y2] + " ");
            }
            System.out.println("");
        }
    }
    public static void main(String[]args)
    {
        Matrix app = new Matrix();
    }
}
