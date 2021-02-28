package APCSA.Polymorphism.files;

import java.util.*;

public class GothamLikeAdventureTown
{
  public static void main(String[] args)
  {
    ArrayList<Person> dailyPlanetStreet = new ArrayList<Person>();
    String[] firstNames = {"Layla", "Laiba", "Rushi", "Leanne", "Talia", "Alika", "Allistair", "Neil", "Kabir", "Yusuf", "Zayn", "Ali"};
    String[] lastNames = {"Akbar", "Khan", "Yusuf", "Mohammed", "Ali", "Rahman", "Abdullah", "Abbasi", "Shah", "Saleh", "Tariq", "Usman"};
    String[] occupations = {"Teacher", "Doctor", "Engineer", "Consultant", "Banker", "Librarian", "Police Officer", "Construction Worker", "Nurse", "College Student"};

    int heroIndex = (int)(Math.random()*49);
    int villanIndex = (int)(Math.random()*49);
    SuperHero hero = new SuperHero("Clark Kent", "Hero", "Superman", "A lot of them", true, 10, "This looks like a job for Superman!", 100, 10, 10);
    SuperVillan villan = new SuperVillan("Jack Napier", "Villan", "Joker", "Laughter", false, 7, "Ahahaha", 90, 7, 7);

    //Initialization of Daily Planet Street
    for (int x = 0; x < 50; x++)
    {
      int firstNameIndex = (int)(Math.random()*12);
      int lastNameIndex = (int)(Math.random()*12);
      int occupationIndex = (int)(Math.random()*10);

      if (x == heroIndex)
      {
        dailyPlanetStreet.add(hero);
      }
      else if (x == villanIndex)
      {
        dailyPlanetStreet.add(villan);
      }
      else
      {
        int rand = (int)(Math.random());
        if (rand == 0)
        {
          dailyPlanetStreet.add(new NormalCitizen(firstNames[firstNameIndex] + " " + lastNames[lastNameIndex], occupations[occupationIndex], 20, 2, 3));
        }
        else
        {
          dailyPlanetStreet.add(new Person(firstNames[firstNameIndex] + " " + lastNames[lastNameIndex], occupations[occupationIndex], 20, 2, 3));
        }
      }
    }

    ArrayList<Person> safetyLand = new ArrayList<Person>();
    ArrayList<Person> unfortunatelyFatallyWoundedPeople = new ArrayList<Person>();

    while (dailyPlanetStreet.size() > 3)
    {
      VillanAttack(dailyPlanetStreet, unfortunatelyFatallyWoundedPeople, villan, hero);
      if (dailyPlanetStreet.size() > 2)
      {
        save(dailyPlanetStreet, safetyLand);
        moveHero(dailyPlanetStreet);
        heroIndex = HeroFinder(dailyPlanetStreet);
      }
    }

    //Combat
    int hitter = (int)(Math.random());
    heroIndex = HeroFinder(dailyPlanetStreet);
    villanIndex = VillanFinder(dailyPlanetStreet);
    System.out.println("It is now time for our final showdown between our hero " + hero.getName() + " aka " + hero.getSuperName() + " and our villan " + villan.getName() + " aka " + villan.getSuperName() + "!\n");
    while (hero.getHitPoint() > 0 && villan.getHitPoint() > 0)
    {
      if (hitter == 0) //Hero Attacks
      {
        int damage = (int)(Math.random()*hero.getMaxDamage()+1);
        int defense = (int)(Math.random()*villan.getDefenseAbility()+1);
        if (damage >= defense)
        {
          System.out.println(hero.getSuperName() + " hits " + villan.getSuperName() + " with " + damage + "!");
          dailyPlanetStreet.get(villanIndex).hpReduction(damage);
          villan.hpReduction(damage);
          System.out.println(villan.getSuperName() + " is left with " + villan.getHitPoint() + "!\n");
        }
        else
        {
          System.out.println(hero.getSuperName() + " is blocked by " + villan.getSuperName() + "! No damage!\n");
        }
        hitter++;
      }
      else //Villan Attacks
      {
        int damage = (int)(Math.random()*villan.getMaxDamage()+1);
        int defense = (int)(Math.random()*hero.getDefenseAbility()+1);
        if (damage >= defense)
        {
          System.out.println(villan.getSuperName() + " hits " + hero.getSuperName() + " with " + damage + "!");
          dailyPlanetStreet.get(heroIndex).hpReduction(damage);
          hero.hpReduction(damage);
          System.out.println(hero.getSuperName() + " is left with " + hero.getHitPoint() + "!\n");
        }
        else
        {
          System.out.println(villan.getSuperName() + " is blocked by " + hero.getSuperName() + "! No damage!\n");
        }
        hitter--;
      }
    }
    if (villan.getHitPoint() < 0)
    {
      System.out.println(villan.getSuperName() + " has been defeated! " + hero.getSuperName() + " wins!");
    }
    else
    {
      System.out.println(hero.getSuperName() + " has been defeated! " + villan.getSuperName() + " wins!");
    }

  }
  public static void print(ArrayList<Person> list)
  {
    for (int x = 0; x < list.size(); x++)
      System.out.print(list.get(x).getName() + ", ");
    System.out.println();
  }
  public static void VillanAttack(ArrayList<Person> street, ArrayList<Person> dead, SuperVillan villan, SuperHero hero)
  {
    int villanIndex = VillanFinder(street);
    int rand = (int)(Math.random());
    if (villanIndex == 0)
    {
      if (!isHero(street.get(villanIndex+1)))
      {
        kill(street, dead, villanIndex+1);
      }
    }
    else if (villanIndex == street.size() - 1)
    {
      if (!isHero(street.get(villanIndex-1)))
      {
        kill(street, dead, villanIndex-1);
      }
    }
    else
    {
      if (rand == 0)
      {
        if (!isHero(street.get(villanIndex+1)))
        {
          kill(street, dead, villanIndex+1);
        }
        else
        {
          kill(street, dead, villanIndex-1);
        }
      }
      else
      {
        if (!isHero(street.get(villanIndex-1)))
        {
          kill(street, dead, villanIndex-1);
        }
        else
        {
          kill(street, dead, villanIndex+1);
        }
      }
    }

    villanIndex = VillanFinder(street);
    System.out.println(street.get(villanIndex).getName() + ": \"" + villan.getEvilLaugh() + "\"");
    System.out.println(hero.getName() + ": \"" + hero.getCatchPhrase() + "\"");
  }
  public static int VillanFinder(ArrayList<Person> list)
  {
    int villanIndex = list.size()-1;
    for (int x = 0; x < list.size(); x++)
    {
      if (isVillan(list.get(x)))
      {
          villanIndex = x;
      }
    }
    return villanIndex;
  }
  public static int HeroFinder(ArrayList<Person> list)
  {
    int heroIndex = list.size()-1;
    for (int x = 0; x < list.size(); x++)
    {
      if (isHero(list.get(x)))
      {
          heroIndex = x;
      }
    }
    return heroIndex;
  }
  public static int randPersonFinder(ArrayList<Person> street)
  {
    int streetindex = (int)(Math.random()*(street.size()));
    while (isHero(street.get(streetindex)) || isVillan(street.get(streetindex)))
    {
      streetindex = (int)(Math.random()*(street.size()));
    }
    return streetindex;
  }
  public static boolean isHero(Person person)
  {
    if (person instanceof SuperHero)
      return true;
    return false;
  }
  public static boolean isVillan(Person person)
  {
    if (person instanceof SuperVillan)
      return true;
    return false;
  }
  public static void kill(ArrayList<Person> street, ArrayList<Person> dead, int streetindex)
  {
    dead.add(street.get(streetindex));
    System.out.println(street.get(streetindex).getName() + " has been killed!");
    street.remove(streetindex);
  }
  public static void save(ArrayList<Person> street, ArrayList<Person> safety)
  {
    if (street.size() != 2)
    {
      int streetIndex = randPersonFinder(street);
      System.out.println(street.get(streetIndex).getName() + " has been saved!");
      safety.add(street.get(streetIndex));
      street.remove(streetIndex);
    }
  }
  public static void moveHero(ArrayList<Person> street)
  {
    if (street.size() != 2)
    {
      int heroIndex = HeroFinder(street);
      int randIndex = randPersonFinder(street);
      Person temp = street.get(heroIndex);
      street.set(heroIndex, street.get(randIndex));
      street.set(randIndex, temp);
    }
  }

}
