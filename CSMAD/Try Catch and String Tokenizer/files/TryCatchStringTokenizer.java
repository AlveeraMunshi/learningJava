import java.util.*;

public class TryCatchStringTokenizer
{
	public TryCatchStringTokenizer()
	{
		String s = "Yusha Yusuf's a dummy. Aloe is an idiot. Mysh is a wannabe drug addict. Sushi has not slept. Naan is mad at the world.";
		StringTokenizer st1 = new StringTokenizer(s, ".,?!';", false);
		System.out.println(st1.countTokens()); //counts tokens that are punctation
		StringTokenizer st2 = new StringTokenizer(s, ".", false);
		System.out.println(st2.countTokens()); //counts tokens that are sentences
		StringTokenizer st3 = new StringTokenizer(s, " ", false);
		System.out.println(st3.countTokens()); //counts tokens that are words
		try
		{
			StringTokenizer st4 = new StringTokenizer(s, ".", false);
			double sum = 0;
			int numSentences = st4.countTokens();
			while (st4.hasMoreElements())
			{
				StringTokenizer st5 = new StringTokenizer((String)st4.nextElement(), " ", false);
				sum+=st5.countTokens();
			}
			double avg = sum/numSentences;
			System.out.println("Average: " + avg);
		}
		catch (Exception e)
		{
			System.out.println("Invalid function");
		}
		try
		{
			StringTokenizer st6 = new StringTokenizer(s, " ", true);
			int numWords = 0;
			while (st6.hasMoreElements())
			{
				String word = (String)st6.nextElement();
				int vowels = 0;
				for (int x = 0; x < word.length(); x++)
				{
					switch (word.substring(x, x+1))
					{
						case "a":
							vowels++;
							break;
						case "e":
							vowels++;
							break;
						case "i":
							vowels++;
							break;
						case "o":
							vowels++;
							break;
						case "u":
							vowels++;
							break;
					}
				}
				if (vowels > 1)
					numWords++;
			}
			System.out.println("Words with more than one vowel: " + numWords);
		}
		catch (Exception e)
		{
			System.out.println("Invalid function");
		}
	}
	public static void main (String[]args)
	{
		TryCatchStringTokenizer runner = new TryCatchStringTokenizer();
	}
}

