package com.example.smackthesquid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.smackthesquid.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private static ActivityMainBinding binding;
    AtomicInteger timeRemaining = new AtomicInteger(60);
    AtomicInteger score = new AtomicInteger(0);
    ArrayList<View> objects = new ArrayList<View>();
    static float hb = 0.03f;
    static float vb = 0.740f;
    //static ImageView iv;
    static int rand = 0;
    static boolean click = false;
    ConstraintLayout cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        objects.add(binding.s1);
        objects.add(binding.s2);
        objects.add(binding.s3);
        objects.add(binding.s4);
        objects.add(binding.s5);
        objects.add(binding.s6);
        objects.add(binding.o1);
        objects.add(binding.o2);
        objects.add(binding.o3);
        //cl = findViewById(R.id.tallyL);
        cl = findViewById(R.id.cl);

        final ScaleAnimation enter = new ScaleAnimation(0f, 1.0f, 0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        enter.setDuration(1000);
        final ScaleAnimation scaleExit = new ScaleAnimation(1.0f, 0f, 1.0f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleExit.setDuration(1000);
        final ScaleAnimation scaleExpand = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleExpand.setDuration(1000);
        final ScaleAnimation scaleShrink = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleShrink.setDuration(1000);
        AnimationSet as = new AnimationSet(true);
        as.addAnimation(scaleExpand);
        as.addAnimation(scaleShrink);

        Timer timer1 = new Timer();
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                timeRemaining.getAndDecrement();
                try
                {
                    Log.d("Task1", "Running");
                    binding.display.setText("Time: " + timeRemaining + " s \nScore: " + score + " s");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                if (timeRemaining.get() <= 0)
                    timer1.cancel();
            }
        };
        timer1.scheduleAtFixedRate(timerTask1, 0, 1000);

        Timer timer2 = new Timer();
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (click) {
                            click = false;
                        }
                        else {
                            ((ImageView)objects.get(rand)).startAnimation(as);
                            ((ImageView)objects.get(rand)).startAnimation(scaleExit);
                            ((ImageView)objects.get(rand)).setVisibility(View.INVISIBLE);
                            ((ImageView)objects.get(rand)).setOnClickListener(null);
                        }
                        rand = (int)(Math.random()*9);
                        ((ImageView)objects.get(rand)).setVisibility(View.VISIBLE);
                        ((ImageView)objects.get(rand)).setX((float) (Math.random()*400+150));
                        ((ImageView)objects.get(rand)).setY((float) (Math.random()*900+150));
                        if (rand == 6 || rand == 7 || rand == 8)
                            ((ImageView)objects.get(rand)).setImageResource(R.drawable.oyster);
                        else
                            ((ImageView)objects.get(rand)).setImageResource(R.drawable.squiddy);
                        ((ImageView)objects.get(rand)).startAnimation(enter);
                        ((ImageView)objects.get(rand)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                click = true;
                                //ConstraintLayout constl = new ConstraintLayout(MainActivity.this);
                                ImageView iv = new ImageView(MainActivity.this);
                                Log.d("im here", "im here");
                                iv.setId(View.generateViewId());
                                iv.setImageResource(R.drawable.squiddy);


                                //iv.setVisibility(View.VISIBLE);
                                //ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*40, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*40);
                                //ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(100, 100);
                                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*70, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*70);

                                iv.setLayoutParams(params);

                                ConstraintSet cs = new ConstraintSet();
                                cs.clone(cl);
                                cl.addView(iv);
                                //Log.d("ID", ""+cl.getId());

                               // Log.d("ID2", ""+iv.getId()+" "+iv.getVisibility());
                                cs.connect(iv.getId(), ConstraintSet.TOP, cl.getId(), ConstraintSet.TOP);
                                cs.connect(iv.getId(), ConstraintSet.BOTTOM, cl.getId(), ConstraintSet.BOTTOM);
                                cs.connect(iv.getId(), ConstraintSet.LEFT, cl.getId(), ConstraintSet.LEFT);
                                cs.connect(iv.getId(), ConstraintSet.RIGHT, cl.getId(), ConstraintSet.RIGHT);

                                //Thread.sleep(1000);
                                if(hb == 0.856f)
                                {
                                    vb+=0.1f;
                                    hb = 0.03f;
                                }
                                else
                                {
                                    hb+=0.07f;
                                }
                               // cs.setVerticalBias(iv.getId(), vb);
                                //cs.setHorizontalBias(iv.getId(), hb);
                                cs.applyTo(cl);

                                score.getAndAdd(1);
                                if (((ImageView)objects.get(rand)) == binding.o1 || ((ImageView)objects.get(rand)) == binding.o2 || ((ImageView)objects.get(rand)) == binding.o3) {
                                    timeRemaining.getAndAdd(10);
                                    ((ImageView)objects.get(rand)).setImageResource(R.drawable.scaredsquiddy);
                                }
                                else {
                                    ((ImageView)objects.get(rand)).setImageResource(R.drawable.inksplat);
                                }
                                ((ImageView)objects.get(rand)).startAnimation(as);
                                ((ImageView)objects.get(rand)).startAnimation(scaleExit);
                                ((ImageView)objects.get(rand)).setVisibility(View.INVISIBLE);
                                ((ImageView)objects.get(rand)).setOnClickListener(null);
                                Log.d("Task2", "Running");
                            }
                        });
                    }
                });

                if (timeRemaining.get() <= 0)
                    timer2.cancel();
            }
        };
        timer2.scheduleAtFixedRate(timerTask2, 0, 4000);
    }
}
