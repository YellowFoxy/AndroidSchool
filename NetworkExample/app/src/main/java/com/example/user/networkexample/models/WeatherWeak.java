package com.example.user.networkexample.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherWeak implements Serializable {

    @SerializedName("forecasts")
    private List<WeatherDay> forecasts;


    @NonNull
    public List<WeatherDay> getForecasts() {
        if (forecasts == null)
            forecasts = new ArrayList<>();

        return forecasts;
    }

    public void setForecasts(List<WeatherDay> days) {
        forecasts = days;
    }

}
