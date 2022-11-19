package CompetitionDay;

import java.io.*;
import java.awt.*;

public class colorMix {
    public colorMix()
    {
        File file = new File("/Users/alveeramunshi/Documents/GitHub/learningJava/CSDS/CompetitionDay/input1.txt");
        try {
            String text;
            int linenum = 0;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((text = reader.readLine()) != null)
            {
                linenum++;
                int real = 0;
                int l = 0;
                for (int i = 0; i < text.length(); i+=3)
                {
                    String check = text.substring(i, i+1) + "" + text.substring(i+1, i+2) + "" + text.substring(i+2, i+3);
                    if (Integer.parseInt(check) <= 225)
                    {
                        real++;
                        System.out.println(check);
                    }
                    //l+=text.charAt(i);
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    public static void main(String[]args)
    {
        colorMix app = new colorMix();
    }
}
