package APCSA.Objects.files.Vehicles;

import java.lang.Math;

public class Car
{
  private double efficiency; // this is miles per gallon
  private double gas;
  private double tankCapacity;
  private double totalMilesDriven;

  public Car(double carEfficiency, double carTankCapacity)
  {
    efficiency = carEfficiency;
    tankCapacity = carTankCapacity;
  }
  public void addGas()
  {
    gas = tankCapacity;
    System.out.println("Filling up ...");
  }
  public void addGas(double amount)
  {
    if (gas+amount > tankCapacity)
      gas = tankCapacity;
    else
      gas+=amount;
    System.out.println("Adding gas ...");
  }
  public double getTotalMilesDriven()
  {
    return totalMilesDriven;
  }
  public void drive(double miles)
  {
    if (canDrive(miles))
    {
      totalMilesDriven+=miles;
      double gasUsed = miles * 1/efficiency;
      gas-=gasUsed;
      System.out.println("Driving " + miles);
    }
    else
    {
      System.out.println("Can't drive " + miles + ". That's too far!");
    }
  }
  public boolean canDrive (double miles)
  {
    if (milesAvailable() > miles)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  public double milesAvailable()
  {
    return gas*efficiency;
  }
  public double getGas()
  {
    return gas;
  }
  public static void main (String[]args)
  {
  }
}
