package com.example.user.serviceexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button step2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeButtons();
    }

    private void initializeButtons() {
        startButton = findViewById(R.id.startButton);
        step2Button = findViewById(R.id.step2Button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        step2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStep2();
            }
        });
    }

    private void startService() {
        startService(MyService.newIntent(this));
    }

    private void goToStep2() {
        startActivity(Step2Activity.newIntent(this));
    }
}
