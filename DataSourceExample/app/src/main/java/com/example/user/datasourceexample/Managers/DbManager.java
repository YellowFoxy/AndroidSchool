package com.example.user.datasourceexample.Managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.user.datasourceexample.models.Note;

import java.util.ArrayList;

public class DbManager {
    private DbHelper dbHelper;

    public DbManager(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void addNewNote(Note note) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues contentValues = getContentValues(note);
            addNoteInternal(db, contentValues);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
        } finally {
            if (db != null) {
                if (db.inTransaction())
                    db.endTransaction();

                db.close();
            }
        }
    }

    public ArrayList<Note> getNoteList() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("NOTE", null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                while (!cursor.isLast()) {
                    Note note = parseCursor(cursor);
                    notes.add(note);
                    cursor.moveToNext();
                }
            }

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.v("SQLiteException", e.getMessage());
        } finally {
            if (db != null) {
                if (db.inTransaction())
                    db.endTransaction();

                db.close();
            }
        }
        return notes;
    }

    public boolean editNote(Note note){
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues contentValues = getContentValues(note);
            editNoteInternal(db, contentValues);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            return false;
        } finally {
            if (db != null) {
                if (db.inTransaction())
                    db.endTransaction();

                db.close();
            }
        }
        return true;
    }

    private Note parseCursor(Cursor cursor) {
        String header = cursor.getString(cursor.getColumnIndex(DbHelper.NOTE_TABLE_HEADER_COLUMN));
        String text = cursor.getString(cursor.getColumnIndex(DbHelper.NOTE_TABLE_BODY_COLUMN));
        String date = cursor.getString(cursor.getColumnIndex(DbHelper.NOTE_TABLE_MYDATE_COLUMN));
        int id = cursor.getInt(cursor.getColumnIndex(DbHelper.NOTE_TABLE_ID_COLUMN));


        Note noteFromDb = new Note(header, text);
        noteFromDb.setId(id);
        noteFromDb.setCreatedDateTime(date);
        return noteFromDb;
    }

    private ContentValues getContentValues(Note note) {
        ContentValues cv = new ContentValues();
        if (note.getId() != 0) {
            cv.put(DbHelper.NOTE_TABLE_ID_COLUMN, note.getId());
        }

        cv.put(DbHelper.NOTE_TABLE_HEADER_COLUMN, note.getHeader());
        cv.put(DbHelper.NOTE_TABLE_BODY_COLUMN, note.getText());
        cv.put(DbHelper.NOTE_TABLE_MYDATE_COLUMN, note.getCreatedDateTime());
        return cv;
    }

    private void addNoteInternal(SQLiteDatabase db, ContentValues cv) {
        db.insert(DbHelper.NOTE_TABLE_NAME, null, cv);
    }

    private void editNoteInternal(SQLiteDatabase db, ContentValues cv) {
        db.replace(DbHelper.NOTE_TABLE_NAME, null, cv);
    }
}
