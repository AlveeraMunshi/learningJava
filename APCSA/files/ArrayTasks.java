package APCSA.files;

import java.util.Scanner;

public class ArrayTasks
{
	public ArrayTasks()
	{
		//Task 1
		System.out.println("Task 1\n");
		int[] task1 = new int[40];
		int sum = 0;
		int max = 1;
		int min = 100;

		for (int i = 0; i < task1.length; i++)
		{
			//Fill array with numbers 1-100 randomly
			task1[i] = (int)(Math.random()*100+1);
			System.out.print(task1[i] + " ");
			//20 numbers on first line
			if ((i+1)%20 == 0)
			System.out.println();
			//Check if min
			if (task1[i] < min)
			min = task1[i];
			//Check if max
			if (task1[i] > max)
			max = task1[i];
			//Add to sum
			sum+=task1[i];
		}
		//Return sum, max, min
		System.out.println("\nSum: " + sum);
		System.out.println("Max: " + max);
		System.out.println("Min: " + min);

		//Reverse order
		System.out.println("\nReversed: ");
		for (int x = 0; x < task1.length/2; x++)
		{
			int temp = task1[x];
			task1[x] = task1[task1.length - (x+1)];
			task1[task1.length - (x+1)] = temp;
		}
		for (int x = 0; x < task1.length; x++)
		{
			System.out.print(task1[x] + " ");
			//20 numbers on first line
			if ((x+1)%20 == 0)
			System.out.println();
		}
		System.out.println();
		System.out.println();

		//Task 2
		System.out.println("Task 2\n");
		int[][] task2 = new int[10][10];
		max = 10;
		min = 50;
		for (int i = 0; i < task2.length; i++)
		{
			for (int j = 0; j < task2[i].length; j++)
			{
				//Fill array with random numbers 10-50
				task2[i][j] = (int)(Math.random()*41+10);
				System.out.print(task2[i][j] + " ");
				//Check if min
				if (task2[i][j] < min)
				min = task2[i][j];
				//Check if max
				if (task2[i][j] > max)
				max = task2[i][j];
			}
			System.out.println();
		}
		System.out.println("\nMax: " + max);
		System.out.println("Min: " + min);
		System.out.println();
		for (int x = 0; x < task2.length; x++)
		{
			int colsum = 0;
			int rowsum = 0;
			int colmin = 50;
			int colmax = 10;
			int rowmin = 50;
			int rowmax = 10;
			for (int y = 0; y < task2[x].length; y++)
			{
				colsum+=task2[y][x];
				rowsum+=task2[x][y];
				//Check if min
				if (task2[y][x] < colmin)
				colmin = task2[y][x];
				//Check if max
				if (task2[y][x] > colmax)
				colmax = task2[y][x];
				if (task2[x][y] < rowmin)
				rowmin = task2[x][y];
				//Check if max
				if (task2[x][y] > rowmax)
				rowmax = task2[x][y];
			}
			//Print it out
			System.out.println("Sum of column #" + (x+1) + ": " + colsum);
			System.out.println("Sum of row #" + (x+1) + ": " + rowsum);
			System.out.println("Max of column #" + (x+1) + ": " + colmax);
			System.out.println("Min of column #" + (x+1) + ": " + colmin);
			System.out.println("Max of row #" + (x+1) + ": " + rowmax);
			System.out.println("Min of row #" + (x+1) + ": " + rowmin);
		}

		//Task 3
		System.out.println("\nTask 3\n");
		int[][] task3 = new int[5][5];
		for (int i = 0; i < task3.length; i++)
		{
			for (int j = 0; j < task3[i].length; j++)
			{
				//Fill array with row counting up
				task3[i][j] = i+j;
				System.out.print(task3[i][j] + " ");
			}
			System.out.println();
		}
		//Reverse order
		System.out.println("\nReversed: ");
		for (int x = 0; x < task3.length; x++)
		{
			for (int y = 0; y < task3[x].length/2; y++)
			{
				int temp = task3[x][y];
				task3[x][y] = task3[x][task3.length - (y+1)];
				task3[x][task3.length - (y+1)] = temp;
			}
		}
		//Print Reversed
		for (int i = 0; i < task3.length; i++)
		{
			for (int j = 0; j < task3[i].length; j++)
			{
				System.out.print(task3[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args)
	{
		ArrayTasks app = new ArrayTasks();
	}
}
