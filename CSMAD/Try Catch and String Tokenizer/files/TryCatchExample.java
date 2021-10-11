import java.util.Scanner;
public class TryCatchExample
{
	public int[] arr = {0, 1, 2, 3, 4, 5, 6};
	public Scanner scan = new Scanner(System.in);
	public TryCatchExample()
	{
		start();
	}
	public static void main (String[]args)
	{
		TryCatchExample run = new TryCatchExample();
	}
	public void start()
	{
		System.out.print("Select a value from the range 0-6:\t");
		int val = scan.nextInt();
		accessValue(val);
	}
	public void accessValue(int val)
	{
		try
		{
			System.out.println(arr[val]);
			System.out.println("My line of code after print the array");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("You tried to access a value outside the range of the array");
		}
		finally
		{
			System.out.println("I'm done!");
		}
	}
}
