package com.example.user.contentproviderexamplesender.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.user.contentproviderexamplesender.Helpers.DbHelper;
import com.example.user.contentproviderexamplesender.Managers.DbManager;

public class NoteProvider extends ContentProvider {

    private DbManager dbManager;


    static final String AUTHORITY = "com.contentproviderexamplesender.provider.Notes";
    static final String NOTE_PATH = "notes";
    public static final Uri NOTE_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + NOTE_PATH);

    static final int URI_NOTES = 1;
    static final int URI_NOTE_ID = 2;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, NOTE_PATH, URI_NOTES);
        uriMatcher.addURI(AUTHORITY, NOTE_PATH + "/#", URI_NOTE_ID);
    }

    @Override
    public boolean onCreate() {
        dbManager = new DbManager(getContext());
        return dbManager != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DbHelper.NOTE_TABLE_NAME);

        int uriType = uriMatcher.match(uri);
        switch (uriType) {
            case URI_NOTES:
                break;
            case URI_NOTE_ID:
                queryBuilder.appendWhere(DbHelper.NOTE_TABLE_ID_COLUMN + "=" + uri.getLastPathSegment());
                break;
        }

        Cursor cursor = queryBuilder.query(dbManager.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int uriType = uriMatcher.match(uri);
        long id = 0;
        switch (uriType) {
            case URI_NOTES:
                id = dbManager.addNewNote(ConverUtils.convertValuesToNote(values));
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(DbHelper.NOTE_TABLE_ID_COLUMN + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
