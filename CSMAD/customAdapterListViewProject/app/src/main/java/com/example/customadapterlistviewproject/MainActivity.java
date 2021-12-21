package com.example.customadapterlistviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Song> playlist = new ArrayList<Song>();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", playlist);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        Song ntk = new Song("Need To Know", "Doja Cat", 3*60+31, "Dance Pop", 1, R.drawable.planether);
        Song iwbys = new Song("I Wanna Be Your Slave", "Maneskin", 2*60+52, "Rock Pop", 2, R.drawable.teatrodia);
        Song beg = new Song("Begging", "Maneskin", 3*60+32, "Rock Pop", 3, R.drawable.planether);
        Song iwby = new Song("I Wanna Be Yours", "Arctic Monkeys", 3*60+4, "Boy Band", 4, R.drawable.planether);
        Song lid = new Song("Lay It Down", "Steelix", 3*60+14, "Dance Pop", 5, R.drawable.planether);
        Song fof = new Song("505", "Arctic Monkeys", 4*60+14, "Boy Band", 6, R.drawable.planether);
        Song shiv = new Song("Shivers", "Ed Sheeran", 3*60+28, "British Pop", 7, R.drawable.planether);
        Song dand = new Song("Dandelions", "Ruth B.", 3*60+54, "Pop", 8, R.drawable.planether);
        Song ilys = new Song("I Love You So", "The Walters", 2*60+40, "Pop", 9, R.drawable.planether);
        Song jen = new Song("867-5309 / Jenny", "Tommy Tutone", 3*60+46, "Rock", 10, R.drawable.planether);
        Song noth = new Song("Nothing", "Bruno Major", 2*60+43, "Love Pop", 11, R.drawable.planether);
        Song perf = new Song("Perfect", "One Direction", 3*60+50, "Boy Band", 12, R.drawable.planether);
        Song fool = new Song("Fool", "Frankie Cosmos", 2*60+4, "Love Pop", 13, R.drawable.planether);
        Song juli = new Song("Juliet", "Cavetown", 4*60+38, "Dance Pop", 14, R.drawable.planether);
        Song tttm = new Song("Talking to The Moon", "Bruno Mars", 3*60+37, "Dance Pop", 15, R.drawable.planether);

        if(savedInstanceState == null || !savedInstanceState.containsKey("key")) {
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
        }
        else
        {
            playlist = savedInstanceState.getParcelableArrayList("key");
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            CustomAdapterP adapter = new CustomAdapterP(this, R.layout.portrait_adapter_layout, playlist);
            lv.setAdapter(adapter);
        }
        else
        {
            CustomAdapterL adapter = new CustomAdapterL(this, R.layout.portrait_adapter_layout, playlist);
            lv.setAdapter(adapter);
        }
    }
    public class CustomAdapterP extends ArrayAdapter<Song>
    {
        List<Song> list;
        Context context;
        int xmlResource;
        public CustomAdapterP(@NonNull Context context, int resource, @NonNull List<Song> objects)
        {
            super(context, resource, objects);
            xmlResource = resource;
            list = objects;
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterLayout = layoutInflater.inflate(xmlResource, null);

            TextView name = adapterLayout.findViewById(R.id.textView1);
            TextView artist = adapterLayout.findViewById(R.id.textView2);
            Spinner spin  = adapterLayout.findViewById(R.id.spinner);
            ImageView cover = adapterLayout.findViewById(R.id.imageView);
            Button remove = adapterLayout.findViewById(R.id.button);
            remove.setText("Remove");

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });

            name.setText(list.get(position).getName());
            artist.setText(list.get(position).getArtist());
            ArrayList<String> attributes = new ArrayList<String>();
            int sec = (int)(list.get(position).getLength());
            attributes.add(String.valueOf(sec/60) + String.valueOf(sec%60));
            attributes.add(list.get(position).getGenre());
            attributes.add(String.valueOf(list.get(position).getRanking()));
            ArrayAdapter spinnerAdapter = new ArrayAdapter(MainActivity.this, R.layout.spinner_item, attributes);
            spin.setAdapter(spinnerAdapter);
            cover.setImageResource(list.get(position).getCover());

            return adapterLayout;
        }
    }
    public class CustomAdapterL extends ArrayAdapter<Song>
    {
        List<Song> list;
        Context context;
        int xmlResource;
        public CustomAdapterL(@NonNull Context context, int resource, @NonNull List<Song> objects)
        {
            super(context, resource, objects);
            xmlResource = resource;
            list = objects;
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterLayout = layoutInflater.inflate(xmlResource, null);

            TextView name = adapterLayout.findViewById(R.id.textView1);
            TextView artist = adapterLayout.findViewById(R.id.textView2);
            TextView length = adapterLayout.findViewById(R.id.textView3);
            TextView genre = adapterLayout.findViewById(R.id.textView4);
            TextView ranking = adapterLayout.findViewById(R.id.textView5);
            ImageView cover = adapterLayout.findViewById(R.id.imageView);
            Button remove = adapterLayout.findViewById(R.id.button);
            remove.setText("Remove");

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });

            name.setText(list.get(position).getName());
            artist.setText(list.get(position).getArtist());
            length.setText(String.valueOf(list.get(position).getLength()));
            genre.setText(list.get(position).getGenre());
            ranking.setText(String.valueOf(list.get(position).getRanking()));

            cover.setImageResource(list.get(position).getCover());

            return adapterLayout;
        }

    }
}