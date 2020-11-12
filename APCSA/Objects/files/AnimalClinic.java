package APCSA.Objects.files;

public class AnimalClinic
{
  private String name;
  private int age;
  private double weight;
  private String type;
  private String breed;

  public AnimalClinic()
  {
    name = "";
    age = -1;
    weight = 0.0;
    type = "none";
    breed = "none";
  }
  public AnimalClinic(String initName, int initAge, double initWeight, String initType, String initBreed)
  {
    name = initName;
    age = initAge;
    weight = initWeight;
    type = initType;
    breed = initBreed;
  }
  // Accessors (Get Methods)
  public String getName()// Accessors (Get Methods)
	{
		return name;
	}
	public int getAge(){
		return age;
	}
	public double getWeight()
	{
		return weight;
	}
	public String getType()
	{
		return type;
	}
	public String getBreed()
	{
		return breed;
	}
  // Mutators (Set Methods)
	public void setName(String newName)
	{
		name = newName;
	}
	public void setAge(int newAge)
	{
		age = newAge;
	}
	public void setWeight(double newWeight)
	{
		weight = newWeight;
	}
	public void setType(String newType)
	{
		type = newType;
	}
	public void setBreed(String newBreed)
	{
		breed = newBreed;
	}
	public String toString()
	{
		return name+" "+age+" "+weight+" "+type+" "+breed;
	}
}
