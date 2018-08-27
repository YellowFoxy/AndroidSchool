package com.example.user.paginationexample;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class ImageLoaderApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Fresco.initialize(this);
    }
}
