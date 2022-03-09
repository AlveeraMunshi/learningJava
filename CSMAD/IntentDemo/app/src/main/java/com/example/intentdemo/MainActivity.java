package com.example.intentdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView number;
    Button launch;

    static final int NUMBER_CODE = 1234;
    static String INTENT_CODE = "number";
    static String OTHER_CODE = "nummy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        number = findViewById(R.id.textView2);
        launch = findViewById(R.id.button);

        launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callSecondActivity(view);
            }
        });
    }

    public void callSecondActivity(View view)
    {
        Intent intentToLoad = new Intent(MainActivity.this, NumberActivity.class); //context, where you're sending it with .class
        //Intent intentToLoad = new Intent(getApplicationContext(), NumberActivity.class); //also works
        // Use key value pars to save/retrieve values
        intentToLoad.putExtra("TEST", "This is a test");
        intentToLoad.putExtra("TEST2", "TEEEEST!");

        //doesn't get result from other activities
        //startActivity(intentToLoad);

        //used to get result from another activity
        //need to override onActivityResult that is invoked automatically when second activity returns result
        startActivityForResult(intentToLoad, NUMBER_CODE);
    }


    //Override onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //used to make sure right activity is sending info and that it went through
        super.onActivityResult(requestCode,  resultCode, data);
        //requestCode
        if(requestCode == NUMBER_CODE && resultCode == RESULT_OK)
        {
            String one = data.getStringExtra(INTENT_CODE);
            String two = data.getStringExtra(OTHER_CODE);
            String three = data.getStringExtra("HelloMoto");
            number.setText(one+two+three);
        }

    }
}