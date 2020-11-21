package APCSA.Objects.files;

import java.util.ArrayList;

public class testHoliday {
  public static void main(String[] args) {
		// Test Code - DO NOT CHANGE THE CODE
		// You will unmute the section below when you have completed creating the Holiday Class to test your work

		ArrayList<Holiday> holidays2019 = new ArrayList<Holiday>(); // Sets up Arraylist of Holidays

		// add new objects instances into the arraylist
		holidays2019.add(new Holiday("Diwali", "October", 27));
		holidays2019.add(new Holiday("Independence Day", "July", 4));
		holidays2019.add(new Holiday("Christmas Day", "December", 25));
		holidays2019.add(new Holiday("New Year's Day", "January", 1));
		holidays2019.add(new Holiday("Valentine's Day", "February", 14));
		holidays2019.add(new Holiday("Halloween", "October", 31));
		holidays2019.add(new Holiday("New Year's Eve", "December", 31));
		holidays2019.add(new Holiday("Thanksgiving", "November", 28));
		holidays2019.add(new Holiday("Mother's Day", "May", 12));
		holidays2019.add(new Holiday("Veteran's Day", "November", 11));

		Holiday blankHoliday = new Holiday(); // Test default constructor
		//test Mutator Methods
		blankHoliday.setName("Memorial Day"); //
		blankHoliday.setMonth("May");
		blankHoliday.setDay(27);

		holidays2019.add(blankHoliday);

		// prints values in arraylist
		System.out.println("Holidays 2019:\n");
		for(int i = 0; i < holidays2019.size(); i++)
			System.out.println(holidays2019.get(i));
		System.out.println();

		System.out.println("Avg Date: " + Holiday.avgDate(holidays2019)+"\n"); // Uses Class method avgDate() to calculate average of ArrayList
		// Result: 19.181818181818183

		ArrayList<Holiday> holidays2020 = new ArrayList<Holiday>();

		holidays2020.add(new Holiday("Diwali", "November", 14));
		holidays2020.add(new Holiday("Independence Day", "July", 4));
		holidays2020.add(new Holiday("Christmas Day", "December", 25));
		holidays2020.add(new Holiday("New Year's Day", "January", 1));
		holidays2020.add(new Holiday("Valentine's Day", "February", 14));
		holidays2020.add(new Holiday("Halloween", "October", 31));
		holidays2020.add(new Holiday("New Year's Eve", "December", 31));
		holidays2020.add(new Holiday("Thanksgiving", "November", 26));
		holidays2020.add(new Holiday("Mother's Day", "May", 10));
		holidays2020.add(new Holiday("Veteran's Day", "November", 11));

		System.out.println("Holidays 2020:\n");
		for(int i = 0; i < holidays2020.size(); i++)
			System.out.println(holidays2020.get(i));
		System.out.println();

		System.out.println("Avg Date: " + Holiday.avgDate(holidays2020)+"\n"); // Result: 16.7

		ArrayList<Holiday> holidays2021 = new ArrayList<Holiday>();

		holidays2021.add(new Holiday("Diwali", "November", 4));
		holidays2021.add(new Holiday("Independence Day", "July", 4));
		holidays2021.add(new Holiday("Christmas Day", "December", 25));
		holidays2021.add(new Holiday("New Year's Day", "January", 1));
		holidays2021.add(new Holiday("Valentine's Day", "February", 14));
		holidays2021.add(new Holiday("Halloween", "October", 31));
		holidays2021.add(new Holiday("New Year's Eve", "December", 31));
		holidays2021.add(new Holiday("Thanksgiving", "November", 25));
		holidays2021.add(new Holiday("Mother's Day", "May", 9));
		holidays2021.add(new Holiday("Veteran's Day", "November", 11));

		System.out.println("Holidays 2021:\n");
		for(int i = 0; i < holidays2021.size(); i++)
			System.out.println(holidays2021.get(i));
		System.out.println();

		System.out.println("Avg Date: " + Holiday.avgDate(holidays2021)+"\n"); // Result 15.5


		// test for accessor methods
		System.out.println(holidays2021.get(5).getName()); // Halloween
		System.out.println(holidays2021.get(5).getMonth()); // October
		System.out.println(holidays2021.get(5).getDay()); // 31
		// test for isSameMonth()
		System.out.println(holidays2020.get(5).isSameMonth(holidays2020.get(6))); // Halloween / NYE 2020 - false
		System.out.println(holidays2020.get(0).isSameMonth(holidays2021.get(7))); // Diwali / Thanksgiving 2021 - true
		// test for isSameDay()
		System.out.println(holidays2020.get(0).isSameDay(holidays2021.get(0))); // Diwali 2020/2021 - false
		System.out.println(holidays2020.get(1).isSameDay(holidays2021.get(1))); // Independence Day 2020/2020 - true

  }
}
