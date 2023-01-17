package Maps.CodeWars;

public class LargeSum {

    public static String firstTenDigitsOfSum(String[] numbers) {
      long sum = 0;
      for (String s : numbers) // 100 numbers
      {
        sum+=Long.parseLong(s.substring(0,12)); //parse 50 digits
      }
      return String.valueOf(sum).substring(0,10);
    }


}
