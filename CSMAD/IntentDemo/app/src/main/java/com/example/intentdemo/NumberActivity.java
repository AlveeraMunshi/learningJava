package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NumberActivity extends AppCompatActivity {

    EditText enteredNumber;
    Button closeAct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        enteredNumber = findViewById(R.id.editText);
        closeAct = findViewById(R.id.button2);


        //Option for passing info from launching activity to secondary activity //don't use to return values -- only for passing
        Bundle extra = getIntent().getExtras();
        String test1 = extra.getString("TEST");
        String test2 = extra.getString("TEST2");


        //Toast.makeText(getApplicationContext(), getIntent().getStringExtra("TEST"), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), test1+" !! " + test2, Toast.LENGTH_LONG).show();

        closeAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFirstActivity(view);
            }
        });
    }3

    public void callFirstActivity(View view)
    {
        Intent sendInfoBack = new Intent();
        sendInfoBack.putExtra(MainActivity.INTENT_CODE, enteredNumber.getText().toString());
        sendInfoBack.putExtra(MainActivity.OTHER_CODE, "127");
        sendInfoBack.putExtra("HelloMoto", "DATADATA");

        setResult(RESULT_OK, sendInfoBack); //need to set result code that it was named before to get the info to be received back
        finish();
    }
}