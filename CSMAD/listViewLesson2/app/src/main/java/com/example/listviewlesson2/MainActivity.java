package com.example.listviewlesson2;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = findViewById(R.id.list_id);
        ArrayList<Reindeer> names = new ArrayList<Reindeer>();
        Reindeer comet = new Reindeer("Comet", R.drawable.reindeer1);
        Reindeer rudy = new Reindeer("Rudolph", R.drawable.reindeer2);
        names.add(comet);
        names.add(rudy);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.adapter_layout, names);
        lv.setAdapter(adapter);

    }
    public class CustomAdapter extends ArrayAdapter<Reindeer>
    {
        List<Reindeer> list;
        Context context;
        int xmlResource;
        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Reindeer> objects)
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

            TextView name = adapterLayout.findViewById(R.id.textView);
            ImageView image = adapterLayout.findViewById(R.id.imageView);
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
            image.setImageResource(list.get(position).getImage());

            return adapterLayout;
        }
    }
}