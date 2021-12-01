package com.example.practiceradiogroupimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton rb1, rb2, rb3;
    RadioGroup rg;
    TextView t1;
    ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rg = findViewById(R.id.rg);
        t1 = findViewById(R.id.t1);
        iv1 = findViewById(R.id.iv1);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb1) {
                    Toast msg = Toast.makeText(MainActivity.this, "You have selected Harry Potter", Toast.LENGTH_SHORT);
                    msg.show();
                    iv1.setImageResource(R.drawable.harrypotter);
                }
                if (i == R.id.rb2) {
                    Toast msg = Toast.makeText(MainActivity.this, "You have selected Hermione Granger", Toast.LENGTH_SHORT);
                    msg.show();
                    iv1.setImageResource(R.drawable.hermionegranger);
                }
                if (i == R.id.rb3) {
                    Toast msg = Toast.makeText(MainActivity.this, "You have selected Ron Weasley", Toast.LENGTH_SHORT);
                    msg.show();
                    iv1.setImageResource(R.drawable.ronweasley);
                }

            }
        });

    }
}
