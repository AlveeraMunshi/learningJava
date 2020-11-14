package APCSA.Objects.files;

public class Author
{
  private String name;
  private String email;
  private char gender;

  public Author(String initName, String initEmail, char initGender)
  {
    name = initName;
    email = initEmail;
    gender = initGender;
  }
  // Accessors (Get Methods)
  public String getName()
	{
		return name;
	}
	public String getEmail()
	{
		return email;
	}
	public char getGender()
	{
		return gender;
	}
  // Mutators (Set Methods)
	public void setEmail(String newEmail)
	{
		email = newEmail;
	}
	public String toString()
	{
		return "Author[name="+name+",email="+email+",gender="+gender+"]";
	}
  public static void main (String[]args)
  {
  }
}
