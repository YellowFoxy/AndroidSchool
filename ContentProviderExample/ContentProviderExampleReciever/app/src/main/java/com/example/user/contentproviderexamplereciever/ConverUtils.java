package com.example.user.contentproviderexamplereciever;

import android.content.ContentValues;
import android.database.Cursor;


import java.util.ArrayList;
import java.util.List;

public  class ConverUtils {

    public static final String NOTE_TABLE_ID_COLUMN = "id";
    public static final String NOTE_TABLE_MYDATE_COLUMN = "myDate";
    public static final String NOTE_TABLE_HEADER_COLUMN = "header";
    public static final String NOTE_TABLE_BODY_COLUMN = "body";

    public static final String ID = "Id";
    public static final String CREATED_DATE_TIME = "CreatedDateTime";
    public static final String HEADER = "Header";
    public static final String TEXT = "Text";

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

    public static List<Note> convertCursorToNotes(Cursor cursor) {
        List<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            String header = cursor.getString(cursor.getColumnIndex(NOTE_TABLE_HEADER_COLUMN));
            String text = cursor.getString(cursor.getColumnIndex(NOTE_TABLE_BODY_COLUMN));
            String date = cursor.getString(cursor.getColumnIndex(NOTE_TABLE_MYDATE_COLUMN));
            int id = cursor.getInt(cursor.getColumnIndex(NOTE_TABLE_ID_COLUMN));

            Note noteFromDb = new Note(header, text);
            noteFromDb.setId(id);
            noteFromDb.setCreatedDateTime(date);
            notes.add(noteFromDb);
        }
        cursor.close();

        return notes;
    }
}
