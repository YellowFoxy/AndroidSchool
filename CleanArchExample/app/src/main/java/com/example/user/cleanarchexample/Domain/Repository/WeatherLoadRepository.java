package com.example.user.cleanarchexample.Domain.Repository;

import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Domain.Models.WeatherWeak;
import com.example.user.cleanarchexample.Domain.Repository.WeatherService.WeatherServiceHelper;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Response;

public class WeatherLoadRepository implements WeatherRepository{

    @Override
    public List<ThisDay> getWeatherWeak(Calendar startDate, Calendar endDate) {

        long milliseconds = endDate.getTime().getTime() - startDate.getTime().getTime();
        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
        try {
            Response<WeatherWeak> responce = WeatherServiceHelper.getService().getWeakWeather(55.75396, 37.620393, days, false).execute();
            if (!responce.isSuccessful() || responce.body() == null)
                return null;
            return responce.body().getForecasts();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ThisDay getWeatherDay(ThisDay thisDay) {
        try {
            Response<WeatherWeak> responce = WeatherServiceHelper.getService().getDayHoursWeather(55.75396, 37.620393,7, true).execute();
            if (!responce.isSuccessful() || responce.body() == null)
                return null;

            ThisDay weatherDay = null;
            List<ThisDay> wwList = responce.body().getForecasts();
            for (int i = 0; i < wwList.size(); i++) {
                if (wwList.get(i).getDate().equals(thisDay.getDate())) {
                    weatherDay = wwList.get(i);
                    break;
                }
            }
            if (weatherDay == null)
                return null;

            return weatherDay;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
