package APCSA.Objects.files.Math;

import java.lang.Math;

public class Randomizer
{
  public Randomizer()
  {

  }
  public static int nextInt()
  {
    return (int)(Math.random()*10+1);
  }
  public static int nextInt(int min, int max)
  {
    return (int)(Math.random()*(max-min+1)+min);
  }
  public static void main (String[]args)
  {
  }
}
