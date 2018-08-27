package com.example.user.cleanarchexample.Presentations.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.cleanarchexample.Domain.Executor.ThreadExecutor;
import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Domain.Repository.WeatherLoadRepository;
import com.example.user.cleanarchexample.Presentations.Presenter.DayWeatherPresenter;
import com.example.user.cleanarchexample.Presentations.Recycler.OneDayWeather.OneDayWeatherAdapter;
import com.example.user.cleanarchexample.R;
import com.example.user.cleanarchexample.Threading.MainThreadImpl;

public class DayWeatherActivity extends AppCompatActivity {

    private static String SELECTED_DAY_PUT_INTENT_KEY = "selected_day";
    private DayWeatherPresenter mDayWeatherPresenter;

    private RecyclerView weatherDayRecyclerView;
    private OneDayWeatherAdapter mDayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_weather);
        initActivity();
        initViews();
    }

    public static Intent newIntent(ThisDay selectedDay, Context context) {
        Intent intent = new Intent(context, DayWeatherActivity.class);
        intent.putExtra(SELECTED_DAY_PUT_INTENT_KEY, selectedDay);
        return intent;
    }

    public void setDayInform(ThisDay day) {
        if (mDayAdapter == null)
            return;
        mDayAdapter.setData(day.getWeatherHours());
    }

    private void initActivity() {
        ThisDay selectedDay = (ThisDay) getIntent().getSerializableExtra(SELECTED_DAY_PUT_INTENT_KEY);
        if (selectedDay == null) {
            finish();
            return;
        }
        setTitle(selectedDay.getDate());

        mDayWeatherPresenter = new DayWeatherPresenter(
                new WeatherLoadRepository(),
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                selectedDay);
        mDayWeatherPresenter.attachView(this);
    }

    private void initViews() {
        weatherDayRecyclerView = findViewById(R.id.weatherDayRecyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        weatherDayRecyclerView.setLayoutManager(lm);

        mDayAdapter = new OneDayWeatherAdapter();
        weatherDayRecyclerView.setAdapter(mDayAdapter);
    }
}
