package com.example.user.modelviewpatternexample.Models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherWeak extends BaseObservable implements Serializable {

    @SerializedName("forecasts")
    public List<WeatherDay> forecasts;

    @NonNull
    @Bindable
    public List<WeatherDay> getForecasts() {
        if (forecasts == null)
            forecasts = new ArrayList<>();

        return forecasts;
    }

    public void setForecasts(List<WeatherDay> days) {

        forecasts = days;
        notifyPropertyChanged(BR.forecasts);
    }
}

