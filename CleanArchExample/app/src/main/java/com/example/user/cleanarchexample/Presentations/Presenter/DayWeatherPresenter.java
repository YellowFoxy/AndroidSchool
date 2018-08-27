package com.example.user.cleanarchexample.Presentations.Presenter;

import com.example.user.cleanarchexample.Domain.Executor.Executor;
import com.example.user.cleanarchexample.Domain.Executor.MainThread;
import com.example.user.cleanarchexample.Domain.Iteractors.DayInteractor;
import com.example.user.cleanarchexample.Domain.Iteractors.Implementation.DayInteractorImpl;
import com.example.user.cleanarchexample.Domain.Iteractors.Implementation.WeatherInteractorImpl;
import com.example.user.cleanarchexample.Domain.Iteractors.WeatherInteractor;
import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Domain.Repository.WeatherLoadRepository;
import com.example.user.cleanarchexample.Presentations.Activity.DayWeatherActivity;

public class DayWeatherPresenter implements DayInteractor.Callback{

    private ThisDay selectedDay;
    private WeatherLoadRepository weatherRepository;
    private Executor executor;
    private MainThread mainThread;

    private DayWeatherActivity view;

    public DayWeatherPresenter(WeatherLoadRepository weatherRepository,
                               Executor executor, MainThread mainThread,
                               ThisDay selectedDay) {
        this.weatherRepository = weatherRepository;
        this.executor = executor;
        this.mainThread = mainThread;
        this.selectedDay = selectedDay;
    }

    public void attachView(DayWeatherActivity view) {
        this.view = view;
        loadDayWeather();
    }

    public void detachView() {
        this.view = null;
    }

    private void loadDayWeather() {
        DayInteractorImpl interactor = new DayInteractorImpl(
                executor,
                mainThread,
                this,
                weatherRepository
        );

        interactor.setDay(selectedDay);
        interactor.execute();
    }

    @Override
    public void onWeatherLoaded(ThisDay day) {
        view.setDayInform(day);
    }
}
