package Maps.CodeWars;

import java.util.*;

public class PickPeaks {
    
    public static Map<String,List<Integer>> getPeaks(int[] arr) {
      HashMap<String, List<Integer>> terrain = new HashMap<>();
      List<Integer> pos = new ArrayList<>();
      List<Integer> peaks = new ArrayList<>();
      terrain.put("pos", pos);
      terrain.put("peaks", peaks);
      if (arr.length == 0)
        return terrain;
      for (int x = 0; x < arr.length; x++)
      {
        System.out.println(arr[x]);
      }
      System.out.println();
      boolean plateau = false;
      int pc = 0;
      for (int x = 1; x < arr.length; x++) //goes through entire array
      {
        if (arr[x] == arr[x-1] && !plateau) //if two values same
        {
          if (x>2 && arr[x-2] < arr[x-1])
            plateau = true; //theres a plateau
        }
        if (plateau)
          pc++;
        if (plateau && arr[x] != arr[x-1]) //when plateau ends (values stop being same)
        {
          plateau = false; //no more plateau
          if (arr[x] < arr[x-1])
          {
            terrain.get("peaks").add(arr[x-pc]);
            terrain.get("pos").add(x-pc);
          }
          pc = 0;
        }
        //normal peak
        if (x != arr.length-1 && (arr[x] > arr[x-1] && arr[x] > arr[x+1]))
        {
          terrain.get("peaks").add(arr[x]);
          terrain.get("pos").add(x);
        }
      }
      return terrain;
    }
}
