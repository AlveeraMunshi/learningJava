public class InnerClasses
{
	public static void main(String[]args)
	{
		Wizard wiz = new Wizard("Harry Potter", "Gryffindor", 2);
		System.out.println(wiz);
		Wizard.Wand wand = new Wizard.Wand();
		System.out.println(wand);
		SortingHat hat = new SortingHat();
		System.out.println(hat);
	}
	public static class SortingHat
	{
		private String nextHouse;
		public SortingHat()
		{
			nextHouse = "Gryffindor";
		}
		public String toString()
		{
			return nextHouse;
		}
	}
}