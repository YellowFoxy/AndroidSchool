package com.example.user.networkexample.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeatherDay implements Serializable {

    private SimpleDateFormat sdf;

    private int id;

    @SerializedName("date")
    private Date mDate;

    @SerializedName("parts")
    private DayParts mDayParts;

    @SerializedName("hours")
    private List<WeatherHour> mWeatherHours;

    private double middleTemp;

    public WeatherDay() {
        sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru", "RU"));
        mDate = new Date();
    }

    public WeatherDay(Date date) {
        this();
        mDate = date;
    }

    public int getId(){
        return id;
    }
    public void setId(int newId) {
        id = newId;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setDate(String date) {
        try {
            mDate = sdf.parse(date);
        } catch (ParseException e) {
            mDate = new Date();
        }
    }

    public String getDate() {
        return sdf.format(mDate);
    }

    public DayParts getDayParts() {
        return mDayParts;
    }

    public void setDayParts(DayParts parts) {
        mDayParts = parts;
    }

    public List<WeatherHour> getWeatherHours() {
        return mWeatherHours;
    }
    public void setWeatherHours(List<WeatherHour> hours) {
        mWeatherHours = hours;
    }

    public double getMidleTemp() {
        double tempMiddleTemp;
        if (mDayParts != null && mDayParts.mDayShort != null)
            tempMiddleTemp = mDayParts.mDayShort.getTemp();
        else
            tempMiddleTemp = middleTemp;
        return middleTemp = tempMiddleTemp;
    }

    public String getMidleTempStr(){
        return String.valueOf(getMidleTemp());
    }
    public void setMiddleTemp(double newMiddleTemp) {
        middleTemp = newMiddleTemp;
    }

    class DayParts implements Serializable {

        @SerializedName("night")
        private DayPart mNight;
        @SerializedName("morning")
        private DayPart mMorning;
        @SerializedName("day")
        private DayPart mDay;
        @SerializedName("evening")
        private DayPart mEvening;
        @SerializedName("day_short")
        private DayPart mDayShort;
        @SerializedName("night_short")
        private DayPart mNightShort;

        public void setNight(DayPart dayPart) {
            mNight = dayPart;
        }

        public DayPart getNight() {
            return mNight;
        }

        public void setMorning(DayPart dayPart) {
            mMorning = dayPart;
        }

        public DayPart getMorning() {
            return mMorning;
        }

        public void setDay(DayPart dayPart) {
            mDay = dayPart;
        }

        public DayPart getDay() {
            return mDay;
        }

        public void setEvening(DayPart dayPart) {
            mEvening = dayPart;
        }

        public DayPart getEvening() {
            return mEvening;
        }

        public void setDayShort(DayPart dayPart) {
            mDayShort = dayPart;
        }

        public DayPart getDayShort() {
            return mDayShort;
        }

        public void setNightShort(DayPart dayPart) {
            mNightShort = dayPart;
        }

        public DayPart getNightShort() {
            return mNightShort;
        }


        class DayPart implements Serializable{

            @SerializedName("temp")
            private double mTemp;

            public double getTemp() {
                return mTemp;
            }

            public void setTemp(double temp) {
                mTemp = temp;
            }
        }
    }
}
