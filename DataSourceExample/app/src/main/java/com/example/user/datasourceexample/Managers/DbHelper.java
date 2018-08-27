package com.example.user.datasourceexample.Managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "note_database";
    public static final String NOTE_TABLE_NAME = "NOTE";

    public static final String NOTE_TABLE_ID_COLUMN = "id";
    public static final String NOTE_TABLE_MYDATE_COLUMN = "myDate";
    public static final String NOTE_TABLE_HEADER_COLUMN = "header";
    public static final String NOTE_TABLE_BODY_COLUMN = "body";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTable(SQLiteDatabase db) {
        String sqlIn = "create table ";
        sqlIn = sqlIn + NOTE_TABLE_NAME + "(" + NOTE_TABLE_ID_COLUMN + " integer primary key, ";
        sqlIn = sqlIn + NOTE_TABLE_MYDATE_COLUMN + " text, " + NOTE_TABLE_HEADER_COLUMN + " text, " + NOTE_TABLE_BODY_COLUMN + " text)";

        db.execSQL(sqlIn);
    }
}
