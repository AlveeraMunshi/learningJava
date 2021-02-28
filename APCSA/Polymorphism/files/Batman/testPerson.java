package APCSA.Polymorphism.files;

public class testPerson {
  public static void main(String[] args) {
    Person me = new Person("Alveera", "Data Scientist", 100, 100, 100);
    System.out.println("1. " + me.getJob() + ": " + me.getName());
    Person bob = new NormalCitizen("Bob", "Builder", 1, 2, 3);
    System.out.println("2. " + bob.getJob() + ": " + bob.getName());
    Person whyattperson = new SuperCitizen("Whyatt", "Hero", 4, 5, 6, "Reading", true, 3, "SuperWhy");
    System.out.println("3. " + whyattperson.getJob() + ": " + whyattperson.getName());
    SuperCitizen whyattsuperc = new SuperHero("Whyatt", "Hero", "SuperWhy", "Reading", true, 3, "With the power to read, I can change this story and save the day!", 4, 5, 6);
    System.out.println("4. " + whyattsuperc.getJob() + ": " + whyattsuperc.getName() + ": " + whyattsuperc.getSuperPower() + ": " + whyattsuperc.getCape() + ": " + whyattsuperc.getPowerLevel());
    System.out.println("Whyatt Old Power Level: " + whyattsuperc.getPowerLevel());
    whyattsuperc.powerLevelModification(4);
    System.out.println("Whyatt New Power Level: " + whyattsuperc.getPowerLevel());
    SuperHero whyattsuperh = new SuperHero("Whyatt", "Hero", "SuperWhy", "Reading", true, 3, "With the power to read, I can change this story and save the day!", 4, 5, 6);
    System.out.println("5. " + whyattsuperh.getJob() + ": " + whyattsuperh.getName() + ": " + whyattsuperh.getSuperPower() + ": " + whyattsuperh.getCape() + ": " + whyattsuperh.getPowerLevel() + ": " + whyattsuperh.getCatchPhrase());
    SuperVillan bigbadwolf = new SuperVillan("Big Bad Wolf", "Villan", "Big Bad Wolf", "Blowing", false, 5, "Awoooo!", 7, 8, 9);
    System.out.println("6. " + bigbadwolf.getJob() + ": " + bigbadwolf.getName() + ": " + bigbadwolf.getSuperPower() + ": " + bigbadwolf.getCape() + ": " + bigbadwolf.getPowerLevel() + ": " + bigbadwolf.getEvilLaugh());
  }
}
