package com.example.user.cleanarchexample.Domain.Repository;

import com.example.user.cleanarchexample.Domain.Models.ThisDay;

import java.util.Calendar;
import java.util.List;

public interface WeatherRepository {
    List<ThisDay> getWeatherWeak(Calendar startDate, Calendar endDate);
    ThisDay getWeatherDay(ThisDay thisDay);
}
