package APCSA.SearchesSorts.files;

import java.util.*;

public class SelectionInsertionReview
{
  public SelectionInsertionReview()
  {
    //ArrayList Initialization
    ArrayList<Integer> list1 = new ArrayList<Integer>();
    ArrayList<Integer> list2 = new ArrayList<Integer>();
    for (int x = 0; x < 100; x++)
    {
      int rand = (int)(Math.random()*1000+1);
      list1.add(rand);
      list2.add(rand);
    }
    System.out.println(list1);

    //Selection Sort
    for (int x = 0; x < list1.size()-1; x++)
    {
      int smallestIndex = x;
      for (int y = x+1; y < list1.size()-1; y++)
      {
        if (list1.get(smallestIndex)>list1.get(y))
          smallestIndex = y;
      }
      int temp = list1.get(x);
      list1.set(x, list1.get(smallestIndex));
      list1.set(smallestIndex, temp);
    }
    System.out.println(list1);

    //Insertion Sort
    for (int x = 1; x < list2.size()-1; x++)
    {
      int temp = list2.get(x);
      int possibleIndex = x-1;
      while (possibleIndex >= 0 && temp<list2.get(possibleIndex))
      {
        list2.set(possibleIndex+1, list2.get(possibleIndex));
        possibleIndex--;

      }
      list2.set(possibleIndex+1, temp);
    }
    System.out.println(list2);
  }
  public static void main(String[] args)
  {
    SelectionInsertionReview app = new SelectionInsertionReview();
  }
}
