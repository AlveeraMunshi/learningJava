package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView display;
    Button b1;
    Button b2;
    Button b3;
    Button bplus;
    Button b4;
    Button b5;
    Button b6;
    Button bminus;
    Button b7;
    Button b8;
    Button b9;
    Button btimes;
    Button bc;
    Button b0;
    Button bdecimal;
    Button bequals;
    Button bdivide;
    Button bleftp;
    Button brightp;
    String input;
    double answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        bplus = findViewById(R.id.button4);
        b4 = findViewById(R.id.button5);
        b5 = findViewById(R.id.button6);
        b6 = findViewById(R.id.button7);
        bminus = findViewById(R.id.button8);
        b7 = findViewById(R.id.button9);
        b8 = findViewById(R.id.button10);
        b9 = findViewById(R.id.button11);
        btimes = findViewById(R.id.button12);
        bc = findViewById(R.id.button13);
        b0 = findViewById(R.id.button14);
        bdecimal = findViewById(R.id.button17);
        bequals = findViewById(R.id.button15);
        bdivide = findViewById(R.id.button16);
        input = "";
        answer = 0;
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        bdecimal.setOnClickListener(this);
        //https://www.color-hex.com/color-palette/4628
    }
    @Override
    public void onClick (View view)
    {
        Button focus = findViewById(view.getId());

        if (input.isEmpty())
        {
            answer = Integer.parseInt((String)focus.getText());
            if (!((String) focus.getText()).equalsIgnoreCase("0"))
                input+=focus.getText();
        }
        else
        {
            input+=focus.getText();
        }
        display.setText(input);
    }
    public void add(View view)
    {
        if (!input.isEmpty()) {
            input += "+";
            display.setText(input);
        }
    }
    public void minus(View view)
    {
        if (!input.isEmpty()) {
            input += "-";
            display.setText(input);
        }
    }
    public void times(View view)
    {
        if (!input.isEmpty()) {
            input += "*";
            display.setText(input);
        }
    }
    public void divide(View view)
    {
        if (!input.isEmpty()) {
            input += "/";
            display.setText(input);
        }
    }
    public void leftParenthesis(View view)
    {
        input += "(";
        display.setText(input);
    }
    public void rightParenthesis(View view)
    {
        input += ")";
        display.setText(input);
    }
    public void equals(View view)
    {
        StringTokenizer st = new StringTokenizer(input, "+-/*", true);
        ArrayList<String> equationParts = new ArrayList<String>();
        while(st.hasMoreElements())
        {
            equationParts.add((String)st.nextElement());
        }
        try
        {
            for (int x = 0; x < equationParts.size(); x++) {
                String element = equationParts.get(x);
                if (element.equals("*")) {
                    double product = Double.parseDouble(equationParts.get(x - 1)) * Double.parseDouble(equationParts.get(x + 1));
                    equationParts.set(x, String.valueOf(product));
                    equationParts.remove(x + 1);
                    equationParts.remove(x - 1);
                    x--;
                } else if (element.equals("/")) {
                    double quotient = Double.parseDouble(equationParts.get(x - 1)) / Double.parseDouble(equationParts.get(x + 1));
                    equationParts.set(x, String.valueOf(quotient));
                    equationParts.remove(x + 1);
                    equationParts.remove(x - 1);
                    x--;
                } else {

                }
            }
            for (int x = 0; x < equationParts.size(); x++) {
                String element = equationParts.get(x);
                if (element.equals("-")) {
                    double difference = Double.parseDouble(equationParts.get(x - 1)) - Double.parseDouble(equationParts.get(x + 1));
                    equationParts.set(x, String.valueOf(difference));
                    equationParts.remove(x + 1);
                    equationParts.remove(x - 1);
                    x--;
                } else if (element.equals("+")) {
                    double sum = Double.parseDouble(equationParts.get(x - 1)) + Double.parseDouble(equationParts.get(x + 1));
                    equationParts.set(x, String.valueOf(sum));
                    equationParts.remove(x + 1);
                    equationParts.remove(x - 1);
                    x--;
                } else {

                }
            }
        }
        catch (Exception e)
        {
            display.setText("Error");
        }
        answer = Double.parseDouble(equationParts.get(0));
        if (String.valueOf(answer).equalsIgnoreCase("infinity"))
            input = "Error";
        else
            input = String.valueOf(answer);

        display.setText(input);
    }
    public void clear(View view)
    {
        input = "";
        display.setText("0");
    }
}
