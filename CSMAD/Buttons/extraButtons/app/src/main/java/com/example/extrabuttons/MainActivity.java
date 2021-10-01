package com.example.extrabuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonA;
    Button buttonB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonA = findViewById(R.id.button1);
        buttonB = findViewById(R.id.button2);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int col;
                int colgen = (int)(Math.random()*5)+1;
                switch (colgen)
                {
                    case 1:
                        col = Color.RED;
                        break;
                    case 2:
                        col = Color.YELLOW;
                        break;
                    case 3:
                        col = Color.GREEN;
                        break;
                    case 4:
                        col = Color.BLUE;
                        break;
                    default:
                        col = Color.BLACK;
                        break;
                }
                buttonA.setTextColor(col);
            }
        });
        buttonB.setOnClickListener(new MyClassListener());
    }
    public class MyClassListener implements View.OnClickListener
    {
        public void onClick(View view)
        {
            CharSequence temp = buttonA.getText();
            buttonA.setText(buttonB.getText());
            buttonB.setText(temp);
        }
    }
}
