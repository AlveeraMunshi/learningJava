package APCSA.files;

import java.util.Scanner;

public class ArrayPractice2
{
	public ArrayPractice2()
	{
		int[] list = new int[40];
		for (int x = 0; x < list.length; x++)
		{
			list[x] = (int)(Math.random()*50)+1;
			System.out.print(list[x] + " ");
			if ((x+1)%10 == 0)
				System.out.println();
		}
		System.out.println();
		for (int x = 0; x < list.length; x++)
		{
			if (list[x]%2 == 0)
				list[x]-=2;
			System.out.print(list[x] + " ");
			if ((x+1)%10 == 0)
				System.out.println();
		}
		System.out.println();
		for (int x = 0; x < list.length; x++)
		{
			if (x%2 != 0)
				list[x]+=5;
			System.out.print(list[x] + " ");
			if ((x+1)%10 == 0)
				System.out.println();
		}
		System.out.println();
		for (int x = 0; x < list.length; x++)
		{
			if (list[x]%3 == 1 && x%2 == 0)
				list[x] = 8;
			System.out.print(list[x] + " ");
			if ((x+1)%10 == 0)
				System.out.println();
		}
		System.out.println();
		for (int x = 0; x < list.length; x++)
		{
			if (list[x]%4 == 3 && x%2 != 0)
				list[x] = 400;
			System.out.print(list[x] + " ");
			if ((x+1)%10 == 0)
				System.out.println();
		}
		System.out.println();
		int counter = 1;
		for (int x = 0; x < list.length; x++)
		{
			if (list[x]%2 == 0)
			{
				counter++;
				if (counter%2 == 0)
				{
					list[x]+=500;
				}
			}
			System.out.print(list[x] + " ");
			if ((x+1)%10 == 0)
				System.out.println();
		}
		System.out.println();
		for (int x = 0; x < list.length; x++)
		{
			if ((x+1)%10 == 0)
				list[x] = 8;
			else if ((x+1)%5 == 0)
				list[x] = 7;
			else
				list[x] = list[x];
			System.out.print(list[x] + " ");
			if ((x+1)%10 == 0)
				System.out.println();
		}
	}

	public static void main(String[] args)
	{
		ArrayPractice2 app = new ArrayPractice2();
  }
}
