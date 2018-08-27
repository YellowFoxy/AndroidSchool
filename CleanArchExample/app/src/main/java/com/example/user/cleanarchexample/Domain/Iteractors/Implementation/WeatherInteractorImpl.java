package com.example.user.cleanarchexample.Domain.Iteractors.Implementation;


import com.example.user.cleanarchexample.Domain.Executor.Executor;
import com.example.user.cleanarchexample.Domain.Executor.MainThread;
import com.example.user.cleanarchexample.Domain.Iteractors.Base.AbstractInteractor;
import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Domain.Repository.WeatherLoadRepository;
import com.example.user.cleanarchexample.Domain.Iteractors.WeatherInteractor;

import java.util.Calendar;
import java.util.List;

public class WeatherInteractorImpl extends AbstractInteractor implements WeatherInteractor {

    private Callback callback;
    private WeatherLoadRepository weatherLoadRepository;

    private Calendar startDay;
    private Calendar endDay;

    public WeatherInteractorImpl(
            Executor threadExecutor,
            MainThread mainThread,
            Callback callback,
            WeatherLoadRepository weatherLoadRepository) {
        super(threadExecutor, mainThread);
        this.weatherLoadRepository = weatherLoadRepository;
        this.callback = callback;
    }

    public void setStartDay(Calendar day){
        startDay=day;
    }

    public void setEndDay(Calendar day){
        endDay=day;
    }

    @Override
    public void run() {
        getWeatherForDays(startDay, endDay);
    }

    private void getWeatherForDays(Calendar startDay, Calendar endDay) {
        List<ThisDay> loadedDays = weatherLoadRepository.getWeatherWeak(startDay, endDay);
        callback.onWeatherLoaded(loadedDays);
    }
}
