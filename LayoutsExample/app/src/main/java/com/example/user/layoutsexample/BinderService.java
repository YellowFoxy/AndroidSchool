package com.example.user.layoutsexample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BinderService extends Service {

    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_UNREGISTER_CLIENT = 2;
    static final int MSG_SET_VALUE_RELATIVE = 3;
    static final int MSG_SET_VALUE_CONSTRAINT = 4;

    private static final int MODE = Service.START_STICKY;

    private ArrayList<Messenger> serviceClients = new ArrayList<>();

    private Messenger serviceMessenger = new Messenger(new BinderService.IncomingHandler());

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "MyService started",
                Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                funkWork();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                funkWorkTwo();
            }
        }).start();
        return MODE;
    }

    private void funkWork() {
        while (true) {
            Bundle b = new Bundle();
            SimpleDateFormat sdf = new SimpleDateFormat("ss");
            String currentDateandTime = sdf.format(new Date());
            b.putString("value", "\"" + currentDateandTime + "\"");
            Message msg = Message.obtain(null, MSG_SET_VALUE_RELATIVE);
            msg.setData(b);
            sendMessage(msg);
        }
    }
    private void funkWorkTwo() {
        while (true) {
            for (int i = 0; i < 360; i++) {
                Bundle b = new Bundle();
                b.putInt("value", i);
                Message msg = Message.obtain(null, MSG_SET_VALUE_CONSTRAINT);
                msg.setData(b);
                sendMessage(msg);
            }
        }
    }

    private void sendMessage(Message msg){
        for (int i = 0; i < serviceClients.size(); i++) {
            try {
                serviceClients.get(i).send(msg);
            } catch (RemoteException e) {
                serviceClients.remove(i);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Someone bind to MyService",
                Toast.LENGTH_SHORT).show();
        return serviceMessenger.getBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "MyService canceled",
                Toast.LENGTH_SHORT).show();
    }


    public class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    serviceClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    serviceClients.remove(msg.replyTo);
                    break;
            }
        }
    }

    public static final Intent newIntent(Context context) {
        Intent newIntent = new Intent(context, BinderService.class);
        return newIntent;
    }
}