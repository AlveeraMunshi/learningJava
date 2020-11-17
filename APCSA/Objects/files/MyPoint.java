package APCSA.Objects.files;

import java.lang.Math;

public class MyPoint
{
  private int x;
  private int y;

  public MyPoint()
  {
    x = 0;
    y = 0;
  }
  public MyPoint(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  // Accessors (Get Methods)
  public int getX()
  {
    return x;
  }
  public int getY()
  {
    return y;
  }
  public int[] getXY()
  {
    int[] array = new int[2];
    array[0] = this.x;
    array[1] = this.y;
    return array;
  }
  // Mutators (Set Methods)
  public void setX(int x)
  {
    this.x = x;
  }
  public void setY(int y)
  {
    this.y = y;
  }
  public void setXY(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
	public String toString()
	{
		return "("+this.x+", "+this.y+")";
	}
  public double distance (int x2, int y2)
  {
    int ydiff = y2 - y;
    int xdiff = x2 - x;
    double ydiffsq = Math.pow(ydiff, 2);
    double xdiffsq = Math.pow(xdiff, 2);
    double xydiffsqsum = ydiffsq + xdiffsq;
    double distance = Math.sqrt(xydiffsqsum);
    return distance;
  }
  public double distance (MyPoint another)
  {
    int ydiff = another.getY() - y;
    int xdiff = another.getX() - x;
    double ydiffsq = Math.pow(ydiff, 2);
    double xdiffsq = Math.pow(xdiff, 2);
    double xydiffsqsum = ydiffsq + xdiffsq;
    double distance = Math.sqrt(xydiffsqsum);
    return distance;
  }
  public double distance()
  {
    int ydiff = 0 - y;
    int xdiff = 0 - x;
    double ydiffsq = Math.pow(ydiff, 2);
    double xdiffsq = Math.pow(xdiff, 2);
    double xydiffsqsum = ydiffsq + xdiffsq;
    double distance = Math.sqrt(xydiffsqsum);
    return distance;
  }
  public static void main (String[]args)
  {
  }
}
