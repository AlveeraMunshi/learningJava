package APCSA.ArrayLists.files;

import java.util.ArrayList;

public class ArrayListTasks
{
  public ArrayListTasks()
  {
      //1
      ArrayList<Integer> list = new ArrayList<Integer>();
      //2
      for (int x = 0; x < 40; x++)
      {
        list.add((int)(Math.random()*100+1));
      }
      System.out.println(list);
      //3
      list.add(list.remove(0));
      System.out.println(list);
      //4
      for (int x = 0; x < list.size(); x++)
      {
        if (list.get(x)%2 == 0)
        {
          list.remove(x);
          x--;
        }
      }
      System.out.println(list);
      //5
      for (int x = list.size()-1; x > 0; x--)
      {
        if (x%2 == 1)
          list.remove(x);
      }
      System.out.println(list);
      //6
      for (int x = 0; x < list.size(); x++)
      {
        if (list.get(x)%2 == 0)
          list.set(x, list.get(x)*1000);
      }
      System.out.println(list);
      //7
      for (int x = 0; x < list.size(); x++)
      {
        if (list.get(x)%2 == 1)
          list.set(x, list.get(x)%10);
      }
      System.out.println(list);
      //8
      for (int x = 0; x < list.size(); x++)
      {
        if (list.get(x)%2 == 0)
          list.add(list.get(x));
      }
      System.out.println(list);
      //9
      for (int x = 0; x < list.size(); x++)
      {
        if (list.get(x)%2 == 0)
          list.set(0, list.remove(x));
      }
      System.out.println(list);
      //10
      for (int x = list.size()-1; x > 0; x--)
      {
        if (x%2 == 1)
          list.remove(x);
      }
      System.out.println(list);
      //11
      int sum = 0;
      for (int x = 0; x < list.size()-1; x+=2)
      {
        int diff;
        if (list.get(x) > list.get(x+1))
          diff = list.get(x) - list.get(x+1);
        else
          diff = list.get(x+1) - list.get(x);
        System.out.println("The difference between " + list.get(x) + " and " + list.get(x+1) + " is " + diff);
        sum+=diff;
      }
      System.out.println("Sum: " + sum);
      //12
      sum = 0;
      for (int x = 0; x < list.size(); x++)
      {
        sum+=list.get(x);
      }
      int mean = sum/list.size();
      System.out.println("Mean: " + mean);
      //13
      sum = 0;
      for (int x = 0; x < list.size(); x++)
      {
        int diff;
        if (list.get(x) > mean)
          diff = list.get(x) - mean;
        else
          diff = mean - list.get(x);
        System.out.println("The difference between " + list.get(x) + " and " + mean + " is " + diff);
        int squarediff = diff*diff;
        sum+=squarediff;
      }
      double avg = sum/(double)list.size();
      double std = Math.sqrt(avg);
      System.out.println("Standard Deviation: " + std);
    }
  public static void main(String[]args)
  {
    ArrayListTasks app = new ArrayListTasks();
  }
}
