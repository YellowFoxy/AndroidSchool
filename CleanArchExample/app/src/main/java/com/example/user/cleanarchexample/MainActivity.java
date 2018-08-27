package com.example.user.cleanarchexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.cleanarchexample.Presentations.Activity.WeatherListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(WeatherListActivity.newIntent(this));
    }
}
