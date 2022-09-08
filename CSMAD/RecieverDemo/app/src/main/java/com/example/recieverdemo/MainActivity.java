package com.example.recieverdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch wifiSwitch;
    private WifiManager wifiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiSwitch = findViewById(R.id.wifi_switch);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    wifiManager.setWifiEnabled(true);
                    wifiSwitch.setText("Wifi is ON");
                    Toast.makeText(MainActivity.this, "Wifi turning ON", Toast.LENGTH_LONG).show();
                }
                else{
                    wifiManager.setWifiEnabled(false);
                    wifiSwitch.setText("Wifi is OFF");
                    Toast.makeText(MainActivity.this, "Wifi turning OFF", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        // These conditionals below are not required once the BroadcastReceiver is set up.
        // Thee BroadcastReceiver will allow for access/changes between the app and the system
        if(wifiManager.isWifiEnabled()){
            wifiSwitch.setChecked(true);
            wifiSwitch.setText("Wifi is ON");
        }
        else{
            wifiSwitch.setChecked(false);
            wifiSwitch.setText("Wifi is OFF");
        }
         */
    }

    @Override
    protected void onStart() {
        super.onStart(); // start program when app is opened, not just running in the background as it would with an onCreate()
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wifiStateReceiver);
    }

    // Set up this BroadcastReceiver to allow/check for changes in the system.

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiStateExtra){
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiSwitch.setChecked(true);
                    wifiSwitch.setText("Wifi is ON");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiSwitch.setChecked(false);
                    wifiSwitch.setText("Wifi is OFF");
                    break;
            }
        }
    };
}