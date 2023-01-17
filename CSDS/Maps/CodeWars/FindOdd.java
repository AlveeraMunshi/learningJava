package Maps.CodeWars;

import java.util.*;

public class FindOdd {
	public static int findIt(int[] a) {
    TreeMap<Integer, Integer> nums = new TreeMap<>();
    for (int n : a)
    {
      if (nums.containsKey(n))
        nums.put(n, nums.get(n)+1);
      else
        nums.put(n, 1);
    }
    for (int n : nums.keySet())
    {
      if (nums.get(n)%2 == 1)
        return n;
    }
    return 0;
  }
}
