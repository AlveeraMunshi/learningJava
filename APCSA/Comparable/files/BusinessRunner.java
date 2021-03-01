package APCSA.Comparable.files;

import java.util.*;

public class BusinessRunner
{
  public BusinessRunner()
  {
    String[] names = {"Burger King", "Wendy's", "T.G.I.F", "K.F.C.", "Taco Bell", "McDonalds", "Chipotle", "Starbucks", "Dunkin Donuts", "Burger King"};
    String[] locations = {"Kendall Park", "Dayton", "Monmouth Junction", "Kendall Park", "Monmouth Junction", "Monmouth Junction", "Dayton", "Kendall Park", "Dayton"};
    String[] phones = {"7323338888", "7326669999", "7325552222", "7323338788", "7325452222", "7325552222", "7326669999", "7323336888", "7326269999"};
    double[] ratings = {5, 4, 3, 2, 1, 0, 5, 4, 3, 2};
    ArrayList<Business> businesses = new ArrayList<Business>();

    for (int x = 0; x < 10; x++)
    {
      int rand1 = (int)(Math.random()*9);
      int rand2 = (int)(Math.random()*9);
      businesses.add(new Business(names[rand1], locations[rand2], phones[rand2], ratings[rand1]));
    }
    System.out.println("Pre-Sorting");
    System.out.println(businesses);
    for (int x = 0; x < businesses.size(); x++)
    {
      int smallestIndex = x;
      for (int y = x+1; y < businesses.size(); y++)
      {
        if (businesses.get(smallestIndex).compareTo(businesses.get(y)) > 0)
          smallestIndex = y;
      }
      Business temp = businesses.get(x);
      businesses.set(x, businesses.get(smallestIndex));
      businesses.set(smallestIndex, temp);
    }
    System.out.println("Post-Sorting");
    System.out.println(businesses);
  }
  public static void main(String[] args) {
    BusinessRunner game = new BusinessRunner();
  }
}
