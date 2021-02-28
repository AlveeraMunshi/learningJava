package APCSA.Inheritance.files.Hogwarts;

public class HogwartsHouse
{
  private String color1;
  private String color2;
  private String founder;
  private String emblemAnimal;
  int housePoints;
  private String head;
  private String ghost;
  public HogwartsHouse (String color1, String color2, String founder, String emblemAnimal, int housePoints, String head, String ghost)
  {
    this.color1 = color1;
    this.color2 = color2;
    this.founder = founder;
    this.emblemAnimal = emblemAnimal;
    this.housePoints = housePoints;
    this.head = head;
    this.ghost = ghost;
  }
  public String getColor1()
  {
    return color1;
  }
  public String getColor2()
  {
    return color2;
  }
  public String getFounder()
  {
    return founder;
  }
  public String getEmblemAnimal()
  {
    return emblemAnimal;
  }
  public int getHousePoints()
  {
    return housePoints;
  }
  public String getHead()
  {
    return head;
  }
  public String getGhost()
  {
    return ghost;
  }
  public void setHousePoints(int housePoints)
  {
    this.housePoints = housePoints;
  }
}
