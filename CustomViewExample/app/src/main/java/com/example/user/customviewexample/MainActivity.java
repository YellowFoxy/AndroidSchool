package com.example.user.customviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RectangleCustomView rectangleCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        textView = findViewById(R.id.textView);
        rectangleCustomView = findViewById(R.id.rectangleCustomView);
        rectangleCustomView.setEventListener(new RectangleCustomView.FigureCenterChangeCallback() {

            @Override
            public void ChangedCenter(float x, float y) {
                textView.setText("X:" + x + " ;Y:" + y);
            }
        });
    }
}
