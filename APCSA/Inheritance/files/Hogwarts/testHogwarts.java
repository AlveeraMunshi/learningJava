package APCSA.Inheritance.files.Hogwarts;

public class testHogwarts {
  public static void main(String[] args) {
    Gryffindor house1 = new Gryffindor(100, true);
    Slytherin house2 = new Slytherin(100, 100);
    System.out.println(house1);
    System.out.println(house2);
    house1.doAnything();
    System.out.println(house1);
    System.out.println(house1.getColor1());
    System.out.println(house1.getColor2());
    System.out.println(house1.getFounder());
    System.out.println(house1.getEmblemAnimal());
    System.out.println(house1.getHousePoints());
    System.out.println(house1.getGhost());
  }
}
