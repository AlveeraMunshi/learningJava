package com.example.surfaceview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity{

    GameSurface gs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        gs = new GameSurface(this);
        setContentView(gs);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        gs.resume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        gs.pause();
    }

    public class GameSurface extends SurfaceView implements Runnable, SensorEventListener
    {

        Thread gThread;
        SurfaceHolder holder;
        volatile boolean running = false;
        Bitmap ball;
        int ballX = 0;
        int ballY = 0;
        Paint paintProperty;
        float x, y, z;

        int sw;
        int sh;

        public GameSurface(Context context)
        {
            super(context);

            holder = getHolder();
            ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);

            Display sd = getWindowManager().getDefaultDisplay();
            Point sizeOfScreen = new Point();
            sd.getSize(sizeOfScreen);

            sw = sizeOfScreen.x;
            sh = sizeOfScreen.y;

            paintProperty = new Paint();
            paintProperty.setColor(Color.WHITE);
            paintProperty.setTextSize(80);


            SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            Sensor accelerometerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sm.registerListener((SensorEventListener) this, accelerometerSensor, sm.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        @Override
        public void run() {

            int flipX = 1;
            int flipY = 1;
            while (running)
            {
                if (holder.getSurface().isValid() == false)
                {
                    continue;
                }
                Canvas canvas = holder.lockCanvas();
                canvas.drawRGB(255,0,0);
                canvas.drawText("Alveera Munshi", 400, 400, paintProperty);
                //canvas.drawBitmap(ball, (sw/2)-(ball.getWidth()/2)+ballX, (sh/2)-ball.getHeight(),null);
                float left = (sw/2)-(ball.getWidth()/2)+ballX;
                float top = (sh/2)-ball.getHeight()/2+ballY;
                canvas.drawBitmap(ball, left, top,null);

                if (x > 0)
                    flipX = -1;
                else
                    flipX = 1;
                if (y > 0)
                    flipY = 1;
                else
                    flipY = -1;

                if (ballX == sw/2 - ball.getWidth()/2)
                {
                    flipX = -1;
                }

                if (ballY == sh/2 - ball.getHeight()/2)
                {
                    flipY = -1;
                }

                if (ballX == -1 * sw/2 + ball.getWidth()/2)
                {
                    flipX = 1;
                }

                if (ballY == -1 * sh/2 + ball.getHeight()/2)
                {
                    flipY = 1;
                }

                ballX+=flipX;
                ballY+=flipY;
                holder.unlockCanvasAndPost(canvas);
            }

        }
        public void resume()
        {
            running = true;
            gThread = new Thread(this);
            gThread.start();
        }
        public void pause()
        {
            running = false;
            while (true)
            {
                try {
                    gThread.join();
                }
                catch (InterruptedException e)
                {

                }
            }
        }
    }
}