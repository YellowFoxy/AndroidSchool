package com.example.user.modelviewpatternexample.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.user.modelviewpatternexample.DayWeatherActivity;
import com.example.user.modelviewpatternexample.Models.WeatherDay;
import com.example.user.modelviewpatternexample.Models.WeatherWeak;
import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class WeakViewModel extends BaseObservable {
    private WeatherWeak weatherWeak;

    @Bindable
    public List<WeatherDay> getDays(){
        return weatherWeak.forecasts;
    }

    @Bindable
    public WeatherWeak getWeatherWeak() {
        return weatherWeak;
    }

    public void setWeatherWeak(WeatherWeak weatherWeak){
        this.weatherWeak=weatherWeak;
        notifyPropertyChanged(BR.weatherWeak);
        notifyPropertyChanged(BR.days);
    }

    public void OnDaySelected(WeatherDay selectedDay){
        //startActivity(DayWeatherActivity.newIntent( ,selectedDay));
    }
}
