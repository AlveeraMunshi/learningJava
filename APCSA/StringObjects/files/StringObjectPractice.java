package APCSA.StringObjects.files;

import java.util.*;

public class StringObjectPractice
{
  public StringObjectPractice()
	{
    //1
    reverseString("hello");
    //2
    String line = "The blue whale went to the beach";
    System.out.println(line);
    for (int x = 0; x<line.length(); x++)
    {
      if (line.substring(x,x+1).equals("a") || line.substring(x,x+1).equals("e") || line.substring(x,x+1).equals("i") || line.substring(x,x+1).equals("o") || line.substring(x,x+1).equals("u"))
        line = line.replace(line.substring(x,x+1), Character.toString((char)(Math.random()*41+80)));
    }
    System.out.println(line);
    String[] firstNames = {"Layla", "Laiba", "Rushi", "Leanne", "Talia", "Alika", "Allistair", "Neil", "Kabir", "Yusuf", "Zayn", "Ali"};
    for (int x = 0; x< firstNames.length; x++)
    {
      chopper(firstNames[x]);
    }
  }
  public static void reverseString(String string)
  {
    for (int x = string.length() - 1; x>=0; x--)
    {
      System.out.print(string.substring(x, x+1));
    }
    System.out.println();
  }
  public static void chopper(String string)
  {
    string = string.substring(1, string.length()-1);
    System.out.print(string + " ");
  }
  public static void main (String[]args)
  {
    StringObjectPractice app = new StringObjectPractice();
  }
}
