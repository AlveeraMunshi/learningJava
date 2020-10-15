package APCSA.MathnMethods.files;

public class Lantern
{
  public Lantern()
  {
    AsteriskSpaces(6, 1);
    AsteriskSpaces(5, 3);
    AsteriskSmallMedium();
    AsteriskBarPattern(1);
    AsteriskSmallBig();
    AsteriskBarPattern(2);
    AsteriskBigSmall();
    AsteriskBarPattern(1);
    AsteriskSmallBig();
    AsteriskBarPattern(2);
    AsteriskBig();
    AsteriskSmallMedium();
    AsteriskSmall();
    AsteriskSpaces(5, 3);
    AsteriskSpaces(6, 1);
  }
  public static void printBar(int l)
  {
    for (int x = 0; x < l; x++)
      System.out.print(" |");
  }
  public static void printAsterisk(int l)
  {
    for (int x = 0; x < l; x++)
      System.out.print("*");
  }
  public static void printSpace(int l)
  {
    for (int x = 0; x < l; x++)
      System.out.print(" ");
  }
  public static void printEnter(int l)
  {
    for (int x = 0; x < l; x++)
      System.out.println();
  }
  public static void AsteriskBarPattern(int length)
  {
    for (int x = 0; x < length; x++)
    {
      printAsterisk(1);
      printBar(5);
      printSpace(1);
      printAsterisk(1);
      printEnter(1);
    }
  }
  public static void AsteriskBig()
  {
    printAsterisk(13);
    printEnter(1);
  }
  public static void AsteriskMedium()
  {
    AsteriskSpaces(2, 9);
  }
  public static void AsteriskSmall()
  {
    AsteriskSpaces(4, 5);
  }
  public static void AsteriskSpaces(int spaces, int asterisks)
  {
    printSpace(spaces);
    printAsterisk(asterisks);
    printEnter(1);
  }
  public static void AsteriskSandwich()
  {
    AsteriskMedium();
    AsteriskSmall();
    AsteriskMedium();
  }
  public static void AsteriskSmallBig()
  {
    AsteriskSandwich();
    AsteriskBig();
  }
  public static void AsteriskBigSmall()
  {
    AsteriskBig();
    AsteriskSandwich();
  }
  public static void AsteriskSmallMedium()
  {
    AsteriskSmall();
    AsteriskMedium();
  }
  public static void main (String[]args)
  {
    Lantern temp = new Lantern();
    //Created by Alveera Munshi, Anushka Majumdar, Divya Ventakarma, and Ridhima Yalaka
  }
}
