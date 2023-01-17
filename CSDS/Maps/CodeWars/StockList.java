package Maps.CodeWars;

import java.util.*;

public class StockList 
{ 
	
  // 1st parameter is the stocklist (L in example), 
	// 2nd parameter is list of categories (M in example)
	public static String stockSummary(String[] lstOfArt, String[] lstOf1stLetter) //stock list, categories in capital letters
    {
		// 26 categories labeled by first character
        // codes followed by positive integers indicating quantity of books
        if (lstOfArt.length == 0 || lstOf1stLetter.length == 0)
            return "";
        TreeMap<String, Integer> categories = new TreeMap<>();
        for (String letter : lstOf1stLetter)
        {
            System.out.println(letter);
            categories.put(letter, 0);
        }
        for (String code : lstOfArt)
        {
            System.out.println(code);
            String[] pieces = code.split(" ");
            String first = pieces[0].substring(0,1);
            int quantity = Integer.parseInt(pieces[1]);
            if (categories.containsKey(first))
            {
                int count = categories.get(first);
                count+=quantity;
                categories.put(first, count);
            }
        }
        String ans = "";
        for (String letter : lstOf1stLetter)
        {
            ans+= "(" + letter + " : " + categories.get(letter) + ") - ";
        }
        ans = ans.substring(0,ans.length()-3);
        return ans;
	}
}
