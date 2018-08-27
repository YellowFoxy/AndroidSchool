package com.example.user.networkexample.weatherRecycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.networkexample.R;
import com.example.user.networkexample.models.WeatherHour;

import java.util.ArrayList;
import java.util.List;

public class WeatherHourAdapter extends RecyclerView.Adapter<WeatherHourViewHolder> {

    private List<WeatherHour> mData;

    public WeatherHourAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public WeatherHourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_hour_layout, parent, false);
        return new WeatherHourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHourViewHolder holder, int position) {
        if (position >= mData.size())
            return;

        holder.setWeatherHour(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(List<WeatherHour> weatherDays) {
        mData = weatherDays != null ? weatherDays : new ArrayList<WeatherHour>();
        notifyDataSetChanged();
    }
}
