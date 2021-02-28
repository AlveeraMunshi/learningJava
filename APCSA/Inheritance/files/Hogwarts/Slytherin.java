package APCSA.Inheritance.files.Hogwarts;

public class Slytherin extends HogwartsHouse
{
  int negativityRating;
  public Slytherin (int housePoints, int negativityRating)
  {
    super("Green", "Silver", "Salazar Slytherin", "Snake", housePoints, "Severus Snape", "Bloody Baron");
    this.negativityRating = negativityRating;
  }
  public String toString()
  {
    return "Slytherin" + " Colors: " + getColor1() + " " + getColor2() + " Founder: " + getFounder() + " Animal: " + getEmblemAnimal() + " House Points: " + getHousePoints() + " Head: " + getHead() + " Ghost: " + getGhost() + " Negativity Rating: " + negativityRating;
  }
}
