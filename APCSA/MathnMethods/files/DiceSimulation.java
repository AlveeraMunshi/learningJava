package APCSA.MathnMethods.files;

class DiceSimulation {
  public DiceSimulation()
  {
    int p1score = 0;
    int p2score = 0;
    while (p1score < 20 && p2score < 20)
    {
      int p1d1 = (int)(Math.random()*6+1);
      int p1d2 = (int)(Math.random()*6+1);
      int p1sum = p1d1 + p1d2;
      int p2d1 = (int)(Math.random()*6+1);
      int p2d2 = (int)(Math.random()*6+1);
      int p2sum = p2d1 + p2d2;

      System.out.println("Player 1 Dice 1: " + p1d1);
      System.out.println("Player 1 Dice 2: " + p1d2);
      System.out.println("Player 2 Dice 1: " + p2d1);
      System.out.println("Player 2 Dice 2: " + p1d2);

      if (p1d1 > p2d1 && p1d2 > p2d2)
      {
        p1score+=3;
      }
      if (p2d1 > p1d1 && p2d2 > p1d2)
      {
        p2score+=3;
      }
      if (p1d1 > p2sum)
      {
        p1score+=6;
      }
      if (p2d1 > p1sum)
      {
        p2score+=6;
      }
      if (p1d2 > p2d1 && p1d2 > p2d2)
      {
        p1score+=1;
      }
      if (p2d2 > p1d1 && p2d2 > p1d2)
      {
        p2score+=1;
      }
      System.out.println("Player 1 Score: " + p1score);
      System.out.println("Player 2 Score: " + p2score);
    }
  }
  public static void main(String[] args) {
    DiceSimulation temp = new DiceSimulation();
  }
}
