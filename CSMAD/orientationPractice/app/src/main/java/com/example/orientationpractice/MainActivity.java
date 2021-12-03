package com.example.orientationpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView t;
    Spinner s;
    Button b;
    ArrayList<String> elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = findViewById(R.id.textView);
        s = findViewById(R.id.spinner);
        b = findViewById(R.id.button);
        elements = new ArrayList<String>();
        elements.add("Cheese");
        elements.add("Lettuce");
        elements.add("Avocado");
        elements.add("Chicken");
        elements.add("Peppers");
        elements.add("Tomatoes");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, elements);
            s.setAdapter(spinnerAdapter);
            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    t.setText(elements.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    t.setText("all elements removed");
                }
            });
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spinnerAdapter.clear();
                    spinnerAdapter.notifyDataSetChanged();
                }
            });

        }

    }
}