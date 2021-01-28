package APCSA.Searches.files;

import java.util.*;

public class LinearBinaryReview
{
  public LinearBinaryReview()
  {
    //Create an arraylist with 100 numbers (from 1 to 500).
    //Each number printed should be 3 more than the previous number printed. (ie. list should be in order).
    ArrayList<Integer> list = new ArrayList<Integer>();
    int initx = (int)(Math.random()*203+1);
    for (int x = 0; x < 100; x++)
    {
      list.add(initx+(3*x));
    }
    System.out.println(list);

    //Generate a random number and perform a linear search to see if the number is in the arraylist.
    //Print out how many comparisons were completed by the end of the search.
    int rand = (int)(Math.random()*500+1);
    int comparisons = 0;
    while ((list.get(comparisons) != rand) && (comparisons < list.size()-1))
    {
      comparisons++;
    }
    System.out.println("There were " + comparisons + " comparisons until reaching an instance of " + rand);

    //Using the same number from the linear search, perform a binary search to see if the number is in the arraylist.
    //Print out how many comparisons were completed by the end of the search.
    comparisons = 0;
    int right = list.size()-1;
    int left = 0;
    while (left <= right)
    {
      int middle = (left+right)/2;
      if (rand < list.get(middle)){
        right = middle - 1;
      }
      else if (rand > list.get(middle)){
        left = middle + 1;
      }
      else
      {
        middle = middle;
      }
      comparisons++;
    }
    System.out.println("There were " + comparisons + " comparisons until reaching an instance of " + rand);

  }
  public static void main(String[] args)
  {
    LinearBinaryReview app = new LinearBinaryReview();
  }
}
