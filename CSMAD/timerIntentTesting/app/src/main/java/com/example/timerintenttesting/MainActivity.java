package com.example.timerintenttesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.timerintenttesting.databinding.ActivityMainBinding;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    AtomicInteger score = new AtomicInteger(0);
    AtomicInteger timeRemaining = new AtomicInteger(30);
    private static ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //30 second timer, ticks every 1 second
        new CountDownTimer(30000, 1000) {

            //Every one second
            public void onTick(long millisUntilFinished) {
                score.getAndAdd(2);
                timeRemaining.getAndDecrement();
                binding.timer.setText("Time Remaining: " + timeRemaining);
            }

            //when timer ends
            public void onFinish() {
                //stop music and update timer and score
                Log.d("On Finish", "running");

                callSecondActivity();
            }
        }.start();
    }
    public void callSecondActivity()
    {
        Log.d("Game over", "call");
        Intent intentToLoad = new Intent(getApplicationContext(), IntentActivity.class); //context, where you're sending it with .class
        //Intent intentToLoad = new Intent(getApplicationContext(), NumberActivity.class); //also works
        // Use key value pars to save/retrieve values
        intentToLoad.putExtra("score", String.valueOf(score));

        //doesn't get result from other activities
        //startActivity(intentToLoad);

        //used to get result from another activity
        //need to override onActivityResult that is invoked automatically when second activity returns result
        startActivity(intentToLoad);
    }
}