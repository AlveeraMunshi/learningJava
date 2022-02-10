package com.example.gpsapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    static double lat, lon, distance, time, lastDistance;
    TextView latDisplay, lonDisplay, addy;
    final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    List<Address> addresses;
    Geocoder gc;
    LocationListener ll;
    ArrayList<Double> distances, times;
    ArrayList<String> addies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latDisplay = findViewById(R.id.lat);
        lonDisplay = findViewById(R.id.lon);
        addy = findViewById(R.id.addy);
        gc = new Geocoder(this);
        ll = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Double xsquared = Math.pow(lat - location.getLatitude(),2);
                Double ysquared = Math.pow(lon - location.getLongitude(),2);
                distance+= Math.sqrt(ysquared - xsquared);
                lat = location.getLatitude();
                lon = location.getLongitude();
                Log.d("location", lat+""+lon);
                latDisplay.setText(String.valueOf(lat));
                lonDisplay.setText(String.valueOf(lon));
                try {
                    addresses = gc.getFromLocation(lat, lon, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(addresses);
                Address ad = addresses.get(0);
                String str = String.valueOf(ad).substring(25);
                addies.add(str.substring(0,str.indexOf("\"")));
                addy.setText(addies.get(addies.size()-1));
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            //return;
        }
        //Log.d("ifrun", "running");
        LocationManager lm = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1: {
                for (int i = 0; i < permissions.length; i++) {
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

}
