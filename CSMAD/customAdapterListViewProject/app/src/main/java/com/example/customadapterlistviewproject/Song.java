package com.example.customadapterlistviewproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
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
    private Song(Parcel in)
    {
        name = in.readString();
        artist = in.readString();
        length = in.readInt();
        genre = in.readString();
        ranking = in.readInt();
        cover = in.readInt();
    }
    public int describeContents() {
        return 0;
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
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(artist);
        out.writeInt(length);
        out.writeString(genre);
        out.writeInt(ranking);
        out.writeInt(cover);
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}
