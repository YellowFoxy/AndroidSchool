package com.example.user.networkexample.WeatherService;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.user.networkexample.Manager.ToastManager;
import com.example.user.networkexample.models.WeatherDay;
import com.example.user.networkexample.models.WeatherWeak;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherService extends IntentService {

    public static final String LOAD_WEAK_SERVICE_ACTION = "ru.example.LOADWEAK";
    public static final String LOAD_WEAK_MESSAGE_KEY = "load_weak_message_key";
    public static final String LOADED_WEAK_OUT_KEY="loaded_weak";

    public static final String LOAD_DAY_SERVICE_ACTION = "ru.example.LOADDAY";
    public static final String SELECTED_DAY_MESSAGE_KEY="selected_day_message_key";
    public static final String LOADED_DAY_OUT_KEY="loaded_day";

    public WeatherService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent.getBooleanExtra(LOAD_WEAK_MESSAGE_KEY,false)) {
            loadWeakWeather();
            return;
        }
        WeatherDay selectedDay = (WeatherDay)intent.getSerializableExtra(SELECTED_DAY_MESSAGE_KEY);
        if(selectedDay!=null){
            loadSelectedDay(selectedDay);
            return;
        }
    }

    private void loadWeakWeather(){
        WeatherServiceHelper.getService().getWeakWeather(55.75396,37.620393,7,false).enqueue(new Callback<WeatherWeak>() {
            @Override
            public void onResponse(@NonNull Call<WeatherWeak> call, @NonNull Response<WeatherWeak> response) {
                if (!response.isSuccessful()) {
                    ToastManager.showToast(getApplicationContext(), "К сожалению, не удалось загрузить данные.", null);
                    return;
                }
                sendLoadedWeakBroadcastMessage(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WeatherWeak> call, @NonNull Throwable t) {
                ToastManager.showToast(getApplicationContext(), "К сожалению, не удалось загрузить данные.", null);
            }
        });
    }

    private WeatherDay tempSelectedDay;
    private void loadSelectedDay(WeatherDay selectedDay) {
        tempSelectedDay = selectedDay;
        WeatherServiceHelper.getService().getDayHoursWeather(55.75396, 37.620393, 7, true).enqueue(new Callback<WeatherWeak>() {

            @Override
            public void onResponse(@NonNull Call<WeatherWeak> call, @NonNull Response<WeatherWeak> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    ToastManager.showToast(getApplicationContext(), "К сожалению, не удалось загрузить данные.", null);
                    return;
                }

                if (tempSelectedDay == null)
                    return;

                WeatherDay weatherDay = null;
                List<WeatherDay> wwList = response.body().getForecasts();
                for (int i = 0; i < wwList.size(); i++) {
                    if (wwList.get(i).getDate().equals(tempSelectedDay.getDate())) {
                        weatherDay = wwList.get(i);
                        break;
                    }
                }
                if (weatherDay == null)
                    return;

                sendLoadedDayBroadcastMessage(weatherDay);
            }

            @Override
            public void onFailure(@NonNull Call<WeatherWeak> call, @NonNull Throwable t) {
                ToastManager.showToast(getApplicationContext(), "К сожалению, не удалось загрузить данные.", null);
            }
        });
    }

    public static final Intent getIntentForLoadWeak(Context context) {
        Intent intent = newIntent(context);
        intent.putExtra(LOAD_WEAK_MESSAGE_KEY,true);
        return intent;
    }

    public static final Intent getIntentForLoadDay(Context context, WeatherDay day) {
        Intent intent = newIntent(context);
        intent.putExtra(SELECTED_DAY_MESSAGE_KEY, day);
        return intent;
    }

    public static final Intent newIntent(Context context) {
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
