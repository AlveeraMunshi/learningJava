package APCSA.Objects.files;

import java.lang.Math;

public class testCar
{
  public static void main (String[]args)
  {
    Car toyota = new Car(5.0, 40.0);
    toyota.addGas();
    System.out.println(toyota.milesAvailable());
    toyota.drive(100.0);
    System.out.println(toyota.milesAvailable());
    toyota.addGas(10);
    System.out.println(toyota.milesAvailable());
    toyota.drive(1000.0);
    toyota.drive(200.0);
    System.out.println(toyota.getGas());
    System.out.println(toyota.getTotalMilesDriven());

  }
}
