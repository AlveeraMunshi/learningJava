package APCSA.MathnMethods.files;

public class MethodReview
{
  public MethodReview()
  {
    System.out.println(4*computePi(1));
    int[] array = new int[genRand(10,10)];
    for (int x = 0; x < array.length; x++)
    {
      array[x] = genRand(100, 1);
    }
    System.out.println("Are half of the numbers in the list even? " + moreThanHalfEven(array));
    output(array);
  }
  public double computePi(int n)
  {
    if ((n+2) >= 1000)
      return 1.0/1000;
    return 1.0/n - computePi((n+2));
  }
  public int genRand(int range, int lower) //random number method
  {
    return (int)(Math.random()*(range))+lower;
  }
  public boolean moreThanHalfEven(int array[]) //This method will search through the array and determine if at least half of the values stored in the array are even.
  {
    int even = 0;
    for (int x = 0; x < array.length; x++)
    {
      if (array[x]%2 == 0)
        even++;
    }
    if (even >= array.length/2)
      return true;
    return false;
  }
  public void output(int list[]) //This method will display all numbers contained in the array.
  {
    for (int x = 0; x < list.length; x++)
    {
      System.out.print(list[x] + " ");
    }
  }
  public static void main (String[]args)
  {
    MethodReview app = new MethodReview();
  }
}
