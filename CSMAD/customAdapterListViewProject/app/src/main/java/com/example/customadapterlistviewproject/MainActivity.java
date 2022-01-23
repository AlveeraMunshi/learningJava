package com.example.customadapterlistviewproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    TextView display;
    ArrayList<Song> playlist = new ArrayList<Song>();
    static MediaPlayer song;
    static SeekBar sb;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("list", playlist);
        outState.putString("display", String.valueOf(display.getText()));
        outState.putInt("progress", sb.getProgress());
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("list") && savedInstanceState.containsKey("display") && savedInstanceState.containsKey("progress"))
        playlist = savedInstanceState.getParcelableArrayList("list");
        display.setText(savedInstanceState.getString("display"));
        sb.setProgress(savedInstanceState.getInt("progress"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        display = findViewById(R.id.display);
        sb = findViewById(R.id.sb);
        sb.setEnabled(false);
        Song ntk = new Song("Need To Know", "Doja Cat", 3*60+31, "Dance Pop", R.drawable.planether, "Doja Cat is iconic, and this song just makes me wanna do TikTok transitions even though I suck at them :clown:", (int)R.raw.ntk);
        Song iwbys = new Song("I Wanna Be Your Slave", "Maneskin", 2*60+52, "Rock Pop", R.drawable.teatrodia, "Maneskin never fails to amaze, this song is embarassing to listen to but its very catchy so idc", (int)R.raw.iwbys);
        Song beg = new Song("Begging", "Maneskin", 3*60+32, "Rock Pop", R.drawable.chosen, "One of Maneskin's most popular songs, this is the kind of song someone would sing to you in a Broadway musical if Broadway utilized rock elements", (int)R.raw.beg);
        Song iwby = new Song("I Wanna Be Yours", "Arctic Monkeys", 3*60+4, "Alternative/Indie", R.drawable.iwby, "Arctic Monkeys is just alternative 1D, perfect song to backtrack a fantasy", (int)R.raw.iwby);
        Song lid = new Song("Lay It Down", "Steelix", 3*60+14, "Dance Pop", R.drawable.lid, "The only Steelix song I know, there was this girl who just kept using this song on TikTok and it was a vibe", (int)R.raw.lid);
        Song fof = new Song("505", "Arctic Monkeys", 4*60+14, "Alternative/Indie", R.drawable.fof, "This Arctic Monkeys Song is going to be in the documentary of my life if I ever have one I guarantee it", (int)R.raw.fof);
        Song shiv = new Song("Shivers", "Ed Sheeran", 3*60+28, "Pop", R.drawable.shivers, "Ed Sheeran is so funny LOL but I liked singing this song with my best friend on zoom I know all the words", (int)R.raw.shiv);
        Song dand = new Song("Dandelions", "Ruth B.", 3*60+54, "Pop", R.drawable.safehaven, "This song by Ruth B. is actually so cute I just remember my mom telling me not to blow dandelions in the house but I would do it anyway and I have pollen allergies so I was just being an idiot", R.raw.dand);
        Song ilys = new Song("I Love You So", "The Walters", 2*60+40, "Alternative/Indie", R.drawable.ilys, "This song from The Walters just makes me sad for no reason, you can love someone and they can still be bad for you", (int)R.raw.ilys);
        Song jen = new Song("867-5309 / Jenny", "Tommy Tutone", 3*60+46, "Classic Rock", R.drawable.jenny,"My best friend showed me this song by Tommy Tutone and now I'm obsessed it gives me Jessie's Girl vibes", (int)R.raw.jenny);
        Song noth = new Song("Nothing", "Bruno Major", 2*60+43, "Alternative/Indie", R.drawable.nothing, "Bruno Major is a wannabe Bruno Mars but this song is so adorable someone post me to this song now", (int)R.raw.noth);
        Song perf = new Song("Perfect", "One Direction", 3*60+50, "Pop", R.drawable.perf1d, "You can never wrong with One Direction, this song is literally the love song of the century it will be played at my wedding", (int)R.raw.perf);
        Song fool = new Song("Fool", "Frankie Cosmos", 2*60+4, "Indie Pop", R.drawable.fool, "This Frankie Cosmos song just describes that awkward feeling when you feel like you care about someone more than they do about you", (int)R.raw.fool);
        Song juli = new Song("Juliet", "Cavetown", 4*60+38, "Indie Pop", R.drawable.juliet, "This Cavetown song has several very cute lyrics the kind you put as captions on pictures or hang up in your room or write in cards", (int)R.raw.juli);
        Song tttm = new Song("Talking to The Moon", "Bruno Mars", 3*60+37, "Rock Pop", R.drawable.tttm, "This Bruno Mars song is my favorite song of all time I just really love the moon <3", (int)R.raw.tttm);

        if(savedInstanceState == null || !savedInstanceState.containsKey("list")) {
            playlist.add(ntk);
            playlist.add(iwbys);
            playlist.add(beg);
            playlist.add(iwby);
            playlist.add(lid);
            playlist.add(fof);
            playlist.add(shiv);
            playlist.add(dand);
            playlist.add(ilys);
            playlist.add(jen);
            playlist.add(noth);
            playlist.add(perf);
            playlist.add(fool);
            playlist.add(juli);
            playlist.add(tttm);
            display.setText("Tap play to play a song and see Alveera's Random Thoughts");
        }
        else
        {
            playlist = savedInstanceState.getParcelableArrayList("list");
            display.setText(savedInstanceState.getString("display"));
            sb.setProgress(savedInstanceState.getInt("progress"));
        }
        int layoutID;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            layoutID = R.layout.portrait_adapter_layout;
        }
        else
        {
            layoutID = R.layout.landscape_adapter_layout;
        }
        CustomAdapter adapter = new CustomAdapter(this, layoutID, playlist, song, MainActivity.this);
        lv.setAdapter(adapter);




    }
}