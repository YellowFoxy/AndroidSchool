package com.example.user.cleanarchexample.Domain.Iteractors.Implementation;

import com.example.user.cleanarchexample.Domain.Executor.Executor;
import com.example.user.cleanarchexample.Domain.Executor.MainThread;
import com.example.user.cleanarchexample.Domain.Iteractors.Base.AbstractInteractor;
import com.example.user.cleanarchexample.Domain.Iteractors.DayInteractor;
import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Domain.Repository.WeatherLoadRepository;


public class DayInteractorImpl extends AbstractInteractor implements DayInteractor {
    private DayInteractor.Callback callback;
    private WeatherLoadRepository weatherLoadRepository;

    private ThisDay selectedDay;

    public DayInteractorImpl(
            Executor threadExecutor,
            MainThread mainThread,
            DayInteractor.Callback callback,
            WeatherLoadRepository weatherLoadRepository) {
        super(threadExecutor, mainThread);
        this.weatherLoadRepository = weatherLoadRepository;
        this.callback = callback;
    }

    public void setDay(ThisDay day) {
        selectedDay = day;
    }

    @Override
    public void run() {
        getWeatherForDay(selectedDay);
    }

    private void getWeatherForDay(ThisDay day) {
        ThisDay loadedDays = weatherLoadRepository.getWeatherDay(day);
        callback.onWeatherLoaded(loadedDays);
    }
}
