package Maps.CodeWars;

public class mostFrequentItem {
    public static int mostFrequentItemCount(int[] collection) {
      TreeMap<Integer, Integer> items = new TreeMap<>();
      for (int i : collection)
      {
        System.out.print(i + " ");
        if (items.containsKey(i))
        {
          int count = items.get(i);
          count++;
          items.put(i, count);
        }
        else
        {
          items.put(i, 1);
        }
      }
      System.out.println();
      if (items.size() == 0)
        return 0;
      int max = items.firstKey();
      for (int val : items.keySet())
      {
        int freq = items.get(val);
        if (freq > items.get(max))
          max = val;
      }
      return items.get(max);
    }
  }
