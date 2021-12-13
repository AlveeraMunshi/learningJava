package com.example.customadapterlistviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        ArrayList<Song> playlist = new ArrayList<Song>();
        Song ntk = new Song("Need To Know", "Doja Cat", 3*60+31, "Dance Pop", 1, R.drawable.planether);
        Song iwbys = new Song("I Wanna Be Your Slave", "Maneskin", 2*60+52, "Rock Pop", 2, R.drawable.teatrodia);

        playlist.add(ntk);
        playlist.add(iwbys);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.adapter_layout, playlist);
        lv.setAdapter(adapter);
    }
    public class CustomAdapter extends ArrayAdapter<Song>
    {
        List<Song> list;
        Context context;
        int xmlResource;
        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Song> objects)
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
            length.setText(list.get(position).getLength());
            genre.setText(list.get(position).getGenre());
            ranking.setText(list.get(position).getRanking());

            cover.setImageResource(list.get(position).getCover());

            return adapterLayout;
        }
    }
}