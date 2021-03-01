package APCSA.Objects.files.Animals;

import java.util.ArrayList;

public class Turkey
{
  private String name;
  private double weight;
  private double avgSpurLength;
  private double beardLength;

  public Turkey()
  {
    name = "";
    weight = 0.0;
    avgSpurLength = 0.0;
    beardLength = 0.0;
  }
  public Turkey(String name, double weight, double avgSpurLength, double beardLength)
  {
    this.name = name;
    this.weight = weight;
    this.avgSpurLength = avgSpurLength;
    this.beardLength = beardLength;
  }
  // Accessors (Get Methods)
  public String getName()
	{
		return name;
	}
	public double getWeight()
	{
		return weight;
	}
	public double getAvgSpurLength()
	{
		return avgSpurLength;
	}
  public double getBeardLength()
	{
		return beardLength;
	}
  // Mutators (Set Methods)
	public void setName(String name)
	{
		this.name = name;
	}
  public void setWeight(double weight)
	{
		this.weight = weight;
	}
  public void setAvgSpurLength(double avgSpurLength)
	{
		this.avgSpurLength = avgSpurLength;
	}
  public void setBeardLength(double beardLength)
	{
		this.beardLength = beardLength;
	}
  public double scoreTurkey()
  {
    double turkeyScore = 1 * weight + 2 * beardLength + 20 * avgSpurLength;
    return turkeyScore;
  }
	public String toString()
	{
		return "[Turkey="+name+", Weight="+weight+", AvgSpurLength="+avgSpurLength+", BeardlLength="+beardLength+", Score="+scoreTurkey()+"]";
	}
  public static double getAvgTurkeyWeight(ArrayList<Turkey> turkeys)
  {
    double sum = 0;
    for (int x = 0; x < turkeys.size(); x++)
    {
      sum+=turkeys.get(x).getWeight();
    }
    double avg = sum/turkeys.size();
    return avg;
  }
  public static void main (String[]args)
  {
  }
}
