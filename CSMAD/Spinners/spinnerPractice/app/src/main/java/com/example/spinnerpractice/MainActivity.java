package com.example.spinnerpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner prefixSelect, nameSelect;
    TextView prefix, display, names;
    EditText nameInput;
    Button add;
    ArrayList<String> prefixes, entered;
    String sp, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefixSelect = findViewById(R.id.prefixSelect);
        nameSelect = findViewById(R.id.nameSelect);
        prefix = findViewById(R.id.prefix);
        display = findViewById(R.id.display);
        names = findViewById(R.id.names);
        nameInput = findViewById(R.id.nameInput);
        add = findViewById(R.id.add);

        prefixes = new ArrayList<String>();
        prefixes.add("Mr.");
        prefixes.add("Mrs.");
        prefixes.add("Ms.");
        prefixes.add("Dr.");
        entered = new ArrayList<String>();

        ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, prefixes);
        prefixSelect.setAdapter(spinnerAdapter1);
        prefixSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp = prefixes.get(i);
                display.setText(sp + " " + name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, entered);
        nameSelect.setAdapter(spinnerAdapter2);

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name = String.valueOf(charSequence);
                display.setText(sp + " " + name);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entered.add(name);
            }
        });

    }
}