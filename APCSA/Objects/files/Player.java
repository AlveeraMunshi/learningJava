package APCSA.Objects.files;

public class Player
{
  static int totalPlayers = 0;
  static final int MAX_PLAYERS = 10;

  public Player()
  {
    totalPlayers++;
  }
  public static boolean gameFull()
  {
    if (totalPlayers == MAX_PLAYERS)
      return true;
    return false;
  }
  public static void main (String[]args)
  {
  }
}
