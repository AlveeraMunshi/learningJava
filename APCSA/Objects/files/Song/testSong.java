package APCSA.Objects.files.Song;

public class testSong
{
  public static void main (String[]args)
  {
    Song song1 = new Song("Rick Montgomery", "He's Singing...", 5, 06);
    System.out.println(song1);
    System.out.println(song1.getArtist());
    song1.setArtist("Ricky Montgomery");
    System.out.println(song1.getTitle());
    song1.setTitle("Line Without a Hook");
    System.out.println(song1.getMinutes());
    song1.setMinutes(4);
    System.out.println(song1.getSeconds());
    song1.setSeconds(10);
    System.out.println(song1);
  }
}
