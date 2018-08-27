package com.example.user.layoutsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(MyService.newIntent(this));
        startService(BinderService.newIntent(this));
    }

    public void onCenterButtonClickFromRelativeFragment() {

    }
}
