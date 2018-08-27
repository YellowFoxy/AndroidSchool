package com.example.user.recyclerviewexample;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class HistoryIntentService extends IntentService {

    public static final String MY_HISTORY_SERVICE_ACTION = "ru.example.recyclerviewexample.RESPONSE";
    public static final String EXTRA_KEY_OUT = "historyIntentService_out";


    private ArrayList<HistoryCollectionItem> operations;
    private Calendar currDate;
    private boolean isWorking;


    public HistoryIntentService() {
        super("historyIntentService");
        operations = new ArrayList<>();

        Date newDate = new Date();
        currDate = Calendar.getInstance();
        currDate.setTime(newDate);
        currDate.add(Calendar.DATE, -100);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (isWorking)
            return;

        isWorking = true;
        new Thread(new Runnable() {
            @Override
            public void run() {

                Random random = new Random();
                while (true) {
                    for (int i = 0; i < 10; i++) {
                        HistoryCollectionItem operation = generateHistory(i, random.nextInt(9) + 1);
                        operations.add(operation);
                        sendBroadcastMessage(operation);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    currDate.add(Calendar.DATE, 1);

                }
            }
        }).start();
    }

    private HistoryCollectionItem generateHistory(int param, int secondParam) {

        if (param == 0) {
            HistoryCollectionSeparatorItem newOperation = new HistoryCollectionSeparatorItem(currDate.getTime());
            return newOperation;
        }

        if (param % 2 == 0) {
            HistoryCollectionItemSuccess newOperation = new HistoryCollectionItemSuccess(currDate.getTime());
            return newOperation;
        }

        if (param % 2 == 1) {
            HistoryCollectionItemFail newOperation = new HistoryCollectionItemFail(currDate.getTime());
            switch (secondParam % 9) {
                case 0:
                    newOperation.setFailReason("Неизвестно");
                    break;
                case 1:
                    newOperation.setFailReason("Ошибка сервера");
                    break;
                case 2:
                    newOperation.setFailReason("Ошибка приложения");
                    break;
                case 3:
                    newOperation.setFailReason("Недостаточно данных");
                    break;
                case 4:
                    newOperation.setFailReason("Вышло время");
                    break;
                case 5:
                    newOperation.setFailReason("Ошибка");
                    break;
                case 6:
                    newOperation.setFailReason("Неверные данные");
                    break;
                case 7:
                    newOperation.setFailReason("Невозможно выполнить");
                    break;
                case 8:
                    newOperation.setFailReason("Сервер не отвечает");
                    break;
            }
            return newOperation;
        }

        return new HistoryCollectionSeparatorItem(new Date());
    }

    private void sendBroadcastMessage(HistoryCollectionItem responseItem) {
        Intent responseIntent = new Intent();
        responseIntent.setAction(MY_HISTORY_SERVICE_ACTION);
        responseIntent.putExtra(EXTRA_KEY_OUT, responseItem);
        sendBroadcast(responseIntent);
    }

    public static final Intent newIntent(Context context) {
        Intent newIntent = new Intent(context, HistoryIntentService.class);
        return newIntent;
    }
}
