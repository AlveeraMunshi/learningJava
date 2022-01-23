package com.example.customadapterlistviewproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Song>
{
    List<Song> list;
    Context context;
    int xmlResource;
    MediaPlayer song;
    static int songID;
    int pauseloc;
    MainActivity m;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Song> objects, MediaPlayer song, MainActivity m)
    {
        super(context, resource, objects);
        xmlResource = resource;
        list = objects;
        this.context = context;
        this.song = song;
        this.songID = 0;
        this.pauseloc = 0;
        this.m = m;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterLayout = layoutInflater.inflate(xmlResource, null);

        TextView name = adapterLayout.findViewById(R.id.name);
        TextView artist = adapterLayout.findViewById(R.id.artist);
        TextView genre = adapterLayout.findViewById(R.id.genre);
        name.setText(list.get(position).getName());
        artist.setText(list.get(position).getArtist());
        genre.setText("Genre: " + list.get(position).getGenre());

        Spinner spin;
        TextView length, ranking;
        int sec = (int)(list.get(position).getLength());
        String len = String.valueOf(sec/60) + ":";
        if (sec%60 < 10)
            len += "0" + String.valueOf(sec%60);
        else
            len += String.valueOf(sec%60);
        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            spin  = adapterLayout.findViewById(R.id.spinner);
            ArrayList<String> attributes = new ArrayList<String>();
            attributes.add("Length: " + len);
            attributes.add("Position: " + String.valueOf(position+1));
            ArrayAdapter spinnerAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, R.id.textView, attributes);
            spin.setAdapter(spinnerAdapter);
        }
        else
        {
            length = adapterLayout.findViewById(R.id.length);
            ranking = adapterLayout.findViewById(R.id.textView5);
            length.setText("Length: " + len);
            ranking.setText("Position: " + String.valueOf(position+1));
        }

        ImageView cover = adapterLayout.findViewById(R.id.imageView);
        cover.setImageResource(list.get(position).getCover());

        Button remove = adapterLayout.findViewById(R.id.remove);
        Button play = adapterLayout.findViewById(R.id.play);
        Button pause = adapterLayout.findViewById(R.id.pause);
        remove.setText("Remove");
        play.setText("Play");
        pause.setText("Pause");

        TextView display = (TextView) ((Activity)context).findViewById(R.id.display);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song != null && songID == list.get(position).getMp3()) {
                    song.stop();
                }
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setText(list.get(position).getNotes());
                if (song != null && song.isPlaying())
                    song.stop();
                song = MediaPlayer.create(getContext(),list.get(position).getMp3());
                if (songID == list.get(position).getMp3())
                    song.seekTo(pauseloc);
                songID = list.get(position).getMp3();
                song.start();
                SeekBar sb = (SeekBar) ((Activity)context).findViewById(R.id.sb);
                if(song != null)
                    sb.setMax(song.getDuration() / 1000);
                //Thanks for teaching me handler Yusha
                Handler handler = new Handler();
                m.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(song != null){
                            sb.setProgress(song.getCurrentPosition()/1000);
                        }
                        handler.postDelayed(this, 1000);
                    }
                });
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song != null)
                {
                    pauseloc = song.getCurrentPosition();
                    song.pause();
                }
            }
        });



        return adapterLayout;
    }
}
