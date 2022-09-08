package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity{


    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FirebaseDatabase database;

    int SPLASH_TIME = 3000; //This is 3 seconds

    String link, city, aqi, o3, lat, lon;
    JSONObject info;
    ArrayList<Forecast> forecasts;
    Button find;
    EditText cityinput;
    TextView cityDisplay, aqiDisplay, o3Display;
    ImageView gyroView;
    AnimationDrawable anim;

    final private String token = "d9a2a6ffab4cf1e838f01bf8fcfdebd8ef222868";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        find = findViewById(R.id.find);
        cityinput = findViewById(R.id.cityinput);
        cityDisplay = findViewById(R.id.cityDisplay);
        aqiDisplay = findViewById(R.id.aqiDisplay);
        o3Display = findViewById(R.id.o3Display);
        gyroView = (ImageView) findViewById(R.id.gyro);

        find.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    city = String.valueOf(cityinput.getText());
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                    // Creating an Editor object to edit(write to the file)
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    // Storing the key and its value as the data fetched from edittext
                    myEdit.putString("city", cityinput.getText().toString());
                    myEdit.putString("aqi", aqi);
                    myEdit.putString("o3", o3);
                    // Once the changes have been made,
                    // we need to commit to apply those changes made,
                    // otherwise, it will throw an error
                    myEdit.commit();
                    link = "https://api.waqi.info/feed/"+city+"?token="+ token;
                    FindInfo task = new FindInfo();
                    task.execute();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        String s1 = sh.getString("city", "");
        String s2 = sh.getString("aqi", "");
        String s3 = sh.getString("o3", "");
        // We can then use the data
        cityinput.setText(s1);
        cityDisplay.setText("City: " + s1);
        aqiDisplay.setText("AQI: " + s2);
        o3Display.setText("O3: " + s3);
        find.performClick();

    }

    private class FindInfo extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String reader = "";
            try
            {
                URL url1;
                URLConnection connection1;
                try
                {
                    //Take URL of geocoding API call
                    url1 = new URL(link);
                    System.out.println(link);
                    //put API call result into buffered reader
                    connection1 = url1.openConnection();
                    InputStream stream1 = connection1.getInputStream();
                    InputStreamReader inputRead1 = new InputStreamReader(stream1);
                    BufferedReader br1 = new BufferedReader(inputRead1);
                    reader = br1.readLine();
                    while (br1.readLine() != null) {
                        reader += br1.readLine();
                    }
                    System.out.println("url1 reader: " + reader);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            try {
                //Save data
                JSONObject call = new JSONObject(reader);
                JSONObject data = call.getJSONObject("data");
                aqi = data.getString("aqi");
                o3 = data.getJSONObject("iaqi").getJSONObject("o3").getString("v");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            reader = "";
            return reader;
        }

        @Override
        protected void onPostExecute(String result) {
            try
            {
                // display the data in UI
                cityDisplay.setText("City: " + city);
                aqiDisplay.setText("AQI: " + aqi);
                o3Display.setText("O3: " + o3);


            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        // Storing the key and its value as the data fetched from edittext
        myEdit.putString("city", cityinput.getText().toString());
        myEdit.putString("aqi", aqiDisplay.getText().toString().substring(4));
        myEdit.putString("o3", o3Display.getText().toString().substring(3));
        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.apply();
        myEdit.commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
        gyroView.setBackgroundResource(R.drawable.drone_animation);
        anim = (AnimationDrawable) gyroView.getBackground();
        anim.start();

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        anim.start();
    }
}