package com.example.buttondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button textButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textButton = findViewById(R.id.button);
    }
    public void myListeningMethod(View view)
    {
        textButton.setText("You clicked me!");
        textButton.setWidth(1000);
        textButton.setHeight(500);
        textButton.setBackgroundColor(Color.rgb(230, 230, 250));
        textButton.setTextColor(Color.rgb(148,0,211));

    }
}
