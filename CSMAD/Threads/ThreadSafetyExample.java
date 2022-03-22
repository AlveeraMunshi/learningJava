package CSMAD.Threads;

import java.util.concurrent.atomic.AtomicInteger;
public class ThreadSafetyExample
{
  static int i = 0;
  static AtomicInteger ai;
  static int si = 0;
  public static void main (String[] args)
  {
    ai = new AtomicInteger(0);
    for (int j = 0; j < 200; j++)
      new MyThread().start();
    try {
      Thread.sleep(5000);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println("Primitve Value: " + i);
    System.out.println("Atomic Value: " + ai);
    System.out.println("Primitive Value from a synchronized method: " + si);
  }
  public static synchronized void addMethod (int k)
  {
    si+=k;
  }
  public static class MyThread extends Thread{
    public void run()
    {
      try {
        Thread.sleep(2);
      }
      catch (Exception e)
      {

      }
      i++;
      ai.getAndAdd(1);
      addMethod(1);
    }
  }
}
