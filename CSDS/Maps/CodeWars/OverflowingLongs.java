package Maps.CodeWars;

public class OverflowingLongs {
  public static long multiply(long a, long b) {
    long p = a*b;
    System.out.println("p" + p+" "+a);
    if (a != 0 && p / a != b) //if a term is 0 there is not an overflow, accounts for getting undefined in p/a
      throw new ArithmeticException("long product overflow");
    if (b != 0 && p/b != a)
      throw new ArithmeticException("long product overflow");
    return p;
  }
}
