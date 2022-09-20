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
                String matrix1 = matrixes[0].substring(1, matrixes[0].length()-1); //{1,2},{3,4}
                String matrix2 = matrixes[1].substring(1, matrixes[1].length()-1); //{4,5},{6,7}
                String[] m1 = matrix1.split(","); //{1,2} {3,4}
                String[] m2 = matrix2.split(","); //{4,5} {6,7}
                for (int x = 0; x < matrix1.length(); x++)
                {
                    String currentterm = 
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
    public static void main(String[]args)
    {
        Matrix app = new Matrix();
    }
}
