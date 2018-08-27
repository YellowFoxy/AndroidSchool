package com.example.user.modelviewpatternexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.modelviewpatternexample.Models.WeatherDay;

import java.util.Calendar;

public class DayWeatherActivity extends AppCompatActivity {

    private static final String SELECTED_DAY_KEY = "selected_day_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_weather);

        outputSelectedDay();
    }

    private void outputSelectedDay() {
        WeatherDay day = (WeatherDay) getIntent().getSerializableExtra(SELECTED_DAY_KEY);
        if (day == null) {
            finish();
            return;
        }

    }

    public static final Intent newIntent(Context context, WeatherDay day) {
        Intent intent = new Intent(context, DayWeatherActivity.class);
        intent.putExtra(SELECTED_DAY_KEY, day);
        return intent;
    }
}
