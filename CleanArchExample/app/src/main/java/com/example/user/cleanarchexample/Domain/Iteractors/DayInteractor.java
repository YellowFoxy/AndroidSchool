package com.example.user.cleanarchexample.Domain.Iteractors;

import com.example.user.cleanarchexample.Domain.Iteractors.Base.Interactor;
import com.example.user.cleanarchexample.Domain.Models.ThisDay;

public interface DayInteractor extends Interactor {
    interface Callback {

        void onWeatherLoaded(ThisDay day);
    }
}
