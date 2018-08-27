package com.example.user.modelviewpatternexample.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.user.modelviewpatternexample.Managers.ToastManager;
import com.example.user.modelviewpatternexample.Models.WeatherDay;
import com.example.user.modelviewpatternexample.Models.WeatherWeak;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Response;

public class WeatherService extends IntentService {

    public static final String LOAD_WEAK_SERVICE_ACTION = "ru.example.LOADWEAK";
    public static final String NEED_LOAD_WEAK_MESSAGE = "need_load_weak_message";
    public static final String LOADED_WEAK_OUT_KEY = "loaded_weak_out";


    public static final String LOAD_DAY_SERVICE_ACTION = "ru.example.LOADDAY";
    public static final String SELECTED_DAY_MESSAGE = "selected_day_message";
    public static final String LOADED_DAY_OUT_KEY = "loaded_day";

    public WeatherService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent.getBooleanExtra(NEED_LOAD_WEAK_MESSAGE, false)) {
            loadWeakWeather();
            return;
        }

        WeatherDay selectedDay = (WeatherDay) intent.getSerializableExtra(SELECTED_DAY_MESSAGE);
        if (selectedDay == null)
            return;
        loadDayWeather(selectedDay);
    }

    private void loadWeakWeather() {
        try {
            Response<WeatherWeak> result = WeatherServiceHelper.getService().getWeakWeather(55.75396, 37.620393, 7, false).execute();
            if (result == null || !result.isSuccessful()) {
                ToastManager.showToast(getApplicationContext(), "К сожалению, не удалось загрузить данные.");
                return;
            }
            sendLoadedWeakBroadcastMessage(result.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDayWeather(@NonNull WeatherDay selectedDay) {
        try {
            Response<WeatherWeak> result = WeatherServiceHelper.getService().getDayHoursWeather(55.75396, 37.620393, 7, true).execute();
            if (result == null || !result.isSuccessful()) {
                ToastManager.showToast(getApplicationContext(), "К сожалению, не удалось загрузить данные.");
                return;
            }

            WeatherDay weatherDay = null;
            List<WeatherDay> wwList = result.body().getForecasts();
            for (int i = 0; i < wwList.size(); i++) {
                if (wwList.get(i).getDate().equals(selectedDay.getDate())) {
                    weatherDay = wwList.get(i);
                    break;
                }
            }
            if (weatherDay == null)
                return;

            sendLoadedDayBroadcastMessage(weatherDay);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final Intent newIntentForLoadWeak(Context context) {
        Intent intent = newIntent(context);
        intent.putExtra(NEED_LOAD_WEAK_MESSAGE, true);
        return intent;
    }

    public static final Intent newIntentForLoadDayWeather(Context context, WeatherDay day) {
        Intent intent = newIntent(context);
        intent.putExtra(SELECTED_DAY_MESSAGE, day);
        return intent;
    }

    private static final Intent newIntent(Context context) {
        Intent newIntent = new Intent(context, WeatherService.class);
        return newIntent;
    }

    private void sendLoadedWeakBroadcastMessage(WeatherWeak weak) {
        Intent responseIntent = new Intent();
        responseIntent.setAction(LOAD_WEAK_SERVICE_ACTION);

        responseIntent.putExtra(LOADED_WEAK_OUT_KEY, weak);
        sendBroadcast(responseIntent);
    }

    private void sendLoadedDayBroadcastMessage(WeatherDay day) {
        Intent responseIntent = new Intent();
        responseIntent.setAction(LOAD_DAY_SERVICE_ACTION);

        responseIntent.putExtra(LOADED_DAY_OUT_KEY, day);
        sendBroadcast(responseIntent);
    }
}
