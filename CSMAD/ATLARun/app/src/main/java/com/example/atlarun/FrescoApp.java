package com.example.atlarun;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class FrescoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
