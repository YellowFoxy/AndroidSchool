package com.example.user.modelviewpatternexample.WeatherWeakRecyclerView;

import android.support.v7.widget.RecyclerView;

import com.example.user.modelviewpatternexample.Models.WeatherDay;
import com.example.user.modelviewpatternexample.ViewModels.WeakViewModel;
import com.example.user.modelviewpatternexample.databinding.WeatherLayoutBinding;

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private WeatherLayoutBinding binding;

    public WeatherViewHolder(WeatherLayoutBinding binding) {
        super(binding.view);
        this.binding = binding;
    }

    public void bindWeatherDay(WeakViewModel weak, WeatherDay day){
        binding.setDay(day);
        binding.setWeak(weak);
    }
}