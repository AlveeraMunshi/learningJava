package APCSA.SearchesSorts.files;

import java.util.*;

public class SelectionInsertionTask
{
  public SelectionInsertionTask()
  {
    ArrayList<Integer> original = fillList(50, 1, 100);
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for (int x = 0; x < original.size()-1; x++)
    {
      temp.add(original.get(x));
    }
    System.out.println(original);
    System.out.println("Steps to find first instance of number 58 in Linear Search: " + LinearSearch(original, 58));
    System.out.println(SelectionSort(original));
    System.out.println(InsertionSort(original));
    System.out.println("Steps to selection sort: " + SelectionSortCount(original));
    SelectionSort(original);
    System.out.println(original);
    for (int x = 0; x < original.size()-1; x++)
    {
      original.set(x, temp.get(x));
    }
    System.out.println(original);
    System.out.println("Steps to insertion sort: " + InsertionSortCount(original));
    InsertionSort(original);
    System.out.println("Steps to find first instance of number 58 in Binary Search: " + BinarySearchCount(original, 58));

  }
  public ArrayList<Integer> fillList(int length, int max, int min)
  {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int x = 0; x < length; x++)
    {
      int rand = (int)(Math.random()*(max-min+1)+min);
      list.add(rand);
    }
    return list;
  }
  public ArrayList<Integer> InsertionSort(ArrayList<Integer> list)
  {
    for (int x = 1; x < list.size()-1; x++)
    {
      int temp = list.get(x);
      int possibleIndex = x-1;
      while (possibleIndex >= 0 && temp<list.get(possibleIndex))
      {
        list.set(possibleIndex+1, list.get(possibleIndex));
        possibleIndex--;

      }
      list.set(possibleIndex+1, temp);
    }
    return list;
  }
  public int InsertionSortCount(ArrayList<Integer> list)
  {
    int count = 0;
    for (int x = 1; x < list.size()-1; x++)
    {
      int temp = list.get(x);
      int possibleIndex = x-1;
      while (possibleIndex >= 0 && temp<list.get(possibleIndex))
      {
        list.set(possibleIndex+1, list.get(possibleIndex));
        possibleIndex--;
        count++;
      }
      list.set(possibleIndex+1, temp);
    }
    return count;
  }
  public ArrayList<Integer> SelectionSort(ArrayList<Integer> list)
  {
    for (int x = 0; x < list.size()-1; x++)
    {
      int smallestIndex = x;
      for (int y = x+1; y < list.size()-1; y++)
      {
        if (list.get(smallestIndex)>list.get(y))
          smallestIndex = y;
      }
      int temp = list.get(x);
      list.set(x, list.get(smallestIndex));
      list.set(smallestIndex, temp);
    }
    return list;
  }
  public int SelectionSortCount(ArrayList<Integer> list)
  {
    int count = 0;
    for (int x = 0; x < list.size()-1; x++)
    {
      int smallestIndex = x;
      for (int y = x+1; y < list.size()-1; y++)
      {
        if (list.get(smallestIndex)>list.get(y))
          smallestIndex = y;
        count++;
      }
      int temp = list.get(x);
      list.set(x, list.get(smallestIndex));
      list.set(smallestIndex, temp);
    }
    return count;
  }
  public int LinearSearch(ArrayList<Integer> list, int key)
  {
    int comparisons = 0;
    while ((list.get(comparisons) != key) && (comparisons < list.size()-1))
    {
      comparisons++;
    }
    return comparisons;
  }
  public int BinarySearch(ArrayList<Integer> list, int key)
  {
    int right = list.size()-1;
    int left = 0;
    while (left <= right)
    {
      int middle = (left+right)/2;
      if (key < list.get(middle)){
        right = middle - 1;
      }
      else if (key > list.get(middle)){
        left = middle + 1;
      }
      else
      {
        middle = middle;
      }
    }
    return (left+right)/2;
  }
  public int BinarySearchCount(ArrayList<Integer> list, int key)
  {
    int comparisons = 0;
    int right = list.size()-1;
    int left = 0;
    while (left <= right)
    {
      int middle = (left+right)/2;
      if (key < list.get(middle)){
        right = middle - 1;
      }
      else if (key > list.get(middle)){
        left = middle + 1;
      }
      comparisons++;
    }
    return comparisons;
  }
  public static void main(String[] args)
  {
    SelectionInsertionTask app = new SelectionInsertionTask();
  }
}
