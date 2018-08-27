package com.example.user.asynctaskexample;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Random;

public class ColorLoader extends AsyncTaskLoader<Integer> {


    public ColorLoader(Context context) {
        super(context);
    }

    @Override
    public Integer loadInBackground() {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
