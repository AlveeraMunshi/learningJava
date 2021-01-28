package APCSA.ForEach.files;

import java.util.*;

public class ForEachPractice
{
  public ForEachPractice()
	{
    int[] array = new int[100];
    for (int x = 0; x < array.length; x++)
    {
      array[x] = (int)(Math.random()*1000+1);
    }
    int sum = 0;
    for (int num : array)
    {
      if (num%2 != 0)
        System.out.print(num + " ");
      sum+=num;
    }
    double avg = sum/array.length;
    System.out.println("\nSum: " + sum);
    System.out.println("Average: " + avg);
  }
  public static void main (String[]args)
  {
    ForEachPractice app = new ForEachPractice();

    ArrayList<Student> classroom = new ArrayList<Student>();
    classroom.add(new Student("Alveera", "Munshi", 10));
    classroom.add(new Student("Bob", "Boberson", 9));
    classroom.add(new Student("Jeff", "Jefferson", 11));
    classroom.add(new Student("Sarah", "Samson", 10));
    classroom.add(new Student("Moe", "Muhammad", 9));
    classroom.add(new Student("Layla", "Khan", 11));
    for (Student kid : classroom)
    {
      System.out.println(kid.getFirstName() + " " + kid.getLastName() + " is in grade " + kid.getGradeLevel());
    }
  }
}
