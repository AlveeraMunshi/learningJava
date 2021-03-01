package APCSA.Objects.files.Math;

import java.lang.Math;

public class MyPoint10
{
  public static void main (String[]args)
  {
    MyPoint[] points = new MyPoint[10];
    for (int x = 0; x < 10; x++)
    {
      points[x] = new MyPoint(x, x);
      System.out.println(points[x]);
    }
  }
}
