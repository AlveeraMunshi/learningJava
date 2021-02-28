package APCSA.SearchesSorts.files;

import java.util.ArrayList;

public class SortRunner
{
	public SortRunner()
  {
		ArrayList<Integer> list = new ArrayList<Integer>();
    for (int x = 0; x < 30; x++)
    {
      list.add((int)(Math.random()*200+1));
    }
    System.out.println(list);

    Sorter sort=new Sorter();
		Integer num=4;
		System.out.println(sort.linearSearch(list,num));

	}
	public static void main(String args[])
	{
		SortRunner app = new SortRunner();
	}
}
