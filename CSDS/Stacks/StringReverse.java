package Stacks;

import java.util.Scanner;
import java.util.Stack;

public class StringReverse {
    public StringReverse()
    {
        System.out.print("Enter a string: ");
        Scanner reader = new Scanner(System.in);
        String str = reader.nextLine();
        Stack<String> forwardstr = new Stack<>();
        for (int x = 0; x < str.length(); x++)
        {
            forwardstr.push(str.substring(x, x+1));
        }
        String backstr = "";
        while (!forwardstr.empty())
        {
            backstr+=String.valueOf(forwardstr.pop());
        }
        System.out.println(backstr);

    }
    public static void main(String[]args)
    {
        StringReverse app = new StringReverse();
    }
}