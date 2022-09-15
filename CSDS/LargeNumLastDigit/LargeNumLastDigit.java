package LargeNumLastDigit;

import java.io.*;
import java.math.BigInteger;

public class LargeNumLastDigit {
    public LargeNumLastDigit()
    {

        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/LargeNumLastDigit/LargeNumDataSet.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
            {
                String[] pieces = text.split(" ");
                BigInteger base = new BigInteger(pieces[0]);
                BigInteger power = new BigInteger(pieces[1]);
                BigInteger mod = new BigInteger("10");
                BigInteger result = base.modPow(power, mod);
                String ans = result.toString();
                System.out.println(ans);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public static void main(String[]args)
    {
        LargeNumLastDigit app = new LargeNumLastDigit();
    }
}
