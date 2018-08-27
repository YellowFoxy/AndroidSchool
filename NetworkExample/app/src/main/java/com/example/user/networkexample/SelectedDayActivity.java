package com.example.user.networkexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.networkexample.Manager.ToastManager;
import com.example.user.networkexample.WeatherService.WeatherService;
import com.example.user.networkexample.WeatherService.WeatherServiceHelper;
import com.example.user.networkexample.models.WeatherDay;
import com.example.user.networkexample.models.WeatherWeak;
import com.example.user.networkexample.weatherRecycler.WeatherHourAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedDayActivity extends AppCompatActivity {

    private static final String SELCTED_DAY_EXTRA_KEY = "selectedDayExtraKey";
    private WeatherDay mDay;
    private WeatherHourAdapter mAdapter;

    private RecyclerView weatherHoursRecyclerView;
    private WeatherDayBroadcastReceiver myBroadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_day);

        initViews();
        initializeReceiver();
        initActivity();
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
        myBroadcastReceiver = new WeatherDayBroadcastReceiver();
        intentFilter = new IntentFilter(WeatherService.LOAD_DAY_SERVICE_ACTION);
    }

    public static Intent newIntent(Context context, WeatherDay day) {
        Intent intent = new Intent(context, SelectedDayActivity.class);
        intent.putExtra(SELCTED_DAY_EXTRA_KEY, day);
        return intent;
    }

    private void initViews() {
        weatherHoursRecyclerView = findViewById(R.id.weatherHoursRecyclerView);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        weatherHoursRecyclerView.setLayoutManager(lm);

        mAdapter = new WeatherHourAdapter();
        weatherHoursRecyclerView.setAdapter(mAdapter);
    }

    private void initActivity() {
        WeatherDay day = (WeatherDay) getIntent().getSerializableExtra(SELCTED_DAY_EXTRA_KEY);
        if (day == null) {
            finish();
            return;
        }

        mDay = day;
        setTitle(day.getDate() + "; " + day.getMidleTempStr() + "C");
        loadDayWeather();
    }

    private void loadDayWeather() {
        startService(WeatherService.getIntentForLoadDay(this, mDay));
    }

    public class WeatherDayBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            WeatherDay result = (WeatherDay) intent.getSerializableExtra(WeatherService.LOADED_DAY_OUT_KEY);
            mAdapter.setData(result.getWeatherHours());
        }
    }
}
