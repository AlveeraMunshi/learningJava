package CompetitionDay2;

import java.io.*;
import java.util.*;

public class EuclidAlgo {
    ArrayList<String> denoms = new ArrayList<String>();
    int sum = 0;
    public EuclidAlgo()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/CompetitionDay2/denoms.txt");
        try 
        {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            while ((text = input.readLine()) != null)
            {
                System.out.println(text);
                denoms.add(text);
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        for (String denom : denoms)
        {
            String[] nums = denom.split("\t");
            int a = Integer.parseInt(nums[0]);
            int b = Integer.parseInt(nums[1]);
            int gcd = euclid(a, b);
            System.out.println(gcd);
            sum+=gcd;
        }
        System.out.println("The sum is " + sum);
    }
    public int euclid(int a, int b)
    {
        if (a < b)
        {
            int temp = a;
            a = b;
            b = temp;
        }
        int gcd;
        if (a%b == 0)
            return b;
        else
        {
            return euclid(b, a%b);
        }
    }
    public static void main(String[] args)
    {
        EuclidAlgo e = new EuclidAlgo();
    }
}
