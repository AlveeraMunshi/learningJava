package com.example.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.audiodemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    private static ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp.isPlaying()) {
                    mp.stop();
                    try {
                        mp.prepare();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                    mp.start();
            }
        });

    }
    @Override
    protected void onStop()
    {
        super.onStop();
        mp.reset();
        mp.release();
        mp = null;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mp = MediaPlayer.create(this,R.raw.maps);
    }
}