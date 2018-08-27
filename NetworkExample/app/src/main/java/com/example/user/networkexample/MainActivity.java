package com.example.user.networkexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.networkexample.WeatherService.WeatherService;
import com.example.user.networkexample.models.WeatherDay;
import com.example.user.networkexample.models.WeatherWeak;
import com.example.user.networkexample.weatherRecycler.OnDaySelectedListener;
import com.example.user.networkexample.weatherRecycler.WeatherAdapter;

public class MainActivity extends AppCompatActivity implements OnDaySelectedListener {

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView weatherRecyclerView;
    private WeatherAdapter mWeatherAdapter;

    private WeatherWeakBroadcastReceiver myBroadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initializeReceiver();
        getWeakWeatherAsync();
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

    private void getWeakWeatherAsync() {
        swipeRefresh.setRefreshing(true);
        startService(WeatherService.getIntentForLoadWeak(this));
    }

    private void initView() {
        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                mWeatherAdapter.clean();
                getWeakWeatherAsync();
            }
        });
        weatherRecyclerView = findViewById(R.id.weatherRecyclerView);
        if (weatherRecyclerView == null)
            return;

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        weatherRecyclerView.setLayoutManager(lm);

        mWeatherAdapter = new WeatherAdapter(this);
        weatherRecyclerView.setAdapter(mWeatherAdapter);
    }

    private void initializeReceiver(){
        myBroadcastReceiver = new WeatherWeakBroadcastReceiver();
        intentFilter = new IntentFilter(WeatherService.LOAD_WEAK_SERVICE_ACTION);
    }

    private void addDataToView (WeatherWeak data) {
        if (mWeatherAdapter == null)
            return;
        mWeatherAdapter.setData(data.getForecasts());
    }

    @Override
    public void selectedDay(WeatherDay selectedDay) {
        startActivity(SelectedDayActivity.newIntent(this, selectedDay));
    }

    public class WeatherWeakBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            WeatherWeak result = (WeatherWeak)intent.getSerializableExtra(WeatherService.LOADED_WEAK_OUT_KEY);
            addDataToView(result);
            swipeRefresh.setRefreshing(false);
        }
    }
}
