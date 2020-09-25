package APCSA.files;

import java.util.Scanner;

public class ArrayPractice1
{
	public ArrayPractice1()
	{
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter array length/width: ");
		int length = reader.nextInt();
		System.out.print("Enter random max: ");
		int max = reader.nextInt();
		System.out.print("Enter random min: ");
		int min = reader.nextInt();
		System.out.print("Number of occurences of each digit: ");
		int limit = reader.nextInt();
		int list[][] = new int[length][length];
		int random[] = new int [list.length*list.length];
		int mintemp = min;
		int index = 0;
		while (mintemp <= max)
		{
			int counter = 0;
			while (counter < limit)
			{
				random[index] = mintemp;
				index++;
				counter++;
			}
			mintemp++;
		}
		for (int x = 0; x < random.length; x++)
		{
			int rand = (int)(Math.random()*random.length);
			int temp = random[rand];
			random[rand] = random[x];
			random[x] = temp;
		}
		index = 0;
		for(int i = 0; i < list.length; i++)
		{
			for(int x = 0; x < list[i].length; x++)
			{
				list[i][x] = random[index];
				index++;
				System.out.print(list[i][x] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args)
	{
		ArrayPractice1 app = new ArrayPractice1();
  }
}
