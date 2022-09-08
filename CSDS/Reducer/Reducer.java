package Reducer;

import java.io.*;
import java.util.ArrayList;

public class Reducer {
    public Reducer()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Reducer/Reducer.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            System.out.println(reader.readLine());
            while ((text = reader.readLine()) != null)
            {
                String[] pieces = text.split("/");
                int num = Integer.parseInt(pieces[0]);
                int denom = Integer.parseInt(pieces[1]);
                int remainder = num%denom;
                ArrayList<Integer> cf = new ArrayList<>();
                for (int x = 1; x <= remainder; x++)
                {
                    if (denom%x == 0)
                        cf.add(x);
                }
                remainder = remainder/cf.get(cf.size()-1);
                denom = denom/cf.get(cf.size()-1);
                if (num < denom)
                {
                    System.out.println(num + " / " + denom + " = " + remainder + "/" + denom);
                }
                else
                {
                    int whole = (num - remainder)/denom;
                    System.out.println(num + " / " + denom + " = " + whole + " " + remainder + "/" + denom);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public static void main(String[]args)
    {
        Reducer app = new Reducer();
    }
}
