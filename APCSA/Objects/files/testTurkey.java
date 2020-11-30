package APCSA.Objects.files;

import java.util.ArrayList;

public class testTurkey
{
  public static void main (String[]args)
  {
     // Test Code // DO NOT CHANGE ANYTHING BELOW. ONLY UNCOMMENT ONCE YOU ARE FINISHED
    ArrayList<Turkey> turkeys = new ArrayList<Turkey>();

    turkeys.add(new Turkey());
    turkeys.add(new Turkey("Turkelton", 29.8, 1.5, 9.2));
    turkeys.add(new Turkey("Gobbles", 22.5, 1.2, 9.8));
    turkeys.add(new Turkey("Butterball", 29.9, 1.6, 9.7));
    turkeys.get(0).setName("Wishbone");
    turkeys.get(0).setWeight(27.5);
    turkeys.get(0).setAvgSpurLength(1.1);
    turkeys.get(0).setBeardLength(8.6);

    for(int i = 0; i < turkeys.size(); i++)
      System.out.println(turkeys.get(i));
    // For loop Should Output as follows
    //  [Turkey=Wishbone, Weight=27.5, AvgSpurLength=1.1, BeardLength=8.6, Score=66.7]
    //  [Turkey=Turkelton, Weight=29.8, AvgSpurLength=1.5, BeardLength=9.2, Score=78.2]
    //  [Turkey=Gobbles, Weight=22.5, AvgSpurLength=1.2, BeardLength=9.8, Score=66.1]
    //  [Turkey=Butterball, Weight=29.9, AvgSpurLength=1.6, BeardLength=9.7, Score=81.3]

    System.out.println(turkeys.get(1).getName()+" "+turkeys.get(1).getWeight()+" "+turkeys.get(1).getAvgSpurLength()+" "+turkeys.get(1).getBeardLength());
    // Prints: Turkelton 29.8 1.5 9.2

    System.out.println(Turkey.getAvgTurkeyWeight(turkeys)); // 27.42499999999997

    //My two turkeys
    turkeys.add(new Turkey("Aloe", 29.8, 1.2, 9.4));
    turkeys.add(new Turkey("Vera", 23.4, 1.2, 9.8));
    for(int i = 0; i < turkeys.size(); i++)
      System.out.println(turkeys.get(i));
    System.out.println(Turkey.getAvgTurkeyWeight(turkeys));
  }
}
