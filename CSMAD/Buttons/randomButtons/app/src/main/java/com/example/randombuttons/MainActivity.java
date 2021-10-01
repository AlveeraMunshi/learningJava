package com.example.randombuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.lang.*;

public class MainActivity extends AppCompatActivity {

    Button buttonA;
    Button buttonB;
    Button buttonC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonA = findViewById(R.id.button1);
        buttonB = findViewById(R.id.button2);
        buttonC = findViewById(R.id.button3);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonB.setTextSize((int)(Math.random()*100)+1);
        }
        });
        buttonC.setOnClickListener(new MyListenerClass());
    }
    public void MyListeningMethod(View view)
    {
        buttonA.setTextColor(Color.rgb((int)(Math.random()*256)+1, (int)(Math.random()*256)+1, (int)(Math.random()*256)+1 ) );
    }
    public class MyListenerClass implements View.OnClickListener
    {
        public void onClick(View view) {
            CharSequence temp = buttonA.getText();
            buttonA.setText(buttonB.getText());
            buttonB.setText(temp);
        }
    }
}
