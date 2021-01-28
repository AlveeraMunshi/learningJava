package APCSA.ForEach.files;

public class Student
{
  private String firstName;
  private String lastName;
  private int gradeLevel;

  public Student(String firstName, String lastName, int gradeLevel)
	{
    this.firstName = firstName;
    this.lastName = lastName;
    this.gradeLevel = gradeLevel;
  }
  public String getFirstName()
  {
    return firstName;
  }
  public String getLastName()
  {
    return lastName;
  }
  public int getGradeLevel()
  {
    return gradeLevel;
  }
  public static void main (String[]args)
  {
  }
}
