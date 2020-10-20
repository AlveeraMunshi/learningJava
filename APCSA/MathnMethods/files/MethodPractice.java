package APCSA.MathnMethods.files;

public class MethodPractice
{
  public MethodPractice()
  {
    int[] array = new int[genRand(10,5)]; //creates length from 5 to 10
    for (int x = 0; x < array.length; x++) //initializes values
    {
      array[x] = genRand(20, 10);
    }
    output(array); //output array
    System.out.println("\nThe average of all the values is " + avg(array)); //display average
  }
  public int genRand(int upper, int lower) //random number method
  {
    return (int)(Math.random()*(upper-lower+1))+lower;
  }
  public double avg(int[] list) //average method
  {
    double sum = 0;
    for (int x = 0; x < list.length; x++)
    {
      sum+=list[x]; //add to sum each time
    }
    return sum/list.length;
  }
  public void output(int[] list) //output method
  {
    for (int x = 0; x < list.length; x++)
    {
      System.out.println("Number " + (x+1) + ": " + list[x]); //print via given format
    }
  }
  public static void main (String[]args)
  {
    MethodPractice app = new MethodPractice();
  }
}
