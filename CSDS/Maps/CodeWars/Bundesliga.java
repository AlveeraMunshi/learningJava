package Maps.CodeWars;

import java.util.*;

public class Bundesliga
{
  public static String table(String[] results)
  {
    TreeMap<String, Integer> pts = new TreeMap<>();
    TreeMap<String, Integer> pms = new TreeMap<>();
    TreeMap<String, Integer> wms = new TreeMap<>();
    TreeMap<String, Integer> tms = new TreeMap<>();
    TreeMap<String, Integer> lms = new TreeMap<>();
    TreeMap<String, Integer> gs = new TreeMap<>();
    TreeMap<String, Integer> gg = new TreeMap<>();
    PriorityQueue<Team> teams = new PriorityQueue<Team>();
    //extract data
    for (int x = 0; x < results.length; x++)
    {
      boolean tie = false;
      String wteam = results[x].substring(results[x].lastIndexOf("-")+2, results[x].length());
      String wptss = results[x].substring(results[x].indexOf(":")+1, results[x].indexOf(" "));
      int wpts;
      if (wptss.equals("-"))
        wpts = 0;
      else
        wpts = Integer.parseInt(wptss);
      String lteam = results[x].substring(results[x].indexOf(" ")+1, results[x].indexOf(" -"));
      String lptss = results[x].substring(0,results[x].indexOf(":"));
      int lpts;
      if (lptss.equals("-"))
        lpts = 0;
      else
        lpts = Integer.parseInt(lptss);
      if (wpts < lpts)
      {
        int temppts = wpts;
        wpts = lpts;
        lpts = temppts;
        String tempt = wteam;
        wteam = lteam;
        lteam = tempt;
      }
      //check tie
      if (wpts == lpts)
        tie = true;
      //saved scored goals
      gs.put(wteam, wpts);
      gs.put(lteam, lpts);
      //save gotten goals
      gg.put(wteam, lpts);
      gg.put(lteam, wpts);
      
      //save played matches
      if (wptss.equals("-"))
      {
        pms.put(wteam, 0);
      }
      else
      {
        pms.put(wteam, 1);
      }
      if (lptss.equals("-"))
      {
        pms.put(lteam, 0);
      }
      else
      {
        pms.put(lteam, 1);
      }
      if (!tie)
      {
        //save won matches
        wms.put(wteam, 1);
        lms.put(wteam, 0);
        tms.put(wteam, 0);
        //save lost matches
        lms.put(lteam, 1);
        wms.put(lteam, 0);
        tms.put(lteam, 0);
        if (!wptss.equals("-"))
        {
          pts.put(wteam, 3);
          pts.put(lteam, 0);
        }
        else
        {
          pts.put(wteam, 0);
          pts.put(lteam, 0);
        }
      }
      else
      {
        //save tie matches
        wms.put(lteam, 0);
        lms.put(lteam, 0);
        wms.put(wteam, 0);
        lms.put(wteam, 0);
        if (!wptss.equals("-"))
        {
          pts.put(wteam, 1);
          pts.put(lteam, 1);
          tms.put(lteam, 1);
          tms.put(wteam, 1);
        }
        else
        {
          pts.put(wteam, 0);
          pts.put(lteam, 0);
          tms.put(lteam, 0);
          tms.put(wteam, 0);
        }
      }
    }
    for (String t : pms.keySet())
    {
      Team temp = new Team(t, pms.get(t), wms.get(t), tms.get(t), lms.get(t), gs.get(t), gg.get(t), pts.get(t));
      teams.add(temp);
    }
    String ans = "";
    int count = 1;
    int tie = 0;
    Team prev = new Team();
    while (!teams.isEmpty())
    {
      Team t = teams.poll();
      if (prev.name != null && t.rank(prev) != 0)
      {
        if (tie != 0)
          count+=tie+1;
        else
          count++;
        tie = 0;
      }
      else
      {
        if (prev.name != null)
          tie++;
      }
      if (count < 10)
        ans+=" "+ count + ". " + t + "\n";
      else
        ans+=count + ". " + t + "\n";
      prev = t;
    }
    return ans.substring(0,ans.length()-1);
  }
  public static class Team implements Comparable<Team>
  {
    String name;
    int pm, wm, tm, lm, gs, gg, pts, same;
    public Team(String name, int pm, int wm, int tm, int lm, int gs, int gg, int pts)
    {
      this.name = name;
      this.pm = pm;
      this.wm = wm;
      this.tm = tm;
      this.lm = lm;
      this.gs = gs;
      this.gg = gg;
      this.pts = pts;
    }
    public Team()
    {
      name = null;
    }
    public int compareTo(Team other)
    {
      if (pts != other.pts)
        return -1*(pts-other.pts);
      if (gs-gg != other.gs-other.gg)
        return -1*((gs-gg) - (other.gs-other.gg));
      if (gs != other.gs)
        return -1*(gs-other.gs);
      return name.toUpperCase().compareTo(other.name.toUpperCase());
    }
    public int rank(Team other)
    {
      if (pts != other.pts)
        return -1*(pts-other.pts);
      if (gs-gg != other.gs-other.gg)
        return -1*((gs-gg) - (other.gs-other.gg));
      if (gs != other.gs)
        return -1*(gs-other.gs);
      return 0;
    }
    public String toString()
    {
      return String.format("%-29s %s  %s  %s  %s  %s:%s  %s", name, pm, wm, tm, lm, gs, gg, pts);
    }
  }
}
