package com.example.tiltdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.tiltdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    float brightnessLevel = 0;
    WindowManager.LayoutParams brightnessParams;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometerSensor, sm.SENSOR_DELAY_NORMAL);

        brightnessParams = getWindow().getAttributes();
        binding.bright.setText(brightnessLevel+"");


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        binding.x.setText(sensorEvent.values[0]+"");
        binding.y.setText(sensorEvent.values[1]+"");
        binding.z.setText(sensorEvent.values[2]+"");

        if (brightnessLevel < 1.0f)
            brightnessLevel+=.01f;
        brightnessParams.screenBrightness = brightnessLevel;
        getWindow().setAttributes(brightnessParams);

        binding.bright.setText(brightnessLevel+"");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}