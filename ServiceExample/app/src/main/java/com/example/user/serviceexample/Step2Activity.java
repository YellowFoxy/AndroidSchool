package com.example.user.serviceexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.security.MessageDigest;

public class Step2Activity extends AppCompatActivity {


    private Messenger myService;
    private ServiceConnection serviceConnection;
    private TextView showedText;
    final Messenger mMessenger = new Messenger(new IncomingHandler());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        initializeElements();


        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myService = new Messenger(service);

                try {
                    Message msg = Message.obtain(null, MyService.MSG_REGISTER_CLIENT);
                    msg.replyTo = mMessenger;
                    myService.send(msg);
                } catch (RemoteException e) {

                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        bindService(MyService.newIntent(this), serviceConnection, BIND_AUTO_CREATE);
    }

    private void initializeElements()
    {
        showedText = findViewById(R.id.showedText);
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, Step2Activity.class);
        return intent;
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MyService.MSG_SET_VALUE)
                showedText.setText("Service Message: " + msg.getData().getString("value"));
        }
    }
}
