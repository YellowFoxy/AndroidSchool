package com.example.user.cleanarchexample.Presentations.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.user.cleanarchexample.Domain.Executor.ThreadExecutor;
import com.example.user.cleanarchexample.Domain.Models.ThisDay;
import com.example.user.cleanarchexample.Domain.Repository.WeatherLoadRepository;
import com.example.user.cleanarchexample.Presentations.Presenter.WeatherListPresenter;
import com.example.user.cleanarchexample.Presentations.Recycler.Days.WeatherAdapter;
import com.example.user.cleanarchexample.Presentations.Recycler.OnDaySelectedListener;
import com.example.user.cleanarchexample.R;
import com.example.user.cleanarchexample.Threading.MainThreadImpl;

import java.util.Calendar;
import java.util.List;


public class WeatherListActivity extends AppCompatActivity implements OnDaySelectedListener {

    private final String START_DATE_KEY = "start_day";
    private final String END_DATE_KEY = "end_day";

    private TextView startDateTextView;
    private TextView endDateTextView;
    private RecyclerView weatherWeakRecyclerView;

    private WeatherAdapter mWeatherAdapter;

    private WeatherListPresenter mWeatherListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        setTitle("Прогноз погоды");
        init();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, WeatherListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    public void implementDaysCollection(List<ThisDay> days) {
        mWeatherAdapter.setData(days);
    }

    private void init() {
        initViews();

        mWeatherListPresenter = new WeatherListPresenter(
                new WeatherLoadRepository(),
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance());
        mWeatherListPresenter.attachView(this);
    }

    private void initViews() {
        startDateTextView = findViewById(R.id.startDateTextView);
        startDateTextView.setText(toDayString());
        startDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(START_DATE_KEY);
            }
        });
        endDateTextView = findViewById(R.id.endDateTextView);
        endDateTextView.setText(toDayString());
        endDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(END_DATE_KEY);
            }
        });

        weatherWeakRecyclerView = findViewById(R.id.weatherWeakRecyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        weatherWeakRecyclerView.setLayoutManager(lm);

        mWeatherAdapter = new WeatherAdapter(this);
        weatherWeakRecyclerView.setAdapter(mWeatherAdapter);
    }

    private String toDayString() {
        return Calendar.getInstance().get(Calendar.YEAR) + " " + Calendar.getInstance().get(Calendar.MONTH) + " " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    private void setDate(String dateKey) {
        DateChangeListener listener = new DateChangeListener(dateKey);
        DatePickerDialog dialog = new DatePickerDialog(this, listener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void selectedDay(ThisDay selectedDay) {
        startActivity(DayWeatherActivity.newIntent(selectedDay, this));
    }

    class DateChangeListener implements DatePickerDialog.OnDateSetListener {

        private String dateKey;

        DateChangeListener(String dateKey) {
            this.dateKey = dateKey;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String value = year + "/" + month + "/" + dayOfMonth;
            switch (dateKey) {
                case START_DATE_KEY:
                    startDateTextView.setText(value);
                    Calendar instStart = Calendar.getInstance();
                    instStart.set(year,month,dayOfMonth);
                    mWeatherListPresenter.setStartDay(instStart);
                    break;
                case END_DATE_KEY:
                    endDateTextView.setText(value);
                    Calendar instEnd = Calendar.getInstance();
                    instEnd.set(year,month,dayOfMonth);
                    mWeatherListPresenter.setEndDay(instEnd);
                    break;
            }
        }
    }
}
