package com.example.openweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText zipInput;
    Button find;
    ListView lv;
    String city, lat, lon, key;
    int imgID;
    static String ziptolatlon, latlon;
    TextView cityDisplay, lonDisplay, latDisplay;
    JSONObject info;
    ArrayList<Hour> forecasts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FindViewById
        zipInput = findViewById(R.id.zipInput);
        find = findViewById(R.id.find);
        cityDisplay = findViewById(R.id.cityDisplay);
        lonDisplay = findViewById(R.id.lonDisplay);
        latDisplay = findViewById(R.id.latDisplay);
        lv = findViewById(R.id.lv);

        //Attributes
        city = "";
        lat = "0";
        lon = "0";
        key = "ba516c98290eeeddf13a83c2d2513f8a";
        imgID = 0;
        forecasts = new ArrayList<Hour>();

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ziptolatlon = "https://api.openweathermap.org/geo/1.0/zip?zip="+String.valueOf(zipInput.getText())+"&appid="+key;
                    FindLatLonTask task = new FindLatLonTask();
                    task.execute();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });
    }
    private class FindLatLonTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String reader = "";
            try {
                URL url1;
                URLConnection connection1;
                try {
                    //Take URL of geocoding API call
                    url1 = new URL(ziptolatlon);
                    System.out.println(ziptolatlon);
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //Parse data from geocoding API and retrieve latitude, longitude, and city name
            String delim = "[,:]";
            String[] parts = reader.substring(1,reader.length()-1).split(delim);
            for (int x = 0; x < parts.length; x++)
            {
                if (parts[x].equalsIgnoreCase("\"lat\""))
                {
                    lat = parts[x+1];
                }
                if (parts[x].equalsIgnoreCase("\"lon\""))
                {
                    lon = parts[x+1];
                }
                if (parts[x].equalsIgnoreCase("\"name\""))
                {
                    city = parts[x+1].substring(1,parts[x+1].length()-1);
                }
            }
            reader = "";
            try {
                URL url2;
                URLConnection connection2;
                try {
                    //Use one call API
                    latlon = "https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&units=imperial&exclude=minutely,daily&appid=" + key;
                    System.out.println(latlon);
                    url2 = new URL(latlon);
                    //Use buffered reader to read the API call
                    connection2 = url2.openConnection();
                    InputStream stream2 = connection2.getInputStream();
                    InputStreamReader inputRead2 = new InputStreamReader(stream2);
                    BufferedReader br2 = new BufferedReader(inputRead2);
                    reader = br2.readLine();
                    while (br2.readLine() != null) {
                        reader += br2.readLine();
                    }
                    System.out.println("url2 reader: " + reader);
                    return reader;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return reader;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                //put data from one call API into JSON Object
                if (result != null)
                    info = new JSONObject(result);
                //retrieve hourly data from the total data
                JSONArray hourly = info.getJSONArray("hourly");
                //Add data from 4 hours into an ArrayList for the ListView
                forecasts.clear();
                for (int x = 0; x < 4; x++)
                {
                    JSONObject curr = hourly.getJSONObject(x);
                    String descrip = curr.getJSONArray("weather").getJSONObject(0).getString("description");
                    forecasts.add(new Hour(curr.getLong("dt"), info.getString("timezone"), curr.getDouble("temp"), descrip, findImgID(descrip)));
                }
                // display the data in UI
                cityDisplay.setText("City: " + city);
                latDisplay.setText("X: " + lat);
                lonDisplay.setText("Y: " + lon);
                System.out.println("object: " + info);
                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.hourly_forecast, forecasts, MainActivity.this);
                lv.setAdapter(adapter);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Retrieves image based on description
        public int findImgID(String description)
        {
            if (description.contains("cloud"))
            {
                return R.drawable.cloudy;
            }
            else if (description.contains("rain"))
            {
                return R.drawable.rainy;
            }
            else if (description.contains("snow"))
            {
                return R.drawable.snowy;
            }
            else if (description.contains("wind"))
            {
                return R.drawable.windy;
            }
            else
            {
                return R.drawable.sunny;
            }
        }
    }
}

