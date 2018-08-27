package com.example.user.layoutsexample;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends IntentService {

    public static final String COLORS_SERVICE_ACTION = "ru.example.layoutsexample.COLORS_SERVICE_RESPONSE";
    public static final String TEXT_SERVICE_ACTION = "ru.example.layoutsexample.TEXT_SERVICE_RESPONSE";



    public static final String COLORS_SERVICE_OUT_ONE ="colors_service_out_one_key";
    public static final String COLORS_SERVICE_OUT_TWO ="colors_service_out_two_key";
    public static final String COLORS_SERVICE_OUT_THREE ="colors_service_out_three_key";

    public static final String TEXT_SERVICE_OUT_ONE ="text_service_out_one_key";
    public static final String TEXT_SERVICE_OUT_TWO ="text_service_out_two_key";
    public static final String TEXT_SERVICE_OUT_THREE ="text_service_out_three_key";


    private final int[] colors;

    public MyService () {
        super("MyService");
        colors = new int[3];
        colors[0] = Color.RED;
        colors[1] = Color.YELLOW;
        colors[2] = Color.GREEN;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 1; i <= colors.length; i++) {
                        workColorFunc(i);
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 1; i <= 15; i++) {
                        workTextFunc(String.valueOf(i));
                    }
                }
            }
        }).start();
    }

    private void workColorFunc(int i) {
        try {
            sendBroadcastColorMessage(colors[i-1], colors[i % 2], colors[i % 3]);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void workTextFunc(String val) {
        try {
            SimpleDateFormat seconds = new SimpleDateFormat("ss");
            String currentSeconds = seconds.format(new Date());


            sendBroadcastTextMessage(currentSeconds, String.valueOf(Integer.parseInt(val) % 3), val);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendBroadcastColorMessage(int colorOne, int colorTwo, int colorThree) {
        Intent responseIntent = new Intent();
        responseIntent.setAction(COLORS_SERVICE_ACTION);

        responseIntent.putExtra(COLORS_SERVICE_OUT_ONE, colorOne);
        responseIntent.putExtra(COLORS_SERVICE_OUT_TWO, colorTwo);
        responseIntent.putExtra(COLORS_SERVICE_OUT_THREE, colorThree);
        sendBroadcast(responseIntent);
    }

    private void sendBroadcastTextMessage(String one,String two, String three) {
        Intent responseIntent = new Intent();
        responseIntent.setAction(TEXT_SERVICE_ACTION);

        responseIntent.putExtra(TEXT_SERVICE_OUT_ONE, one);
        responseIntent.putExtra(TEXT_SERVICE_OUT_TWO, two);
        responseIntent.putExtra(TEXT_SERVICE_OUT_THREE, three);
        sendBroadcast(responseIntent);
    }

    public static final Intent newIntent(Context context) {
        Intent newIntent = new Intent(context, MyService.class);
        return newIntent;
    }
}
