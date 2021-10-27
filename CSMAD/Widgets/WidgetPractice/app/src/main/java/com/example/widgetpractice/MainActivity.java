package com.example.widgetpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView instructions, sliderControl, displayColor;
    EditText colorInput;
    Switch sliderSwitch;
    Button testButton;
    SeekBar slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instructions = findViewById(R.id.switchInstructions);
        sliderControl = findViewById(R.id.sliderControl);
        displayColor = findViewById(R.id.displayColor);
        colorInput = findViewById(R.id.colorInput);
        sliderSwitch = findViewById(R.id.sliderSwitch);
        slider = findViewById(R.id.slider);
        testButton = findViewById(R.id.testButton);
        colorInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String color = String.valueOf(charSequence).toUpperCase();
                switch(color)
                {
                    case "RED":
                        displayColor.setTextColor(Color.RED);
                        break;
                    case "GREEN":
                        displayColor.setTextColor(Color.GREEN);
                        break;
                    case "BLUE":
                        displayColor.setTextColor(Color.BLUE);
                        break;
                    default:
                        displayColor.setTextColor(Color.BLACK);
                        break;

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (sliderSwitch.isChecked())
                    testButton.setWidth(slider.getProgress()*10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
