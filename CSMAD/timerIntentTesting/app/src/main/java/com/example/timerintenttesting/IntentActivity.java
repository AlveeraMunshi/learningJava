package com.example.timerintenttesting;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timerintenttesting.databinding.ActivityMainBinding;

public class IntentActivity extends AppCompatActivity {

    TextView scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        Bundle extra = getIntent().getExtras();
        String score = extra.getString("score");
        scoreDisplay = findViewById(R.id.scoreDisplay);

        scoreDisplay.setText("Score: " + score);

    }
}
