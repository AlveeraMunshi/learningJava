package APCSA.Inheritance.files;

class Tshirt extends Clothing
{
  private String fabric;

	public Tshirt(String size, String color, String fabric)
	{
		super(size, color);
    this.fabric = fabric;
	}
  public String getFabric()
  {
    return fabric;
  }
}
