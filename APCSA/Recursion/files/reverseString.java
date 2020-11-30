package APCSA.Recursion.files;

public class reverseString
{
  public static void main (String[]args)
  {
    System.out.print(reverseString("hello"));
  }
  public static String reverseString(String str)
  {
    if (str.length() == 1)
    {
      return str;
    }
    return str.substring(str.length()-1) + reverseString(str.substring(0,str.length()-1));
  }
}
