package com.example.user.broadcastreceiversample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button myButtonOne;
    private Button myButtonTwo;
    private TextView myTextView;

    private MyBroadcastReceiver myBroadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeControls();

        initializeReceiver();
    }

    private void initializeControls() {
        myTextView = findViewById(R.id.myTextView);

        myButtonOne = findViewById(R.id.myButtonOne);
        myButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(MyIntentService.getIntentForSend(v.getContext(), MySingleton.NEXT));
            }
        });

        myButtonTwo = findViewById(R.id.myButtonTwo);
        myButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(MyIntentService.getIntentForSend(v.getContext(), MySingleton.BACK));
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastReceiver);
    }

    private void initializeReceiver(){
        myBroadcastReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter(MyIntentService.MY_INTENT_SERVICE_ACTION);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            SingletonState result = (SingletonState)intent.getSerializableExtra(MyIntentService.EXTRA_KEY_OUT);
            myTextView.setText(result.toString());
        }
    }
}
