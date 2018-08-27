package com.example.user.cleanarchexample.Domain.Iteractors;

import com.example.user.cleanarchexample.Domain.Iteractors.Base.Interactor;
import com.example.user.cleanarchexample.Domain.Models.ThisDay;

import java.util.List;


public interface WeatherInteractor extends Interactor {
    interface Callback {

        void onWeatherLoaded(List<ThisDay> days);
    }
}
