package com.example.gpsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    static double lat, lon, distance, starttime, endtime;
    static String lastAddress;
    TextView latDisplay, lonDisplay, addy, dist, longest, recent;
    List<Address> address;
    Geocoder gc;
    LocationListener ll;
    LocationManager lm;
    ArrayList<Double> times= new ArrayList<Double>();
    ArrayList<String> addies= new ArrayList<String>();
    ArrayList<Location> locations= new ArrayList<Location>();
    RecyclerView rw;

    protected void onDestroy() {
        super.onDestroy();
        lm.removeUpdates(ll);
        Log.d("LIFECYCLETAG", "onDestroy()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initializations
        setContentView(R.layout.activity_main);
        SlidingUpPanelLayout sp = findViewById(R.id.slidingUp);
        sp.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                findViewById(R.id.label).setAlpha(1 - slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });
        latDisplay = findViewById(R.id.lat);
        lonDisplay = findViewById(R.id.lon);
        addy = findViewById(R.id.addy);
        dist = findViewById(R.id.distance);
        longest = findViewById(R.id.longest);
        recent = findViewById(R.id.recent);
        rw = findViewById(R.id.rw);
        RecyclerCustomAdapter adapter = new RecyclerCustomAdapter(getApplicationContext(), addies);
        rw.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rw.setLayoutManager(linearLayoutManager);
        //Geocoder
        gc = new Geocoder(this);
        //Check Permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            //return;
        }
        //Location Listener Inner Class
        ll = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                adapter.notifyDataSetChanged();
                //Lat Lon Display
                lat = location.getLatitude();
                lon = location.getLongitude();
                Log.d("location", lat+""+lon);
                latDisplay.setText("X: " + fourPlaces(String.valueOf(lat)));
                lonDisplay.setText("Y: " + fourPlaces(String.valueOf(lon)));
                //Retrieve Address
                try {
                    address = gc.getFromLocation(lat, lon, 1);
                    lastAddress = address.get(0).getAddressLine(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Add Address, Times, and Distance Calculations
                if (lastAddress != null)
                {
                    if (addies.isEmpty() || !lastAddress.equals(addies.get(addies.size() - 1))) {
                        endtime = (System.currentTimeMillis());
                        times.add((endtime-starttime)/1000);
                        starttime = (System.currentTimeMillis());
                        addies.add(lastAddress);
                        if (locations.size()>0)
                            distance+=location.distanceTo(locations.get(locations.size()-1));
                        locations.add(location);
                    }
                    else
                    {
                        endtime = System.currentTimeMillis();
                        if (times.size()>0)
                            times.set(times.size()-1, (endtime-starttime)/1000);
                    }

                }
                //Set Texts Section
                addy.setText(((addies.size()>0)?(addies.get(addies.size() - 1)) : "") + ((times.size()>0) ? ("\n" + times.get(times.size()-1)):"") + " s");
                if (addies.size()>=2)
                    recent.setText("Recent: " + addies.get(addies.size() - 2) + "\n" + times.get(times.size()-2) + " s");
                else
                    recent.setText("Recent: " + lastAddress + "\n" + ((times.size()>0)?(times.get(0)):"")  + " s");
                dist.setText("Distance: " + fourPlaces(String.valueOf(distance/1000)) + " km");
                int maxTimeIndex = 0;
                for (int x = 0; x < times.size(); x++)
                {
                    if (times.get(x) > times.get(maxTimeIndex))
                    {
                        maxTimeIndex = x;
                    }
                }
                String fav = "";
                try {
                    fav = addies.get(maxTimeIndex);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                longest.setText("Longest: " + fav + "\n" + ((times.size()>0)?(times.get(maxTimeIndex)):"") + " s");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {

            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
            }
        };
        lm = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1: {
                for (int i = 0; i < permissions.length; i++)
                {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];

                    if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            Log.d("RequestPerms", "granted");
                            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("RequestPerms", "denied");
                            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        }
                    }
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
    public String fourPlaces(String str)
    {
        try {
            str = str.substring(0, str.indexOf(".") + 4);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return str;
    }

}
