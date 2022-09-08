package com.example.fragmentsdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BotFragment extends Fragment {

    TextView textViewOne;
    TextView textViewTwo;
    ReceiveString receiveString;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_bot, null);
        textViewOne = fragmentView.findViewById(R.id.textView_bot_title);
        textViewTwo = fragmentView.findViewById(R.id.textView_bot_info);
        Button button = fragmentView.findViewById(R.id.button_bot);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewTwo.setText("CLICKED");
                receiveString.receive("Button click sent from bot fragment");
            }
        });

        return fragmentView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // interface is one way to send info back to main / "this a more elegant solution" - schiff
        receiveString = (ReceiveString) context;

    }

    public interface ReceiveString{
        void receive(String str);
    }
}
