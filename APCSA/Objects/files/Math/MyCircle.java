package APCSA.Objects.files.Math;

import java.lang.Math;

public class MyCircle
{
  private MyPoint center;
  private int r;

  public MyCircle()
  {
    center = new MyPoint();
    this.r = 1;
  }
  public MyCircle(MyPoint center, int r)
  {
    this.center = center;
    this.r = r;
  }
  public MyCircle(int x, int y, int r)
  {
    center = new MyPoint(x, y);
    this.r = r;
  }
  // Accessors (Get Methods)
  public int getCenterX()
  {
    return center.getX();
  }
  public int getCenterY()
  {
    return center.getY();
  }
  public int[] getCenter()
  {
    int[] array = new int[2];
    array[0] = center.getX();
    array[1] = center.getY();
    return array;
  }
  public int getR()
  {
    return r;
  }
  // Mutators (Set Methods)
  public void setCenterX(int x)
  {
    center.setX(x);
  }
  public void setCenterY(int y)
  {
    center.setY(y);
  }
  public void setXY(int x, int y)
  {
    center.setX(x);
    center.setY(y);
  }
  public void setR(int r)
  {
    this.r = r;
  }
	public String toString()
	{
		return "MyCircle[radius="+ r + ",center=" + center.toString();
	}
  public double getArea()
  {
    return 3.14*r*r;
  }
  public double getCircumference()
  {
    return 2*3.14*r;
  }
  public double getDistance(MyCircle another)
  {
    return center.distance(another.getCenterX(), another.getCenterY());
  }
  public static void main (String[]args)
  {
  }
}
