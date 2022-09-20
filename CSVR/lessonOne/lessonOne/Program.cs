// See https://aka.ms/new-console-template for more information
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Diagnostics.Metrics;
using System.IO;
using System.Numerics;
using System.Reflection.Emit;
using System.Reflection.Metadata;
using System.Runtime.Intrinsics.X86;

namespace SampleNamespace
{
    class SampleClass
    {
        public void SampleMethod()
        {
        }
        static void Main(String[]args)
        {
            //1a. Use a method to create a List and fill it with the first 500 numbers that are divisible by 2
            //and 3, but do not end in a 0.Return the List after it is built.
            List<int> list = createList();
            //1b. Use a different method that is sent the List, and find the average and total number of
            //values.Return the pair of values as a tuple.
            foreach (var y in list)
                Console.WriteLine(y);
            Tuple<double, int> tup = average(list);

            //1c. Write method that has a loop that will allow the user to enter 4 strings via the console.
            //Find the string that is the longest and the second longest.Return a List that contains
            //two different Tuples. Each should have the following format : (“String 1”,8 ) – The string
            //and then the length.
            string input1, input2, input3, input4, longest, secondlongest;
            List<String> array = new List<String>();
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
                array.Add(input1);
                array.Add(input2);
                array.Add(input3);
                array.Add(input4);
            }
            catch (Exception e)
            {
                e.GetBaseException();
            }
            for (int x = 0; x < 4; x++)
            {
                for (int y = x; y < 4; y++)
                {
                    if (array[x].Length > array[y].Length)
                    {
                        SwapItems(array, x, y);
                    }
                }
            }
            Console.WriteLine("Longest: " + array[3] + " Second Longest: " + array[2]);
            List<Tuple<string, int>> list2 = enterFourTuples(array[3], array[2]);

            //Create a dictionary and fill it your current teachers. The keys can be the subject. If we assume
            //you are only taking each subject once they should all be unique. After the dictionary is full
            //write a method that will take in the dictionary as a parameter and return a List of teachers that
            //teach math, science, or art.You can use the “get” method we used in class to complete this,
            //but I also want you to do it using a for loop.You can iterate over the dictionary and return the
            //entire entry and check the individual properties of each one.This will require a little bit
            //research.Please take the time to understand what is happening.It will help you later on if you
            //know how to use the.NET api documentation. Please don’t Google it and paste in a solution
            //from stackoverflow.

            Dictionary<string, string> subjects = new Dictionary<string, string>();
            subjects.Add("Math", "Ms. Lisa King");
            subjects.Add("Science", "Dr. Ithan Zimmer");
            subjects.Add("Psychology", "Ms. Cristina Janis");
            subjects.Add("English", "Ms. Anna Lehre");
            subjects.Add("History", "Ms. Kimberly Greenberg");
            subjects.Add("Data Structures", "Mr. Stephen Dentler");
            subjects.Add("Virtual Reality", "Mr. Steven Trainor");
            subjects.Add("Physical Education", "Ms. Alexandria Bravo");
            foreach (KeyValuePair<string, string> entry in subjects)
            {
                string key = entry.Key;
                if (key.Equals("Math") || key.Equals("Science") || key.Equals("Art"))
                {
                    Console.WriteLine(entry);
                }
            }

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
        static List<Tuple<string, int>> enterFourTuples(String longest, String secondlongest)
        {
            Tuple<string, int> first = Tuple.Create(longest, longest.Length);
            Tuple<string, int> second = Tuple.Create(secondlongest, secondlongest.Length);
            List<Tuple<string, int>> list = new List< Tuple<string, int>>();
            list.Add(first);
            list.Add(second);

            return list;
        }
        public static void SwapItems<String>(List<String> list, int idxX, int idxY)
        {
            if (idxX != idxY)
            {
                String tmp = list[idxX];
                list[idxX] = list[idxY];
                list[idxY] = tmp;
            }
        }


    }
}

