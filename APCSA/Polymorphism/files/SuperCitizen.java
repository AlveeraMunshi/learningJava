package APCSA.Polymorphism.files;

public class SuperCitizen extends Person
{
    private String superPower;
    private boolean cape;
    private int powerLevel;
    private String superName;

    public SuperCitizen(String name, String job, int hitPoint, int maxDamage, int defenseAbility, String superPower, boolean cape, int powerLevel, String superName)
		{
			super(name, job, hitPoint, maxDamage, defenseAbility);
      this.superPower = superPower;
      this.cape = cape;
      this.powerLevel = powerLevel;
      this.superName = superName;
		}
    public String getSuperPower()
		{
			return superPower;
		}
    public boolean getCape()
		{
			return cape;
		}
    public int getPowerLevel()
		{
			return powerLevel;
		}
    public void powerLevelModification(int newLevel)
		{
			powerLevel = newLevel;
		}
    public String getSuperName()
		{
			return superName;
		}
		public static void main (String[]args)
		{
		}
}
