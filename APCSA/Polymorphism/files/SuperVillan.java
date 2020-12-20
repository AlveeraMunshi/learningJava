package APCSA.Polymorphism.files;

public class SuperVillan extends SuperCitizen
{
    private String evilLaugh;

    public SuperVillan(String name, String job, String superName, String superPower, boolean cape, int powerLevel, String evilLaugh, int hitPoints, int maxDamage, int defenseAbility)
		{
			super(name, job, hitPoints, maxDamage, defenseAbility, superPower, cape, powerLevel, superName);
      this.evilLaugh = evilLaugh;
		}
    public String getEvilLaugh()
		{
			return evilLaugh;
		}
		public static void main (String[]args)
		{
		}
}
