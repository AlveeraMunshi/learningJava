package APCSA.Inheritance.files.Hogwarts;

public class Hufflepuff extends HogwartsHouse
{
  public Hufflepuff (int housePoints)
  {
    super("Yellow", "Black", "Helga Hufflepuff", "Racoon", housePoints, "Professor Sprout", "Fat Friar");
  }
  public String toString()
  {
    return "Hufflepuff" + " Colors: " + getColor1() + " " + getColor2() + " Founder: " + getFounder() + " Animal: " + getEmblemAnimal() + " House Points: " + getHousePoints() + " Head: " + getHead() + " Ghost: " + getGhost();
  }
}
