package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = findViewById(R.id.textView);

        JSONObject schoolInfo = new JSONObject();
        try
        {
            schoolInfo.put("Name", "Aloe");
            schoolInfo.put("Grade", 11);
            schoolInfo.put("ID", 10016836);
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        Log.d("school", String.valueOf(schoolInfo));
        try
        {
            t.setText(schoolInfo.get("name").toString());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        try
        {
            JSONObject compSci = new JSONObject();
            compSci.put("grade", "A");
            compSci.put("percentage", 94);
            schoolInfo.put("Computer Science", compSci);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        try {
            JSONObject findCourse;
            findCourse = schoolInfo.getJSONObject("Computer Science");
            t.setText(findCourse.get("grade").toString());
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        JSONArray clubs = new JSONArray();
        clubs.put("GWC");
        clubs.put("Computer Science Club");
        try {
            schoolInfo.put("clubs", clubs);
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }

}