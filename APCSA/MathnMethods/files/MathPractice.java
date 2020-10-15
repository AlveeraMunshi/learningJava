package APCSA.MathnMethods.files;

import java.lang.Math;

public class MathPractice
{
	public MathPractice()
	{
		System.out.println(Step1());
		System.out.println();

		System.out.println(Step2());
		System.out.println();

		System.out.println(Step3());
		System.out.println();

		System.out.println(Step4());
		System.out.println();

		System.out.println(Step5());
		System.out.println();

  }
	public static int Step1()
	{
		//Step 1
		System.out.println("1: ");
		int rand1 = (int)(Math.random() * 8) + 56;
		return rand1;
	}
	public static int Step2()
	{
		//Step 2
		System.out.println("2: ");
		int rand2 = (int)(Math.random() * 963) + 45;
		System.out.println(rand2);
		System.out.print("Squared: ");
		return (int)(Math.pow(rand2, 2));
	}
	public static double Step3()
	{
		System.out.println("3: ");
		int rand3 = (int)(Math.random() * 117) + 633;
		System.out.println(rand3);
		System.out.print("Square root: ");
		return Math.sqrt(rand3);
	}
	public static double Step4()
	{
		System.out.println("4: ");
		int sum = 0;
		for (int x = 0; x < 10; x++)
		{
			int rand4 = (int)(Math.random() * 67) + 7;
			sum+=rand4;
			System.out.print(rand4 + " ");
		}
		double avg = sum/10.0;
		System.out.print("\nAverage: ");
		return avg;
	}
	public static int Step5()
	{
		System.out.println("5: ");
		int min = 103;
		for (int x = 0; x < 20; x++)
		{
			int rand5 = (int)(Math.random() * 19) + 85;
			if (rand5 < min)
				min = rand5;
			System.out.print(rand5 + " ");
		}
		System.out.print("\nMin: ");
		return min;
	}
	public static void main (String[]args)
	{
		MathPractice temp = new MathPractice();
	}
}
