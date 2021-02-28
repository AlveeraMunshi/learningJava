package APCSA.SearchesSorts.files;

import java.util.*;

public interface SearchingSortingInterface
{
  public ArrayList<Integer> selectionSort(ArrayList<Integer> list);
  public ArrayList<Integer> insertionSort(ArrayList<Integer> list);
  public ArrayList<Integer> mergeSort(ArrayList<Integer> list, Integer lo, Integer hi);
  public boolean binarySearch(ArrayList<Integer> list, Integer key);
  public boolean linearSearch(ArrayList<Integer> list, Integer key);
}
