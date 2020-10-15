package APCSA.ArraysReview.files;

import java.util.Scanner;

public class ArrayPractice3
{
	public ArrayPractice3()
	{
		int[] list = new int[30];
		for (int x = 0; x < list.length; x++)
		{
			list[x] = (int)(Math.random()*50)+1;
			System.out.print(list[x] + " ");
		}
		System.out.println();
		System.out.println();

		for (int x = 0; x < list.length/2; x++)
		{
			int temp = list[x];
			list[x] = list[list.length - (x+1)];
			list[list.length - (x+1)] = temp;
		}
		for (int x = 0; x < list.length; x++)
		{
			System.out.print(list[x] + " ");
		}
		System.out.println();
		System.out.println();

		for (int x = 0; x < list.length; x++)
		{
			if (x%2 == 0)
				list[x] = 5;
			System.out.print(list[x] + " ");
		}
		System.out.println();
		System.out.println();

		int position = list.length - 1;
		int temp1 = 0;
		int temp2 = 0;
		while (position >= 0)
		{
			if (position == list.length - 1)
			{
				temp1 = list[position];
				list[position] = list[0];
			}
			else
			{
				temp2 = list[position];
				list[position] = temp1;
				temp1 = temp2;
			}
			position--;
		}

		for (int x = 0; x < list.length; x++)
		{
			System.out.print(list[x] + " ");
		}
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args)
	{
		ArrayPractice3 app = new ArrayPractice3();
  }
}
