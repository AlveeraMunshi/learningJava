package CSMAD.Threads;

public class ThreadPractice
{
  public static void main(String[]args)
  {
    BufferedReader br = null;




    Thread t1 = new Thread("Thread: "){
      public void run(){
        int sum = 0;
        int count = 0;
        File file = new File ("/Users/alveeramunshi/Documents/GitHub/learningJava/CSMAD/Threads/integers.txt");
        Scanner s = new Scanner(System.in);
        try
        {
          s = new Scanner(file);
        }
        catch(Exception e)
        {
          System.out.println("error");
        }
        while (s.hasNextInt())
        {
          sum+=s.hasNextInt;
          count++;
        }
        double avg = (double)sum/count;
        System.out.println("Sum: " + sum);
        System.out.println("Avg: " + avg);


      };

      t1.start();

      try
      {
        t1.join();
      }
      catch(Exception e)
      {
        System.out.println("Caught");
      }
    }
  }
