import java.util.StringTokenizer;

public class StringTokenizerExample
{
	public StringTokenizerExample()
	{
		String s = "I solmenly swear that I up to no good";
		StringTokenizer st = new StringTokenizer(s, ",?", false);
		/*while (st.hasMoreElements())
		{
			System.out.println(st.nextElement());
		}*/
		System.out.println(st.countTokens());
		while(st.hasMoreTokens())
		{
			System.out.println(st.nextToken());
		}
		System.out.println(st.countTokens());
	}
	public static void main (String[]args)
	{
		StringTokenizerExample run = new StringTokenizerExample();
	}
}