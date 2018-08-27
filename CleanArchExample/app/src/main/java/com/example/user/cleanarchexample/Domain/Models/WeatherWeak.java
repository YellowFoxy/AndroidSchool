package com.example.user.cleanarchexample.Domain.Models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherWeak {
    @SerializedName("forecasts")
    private List<ThisDay> forecasts;


    @NonNull
    public List<ThisDay> getForecasts() {
        if (forecasts == null)
            forecasts = new ArrayList<>();

        return forecasts;
    }

    public void setForecasts(List<ThisDay> days) {
        forecasts = days;
    }
}
