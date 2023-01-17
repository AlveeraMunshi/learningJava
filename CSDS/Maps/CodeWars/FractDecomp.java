package Maps.CodeWars;

import java.util.*;

class FactDecomp {
    
    public static String decomp(int n) {
      TreeMap<Integer, Integer> factors = new TreeMap<>();
      for (int x = n; x >= 2; x--) // check all nums < max from max to 2
      {
        int current = x; // save initial
        int f = 2; // first factor
        while (current > 1) // check all possible factors
        {
          //counts number of times divisible
          while (current%f == 0) // if a factor
          {
            current = current/f; // divide cleanly
            if (factors.containsKey(f))
            {
              int count = factors.get(f);
              count++; //increment
              factors.put(f, count);
            }
            else
            {
              factors.put(f, 1);
            }
          }
          f++;
        }
      }
      String ans = "";
      System.out.println(factors);
      for (int fact : factors.keySet())
      {
        int count = factors.get(fact);
        if (count == 1)
          ans+=fact+" * ";
        else
          ans+=fact+"^"+count+" * ";
      }
      if (ans.length() > 1)
        ans = ans.substring(0,ans.length()-3);
      return ans;
    }
} 
