package com.example.thanksgivingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    ImageView blueTurkey, brownTurkey, orangeTurkey, fluffy, margarite, tomtom, tb, givet, harvestb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        blueTurkey = findViewById(R.id.blueTurkey);
        orangeTurkey = findViewById(R.id.orangeTurkey);
        brownTurkey = findViewById(R.id.brownTurkey);
        fluffy = findViewById(R.id.fluffy);
        margarite = findViewById(R.id.margarite);
        tomtom = findViewById(R.id.tomtom);
        tb = findViewById(R.id.tb);
        givet = findViewById(R.id.givet);
        harvestb = findViewById(R.id.harvestb);
        blueTurkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Blue Turkey gobble gobble", Toast.LENGTH_SHORT).show();
            }
        });
        orangeTurkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Orange Turkey gobble gobble", Toast.LENGTH_SHORT).show();
            }
        });
        brownTurkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Brown Turkey gobble gobble", Toast.LENGTH_SHORT).show();
            }
        });
        margarite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Margarite is very proud to be here", Toast.LENGTH_SHORT).show();
            }
        });
        tomtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Tomtom is a little brocken", Toast.LENGTH_SHORT).show();
            }
        });
        fluffy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Fluffy broke Tomtom's heart #genderisasocialconstruct", Toast.LENGTH_SHORT).show();
            }
        });
    }
}