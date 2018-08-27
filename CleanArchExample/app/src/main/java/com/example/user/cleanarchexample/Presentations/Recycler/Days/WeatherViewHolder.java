package com.example.user.cleanarchexample.Presentations.Recycler.Days;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Presentations.Recycler.OnDaySelectedListener;
import com.example.user.cleanarchexample.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private ThisDay currentDay;

    private TextView dateTextView;
    private TextView tempTextView;
    OnDaySelectedListener itemSelectedListener;

    public WeatherViewHolder(View itemView, OnDaySelectedListener itemSelectedListener) {
        super(itemView);
        this.itemSelectedListener = itemSelectedListener;
        initViews(itemView);
    }

    public void setWeatherDay(ThisDay weatherDay) {
        currentDay = weatherDay;
        setDataToUI();
    }

    private void initViews(View view) {
        dateTextView = view.findViewById(R.id.dateTextView);
        tempTextView = view.findViewById(R.id.tempTextView);

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.selectedDay(currentDay);
            }
        });
        tempTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.selectedDay(currentDay);
            }
        });
    }

    private void setDataToUI() {
        if (currentDay == null) {
            dateTextView.setText("");
            tempTextView.setText("");
            return;
        }

        dateTextView.setText(currentDay.dateToString());
        //tempTextView.setText(currentDay.getMidleTempStr());
    }
}
