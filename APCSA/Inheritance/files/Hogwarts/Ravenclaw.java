package APCSA.Inheritance.files.Hogwarts;

public class Ravenclaw extends HogwartsHouse
{
  public Ravenclaw (int housePoints)
  {
    super("Blue", "Bronze", "Rowena Ravenclaw", "Crow", housePoints, "Filius Flitwick", "Grey Lady");
  }
  public String toString()
  {
    return "Ravenclaw" + " Colors: " + getColor1() + " " + getColor2() + " Founder: " + getFounder() + " Animal: " + getEmblemAnimal() + " House Points: " + getHousePoints() + " Head: " + getHead() + " Ghost: " + getGhost();
  }
}
