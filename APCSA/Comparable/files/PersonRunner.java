package APCSA.Comparable.files;

import java.util.*;

public class PersonRunner
{
  public PersonRunner()
  {
    String[] firstNames = {"Layla", "Laiba", "Rushi", "Leanne", "Talia", "Alika", "Allistair", "Neil", "Kabir", "Yusuf", "Zayn", "Ali", "Ahmed", "Leora", "Sufi"};
    String[] lastNames = {"Akbar", "Khan", "Yusuf", "Mohammed", "Ali", "Rahman", "Abdullah", "Abbasi", "Shah", "Saleh", "Tariq", "Usman", "Saleh", "Hafeez", "Zayd"};
    ArrayList<Person> people = new ArrayList<Person>();
    for (int x = 0; x < 50; x++)
    {
      int gend = (int)(Math.random()*2+1);
      char gender;
      if (gend == 1)
        gender = 'M';
      else
        gender = 'F';
      people.add(new Person(firstNames[(int)(Math.random()*14)] +" "+ lastNames[(int)(Math.random()*14)], (int)(Math.random()*80+1), gender));
    }
    System.out.println("Pre-Sorting");
    System.out.println(people);
    for (int x = 0; x < people.size(); x++)
    {
      int smallestIndex = x;
      for (int y = x+1; y < people.size(); y++)
      {
        if (people.get(smallestIndex).compareTo(people.get(y)) > 0)
          smallestIndex = y;
      }
      Person temp = people.get(x);
      people.set(x, people.get(smallestIndex));
      people.set(smallestIndex, temp);
    }
    System.out.println("Post-Sorting");
    System.out.println(people);
  }
  public static void main(String[] args) {
    PersonRunner app = new PersonRunner();
  }
}
