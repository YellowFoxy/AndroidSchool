package com.example.user.cleanarchexample.Presentations.Presenter;

import com.example.user.cleanarchexample.Domain.Executor.Executor;
import com.example.user.cleanarchexample.Domain.Executor.MainThread;
import com.example.user.cleanarchexample.Domain.Iteractors.Implementation.WeatherInteractorImpl;
import com.example.user.cleanarchexample.Domain.Iteractors.WeatherInteractor;
import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Domain.Repository.WeatherLoadRepository;
import com.example.user.cleanarchexample.Presentations.Activity.WeatherListActivity;

import java.util.Calendar;
import java.util.List;

public class WeatherListPresenter  implements WeatherInteractor.Callback{
    private WeatherLoadRepository weatherRepository;
    private Executor executor;
    private MainThread mainThread;

    private WeatherListActivity view;
    private ThisDay startDay;
    private ThisDay endDay;

    public WeatherListPresenter(WeatherLoadRepository weatherRepository,
                                Executor executor,
                                MainThread mainThread) {
        this.weatherRepository = weatherRepository;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    public void attachView(WeatherListActivity view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public void setStartDay(Calendar day) {
        startDay = new ThisDay(day);
        dateChanged();
    }

    public void setEndDay(Calendar day) {
        endDay = new ThisDay(day);
        dateChanged();
    }

    private void dateChanged() {
        if (startDay == null || endDay == null) {
            return;
        }
        if (endDay.getThisDate().before(startDay.getThisDate())) {
            return;
        }

        loadDaysWeather();
    }

    private void loadDaysWeather(){
        WeatherInteractorImpl interactor = new WeatherInteractorImpl(
                executor,
                mainThread,
                this,
                weatherRepository
        );

        interactor.setStartDay(startDay.getThisDate());
        interactor.setEndDay(endDay.getThisDate());
        interactor.execute();
    }

    @Override
    public void onWeatherLoaded(List<ThisDay> days) {
        if(days!=null && days.size()>0){
            view.implementDaysCollection(days);
        }
    }
}
