package com.example.atlarun;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.atlarun.databinding.ActivityMainBinding;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity{

    GameSurface gs;
    private static ActivityMainBinding binding;
    private SoundPool sp;
    int scream;
    MediaPlayer mp;
    AtomicInteger timeRemaining = new AtomicInteger(30);
    AtomicInteger score = new AtomicInteger(0);
    AtomicInteger change = new AtomicInteger(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        binding.drawee.setController(
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(ImageRequest.fromUri("https://media.giphy.com/media/rI1oTB9f3RAuGsiMJ1/giphy.gif"))
                        .setAutoPlayAnimations(true)
                        .build());

        gs = new GameSurface(this);
        gs.setId(View.generateViewId());
        gs.setZOrderOnTop(true);
        gs.getHolder().setFormat(PixelFormat.TRANSPARENT);

        ConstraintLayout.LayoutParams matchParentLayout = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        binding.cl.setLayoutParams(matchParentLayout);
        binding.cl.addView(gs, matchParentLayout);

        setContentView(binding.cl);

        mp = MediaPlayer.create(this,R.raw.avatarslove);
        if (mp.isPlaying()) {
            mp.stop();
            try {
                mp.prepare();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
            mp.start();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes aa = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();

            sp = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(aa).build();
        }
        else
            sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        scream = sp.load(this, R.raw.sukiscream, 1);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        gs.resume();
        mp = MediaPlayer.create(this,R.raw.avatarslove);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        gs.pause();
        mp.pause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mp.reset();
        mp.release();
        mp = null;
    }

    public class GameSurface extends SurfaceView implements Runnable, SensorEventListener
    {

        Thread gThread;
        SurfaceHolder holder;
        volatile boolean running = false;
        Bitmap player, opponent, hitplayer, gameover, endchar;
        int playerX = 0;
        int opponentY =0;
        Paint paintProperty;
        float x, y, z, pleft, ptop, oleft, otop, speed;
        BitmapFactory.Options options;

        int sw;
        int sh;

        boolean hit = false;
        boolean game = true;
        boolean chartap = false;

        public GameSurface(Context context)
        {
            super(context);

            holder = getHolder();

            //Bitmap options for memory saving purposes
            options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inSampleSize = 5;

            //Bitmap refrences
            player = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.suki_back, options), 4*300, 3*300, true);
            opponent = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rock, options), 4*150, 3*150, true);
            hitplayer = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sukietear), 4*300, 3*300, true);
            gameover = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fireforest), 1000, 1500, true);
            endchar = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.suki_side), 4*250, 3*250, true);

            Display sd = getWindowManager().getDefaultDisplay();
            Point sizeOfScreen = new Point();
            sd.getSize(sizeOfScreen);

            sw = sizeOfScreen.x;
            sh = sizeOfScreen.y;

            paintProperty = new Paint();
            paintProperty.setColor(Color.WHITE);
            paintProperty.setTextSize(50);

            //Initialize sensormanager
            SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            Sensor accelerometerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sm.registerListener((SensorEventListener) this, accelerometerSensor, sm.SENSOR_DELAY_NORMAL);

            //30 second timer, ticks every 1 second
            new CountDownTimer(30000, 1000) {

                //Every one second
                public void onTick(long millisUntilFinished) {
                    timeRemaining.getAndDecrement();
                    //updates timer and score
                    binding.time.setText("Time Remaining: " + timeRemaining);
                    binding.score.setText("Score: " + score);
                }

                //when timer ends
                public void onFinish() {
                    //stop music and update timer and score
                    Log.d("On Finish", "running");
                    mp.stop();
                    binding.time.setText("Time Remaining: " + timeRemaining);
                    binding.score.setText("Score: " + score);
                    game = false;
                }
            }.start();

            //30 second timer, ticks every 3 seconds
            new CountDownTimer(30000, 3000) {

                //Every 3 seconds
                public void onTick(long millisUntilFinished) {
                    Log.d("hit value", " " + hit);
                    //Changes scores based on hit
                    if (hit) //if hit, reduce by 1
                        score.getAndDecrement();
                    else //if not, increase by 1
                        score.getAndIncrement();
                    //reset hit condition to false
                    hit = false;
                    if (millisUntilFinished/3000 % 2 == 0)
                        chartap = false;
                    //random left starting position for our opponent
                    oleft = (float) ((Math.random()*500)-250);
                    //opponent position back to top
                    opponentY = -300;
                }

                public void onFinish() {

                }
            }.start();
        }

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];
            //tilt angle determines speed
            if (Math.abs(x) > 6) //heavy tilt, faster speed
                speed = 7;
            else //less tilt, slower speed
                speed = 4;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        @Override
        public void run() {

            int flipX = 1;
            while (running)
            {
                Canvas canvas = holder.lockCanvas();
                if (holder.getSurface().isValid() == false)
                {
                    continue;
                }
                canvas.drawColor(0, PorterDuff.Mode.CLEAR); //clears previous bitmaps
                if (game)
                {
                    pleft = (sw/2)-(player.getWidth()/2)+playerX; // changes player position with respect to rightmost
                    ptop = 500; // keeps y val of player constant at 500
                    if (chartap)
                    {
                        canvas.drawBitmap(endchar, pleft, ptop, null);
                    }
                    else if (hit) //if hit, then display sad suki
                        canvas.drawBitmap(hitplayer, pleft, ptop, null);
                    else //if not hit, then regular suki
                        canvas.drawBitmap(player, pleft, ptop, null);

                    //Log.d("Player loc", "(" + pleft + " " + ptop + ")");

                    Log.d("Change", "" + change);
                    opponentY += Integer.parseInt(String.valueOf(change));
                    otop = (sh / 2) - (opponent.getHeight() / 2) + opponentY; // opponent moves DOWN with respect to topmost
                    canvas.drawBitmap(opponent, oleft, otop, null);
                    //Log.d("Opponent loc", "(" + oleft + " " + otop + ")");

                    //Tilt determines direction
                    if (x > 0)
                        flipX = -1; //Tilt right means move right
                    else
                        flipX = 1; //Tilt left means move left

                    //Prevent going off the screen
                    double rightmost = sw / 2 - player.getWidth() / 2;
                    double leftmost = -1 * sw / 2 + player.getWidth() / 2;
                    if (playerX <= rightmost) //If player touching left edge
                    {
                        flipX = 1; //move left
                        //Prevent Wiggle
                        if (x > 0)
                            speed = 0;
                    } else if (playerX >= leftmost) //if player touching right edge
                    {
                        flipX = -1; //move right
                        //Prevent Wiggle
                        if (x < 0)
                            speed = 0;
                    }

                    Rect p = new Rect((int) pleft + player.getWidth() * 5 / 16, (int) ptop + player.getHeight() / 4, (int) pleft + player.getWidth() * 4 / 6, (int) ptop + player.getHeight() * 5 / 8);
                    Rect o = new Rect((int) oleft + player.getWidth() / 4, (int) otop + player.getHeight() / 4, (int) oleft + opponent.getWidth() * 4 / 8, (int) otop + opponent.getHeight() * 5 / 8);

                    if (Rect.intersects(p, o) || Rect.intersects(o, p)) {
                        hit = true;
                        sp.play(scream, 1, 1, 1, 0, 1);
                    }

                    //Change player position based on tilt and speed
                    playerX += flipX * speed;
                    /*Log.d("Player X", " " + playerX);
                    Log.d("Flip", " " + flipX);
                    Log.d("Hit Value", " " + hit);*/
                }
                else
                {
                    canvas.drawBitmap(gameover, 0, 0,null);
                    canvas.drawBitmap(endchar,  -25, 400, null);
                    canvas.drawText("Score: " + score, 300, 100, paintProperty);
                }

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

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent)
        {
            Log.d("on touch event", "running");
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
            {
                switch (Integer.parseInt(String.valueOf(change))) {
                    case 30:
                        change.set(5);
                        break;
                    case 5:
                        change.set(15);
                        break;
                    case 15:
                        change.set(30);
                        break;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if( x > pleft + player.getWidth() * 5 / 16 && x < pleft + player.getWidth() * 4 / 6 && y > ptop + player.getHeight() / 4 && y < ptop + player.getHeight() * 5 / 8 )
                {
                    chartap = true;
                }
            }
            return true;
        }
    }
}