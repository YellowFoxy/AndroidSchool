package com.example.user.contentproviderexamplesender.Provider;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.user.contentproviderexamplesender.Managers.DbManager;
import com.example.user.contentproviderexamplesender.Models.Note;

import java.util.ArrayList;
import java.util.List;

public class ConverUtils {
    public static final String ID="Id";
    public static final String CREATED_DATE_TIME="CreatedDateTime";
    public static final String HEADER="Header";
    public static final String TEXT="Text";

    public static Note convertValuesToNote(ContentValues values) {
        Note note = new Note(values.getAsString(HEADER), values.getAsString(TEXT));
        note.setCreatedDateTime(values.getAsString(CREATED_DATE_TIME));
        note.setId(values.getAsInteger(ID));
        return note;
    }

    public static ContentValues convertNoteToValues(Note note) {
        ContentValues cv = new ContentValues();
        cv.put(ID, note.getId());
        cv.put(CREATED_DATE_TIME, note.getCreatedDateTime());
        cv.put(HEADER, note.getHeader());
        cv.put(TEXT, note.getText());
        return cv;
    }

    public static List<Note> convertCursorToNotes(Cursor cursor){
        List<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()){
            Note note = DbManager.parseCursor(cursor);
            notes.add(note);
        }
        cursor.close();

        return notes;
    }
}
