package CompetitionDay2;

import java.io.*;
import java.util.*;

public class StdDev {
    ArrayList<String> lines = new ArrayList<String>();
    ArrayList<Double> stdDevs = new ArrayList<Double>();
    public StdDev()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/CompetitionDay2/populationdata.txt");
        try 
        {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            while ((text = input.readLine()) != null)
            {
                System.out.println(text);
                lines.add(text);
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        for (String line : lines)
        {
            String[] nums = line.split(", ");
            double[] nums2 = new double[nums.length];
            for (int i = 0; i < nums.length; i++)
            {
                nums2[i] = Double.parseDouble(nums[i]);
            }
            double sd = stdDev(nums2);
            stdDevs.add(sd);
            System.out.println(sd);
        }
        //find range of standard deviations
        double max = stdDevs.get(0);
        double min = stdDevs.get(0);
        for (double sd : stdDevs)
        {
            if (sd > max)
                max = sd;
            if (sd < min)
                min = sd;
        }
        System.out.println("The range of standard deviations is " + (max-min));
    }
    public double stdDev(double[] nums)
    {
        //find the mean
        double mean = 0;
        for (double num : nums)
        {
            mean+=num;
        }
        mean/=nums.length;
        //find the sum of the squares of the differences between each number and the mean
        double sum = 0;
        for (double num : nums)
        {
            sum+=Math.pow(num-mean, 2);
        }
        //divide by the number of numbers then square root
        return Math.sqrt(sum/nums.length);
    }
    public static void main(String[] args)
    {
        StdDev s = new StdDev();
    }
}
