package com.example.user.modelviewpatternexample.Models;

import android.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherHour extends BaseObservable implements Serializable {

    @SerializedName("hour")
    private int mHour;
    @SerializedName("temp")
    private double mTemp;
    @SerializedName("feels_like")
    private double mFeelsLike;
    @SerializedName("wind_speed")
    private double mWindSpeed;

    public int getHour(){
        return mHour;
    }
    public void setHour(int hour) {
        mHour = hour;
    }


    public double getTemp(){
        return mTemp;
    }
    public void setTemp(double temp) {
        mTemp = temp;
    }


    public double getFeelsLike(){
        return mFeelsLike;
    }
    public void setFeelsLike(double feelsLike) {
        mFeelsLike = feelsLike;
    }


    public double getWindSpeed(){
        return mWindSpeed;
    }
    public void setWindSpeed(double windSpeed) {
        mWindSpeed = windSpeed;
    }
}

