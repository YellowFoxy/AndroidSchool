package com.example.user.cleanarchexample.Domain.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ThisDay implements Serializable {

    @SerializedName("date")
    private Date mDate;

    private Calendar thisDate;

    @SerializedName("parts")
    private DayParts mDayParts;

    @SerializedName("hours")
    private List<WeatherHour> mWeatherHours;


    public ThisDay(Calendar calendar) {
        thisDate = calendar;
        //sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru", "RU"));
    }

    public Calendar getThisDate() {
        if (thisDate == null)
            setThisDate(this.mDate);
        return thisDate;
    }

    public void setThisDate(Calendar calendar) {
        thisDate = calendar;
    }
    public void setThisDate(String date) {
        try {
            Calendar inst = Calendar.getInstance();
            inst.setTime(sdf.parse(date));
            thisDate = inst;
        } catch (ParseException e) {
            thisDate = Calendar.getInstance();
        }
    }

    public void setThisDate(Date date){
        Calendar inst = Calendar.getInstance();
        inst.setTime(date);
        thisDate = inst;
    }

    public String dateToString() {
        return getThisDate().get(Calendar.YEAR) + "/" + getThisDate().get(Calendar.MONTH) + "/" + getThisDate().get(Calendar.DAY_OF_MONTH);
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru", "RU"));

    private int id;

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
            setThisDate(date);
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
