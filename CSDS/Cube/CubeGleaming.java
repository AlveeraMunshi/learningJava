package Cube;

import java.io.*;

public class CubeGleaming {
    public CubeGleaming()
    {
        int up = 1;
        int north = 2;
        
        File file = new File("text.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
            {
                String[] pieces = text.split(" ");
                int num1 = Integer.parseInt(pieces[0]);
                int num2 = Integer.parseInt(pieces[1]);
                int sum = num1 + num2;
                System.out.println(num1 + " + " + num2 + " = " + sum);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public static void main(String[]args)
    {
        CubeGleaming app = new CubeGleaming();
    }
}
