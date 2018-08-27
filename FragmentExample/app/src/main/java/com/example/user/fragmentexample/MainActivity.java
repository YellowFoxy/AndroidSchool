package com.example.user.fragmentexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    private MyBroadcastReceiver myBroadcastReceiver;
    private IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(MyIntentService.newIntent(this));
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
            String result = intent.getStringExtra(MyIntentService.EXTRA_KEY_OUT);
            FragmentOne fragment = FragmentOne.newInstance(result);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragmentOne, fragment).commitNow();
        }
    }
}
