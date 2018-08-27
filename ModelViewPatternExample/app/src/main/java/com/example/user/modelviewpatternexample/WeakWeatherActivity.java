package com.example.user.modelviewpatternexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.user.modelviewpatternexample.Models.WeatherWeak;
import com.example.user.modelviewpatternexample.Services.WeatherService;
import com.example.user.modelviewpatternexample.ViewModels.WeakViewModel;
import com.example.user.modelviewpatternexample.WeatherWeakRecyclerView.WeatherAdapter;
import com.example.user.modelviewpatternexample.databinding.ActivityWeakWeatherBinding;

public class WeakWeatherActivity extends AppCompatActivity {

    private WeakViewModel weakViewModel;

    private WeatherWeakBroadcastReceiver myBroadcastReceiver;
    private IntentFilter intentFilter;
    private WeatherAdapter mWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeReceiver();
        setTitle("Недельный прогноз погоды");
        weakViewModel = new WeakViewModel();
        ActivityWeakWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weak_weather);
        binding.setViewModel(weakViewModel);
        weakViewModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.days)
                    mWeatherAdapter.setData(weakViewModel.getDays());
            }
        });

        initRecycler(binding);

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

    private void initRecycler(ActivityWeakWeatherBinding binding){
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.weatherRecyclerView.setLayoutManager(lm);

        mWeatherAdapter = new WeatherAdapter(weakViewModel);
        binding.weatherRecyclerView.setAdapter(mWeatherAdapter);
    }

    private void getWeakWeatherAsync() {
        startService(WeatherService.newIntentForLoadWeak(this));
    }

    private void initializeReceiver(){
        myBroadcastReceiver = new WeatherWeakBroadcastReceiver();
        intentFilter = new IntentFilter(WeatherService.LOAD_WEAK_SERVICE_ACTION);
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, WeakWeatherActivity.class);
        return intent;
    }

    public class WeatherWeakBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            WeatherWeak result = (WeatherWeak)intent.getSerializableExtra(WeatherService.LOADED_WEAK_OUT_KEY);
            weakViewModel.setWeatherWeak(result);
        }
    }
}
