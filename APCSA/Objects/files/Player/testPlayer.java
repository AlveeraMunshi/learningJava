package APCSA.Objects.files.Player;

import java.lang.Math;

public class testPlayer
{
  public static void main (String[]args)
  {
    Player p1 = new Player();
    Player p2 = new Player();
    Player p3 = new Player();
    Player p4 = new Player();
    Player p5 = new Player();
    System.out.println("Total Players: "+Player.totalPlayers); // == 5
    System.out.println("Is Game Full?: "+Player.gameFull()); // false
    Player p6 = new Player();
    Player p7 = new Player();
    Player p8 = new Player();
    Player p9 = new Player();
    Player p10 = new Player();
    System.out.println("Total Players: "+Player.totalPlayers); // == 10
    System.out.println("Is Game Full?: "+Player.gameFull()); // true
  }
}
