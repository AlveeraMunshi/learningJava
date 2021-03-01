package APCSA.Comparable.files;

import java.util.*;

public class Person implements Comparable<Person> {
  private String name;
  private int age;
  private char gender;

  public Person(String name, int age, char gender)
  {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public String getName()
  {
    return name;
  }

  public int getAge()
  {
    return age;
  }

  public char getGender()
  {
    return gender;
  }

  public int compareTo(Person person)
  {
    if (this.age > person.getAge())
      return 1;
    else if (this.age < person.getAge())
      return -1;
    else
    {
      if (this.gender > person.getGender())
        return 1;
      else if (this.gender < person.getGender())
        return -1;
      else
      {
        if (this.name.compareTo(person.getName()) > 0)
          return 1;
        else if (this.name.compareTo(person.getName()) > 0)
          return -1;
        else
          return 0;
      }
    }
  }

  public String toString()
  {
    return "Name: " + name + " Age: " + age + " Gender: " + gender + "\n";
  }
}
