package com.example.lifecycledemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView port1, land1;
    Button button;
    int count = 0;
    @Override

    protected void onStart() {
        super.onStart();
        Log.d("LIFECYCLETAG", "onStart()");
    }
    protected void onRestart() {
        super.onRestart();
        Log.d("LIFECYCLETAG", "onRestart()");
    }
    protected void onResume() {
        super.onResume();
        Log.d("LIFECYCLETAG", "onResume()");
    }
    protected void onPause() {
        super.onPause();
        Log.d("LIFECYCLETAG", "onPause()");
    }
    protected void onStop() {
        super.onStop();
        Log.d("LIFECYCLETAG", "onStop()");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LIFECYCLETAG", "onDestroy()");
    }
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("LIFECYCLETAG", "onSaveInstanceState()");
        outState.putInt("count", count);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("LIFECYCLETAG", "onRestoreInstanceState()");
        //count = savedInstanceState.getInt("count");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        port1 = findViewById(R.id.textView);
        land1 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Log.d("LIFECYCLETAG", "onCreate() - Portrait");
            if (savedInstanceState != null)
            {
                count = savedInstanceState.getInt("count");
            }
            port1.setText("count " + count);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    port1.setText("count " + count);
                }
            });
        }
        else
        {
            Log.d("LIFECYCLETAG", "onCreate() - Landscape");
            if (savedInstanceState != null)
            {
                count = savedInstanceState.getInt("count");
            }
            land1.setText("count: " + count);
        }
    }
}