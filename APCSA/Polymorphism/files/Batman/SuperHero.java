package APCSA.Polymorphism.files;

public class SuperHero extends SuperCitizen
{
    private String catchPhrase;

    public SuperHero(String name, String job, String superName, String superPower, boolean cape, int powerLevel, String catchPhrase, int hitPoints, int maxDamage, int defenseAbility)
		{
			super(name, job, hitPoints, maxDamage, defenseAbility, superPower, cape, powerLevel, superName);
      this.catchPhrase = catchPhrase;
		}
    public String getCatchPhrase()
		{
			return catchPhrase;
		}
    public int getPowerLevel()
		{
			return super.getPowerLevel() * (int)(Math.random()*10 + 1);
		}
		public static void main (String[]args)
		{
		}
}
