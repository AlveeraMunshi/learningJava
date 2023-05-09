package Maps.CodeWars;

import java.util.*;

public class CommonDenominators {
  // your code
  public static String convertFrac(long[][] lst) {
    TreeMap<Long, Integer> primefactors = new TreeMap<>();
    for (long[] pair : lst)
    {
      long numer = pair[0]; //curr numerator
      long denom = pair[1]; // curr denominator
      //simplify fractions
      for (int i = 2; i <= numer || i <= denom; i++) //checks possible divisors
      {
        while (numer%i == 0 && denom%i == 0) //if both can be divided, then divide
        {
          numer/=i;
          denom/=i;
        }
      }
      pair[0] = numer;
      pair[1] = denom;
      long f = 2;
      int count = 0;
      while (denom != 1)
      {
        if (isPrime(f) && denom%f == 0)
        {
          denom/=f;
          count++;
          if (!primefactors.containsKey(f) || primefactors.get(f) < count)
            primefactors.put(f, count);
        }
        else
        {
          f++;
          count = 0;
        }
      }
    }
    for(long[] pair:lst)
      System.out.println(pair[0]+","+pair[1]);
    long cd = 1;
    for (long f : primefactors.keySet())
    {
      System.out.println(f + " " + primefactors.get(f));
      for (int x = 0; x < primefactors.get(f); x++)
        cd*=f;
    }
    System.out.println(cd);
    String ans = "";
    for (long[] pair : lst)
    {
      long numer = pair[0]; //curr numerator
      long denom = pair[1]; // curr denominator
      if (denom == cd)
      {
        numer = numer;
      }
      else if (denom < cd) // bigger common than curr
      {
        numer*=(cd/denom); //to get from denom to biggert cd, we would multiply. number to multiply would be the division of cd and denom
        System.out.println("*: "+numer+"/"+denom+" "+cd);
      }
      else // bigger denom than common
      {
        numer/=(denom/cd); //to get from denom to common, we need to divide. number to divide is division of denom and cd 
        System.out.println("/: "+numer+"/"+denom+" "+cd);

      }
      ans+= "(" + numer + "," + cd + ")";
    }
    return ans;
  }
  public static boolean isPrime(long val)
  {
    for (int x = 2; x < val; x++)
    {
      if (val%x == 0)
        return false;
    }
    return true;
  }

}
