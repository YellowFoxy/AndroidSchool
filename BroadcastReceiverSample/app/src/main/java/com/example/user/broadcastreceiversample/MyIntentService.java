package com.example.user.broadcastreceiversample;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

public class MyIntentService extends IntentService {

    public static final String MESSAGE_KEY = "intentService_message_key";
    public static final String MY_INTENT_SERVICE_ACTION = "ru.example.broadcastreceiversample.RESPONSE";
    public static final String EXTRA_KEY_OUT = "intentService_out";

    private MySingleton singletonManager = MySingleton.getInstance();

    public MyIntentService() {
        super("myIntentService");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String result = intent.getStringExtra(MESSAGE_KEY);
        singletonManager.changeState(result);
        sendBroadcastMessage();
    }

    private void sendBroadcastMessage() {
        Intent responseIntent = new Intent();
        responseIntent.setAction(MY_INTENT_SERVICE_ACTION);

        responseIntent.putExtra(EXTRA_KEY_OUT, singletonManager.getState());
        sendBroadcast(responseIntent);
    }

    public static final Intent getIntentForSend(Context context, String message){
        Intent intent = newIntent(context);
        intent.putExtra(MESSAGE_KEY,message);
        return intent;
    }

    public static final Intent newIntent(Context context) {
        Intent newIntent = new Intent(context, MyIntentService.class);
        return newIntent;
    }
}
