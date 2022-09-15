// See https://aka.ms/new-console-template for more information
using System.Collections.Generic;

namespace SampleNamespace
{
    class SampleClass
    {
        public void SampleMethod()
        {
        }
        static void Main(String[]args)
        {
            List<int> list = createList();
            foreach (var y in list)
                Console.WriteLine(y);
            Tuple<double, int> tup = average(list);
        }
        static List<int> createList()
        {
            List<int> list = new List<int>();
            int i = 0;
            while (list.Count < 500)
            {
                if (i % 2 == 0 && i % 3 == 0 && i % 10 != 0)
                {
                    list.Add(i);
                }
                i++;
            }
            return list;
        }
        static Tuple<double, int> average(List<int> nums)
        {
            int sum = 0;
            for (int x = 0; x < nums.Count; x++)
            {
                sum += nums[x];
            }
            double average = sum / nums.Count;
            Tuple<double, int> tup = Tuple.Create(average, nums.Count);
            return tup;
        }
        public static void enterFour()
        {
            string input1, input2, input3, input4;
            string longest = "";
            string secondlongest = "";
            try
            {
                Console.WriteLine("Enter Line #1: ");
                input1 = Console.ReadLine();
                Console.WriteLine("Enter Line #2: ");
                input2 = Console.ReadLine();
                Console.WriteLine("Enter Line #3: ");
                input3 = Console.ReadLine();
                Console.WriteLine("Enter Line #4: ");
                input4 = Console.ReadLine();
                if (input1.Length > input2.Length && input1.Length > input3.Length && input1.Length > input4.Length)
                {
                    longest = input1;
                }
                if (input2.Length > input1.Length && input2.Length > input3.Length && input2.Length > input4.Length)
                {
                    longest = input2;
                }
                if (input3.Length > input2.Length && input3.Length > input1.Length && input3.Length > input4.Length)
                {
                    longest = input3;
                }
                if (input4.Length > input2.Length && input4.Length > input3.Length && input4.Length > input1.Length)
                {
                    longest = input4;
                }
                if (longest != input1)
                {

                }
            }
            catch (Exception e)
            {

            }

        }


    }
}

