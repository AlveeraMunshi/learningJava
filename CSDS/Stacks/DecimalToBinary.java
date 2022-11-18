package Stacks;

import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinary {
    public DecimalToBinary()
    {
        System.out.print("Enter a num: ");
        Scanner reader = new Scanner(System.in);
        int num = reader.nextInt();
        reader.nextLine();
        Stack<Integer> backbinary = new Stack<>();
        while (num != 0)
        {
            backbinary.push(num%2);
            num = num/2;  
        }
        String forwardbinary = "";
        while (!backbinary.empty())
        {
            forwardbinary+=String.valueOf(backbinary.pop());
        }
        System.out.println(forwardbinary);

    }
    public static void main(String[]args)
    {
        DecimalToBinary app = new DecimalToBinary();
    }
}
