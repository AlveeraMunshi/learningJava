package APCSA.ArrayLists.files;

import java.util.ArrayList;

public class LearningArrayLists
{
  public LearningArrayLists()
  {
    //1
    System.out.println("Step 1: ");
    ArrayList<Integer> list1 = new ArrayList<Integer>();
    list1 = filler(30);
    output(list1);
    output(sorter(list1,0));
    output(sorter(list1,1));

    //2
    System.out.println("Step 2: ");
    ArrayList<Integer> list2 = new ArrayList<Integer>();
    list2 = filler(100, 100, 1);
    output(list2);
    list2 = removeMultiplesOf3(list2);
    output(list2);
    list2 = oddShift(list2);
    output(list2);
    System.out.println("Size: " + list2.size());
    list2 = sizeFix(list2);
    System.out.println("New Size: " + list2.size());
    output(list2);
    System.out.println("Min: " + minOrMax(list2, 0));
    System.out.println("Max: " + minOrMax(list2, 1)); //set to 0 for min, anything else for max

  }
  public ArrayList<Integer> filler (int n)
  {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int x = 0; x < n; x++)
      list.add((int)(Math.random()*9));
    return list;
  }
  public ArrayList<Integer> filler (int n, int upper, int lower)
  {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int x = 0; x < n; x++)
      list.add((int)(Math.random()*(upper-lower+1)+lower));
    return list;
  }
  public void output(ArrayList<Integer> list)
  {
    System.out.println(list);
  }
  public ArrayList<Integer> sorter(ArrayList<Integer> list, int n)
  {
    int index = 0;
    while (index < list.size())
    {
      if (n == 0) //ascending
      {
        int min = list.get(index);
        int minindex = index;
        for (int x = index; x < list.size(); x++)
        {
          if (list.get(x) < min)
          {
            min = list.get(x);
            minindex = x;
          }
        }
        int temp = list.get(index);
        list.set(index, list.get(minindex));
        list.set(minindex, temp);
      }
      else //descending
      {
        int max = list.get(index);
        int maxindex = index;
        for (int x = index; x < list.size(); x++)
        {
          if (list.get(x) > max)
          {
            max = list.get(x);
            maxindex = x;
          }
        }
        int temp = list.get(index);
        list.set(index, list.get(maxindex));
        list.set(maxindex, temp);
      }
      index++;
    }
    return list;
  }
  public ArrayList<Integer> removeMultiplesOf3(ArrayList<Integer> list)
  {
    for (int x = 0; x < list.size(); x++)
    {
      if (list.get(x)%3 == 0)
        list.remove(x);
    }
    return list;
  }
  public boolean isOdd(int n)
  {
    if (n%2 == 1)
      return true;
    else
      return false;
  }
  public ArrayList<Integer> oddShift(ArrayList<Integer> list)
  {
    for (int x = 0; x < list.size(); x++)
    {
      if (isOdd(list.get(x)))
      {
        if (x == list.size() - 1)
          list.set(x, list.get(0));
        else
          list.set(x, list.get(x+1));
      }
    }
    return list;
  }
  public ArrayList<Integer> sizeFix(ArrayList<Integer> list)
  {
    ArrayList<Integer> finallist = new ArrayList<Integer>();
    if (isOdd(list.size()))
    {
      for (int x = 0; x < 5; x++)
      {
        finallist.add((int)(Math.random()*100+101));
      }
      for (int x = 0; x < list.size(); x++)
      {
        finallist.add(list.get(x));
      }
    }
    else
    {
      for (int x = 0; x < 10; x++)
      {
        finallist.add((int)(Math.random()*100+101));
      }
      for (int x = 0; x < list.size(); x++)
      {
        finallist.add(list.get(x));
      }
      for (int x = 0; x < 10; x++)
      {
        int rand = (int)(Math.random()*list.size()+1);
        while (rand%2 != 0)
          rand = (int)(Math.random()*list.size()+1);
        int temp = finallist.get(x);
        finallist.set(x, finallist.get(rand));
        finallist.set(rand, temp);
      }
    }
    return finallist;
  }
  public int minOrMax(ArrayList<Integer> list, int key)
  {
    int min = list.get(0);
    int max = list.get(0);
    for (int x = 0; x < list.size(); x++)
    {
      if (list.get(x) < min)
      {
        min = list.get(x);
      }
      if (list.get(x) > max)
      {
        max = list.get(x);
      }
    }
    if (key == 0)
      return min;
    else
      return max;
  }
  public static void main (String[]args)
  {
    LearningArrayLists app = new LearningArrayLists();
  }
}
