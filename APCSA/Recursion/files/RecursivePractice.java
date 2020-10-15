package APCSA.Recursion.files;

public class RecursivePractice
{
  public RecursivePractice()
  {
    System.out.println(addtill10(1));
    //printFactorials(3);
    printPrimes();
    hailstoneSequence(5);
    System.out.println();
    System.out.println(euclid(102, 68));
  }
  public int addtill10(int x)
  {
    if (x != 10)
      return x + addtill10(x+1);
    return x;
  }
  public int fact(int n)
  {
    if (n == 2)
      return 2;
    return n * fact(n-1);
  }
  public void printFactorials(int n)
  {
    for (int x = 0; x < n; x++)
    {
      System.out.println(x + "! is equal to " + fact(x));
    }
  }
  public boolean isPrime(int x, int halfx)
  {
    if (halfx == 1)
      return true;
    else if (x%halfx == 0)
      return false;
    else
      return isPrime(x, halfx - 1);
  }
  public void printPrimes()
  {
    for (int x = 0; x < 30; x++)
    {
      int rand = (int)(Math.random()*99+2);
      System.out.print(rand + " is ");
      if (isPrime(rand, rand/2))
        System.out.println("prime.");
      else
        System.out.println("not prime.");
    }
  }
  public void hailstoneSequence(int x)
  {
    System.out.print(x + " ");
    if (x != 1)
    {
      if (x%2 == 0)
        hailstoneSequence(x/2);
      else
        hailstoneSequence(x*3+1);
    }
  }
  public int euclid(int p, int q)
  {
    if (p < q)
    {
      int temp = p;
      p = q;
      q = temp;
    }
    int gcd;
    if (p%q == 0)
      return q;
    else
    {
      return euclid(p, p%q);
    }
  }
  public static void main (String[]args)
  {
    RecursivePractice app = new RecursivePractice();
  }
}
