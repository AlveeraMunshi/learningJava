package com.example.intentpractice;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText enterName;
    Button nextA;

    static final int NUMBER_CODE = 1234;
    static String INTENT_CODE = "number";
    static String OTHER_CODE = "nummy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterName = findViewById(R.id.enterName);
        nextA = findViewById(R.id.nextA);

        nextA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callSecondActivity(view);
            }
        });
    }
    public void callSecondActivity(View view)
    {
        Intent intentToLoad = new Intent(getApplicationContext(), enterActivity.class);
        intentToLoad.putExtra("username", enterName.getText());
    }
    @Override
    public void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NUMBER_CODE && resultCode == RESULT_OK)
        {
            nameDisplay.setText(data.getStringExtra("username"));
        }

    }
}