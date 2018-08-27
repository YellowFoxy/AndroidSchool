package com.example.user.cleanarchexample.Presentations.Recycler.Days;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Presentations.Recycler.OnDaySelectedListener;
import com.example.user.cleanarchexample.R;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> implements OnDaySelectedListener {

    private List<ThisDay> mData;
    private OnDaySelectedListener selectedListener;

    public WeatherAdapter(OnDaySelectedListener listener) {
        mData = new ArrayList<>();
        selectedListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_shoart_weather_layout, parent, false);
        return new WeatherViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        if (position >= mData.size())
            return;

        holder.setWeatherDay(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(List<ThisDay> weatherDays) {
        mData = weatherDays != null ? weatherDays : new ArrayList<ThisDay>();
        notifyDataSetChanged();
    }

    @Override
    public void selectedDay(ThisDay selectedDay) {
        selectedListener.selectedDay(selectedDay);
    }

    public void clean(){
        mData=new ArrayList<>();
        notifyDataSetChanged();
    }
}

