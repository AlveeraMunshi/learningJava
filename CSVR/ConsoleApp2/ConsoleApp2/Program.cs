namespace ListofNums
{
    public class Program
    {
        static void Main(string[]args)
        {
            List<int> list = new List<int>();
            int x = 1;
            while (list.Count < 500)
            {
                if (x % 6 == 0 && x % 10 != 0)
                {
                    list.Add(x);
                    Console.WriteLine(x);
                }
                x++;
            }
            foreach (var y in list)
                Console.WriteLine(y);

        }
    }
}
