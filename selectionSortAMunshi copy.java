package selectionSortAMunshi;

public class selectionSortAMunshi 
{

	public static void main(String[] args) 
	{
		int[] a = {5, 6, 7, 2, 4, 3, 0, -1};
		selectionSort(a);
		
	}
	
	public static void selectionSort(int[] a)
	{
		for (int x = 0; x < a.length; x++)
		{
			System.out.print(a[x]+"\t");
		}
		System.out.println();
		int start = 0;
		while (start < a.length)
		{
			int index = start;
			for (int x = start; x < a.length; x++)
			{
				if (a[x]< a[start] && a[x]< a[index])
				{
	
					index = x;
				}
			}
			int temp = a[start];
			a[start]=a[index];
			a[index]=temp;
			start++;
		}
		for (int x = 0; x < a.length; x++)
		{
			System.out.print(a[x]+"\t");
		}
		System.out.println();
	}


}
