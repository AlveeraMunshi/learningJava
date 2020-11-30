package APCSA.Inheritance.files;

class Sweatshirt extends Clothing
{
  private boolean hasHood;

	public Sweatshirt(String size, String color, boolean hasHood)
	{
		super(size, color);
    this.hasHood = hasHood;
	}
  public boolean getHasHood()
  {
    return hasHood;
  }
}
