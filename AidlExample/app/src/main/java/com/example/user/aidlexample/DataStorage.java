package com.example.user.aidlexample;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStorage {
    public static final String PREF_NAME = "PREF";
    private static final String DATA_KEY = "SOME_DATA";

    private Context context;

    public DataStorage(Context context) {
        this.context = context;
    }

    public String getData() {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(DATA_KEY, "Data is empty, sorry!");
    }

    public void setData(String data) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(DATA_KEY, data);
        editor.commit();
    }
}
