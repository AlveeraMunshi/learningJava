public class Wizard
{
	private String name;
	private String house;
	private int year;
	public Wizard(String name, String house, int year)
	{
		this.name = name;
		this.house = house;
		this.year = year;
	}
	public String toString()
	{
		return "The wizard's name is " + name + ". They are in " + house + " house. They are in year " + year + ".";
	}
	public static class Wand
	{
		private String core;
		public Wand()
		{
			core = "Phoenix Feather";
		}
		public String toString()
		{
			return "Core Type: " + core;
		}
	}
}