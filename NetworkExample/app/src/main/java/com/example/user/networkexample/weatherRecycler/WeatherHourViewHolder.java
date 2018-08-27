package com.example.user.networkexample.weatherRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.networkexample.R;
import com.example.user.networkexample.models.WeatherHour;

public class WeatherHourViewHolder extends RecyclerView.ViewHolder {

    private WeatherHour currentHour;

    private TextView dateTextView;
    private TextView dayWeatherTextView;

    public WeatherHourViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }
    public void setWeatherHour(WeatherHour weatherHour) {
        currentHour = weatherHour;
        setDataToUI();
    }

    private void initViews(View view){
        dateTextView = view.findViewById(R.id.dateTextView);
        dayWeatherTextView = view.findViewById(R.id.dayWeatherTextView);
    }

    private void setDataToUI() {
        if (currentHour == null) {
            dateTextView.setText("");
            dayWeatherTextView.setText("");
            return;
        }

        dateTextView.setText("Час: " + currentHour.getHour());
        dayWeatherTextView.setText("Темперарута: " + currentHour.getTemp());
    }
}
