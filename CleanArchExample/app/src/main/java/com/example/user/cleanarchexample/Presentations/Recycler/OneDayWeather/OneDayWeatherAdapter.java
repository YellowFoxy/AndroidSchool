package com.example.user.cleanarchexample.Presentations.Recycler.OneDayWeather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.cleanarchexample.Domain.Models.WeatherHour;
import com.example.user.cleanarchexample.R;

import java.util.ArrayList;
import java.util.List;

public class OneDayWeatherAdapter extends RecyclerView.Adapter<OneDayWeatherViewHolder> {

    private List<WeatherHour> mData;

    public OneDayWeatherAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public OneDayWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_hour_layout, parent, false);
        return new OneDayWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OneDayWeatherViewHolder holder, int position) {
        if (position >= mData.size())
            return;

        holder.setWeatherHour(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(List<WeatherHour> weatherHours) {
        mData = weatherHours != null ? weatherHours : new ArrayList<WeatherHour>();
        notifyDataSetChanged();
    }

    public void clean() {
        mData = new ArrayList<>();
        notifyDataSetChanged();
    }
}
