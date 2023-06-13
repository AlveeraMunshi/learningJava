package CompetitionDay2;

import java.io.*;
import java.util.*;

public class LuhnsAlgo {
    ArrayList<String> cardNums = new ArrayList<String>();
    int valid = 0;
    public LuhnsAlgo()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/CompetitionDay2/cardNumbers.txt");
        try 
        {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            while ((text = input.readLine()) != null)
            {
                System.out.println(text);
                cardNums.add(text);
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        for (String num : cardNums)
        {
            if (checkLuhn(num))
            {
                System.out.println(num + " is a valid card");
                valid++;
            }
            else
                System.out.println(num + " is not a valid card");
        }
        System.out.println("There are " + valid + " valid cards");
    }
    static boolean checkLuhn(String cardNo)
    {
        int nDigits = cardNo.length();
        int nSum = 0;
        int checkDigit = Integer.valueOf(cardNo.substring(nDigits - 1)); // get the check digit, last digit in thing
        boolean isSecond = true;
        //payload
        for (int i = nDigits - 2; i >= 0; i--)
        {
    
            String d = cardNo.substring(i, i+1); // convert char to int
    
            if (isSecond == true) // double every second digit
                d = String.valueOf(Integer.valueOf(d) * 2);
    
            System.out.println(i + " " + d);
            for (int x = 0; x < d.length(); x++)
            {
                nSum += Integer.parseInt(d.substring(x, x+1)); // add the digits of the number
            }
            //The check digit is calculated by first finding the modulus of the sum when divided by 10 and then, secondly, subtracting that remainder from 10.
    
            isSecond = !isSecond;
        }
        System.out.println("nSum: " + nSum);
        int ccd = 10 - (nSum % 10); // calculate the check digit
        System.out.println("ccd: " + ccd);
        System.out.println("checkDigit: " + checkDigit);
        return ccd == checkDigit;
    }
 
    // Driver code
    static public void main (String[] args)
    {
        LuhnsAlgo la = new LuhnsAlgo();
    }
}
