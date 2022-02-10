package com.example.openweatherapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Hour>
{
    List<Hour> list;
    Context context;
    int xmlResource;
    MainActivity m;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Hour> objects, MainActivity m)
    {
        super(context, resource, objects);
        xmlResource = resource;
        list = objects;
        this.context = context;
        this.m = m;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterLayout = layoutInflater.inflate(xmlResource, null);

        //FindViewByID
        TextView hour = adapterLayout.findViewById(R.id.hour);
        TextView temp = adapterLayout.findViewById(R.id.temp);
        TextView desc = adapterLayout.findViewById(R.id.desc);

        //Format unixDate from API call into a readable date
        Date date = new java.util.Date(list.get(position).getHour()*1000);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm a 'EST'");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("EST"));
        String formattedDate = sdf.format(date);

        //Input attributes from the Hour objects into the widgets in the listview adapter layout
        hour.setText("Time: " + formattedDate);
        temp.setText("Temperature: " + list.get(position).getTemp() + " F");
        desc.setText("Description: " + list.get(position).getDesc());
        ImageView pic = adapterLayout.findViewById(R.id.pic);
        pic.setImageResource(list.get(position).getImgID());



        return adapterLayout;
    }
}