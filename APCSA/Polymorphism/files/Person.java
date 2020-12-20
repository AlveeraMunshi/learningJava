package APCSA.Polymorphism.files;

public class Person
{
		private String name;
		private String job;
		private int hitPoint;
		private int maxDamage;
		private int defenseAbility;

		public Person(String name, String job, int hitPoint, int maxDamage, int defenseAbility)
		{
			this.name = name;
			this.job = job;
			this.hitPoint = hitPoint;
			this.maxDamage = maxDamage;
			this.defenseAbility = defenseAbility;
		}
		public String getName()
		{
			return name;
		}
		public String getJob()
		{
			return job;
		}
		public int getHitPoint()
		{
			return hitPoint;
		}
		public int getMaxDamage()
		{
			return maxDamage;
		}
		public int getDefenseAbility()
		{
			return defenseAbility;
		}
		public void hpReduction(int damage)
		{
			hitPoint-=damage;
		}
		public static void main (String[]args)
		{
		}
}
