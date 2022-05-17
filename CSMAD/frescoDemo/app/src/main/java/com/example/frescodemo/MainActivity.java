package com.example.frescodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.frescodemo.databinding.ActivityMainBinding;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

public class MainActivity extends AppCompatActivity {

    private static ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.cl);

        binding.drawee.setController(
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(ImageRequest.fromUri("https://media.giphy.com/media/rI1oTB9f3RAuGsiMJ1/giphy.gif"))
                        .setAutoPlayAnimations(true)
                        .build());

    }
}