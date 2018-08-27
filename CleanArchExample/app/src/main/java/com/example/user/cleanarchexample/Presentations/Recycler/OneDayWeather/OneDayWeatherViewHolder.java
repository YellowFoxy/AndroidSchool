package com.example.user.cleanarchexample.Presentations.Recycler.OneDayWeather;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.cleanarchexample.Domain.Models.WeatherHour;
import com.example.user.cleanarchexample.R;

public class OneDayWeatherViewHolder extends RecyclerView.ViewHolder {
    private WeatherHour currentHour;

    private TextView dateTextView;
    private TextView dayWeatherTextView;

    public OneDayWeatherViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    public void setWeatherHour(WeatherHour weatherHour) {
        currentHour = weatherHour;
        setDataToUI();
    }

    private void initViews(View view) {
        dateTextView = view.findViewById(R.id.dateTextView);
        dayWeatherTextView = view.findViewById(R.id.dayWeatherTextView);
    }

    private void setDataToUI() {
        if (currentHour == null) {
            dateTextView.setText("");
            dayWeatherTextView.setText("");
            return;
        }

        dateTextView.setText("Час: " + currentHour.getHour()+1);
        dayWeatherTextView.setText("Темперарута: " + currentHour.getTemp());
    }
}
