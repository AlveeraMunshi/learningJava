package APCSA.SearchesSorts.files;

import java.util.*;

public class Sorter implements SearchingSortingInterface
{
  public ArrayList<Integer> selectionSort(ArrayList<Integer> list)
  {
    for (int x = 0; x < list.size(); x++)
    {
      int smallestIndex = 0;
      for (int y = x+1; y< list.size(); y++)
      {
        if (list.get(x)>list.get(y))
          smallestIndex = y;
      }
      Integer temp = list.get(x);
      list.set(x, list.get(smallestIndex));
      list.set(smallestIndex, temp);
    }
    return list;
  }
  public ArrayList<Integer> insertionSort(ArrayList<Integer> list)
  {
    for (int x = 1; x < list.size(); x++)
    {
      int possibleIndex = x - 1;
      int temp = list.size();
      while (possibleIndex >= 0 && temp<list.get(possibleIndex))
      {
        possibleIndex--;
        list.set(possibleIndex+1, possibleIndex);
      }
      list.set(possibleIndex, temp);
    }
    return list;
  }
  public ArrayList<Integer> mergeSort(ArrayList<Integer> list, Integer lo, Integer hi)
  {
    Integer low = lo;
    Integer high = hi;
    if (low >= high)
      return list;
    Integer middle = (low + high) / 2;
    mergeSort(list, low, middle);
    mergeSort(list, middle + 1, high);
    Integer endLow = middle;
    Integer startHigh = middle + 1;
    while ((lo <= endLow) && (startHigh <= high))
    {
      if (list.get(low) < list.get(startHigh))
        low++;
      else
      {
        Integer temp = list.get(startHigh);
        for (int k = startHigh- 1; k >= low; k--)
          list.set(k+1,list.get(k));
        list.set(low,temp);
        low++;
        endLow++;
        startHigh++;
     	}
    }
    return list;
  }
  public boolean binarySearch(ArrayList<Integer> list, Integer key)
  {
    int left = 0;
    int right = list.size();
    while (left<=right)
    {
      int middle = (left + right)/2;
      if (middle == key)
        return true;
      if (middle > key)
        right = middle - 1;
      if (middle < key)
        left = middle + 1;
    }
    return false;
  }
  public boolean linearSearch(ArrayList<Integer> list, Integer key)
  {
    for (int x = 0; x < list.size(); x++)
    {
      if (list.get(x) == key)
        return true;
    }
    return false;
  }
}
