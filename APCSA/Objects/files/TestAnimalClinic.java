package APCSA.Objects.files;

public class TestAnimalClinic
{
  public static void main (String[]args)
  {
    AnimalClinic pet1 = new AnimalClinic("Fluffy", 3, 15.7, "dog", "husky"); // Create pet 1
    AnimalClinic pet2 = new AnimalClinic("Chirpy", 5, 16.2, "bird", "canary"); // Create pet 2
    AnimalClinic pet3 = new AnimalClinic("Shadow", 2, 10.5, "cat", "black"); // Create pet 3
    System.out.println(pet1.getName());
    pet1.setName("Tucker");
    System.out.println(pet1.getName());
	  System.out.println(pet1);
  }
}
