package com.example.user.fragmentexample;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyIntentService extends IntentService {

    public static final String MESSAGE_KEY = "intentService_message_key";
    public static final String MY_INTENT_SERVICE_ACTION = "ru.example.fragmentexample.RESPONSE";
    public static final String EXTRA_KEY_OUT = "intentService_out";

    public MyIntentService() {
        super("myIntentService");

    }

    private void funkWork() {
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateAndTime = sdf.format(new Date());
            sendBroadcastMessage(currentDateAndTime);
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                funkWork();
            }
        }).start();
    }

    private void sendBroadcastMessage(String value) {
        Intent responseIntent = new Intent();
        responseIntent.setAction(MY_INTENT_SERVICE_ACTION);
        responseIntent.putExtra(EXTRA_KEY_OUT, value);

        sendBroadcast(responseIntent);
    }

    public static final Intent newIntent(Context context) {
        Intent newIntent = new Intent(context, MyIntentService.class);
        return newIntent;
    }
}
