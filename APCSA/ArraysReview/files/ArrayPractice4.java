package APCSA.ArraysReview.files;

import java.util.Scanner;

public class ArrayPractice4
{
	public ArrayPractice4()
	{
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter max: ");
		int max = reader.nextInt();
		int length = 0;
		for (int x = max; x > 0; x--)
		{
			if ((x*x)>max)
				length = x;
		}

		int[][] list = new int[length][length];
		int counter = 0;
		int x = 0;
		int y = 0;
		int barrier1 = length - 1;
		int barrier2 = 0;
		for (int i = 0; i <= length/2; i++)
		{
			while( y < (barrier1) )
			{
				if (counter <= max)
				{
					list[x][y] = counter;
					counter++;
				}
				else
				{
					list[x][y] = 0;
				}
				y++;
			}
			while( x < (barrier1) )
			{
				if (counter <= max)
				{
					list[x][y] = counter;
					counter++;
				}
				else
				{
					list[x][y] = 0;
				}
				x++;
			}
			while( y > (barrier2) )
			{
				if (counter <= max)
				{
					list[x][y] = counter;
					counter++;
				}
				else
				{
					list[x][y] = 0;
				}
				y--;
			}
			while( x > (barrier2+1) )
			{
				if (counter <= max)
				{
					list[x][y] = counter;
					counter++;
				}
				else
				{
					list[x][y] = 0;
				}
				x--;
			}
			barrier1--;
			barrier2++;
		}
		for (int i = 0; i < list.length; i++)
		{
			for (int j = 0; j < list[i].length; j++)
			{
				System.out.print(list[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args)
	{
		ArrayPractice4 app = new ArrayPractice4();
  }
}
