package CompetitionDay2;

import java.io.*;
import java.util.*;

public class JugglerSequence {
    ArrayList<String> nums = new ArrayList<String>();
    double maxsum = 0;
    double countsum = 0;
    public JugglerSequence()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/CompetitionDay2/input4.txt");
        try 
        {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            while ((text = input.readLine()) != null)
            {
                nums.add(text);
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        for (String num : nums) // for each number in the list
        {
            /*double n = Double.parseDouble(num); // convert to double
            double count = 0;
            double max = 0;
            boolean first = true;
            boolean increasing = true;
            while (n != 1 || increasing)
            {
                if (n%2 == 0)
                    n = Math.pow(n, 1.5);
                else
                    n = Math.pow(n, 2.5);
                if (first || n > max)
                    max = n;
                if (n < max)
                    increasing = false;
                first = false;
                count++;
            }*/
            maxsum += jummax(Integer.parseInt(num), -1); // add the max of the juggler sequence to the max
            countsum += jumcount(Integer.parseInt(num), 0); // add the count of the juggler sequence to the count
        }
        System.out.println("The sum of the maxes is " + maxsum);
        System.out.println("The sum of the counts is " + countsum);
        maxsum/=nums.size();
        countsum/=nums.size();
        System.out.println("The average max is " + maxsum);
        System.out.println("The average count is " + countsum);
    }
    public int jummax(int N, int max) {
 
        System.out.print(N + " ");
        boolean first = true;
        if (N <= 1)
            return max;
        else if (N % 2 == 0) {
            N = (int) (Math.floor(Math.sqrt(N)));
            if (first || N > max)
                max = N;
            return jummax(N, max);
        } else {
            N = (int) Math.floor(N * Math.sqrt(N));
            if (first || N > max)
                max = N;
            return jummax(N, max);
        }
    }
    public int jumcount(int N, int count) 
    {
        System.out.print(N + " ");
        if (N <= 1)
            return count;
        else if (N % 2 == 0) {
            N = (int) (Math.floor(Math.sqrt(N)));
            count++;
            return jumcount(N, count);
        } else {
            N = (int) Math.floor(N * Math.sqrt(N));
            count++;
            return jumcount(N, count);
        }
    }
    public static void main(String[] args)
    {
        JugglerSequence j = new JugglerSequence();
    }
}
