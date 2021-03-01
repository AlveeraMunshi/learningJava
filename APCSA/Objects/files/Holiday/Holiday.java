package APCSA.Objects.files.Holiday;

import java.util.ArrayList;

public class Holiday
{
  private String name;
  private String month;
  private int day;

  public Holiday()
  {
    name = "";
    month = "";
    day = 0;
  }
  public Holiday(String name, String month, int day)
  {
    this.name = name;
    this.month = month;
    this.day = day;
  }
  // Accessors (Get Methods)
  public String getName()
	{
		return name;
	}
  public String getMonth()
	{
		return month;
	}
	public int getDay(){
		return day;
	}
  // Mutators (Set Methods)
  public void setName(String name)
  {
    this.name =  name;
  }
  public void setMonth(String month)
  {
    this.month = month;
  }
  public void setDay(int day){
    this.day = day;
  }
  public boolean isSameDay(Holiday another)
	{
		if (this.day == another.getDay())
      return true;
    return false;
	}
  public boolean isSameMonth(Holiday another)
	{
		if (this.month == another.getMonth())
      return true;
    return false;
	}
  public static double avgDate(ArrayList<Holiday> list)
  {
    double sum = 0;
    for (int x = 0; x < list.size(); x++)
    {
      sum = sum + list.get(x).getDay();
    }
    double avg = sum/list.size();
    return avg;
  }
	public String toString()
	{
		return this.name+", "+this.month+" "+this.day;
	}
  public static void main (String[]args)
  {
  }
}
