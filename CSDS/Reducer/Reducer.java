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
            while ((text = reader.readLine()) != null)
            {
                //get fraction
                String[] pieces = text.split("/");
                int num = Integer.parseInt(pieces[0]);
                int denom = Integer.parseInt(pieces[1]);
                String ans = calculateReduction(num, denom);
                System.out.println(num + " / " + denom + " = " + ans);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public String calculateReduction(int n, int d)
    {
        //invalid input
        if (d == 0)
        {
            return "indeterminate";
        }

        //whole and remainder
        int whole = n/d;
        int remainder = n%d;

        if (remainder == 0)
        {
            return String.valOf(whole);
        }

        //simplify remainder
        int gcf = getGCF(d, remainder);
        remainder = remainder/gcf;
        d = d/gcf;
        
        if (whole == 0)
        {
            return String.valOf(remainder + "/" + d);
        }
        else
        {
            return String.valOf(whole + " " + remainder + "/" + d);
        }

    }
    public int getGCF(int n1, int n2)
    {
        boolean found = false;

        int gcf = 0;
        if (n1 > n2)
            gcf = n2+1;
        else
            gcf = n1+1;
        while (!found)
        {
            gcf--;
            if (n1%gcf == 0 && n2%gcf == 0)
                found = true;
        }
        return gcf;
    }
    public static void main(String[]args)
    {
        Reducer app = new Reducer();
    }
}
