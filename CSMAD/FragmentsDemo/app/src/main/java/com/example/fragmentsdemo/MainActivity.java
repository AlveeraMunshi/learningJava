package com.example.fragmentsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements BotFragment.ReceiveString {

    TextView textView;
    Button replaceButton;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView_main);
        replaceButton = findViewById(R.id.button_replace);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        TopFragment topFragment = new TopFragment();
        BotFragment botFragment = new BotFragment();

        fragmentTransaction.add(R.id.layout_top, topFragment);
        fragmentTransaction.add(R.id.layout_bot, botFragment);
        fragmentTransaction.commit();

        replaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = fragmentManager.beginTransaction();
                BotFragment replacementFragment = new BotFragment();
                fragmentTransaction.replace(R.id.layout_top, replacementFragment);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void receive(String str) {
        textView.setText(str);
    }
}