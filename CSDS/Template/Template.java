package Template;
import java.io.*;
public class Template
{
    public Template()
    {
        File file = new File("text.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
            {
                String[] pieces = text.split(" ");
                int num1 = Integer.parseInt(pieces[0]);
                int num2 = Integer.parseInt(pieces[1]);
                int sum = num1 + num2;
                System.out.println(num1 + " + " + num2 + " = " + sum);
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
    public static void main(String[]args)
    {
        Template app = new Template();
    }
}