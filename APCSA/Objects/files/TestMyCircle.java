package APCSA.Objects.files;

import java.lang.Math;

public class testMyCircle
{
  public static void main (String[]args)
  {
    MyCircle c1 = new MyCircle();
    MyCircle c2 = new MyCircle(2,2,2);
    MyPoint p = new MyPoint(0,3);
    MyCircle c3 = new MyCircle(p,3);
    System.out.println(c1);
    System.out.println(c1.getCenterX());
    System.out.println(c1.getCenterY());
    System.out.println(c1.getR());
    c1.setCenterX(1);
    c1.setCenterY(1);
    c1.setR(2);
    System.out.println(c1.getCenter()[0]);
    System.out.println(c1.getCenter()[1]);
    System.out.println(c1.getR());
    System.out.println(c2);
    c2.setXY(0,4);
    System.out.println(c2);
    System.out.println(c3);
    System.out.println(c3.getArea());
    System.out.println(c3.getCircumference());
    System.out.println(c3.getDistance(c2));
  }
}
