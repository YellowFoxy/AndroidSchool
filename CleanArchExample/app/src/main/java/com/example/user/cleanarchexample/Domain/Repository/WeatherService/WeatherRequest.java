package com.example.user.cleanarchexample.Domain.Repository.WeatherService;

import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Domain.Models.WeatherWeak;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherRequest {

    @Headers({"X-Yandex-API-Key:8ea54354-79c7-439e-aaca-70c7161a829a"})
    @GET("forecast")
    Call<WeatherWeak> getWeakWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("limit") int limit, @Query("hours") boolean hours);


    @Headers({"X-Yandex-API-Key:8ea54354-79c7-439e-aaca-70c7161a829a"})
    @GET("forecast")
    Call<WeatherWeak> getDayHoursWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("limit") int limit, @Query("hours") boolean hours);
}
