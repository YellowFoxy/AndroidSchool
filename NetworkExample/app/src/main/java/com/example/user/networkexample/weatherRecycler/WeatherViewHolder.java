package com.example.user.networkexample.weatherRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.networkexample.R;
import com.example.user.networkexample.models.WeatherDay;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private WeatherDay currentDay;

    private TextView dateTextView;
    private TextView dayWeatherTextView;
    OnDaySelectedListener itemSelectedListener;

    public WeatherViewHolder(View itemView, OnDaySelectedListener itemSelectedListener) {
        super(itemView);
        this.itemSelectedListener = itemSelectedListener;
        initViews(itemView);
    }

    public void setWeatherDay(WeatherDay weatherDay) {
        currentDay = weatherDay;
        setDataToUI();
    }

    private void initViews(View view) {
        dateTextView = view.findViewById(R.id.dateTextView);
        dayWeatherTextView = view.findViewById(R.id.dayWeatherTextView);

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.selectedDay(currentDay);
            }
        });
        dayWeatherTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.selectedDay(currentDay);
            }
        });
    }

    private void setDataToUI() {
        if (currentDay == null) {
            dateTextView.setText("");
            dayWeatherTextView.setText("");
            return;
        }

        dateTextView.setText(currentDay.getDate());
        dayWeatherTextView.setText(currentDay.getMidleTempStr());
    }
}
