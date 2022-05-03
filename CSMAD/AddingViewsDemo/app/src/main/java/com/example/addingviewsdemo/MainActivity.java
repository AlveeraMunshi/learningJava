package com.example.addingviewsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout l;
    TextView t;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.layout);

        //Programmatic Text
        t = new TextView(this);
        t.setId(View.generateViewId());
        t.setText("I <3 CS");

        //Programmatic Image
        iv = new ImageView(this);
        iv.setId(View.generateViewId());
        iv.setImageResource(R.drawable.aloe);



        //Set up width/height
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        ConstraintLayout.LayoutParams params2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*80, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*80);
        t.setLayoutParams(params);
        iv.setLayoutParams(params2);

        l.addView(t);
        l.addView(iv);

        //Save constraints from current and add new
        ConstraintSet cs = new ConstraintSet();
        cs.clone(l);

        //bind new widgets to other things in layout
        //cs.connect(t.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP);
        //cs.connect(t.getId(), ConstraintSet.BOTTOM, l.getId(), ConstraintSet.BOTTOM);
        //cs.connect(t.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT);
        //cs.connect(t.getId(), ConstraintSet.RIGHT, l.getId(), ConstraintSet.RIGHT);
        //cs.setVerticalBias(t.getId(), .2f);
       // cs.setHorizontalBias(t.getId(), .5f);

        cs.connect(iv.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP);
        cs.connect(iv.getId(), ConstraintSet.BOTTOM, l.getId(), ConstraintSet.BOTTOM);
        cs.connect(iv.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT);
        cs.connect(iv.getId(), ConstraintSet.RIGHT, l.getId(), ConstraintSet.RIGHT);
        cs.setVerticalBias(iv.getId(), .7f);
        cs.setHorizontalBias(iv.getId(), .5f);

    }
}