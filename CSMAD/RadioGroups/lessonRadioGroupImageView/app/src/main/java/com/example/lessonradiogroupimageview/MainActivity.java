package com.example.lessonradiogroupimageview;

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
    ImageView iv1, iv2;
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
        iv2 = findViewById(R.id.iv2);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb1) {
                    t1.setText("A Selected");
                }
                if (i == R.id.rb2) {
                    t1.setText("B Selected");
                }
                if (i == R.id.rb3) {
                    t1.setText("C Selected");
                    Toast msg = Toast.makeText(MainActivity.this, "C is the best", Toast.LENGTH_SHORT);
                    msg.show();
                }

            }
        });

    }
}
