package Stacks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Stack;

public class StarWars {
    public StarWars()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/Stacks/StarWarsCharacters.csv");
        Stack<Character> males = new Stack<>();
        Stack<Character> females = new Stack<>();
        Stack<Character> droids = new Stack<>();
        Stack<Character> validbirthyear = new Stack<>();
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
            {
                String[] pieces = text.split(",");
                Character c = new Character(pieces[0], pieces[5], pieces[6], pieces[7], pieces[8]);
                if (c.getGender().equalsIgnoreCase("female"))
                {
                    females.push(c);
                }
                if (c.getGender().equalsIgnoreCase("male"))
                {
                    males.push(c);
                }
                if (c.getSpecies().equalsIgnoreCase("droid"))
                {
                    droids.push(c);
                }
                if (!c.getBirth().equalsIgnoreCase("NA"))
                {
                    validbirthyear.push(c);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println("Male Characters");
        System.out.format("%1$-25s %2$-20s %3$-20s %4$-20s %5$-20s\n", "Name", "Birth Year", "Gender", "Homeworld", "Species");
        while(!males.isEmpty())
        {
            Character c = males.pop();
            System.out.format("%1$-25s %2$-20s %3$-20s %4$-20s %5$-20s\n", c.getName(), c.getBirth(), c.getGender(), c.getHome(), c.getSpecies());
        }

    }
    public class Character
    {
        private String name, birthyear, gender, homeworld, species;
        public Character(String name, String birthyear, String gender, String homeworld, String species)
        {
            this.name = name;
            this.birthyear = birthyear;
            this.gender = gender;
            this.homeworld = homeworld;
            this.species = species;
        }
        public String getName()
        {
            return name;
        }
        public String getBirth()
        {
            return birthyear;
        }
        public String getGender()
        {
            return gender;
        }
        public String getHome()
        {
            return homeworld;
        }
        public String getSpecies()
        {
            return species;
        }
    }
    public static void main(String[]args)
    {
        StarWars app = new StarWars();
    }
}