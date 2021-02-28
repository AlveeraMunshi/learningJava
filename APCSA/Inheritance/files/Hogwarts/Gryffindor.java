package APCSA.Inheritance.files.Hogwarts;

public class Gryffindor extends HogwartsHouse
{
  private boolean hasHarryPotterThisYear;
  public Gryffindor (int housePoints, boolean hasHarryPotterThisYear)
  {
    super("Red", "Yellow", "Godric Gryffindor", "Lion", housePoints, "Minerva McGonagall", "Sir Peeves");
    this.hasHarryPotterThisYear = hasHarryPotterThisYear;
  }
  public void doAnything()
  {
    this.housePoints+=10;
  }
  public String toString()
  {
    return "Gryffindor" + " Colors: " + getColor1() + " " + getColor2() +  " Founder: " + getFounder() + " Animal: " + getEmblemAnimal() + " House Points: " + getHousePoints() + " Headmistress: " + getHead() + " Ghost: " + getGhost() + " Harry?: " + hasHarryPotterThisYear;
  }
}
