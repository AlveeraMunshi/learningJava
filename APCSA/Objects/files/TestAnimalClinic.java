package APCSA.Objects.files;

public class TestAnimalClinic
{
  public static void main (String[]args)
  {
    AnimalClinic pet1 = new AnimalClinic("Fluffy", 3, 15.7, "dog", "husky"); // Create pet 1
    AnimalClinic pet2 = new AnimalClinic("Chirpy", 5, 16.2, "bird", "canary"); // Create pet 2
    AnimalClinic pet3 = new AnimalClinic("Shadow", 2, 10.5, "cat", "black"); // Create pet 3
    System.out.println(pet1);
    System.out.println(pet1.getName());
    pet1.setName("Tucker");
    System.out.println(pet1.getName());
	  System.out.println(pet1);
    System.out.println(pet2);
    System.out.println(pet2.getAge());
    pet2.setAge(10);
    System.out.println(pet2.getAge());
	  System.out.println(pet2);
    System.out.println(pet3);
    System.out.println(pet3.getWeight());
    pet3.setWeight(10.0);
    System.out.println(pet3.getWeight());
	  System.out.println(pet3);
    System.out.println(pet1);
    System.out.println(pet1.getType());
    pet1.setType("cat");
    System.out.println(pet1.getType());
	  System.out.println(pet1);
    System.out.println(pet2);
    System.out.println(pet2.getBreed());
    pet2.setBreed("calico");
    System.out.println(pet2.getBreed());
	  System.out.println(pet2);
  }
}
