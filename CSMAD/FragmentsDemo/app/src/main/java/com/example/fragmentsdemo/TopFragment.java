package com.example.fragmentsdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TopFragment extends Fragment {

    TextView textViewOne;
    TextView textViewTwo;
    TextView mainActivityText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_top, null);
        mainActivityText = getActivity().findViewById(R.id.textView_main);
        textViewOne = fragmentView.findViewById(R.id.textView_top_title);
        textViewTwo = fragmentView.findViewById(R.id.textView_top_info);
        Button button = fragmentView.findViewById(R.id.button_top);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewTwo.setText("CLICKED");
                mainActivityText.setText("Clicked from top fragment");
            }
        });

        return fragmentView;
    }
    // lifecycle methods are available!
    @Override
    public void onStart() {
        super.onStart();
    }
}