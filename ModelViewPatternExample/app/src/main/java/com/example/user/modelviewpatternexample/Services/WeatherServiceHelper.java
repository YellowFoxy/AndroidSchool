package com.example.user.modelviewpatternexample.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherServiceHelper {

    private static final String API_VERSION = "v1/";
    private static final String YANDEX_URI = "https://api.weather.yandex.ru/" + API_VERSION;

    private static WeatherRequest instance;

    private WeatherServiceHelper(){
    }

    public static WeatherRequest getService() {
        if (instance != null)
            return instance;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YANDEX_URI)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return instance = retrofit.create(WeatherRequest.class);
    }
}