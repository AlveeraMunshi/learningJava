package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    Button bexponent;
    Button bsin;
    Button bcos;
    Button btan;
    Button bsqrt;
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
        bexponent = findViewById(R.id.button20);
        bleftp = findViewById(R.id.button18);
        brightp = findViewById(R.id.button19);
        bsin = findViewById(R.id.button21);
        bcos = findViewById(R.id.button22);
        btan = findViewById(R.id.button23);
        bsqrt = findViewById(R.id.button24);
        input = "";
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
        input += "-";
        display.setText(input);
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
    public void exponent(View view)
    {
        if (!input.isEmpty()) {
            input += "^";
            display.setText(input);
        }
    }
    public void sin(View view)
    {
        input += "sin(";
        display.setText(input);
    }
    public void cos(View view)
    {
        input += "cos(";
        display.setText(input);
    }
    public void tan(View view)
    {
        input += "tan(";
        display.setText(input);
    }
    public void sqrt(View view)
    {
        input += "√(";
        display.setText(input);
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
        for (int x = 0; x < input.length(); x++)
        {
            input = input.replace("sin", "s");
            input = input.replace("cos", "c");
            input = input.replace("tan", "t");
        }
        StringTokenizer st = new StringTokenizer(input, "+-/*^()sct", true);
        ArrayList<String> equationParts = new ArrayList<String>();
        while(st.hasMoreElements())
        {
            equationParts.add((String)st.nextElement());
        }
        System.out.println(equationParts);
        boolean exthrown = false;
        try {
            boolean hasP = true;
            while (hasP)
            {
                int leftpindex = -1;
                int rightpindex = -1;
                for (int x = 0; x < equationParts.size(); x++)
                {
                    if (equationParts.get(x).equals("("))
                        leftpindex = x;
                }
                if (leftpindex != -1)
                {
                    for (int x = leftpindex; x < equationParts.size() && rightpindex == -1; x++)
                    {
                        if (equationParts.get(x).equals(")"))
                            rightpindex = x;
                    }
                }
                if (leftpindex == -1 || rightpindex == -1)
                    hasP = false;
                else {
                    mdas(equationParts, leftpindex+1, rightpindex-1);
                    equationParts.remove(leftpindex+2);
                    equationParts.remove(leftpindex);
                }

            }
            mdas(equationParts, 0, equationParts.size());
            String ans = equationParts.get(0);
            if (ans.equalsIgnoreCase("infinity") || ans.equalsIgnoreCase("nan") || equationParts.size() > 1)
                exthrown = true;
        } catch (NumberFormatException e) {
            input = "Error";
            exthrown = true;
        }
        if ( exthrown == true) {
            input = "Error";
        }
        else
        {
            input = equationParts.get(0);
        }

        display.setText(input);
    }
    public void clear(View view)
    {
        input = "";
        display.setText("0");
    }
    public void simplify1(ArrayList<String> equationParts, int x, double ans)
    {
        equationParts.set(x, String.valueOf(ans));
        equationParts.remove(x+1);
    }
    public void simplify2(ArrayList<String> equationParts, int x, double value)
    {
        equationParts.set(x, String.valueOf(value));
        equationParts.remove(x + 1);
        equationParts.remove(x - 1);
    }
    public void mdas (ArrayList<String> equationParts, int min, int max)
    {
        DecimalFormat df = new DecimalFormat("#.#########");
        df.setRoundingMode(RoundingMode.FLOOR);

        for (int x = min; x < max; x++) {
            String element = equationParts.get(x);
            if (element.equals("s"))
            {
                if (equationParts.get(x+1).equals("-")) {
                    setNegative(equationParts, x + 1);
                    max -= 1;
                }
                double right = Double.parseDouble(equationParts.get(x + 1));
                double ans = Math.sin(Math.toRadians(right));
                ans = Double.parseDouble(df.format(ans));
                simplify1(equationParts, x, ans);
                x--;
                max-=1;
            }
            else if (element.equals("c"))
            {
                if (equationParts.get(x+1).equals("-")) {
                    setNegative(equationParts, x + 1);
                    max -= 1;
                }
                double right = Double.parseDouble(equationParts.get(x + 1));
                double ans = Math.cos(Math.toRadians(right));
                ans = Double.parseDouble(df.format(ans));
                simplify1(equationParts, x, ans);
                x--;
                max-=1;
            }
            else if (element.equals("t"))
            {
                if (equationParts.get(x+1).equals("-")) {
                    setNegative(equationParts, x + 1);
                    max -= 1;
                }
                double right = Double.parseDouble(equationParts.get(x + 1));
                double ans = Math.tan(Math.toRadians(right));
                ans = Double.parseDouble(df.format(ans));
                if (right == 90 || right == 270)
                    equationParts.set(x, "Error");
                else
                    simplify1(equationParts, x, ans);
                x--;
                max-=1;
            }
            else
            {

            }
        }
        for (int x = min; x < max; x++) {
            String element = equationParts.get(x);
            if (element.equals("^"))
            {
                if (equationParts.get(x+1).equals("-")) {
                    setNegative(equationParts, x + 1);
                    max -= 1;
                }
                double left = Double.parseDouble(equationParts.get(x - 1));
                double right = Double.parseDouble(equationParts.get(x + 1));
                double ans = Math.pow(left, right);
                ans = Double.parseDouble(df.format(ans));
                simplify2(equationParts, x, ans);
                x--;
                max-=2;
            }
            else if (element.equals("√"))
            {
                if (equationParts.get(x+1).equals("-")) {
                    setNegative(equationParts, x + 1);
                    max -= 1;
                }
                double right = Double.parseDouble(equationParts.get(x + 1));
                double ans = Math.sqrt(right);
                ans = Double.parseDouble(df.format(ans));
                simplify1(equationParts, x, ans);
                x--;
                max-=1;
            }
        }
        for (int x = min; x < max; x++) {
            String element = equationParts.get(x);
            if ((x == 0 || equationParts.get(x-1).equals("(")) && element.equals("-")) {
                String connect = equationParts.get(x) + equationParts.get(x+1);
                simplify1(equationParts, x, Double.parseDouble(connect));
                x--;
                max-=1;
            }
            if (element.equals("*")) {
                if (equationParts.get(x+1).equals("-")) {
                    setNegative(equationParts, x + 1);
                    max -= 1;
                }
                double product = Double.parseDouble(equationParts.get(x - 1)) * Double.parseDouble(equationParts.get(x + 1));
                simplify2(equationParts, x, product);
                x--;
                max-=2;
            } else if (element.equals("/")) {
                if (equationParts.get(x+1).equals("-")) {
                    setNegative(equationParts, x + 1);
                    max -= 1;
                }
                double quotient = Double.parseDouble(equationParts.get(x - 1)) / Double.parseDouble(equationParts.get(x + 1));
                quotient = Double.parseDouble(df.format(quotient));
                simplify2(equationParts, x, quotient);
                x--;
                max-=2;
            } else {

            }
        }
        for (int x = min; x < max; x++) {
            String element = equationParts.get(x);
            if (element.equals("+")) {
                if (equationParts.get(x+1).equals("-")) {
                    setNegative(equationParts, x + 1);
                    max -= 1;
                }
                double sum = Double.parseDouble(equationParts.get(x - 1)) + Double.parseDouble(equationParts.get(x + 1));
                simplify2(equationParts, x, sum);
                x--;
                max-=2;
            }
        }
        for (int x = min; x < max; x++) {
            String element = equationParts.get(x);
            if (element.equals("-"))
            {
                double difference = Double.parseDouble(equationParts.get(x - 1)) - Double.parseDouble(equationParts.get(x + 1));
                simplify2(equationParts, x, difference);
                x--;
                max-=2;
            }
        }
        System.out.println(equationParts);
    }
    public void setNegative (ArrayList<String> equationParts, int x)
    {
        equationParts.set(x, equationParts.get(x) + equationParts.get(x+1));
        equationParts.remove(x+1);
    }
}