package APCSA.ArrayLists.files;

import java.util.ArrayList;

public class DoNow111020
{
  public DoNow111020()
  {
      ArrayList<Character> first = new ArrayList<Character>();
      ArrayList<Character> nickname = new ArrayList<Character>();
      ArrayList<Character> last = new ArrayList<Character>();

      first.add(new Character ('A'));
      first.add(new Character ('l'));
      first.add(new Character ('v'));
      first.add(new Character ('e'));
      first.add(new Character ('e'));
      first.add(new Character ('r'));
      first.add(new Character ('a'));

      last.add(new Character ('M'));
      last.add(new Character ('u'));
      last.add(new Character ('n'));
      last.add(new Character ('s'));
      last.add(new Character ('h'));
      last.add(new Character ('i'));

      int firstIndex = first.size()-1;
		  int lastIndex = last.size()-1;
      while(firstIndex > 0 || lastIndex > 0)
      {
        if(firstIndex%2!=0 && firstIndex > 0)
        {
          first.remove(firstIndex);
        }
        if(lastIndex%2!=0 && lastIndex > 0)
        {
          last.remove(lastIndex);
        }
        firstIndex--;
        lastIndex--;
      }
      System.out.println(first+"\n"+last);
    }
  public static void main(String[]args)
  {
    DoNow111020 app = new DoNow111020();
  }
}
