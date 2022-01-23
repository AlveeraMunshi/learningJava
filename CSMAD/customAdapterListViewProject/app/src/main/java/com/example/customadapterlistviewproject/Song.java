package com.example.customadapterlistviewproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    String name;
    String artist;
    int length;
    String genre;
    int cover;
    String notes;
    int mp3;

    public Song(String name, String artist, int length, String genre, int cover, String notes, int mp3)
    {
        this.name = name;
        this.artist = artist;
        this.length = length;
        this.genre = genre;
        this.cover = cover;
        this.notes = notes;
        this.mp3 = mp3;
    }
    private Song(Parcel in)
    {
        name = in.readString();
        artist = in.readString();
        length = in.readInt();
        genre = in.readString();
        cover = in.readInt();
        notes = in.readString();
        mp3 = in.readInt();
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
    public int getCover()
    {
        return cover;
    }
    public String getNotes() {
        return notes;
    }
    public int getMp3() {
        return mp3;
    }
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(artist);
        out.writeInt(length);
        out.writeString(genre);
        out.writeInt(cover);
        out.writeString(notes);
        out.writeInt(mp3);
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
