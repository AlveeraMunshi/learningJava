package com.example.practice2radiogroupimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioButton v25, v50, v75, v100, rock, paper, scissor;
    RadioGroup rgVolume, rgSelection;
    Button play;
    TextView selection, scoreDisplay;
    ImageView CPU;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v25 = findViewById(R.id.v25);
        v50 = findViewById(R.id.v50);
        v75 = findViewById(R.id.v75);
        v100 = findViewById(R.id.v100);
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissor = findViewById(R.id.scissor);
        rgVolume = findViewById(R.id.rgVolume);
        rgSelection = findViewById(R.id.rgSelection);
        play = findViewById(R.id.play);
        selection = findViewById(R.id.selection);
        scoreDisplay = findViewById(R.id.score);
        CPU = findViewById(R.id.CPU);
        score = 0;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rgSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        int rand = (int)(Math.random()*3+1);
                        String compMove = "";
                        switch (rand)
                        {
                            case 1:
                                CPU.setImageResource(R.drawable.rock);
                                compMove = "rock";
                                break;
                            case 2:
                                CPU.setImageResource(R.drawable.paper);
                                compMove = "paper";
                                break;
                            case 3:
                                CPU.setImageResource(R.drawable.scissor);
                                compMove = "scissor";
                                break;
                        }
                        switch (i)
                        {
                            case R.id.rock:
                                if (userWin("rock", compMove)) {
                                    selection.setText("You win!");
                                    score++;
                                }
                            case R.id.paper:
                                if (userWin("paper", compMove)) {
                                    selection.setText("You win!");
                                    score++;
                                }
                            case R.id.scissor:
                                if (userWin("scissor", compMove)) {
                                    selection.setText("You win!");
                                    score++;
                                }
                            default:
                                selection.setText("Make a selection");
                        }

                    }
                });
            }
        });
    }
    public boolean userWin(String selection1, String selection2)
    {
        if (selection1.equalsIgnoreCase("rock") && selection2.equalsIgnoreCase("scissor"))
        {
            return true;
        }
        else if (selection1.equalsIgnoreCase("paper") && selection2.equalsIgnoreCase("rock"))
        {
            return true;
        }
        else if (selection1.equalsIgnoreCase("scissor") && selection2.equalsIgnoreCase("paper"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}