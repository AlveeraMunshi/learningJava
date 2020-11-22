package APCSA.Objects.files;

import java.lang.Math;

public class Song
{
  String artist;
  String title;
  int minutes;
  int seconds;

  public Song(String artist, String title, int minutes, int seconds)
  {
    this.artist = artist;
    this.title = title;
    this.minutes = minutes;
    this.seconds = seconds;
  }
  public String getArtist()
  {
    return artist;
  }
  public String getTitle()
  {
    return title;
  }
  public int getMinutes()
  {
    return minutes;
  }
  public int getSeconds()
  {
    return seconds;
  }
  public void setArtist(String artist)
  {
    this.artist = artist;
  }
  public void setTitle(String title)
  {
    this.title = title;
  }
  public void setMinutes(int minutes)
  {
    this.minutes = minutes;
  }
  public void setSeconds(int seconds)
  {
    this.seconds = seconds;
  }
  public String toString()
  {
    return "[Artist= " + artist + ", Title= " + title + ", Time= " + minutes + ":" + seconds + "]";
  }
  public static void main (String[]args)
  {
  }
}
