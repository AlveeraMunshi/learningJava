package Maps.CodeWars;

import java.util.HashMap;

public class LooseChange {
	public static HashMap<String, Integer> looseChange(int cent) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("Pennies", 0);
		map.put("Nickels", 0);
		map.put("Dimes", 0);
		map.put("Quarters", 0);
    if (cent <= 0)
      return map;
		if (cent >= 25)
    {
      System.out.println("25 " + cent);
      int numofq = cent/25;
      cent-=25*numofq;
      map.put("Quarters", map.get("Quarters") + numofq);
    }
    if (cent >= 10)
    {
      System.out.println("10 " + cent);
      int numofd = cent/10;
      cent-=10*numofd;
      map.put("Dimes", map.get("Dimes") + numofd);
    }
    if (cent >= 5)
    {
      System.out.println("5 " + cent);
      int numofn = cent/5;
      cent-=5*numofn;
      map.put("Nickels", map.get("Nickels") + numofn);
    }
    System.out.println("1 " + cent);
    map.put("Pennies", cent);
		return map;
	}
  public static HashMap<String, Integer> looseChange(float cent) {
    int c = (int)(cent);
    return looseChange(c);
  }
}
