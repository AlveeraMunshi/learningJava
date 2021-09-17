public class Pendant
{
	private String color;
	public Pendant(String color)
	{
		this.color = color;
	}
	public String toString()
	{
		return color + " pendant will lead you home";
	}
	public static class Stone
	{
		private String type;
		public Stone(String type)
		{
			this.type = type;
		}
		public String toString()
		{
			return "Type: " + type;
		}
	}

}