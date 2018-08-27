package com.example.user.modelviewpatternexample.WeatherWeakRecyclerView;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.modelviewpatternexample.Models.WeatherDay;
import com.example.user.modelviewpatternexample.R;
import com.example.user.modelviewpatternexample.ViewModels.WeakViewModel;
import com.example.user.modelviewpatternexample.databinding.WeatherLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private List<WeatherDay> mData;
    private WeakViewModel weak;

    public WeatherAdapter(WeakViewModel weak) {
        mData = new ArrayList<>();
        this.weak = weak;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.weather_layout,
                parent,
                false);
        return new WeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        if (position >= mData.size())
            return;

        holder.bindWeatherDay(weak, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(List<WeatherDay> weatherDays) {
        mData = weatherDays != null ? weatherDays : new ArrayList<WeatherDay>();
        notifyDataSetChanged();
    }

    public void clean() {
        mData = new ArrayList<>();
        notifyDataSetChanged();
    }
}