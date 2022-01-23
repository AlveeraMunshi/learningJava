package CSMAD.Threads;

public class ThreadPractice2
{
  public static void main(String[]args)
  {
    Thread t1 = new Thread("Thread: "){
      public void run(){
        
        for (int i = 1; i<=100; i++)
        {
          System.out.println(getName()+" "+i);
        }
      };
      Thread t2 = new Thread("Thread 2: ")
      {
        for (int i = 1; i<=10; i++)
        {
          System.out.println(getName()+" "+i);
        }
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
      t2.start();
  }
}
