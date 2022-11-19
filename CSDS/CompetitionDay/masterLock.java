package CompetitionDay;

import java.io.*;
import java.util.ArrayList;

public class masterLock 
{
    public masterLock()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/CompetitionDay/comboFile.txt");
        ArrayList<Integer> nums = new ArrayList<Integer>();
        try
        {
            String text;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null)
            {
                for (int i = 0; i < text.length(); i+=3)
                {
                    nums.add(Integer.parseInt(text.substring(i+1, i+2)));
                    nums.add(Integer.parseInt(text.substring(i+2, i+3)));
                }

            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        for (int i = 0; i < nums.size(); i++)
        {
            
        }
    }
    public static void main(String[]args)
    {
        masterLock app = new masterLock();
    }
}
