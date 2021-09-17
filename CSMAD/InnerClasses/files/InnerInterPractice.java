import java.util.*;

public class InnerInterPractice
{
	public static void main(String[]args)
	{
		//demonstrate SuperHero object functionality
		SuperHero shangchi = new SuperHero("Shang Chi", "10 rings", 1000);
		System.out.println(shangchi);
		shangchi.usePower(200);
		shangchi.takeDamage(100);
		shangchi.powerUp();
		System.out.println(shangchi);

		//demonstrate Pendant object functionality and inner class Stone functionality
		Pendant pendant = new Pendant("Green");
		System.out.println(pendant);
		Pendant.Stone stone = new Pendant.Stone("Jade");

		//comparable for SuperHero and Array List
		SuperHero superman = new SuperHero("Superman", "Flight", 800);
		System.out.println(shangchi.compareTo(superman));
		ArrayList<SuperHero> list = new ArrayList<SuperHero>();
		list.add(shangchi);
		list.add(superman);
		list.add(new SuperHero("Spider Man", "Webs", 900));
		list.add(new SuperHero("Iron Man", "Suit", 1500));
		list.add(new SuperHero("Wanda Maximoff", "Magic", 2000));
		list.add(new SuperHero("Vision", "Build", 2000));

		System.out.println(list);
		for (int x = 0; x < list.size(); x++)
		{
			for (int y = x; y < list.size(); y++)
			{
				if (list.get(x).compareTo(list.get(y)) == 1)
				{
					SuperHero temp = new SuperHero(list.get(y).getName(), list.get(y).getPower(), list.get(y).getHp());
					list.set(y, list.get(x));
					list.set(x, temp);
				}
			}
		}
		System.out.println();
		System.out.println(list);


	}
	public static class SuperHero implements Comparable<SuperHero>
	{
		private String name;
		private String power;
		private int hp;
		public SuperHero(String name, String power, int hp) //Initalize hero
		{
			this.name = name;
			this.power = power;
			this.hp = hp;
		}
		public void usePower(int damage) //inflict damage
		{
			System.out.println(power + " activate! Inflicted " + damage + " hp damage!");
		}
		public void takeDamage(int damage) //take damage
		{
			hp-=damage;
			System.out.println("Defense failure! " + damage + " hp lost!");
		}
		public void powerUp() //heal
		{
			System.out.println("Power up serum! 400 hp gained!");
			hp+=400;
		}
		public int getHp() //returns hitpoints
		{
			return hp;
		}
		public String getName()
		{
			return name;
		}
		public String getPower()
		{
			return power;
		}
		public String toString()
		{
			return "The hero's name is " + name + ". Their power is " + power + ". Their hp is " + hp + ".";
		}
		public int compareTo(SuperHero person)
		{
			if (this.getHp() > person.getHp()) //if this is greater, return 1
				return 1;
			else
				return 0; //if that is greater, return 0
		}
	}
}