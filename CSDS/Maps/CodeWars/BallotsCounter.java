package Maps.CodeWars;

import java.util.*;

public class BallotsCounter {

    public static String getWinner(final List<String> listOfBallots) {
      TreeMap<String, Integer> votes = new TreeMap<>();
      for (String ballot : listOfBallots)
      {
        if (votes.containsKey(ballot))
        {
          int count = votes.get(ballot);
          count++;
          votes.put(ballot, count);
        }
        else
        {
          votes.put(ballot, 1);
        }
      }
      for (String candidate : votes.keySet())
      {
        if (votes.get(candidate) > listOfBallots.size()/2)
          return candidate;
      }
      return null;
    }
}
