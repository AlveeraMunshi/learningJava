package com.example.customadapterlistviewproject;

public class Song {
    String name;
    String artist;
    int length;
    String genre;
    int ranking;
    int cover;

    public Song(String name, String artist, int length, String genre, int ranking, int cover)
    {
        this.name = name;
        this.artist = artist;
        this.length = length;
        this.genre = genre;
        this.ranking = ranking;
        this.cover = cover;
    }
    public String getName()
    {
        return name;
    }
    public String getArtist()
    {
        return artist;
    }
    public int getLength()
    {
        return length;
    }
    public String getGenre()
    {
        return genre;
    }
    public int getRanking()
    {
        return ranking;
    }
    public int getCover()
    {
        return cover;
    }
}
