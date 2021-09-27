package com.example.androidstudiointro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    Button buttonA;
    Button buttonB;
    Button buttonC;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonA = findViewById(R.id.button1);
        buttonB = findViewById(R.id.button2);
        buttonC = findViewById(R.id.button3);
        buttonB.setOnClickListener(new MyClassListener());
        buttonC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                buttonC.setBackgroundColor(Color.GREEN);
            }
        });

    }
    public void myListeningMethod(View view)
    {
        buttonA.setText("You clicked me!");
        buttonA.setBackgroundColor(Color.rgb(230, 230, 250));

    }
    public class MyClassListener implements View.OnClickListener
    {
        public void onClick (View view)
        {
            buttonB.setBackgroundColor(Color.GREEN);
        }
    }

}
