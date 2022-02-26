package com.example.gpsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCustomAdapter extends RecyclerView.Adapter<RecyclerCustomAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> addresses = new ArrayList<>();
    private Context mContext;

    public RecyclerCustomAdapter(Context context, ArrayList<String> addresses ) {
        this.addresses = addresses;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_template, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.t.setText(addresses.get(position));
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView t;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.t);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}

