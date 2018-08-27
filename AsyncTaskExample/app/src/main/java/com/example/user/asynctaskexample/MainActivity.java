package com.example.user.asynctaskexample;

import android.app.Fragment;
import android.graphics.Color;
import android.support.constraint.Guideline;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Guideline guidelineHorizontal;
    private Guideline guidelineVertical;

    private ColorFragment colorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGuidelins();
        createViews();
    }

    private void createViews() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.testLinearLayout, ColorFragment.newInstance())
                .commit();

        //someView.setBackgroundColor(Color.RED);
    }

    private void initGuidelins(){
        //guidelineHorizontal = findViewById(R.id.guidelineHorizontal);
        //guidelineVertical = findViewById(R.id.guidelineVertical);
    }
}
