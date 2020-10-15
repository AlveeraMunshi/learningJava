package APCSA.MathnMethods.files;
import java.lang.Math;

public class Spinners
{
	public Spinners()
	{
		int spin1 = spin(2);
		int spin2;
		int spin3;
		int spin4;
		int spin5;
		boolean result = false;
		System.out.println("Spin 1: " + spin1);
		if (isEven(spin1)) //Spin is even
		{
			spin2 = spin(); //5 section spinner 1
			System.out.println("Spin 2: " + spin2);
			spin3 = spin(); //5 section spinner 2
			System.out.println("Spin 3: " + spin3);
			spin4 = spin(4); //4 section spinner 1
			System.out.println("Spin 4: " + spin4);
			int small5 = lowest(spin2, spin3);
			int big5 = highest(spin2, spin3);
			//if the product of the lowest 5 section spin and the 4 section spin are greater than square of other 5 section spin
			if (product(small5, spin4) >= Math.pow(big5, 2))
				result = true;
		}
		else //Spin is odd
		{
			spin2 = spin(); //5 section spinner 1
			System.out.println("Spin 2: " + spin2);
			spin3 = spin(); //5 section spinner 2
			System.out.println("Spin 3: " + spin3);
			spin4 = spin(); //5 section spinner 3
			System.out.println("Spin 4: " + spin4);
			spin5 = spin(10); //10 section spinner
			System.out.println("Spin 5: " + spin5);
			int small5 = lowest(spin2, spin3, spin4);
			int firstbigger5;
			int secondbigger5;
			if (small5 == spin2) //if spin2 is smallest, spin 3 and 4 remain
			{
				firstbigger5 = spin3;
				secondbigger5 = spin4;
			}
			else if (small5 == spin3) //if spin3 is smallest, spin 2 and 4 remain
			{
				firstbigger5 = spin2;
				secondbigger5 = spin4;
			}
			else //if spin4 is smallest, spin 2 and 3 remain
			{
				firstbigger5 = spin2;
				secondbigger5 = spin3;
			}
			if (product(firstbigger5, secondbigger5) >= product(small5, spin5))
				result = true;
		}
		gameresult(result);
  }
	public static int spin() //default spinner method
	{
		return (int)(Math.random()*5+1); //random 1-5
	}
	public static int spin(int section) //modifiable spinner method
	{
		return (int)(Math.random()*section+1); //random 1-bound
	}
	public static int product(int n1, int n2) //returns the product of two integers
	{
		return n1*n2;
	}
	public static boolean isEven(int n) //determines if recieved spin is even
	{
		if (n%2 == 0) //even
			return true;
		else //odd
			return false;
	}
	public static boolean isGreater (int n1, int n2) //Is 1st greater than or equal to second?
	{
		return n1 >= n2;
	}
	public static int highest(int n1, int n2) //determines which of two values is highest
	{
		if (isGreater(n1, n2)) //n1 > n2
			return n1;
		else
			return n2; // n2 > n1
	}
	public static int lowest(int n1, int n2) //determines which of two values is lowest
	{
		if (isGreater(n1, n2)) //n1 > n2
			return n2;
		else
			return n1; // n2 > n1
	}
	public static int lowest(int n1, int n2, int n3)//determines which of three values is lowest
	{
		if (isGreater(n2, n1)) //n1 < n2
		{
			if (isGreater(n3, n2)) //n2 < n3
				return n1;
			else //n3 < n2
			{
				if (isGreater(n3, n1)) //n1 < n3
					return n1;
				else // n3 < n1
					return n3;
			}
		}
		else //n2 < n1
		{
			if (isGreater(n3, n1)) //n1 < n3
				return n2;
			else //n3 < n1
			{
				if (isGreater(n3, n2)) //n2 < n3
					return n2;
				else //n3 < n2
					return n3;
			}
		}
	}
	public static void gameresult (boolean result) //Uses bool to determine win or lose
	{
		if (result)
			System.out.println("You win!");
		else
			System.out.println("You lose!");
	}
  public static void main (String[]args)
  {
		Spinners app = new Spinners();
  }
}
