package com.example.tiltpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.example.tiltpractice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ActivityMainBinding binding;
    WindowManager.LayoutParams bp;
    static float x, y, z, brightLevel;
    float brightspeed = 0.1f;
    float blue = 125;
    float prevX = 0;
    float prevY = 0;
    float prevZ = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometerSensor, sm.SENSOR_DELAY_NORMAL);

        bp = getWindow().getAttributes();
        binding.bright.setText(brightLevel+"");

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x = sensorEvent.values[0];
        y = sensorEvent.values[1];
        z = sensorEvent.values[2];
        if (prevX == 0)
        {
            prevX = x;
            prevY = y;
            prevZ = z;
        }
        brightLevel = getWindow().getAttributes().screenBrightness;
        if (brightLevel < 1.0f) {
            brightLevel += .01f;
        }
        if (blue < 250)
        {
            blue-= .01f;
        }
        binding.getRoot().setBackgroundColor(Color.argb(1, (float)250, (float)250, blue));
        bp.screenBrightness = brightLevel;
        getWindow().setAttributes(bp);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}