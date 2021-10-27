package com.example.widgetlesson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch slider;
    TextView sliderText, displayTextBox, displaySeek;
    EditText textBox;
    SeekBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slider = findViewById(R.id.switch1);
        bar = findViewById(R.id.seekBar);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            displaySeek.setText(bar.getProgress()+"%"); //shows progress as being dragged
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            //displaySeek.setText(bar.getProgress()+"%"); //shows progress where it started
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            //displaySeek.setText(bar.getProgress()+"%"); //shows progress were it stops
            }
        });
        sliderText = findViewById(R.id.textViewSwitch);
        displayTextBox = findViewById(R.id.textViewEdit);
        displaySeek = findViewById(R.id.textViewSeek);
        textBox = findViewById(R.id.editText);
        textBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                displayTextBox.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
