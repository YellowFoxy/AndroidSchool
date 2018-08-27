package com.example.user.contentproviderexamplereciever;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final Uri NOTE_CONTENT_URI = Uri.parse("content://com.contentproviderexamplesender.provider.Notes/notes");

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mListAdapter;
    private RecyclerView noteListView;
    private ContentObserver mContentObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mContentObserver = new MyObserver(new Handler(Looper.getMainLooper()));
    }

    @Override
    public void onResume(){
        super.onResume();
        getContentResolver().registerContentObserver(NOTE_CONTENT_URI,true,mContentObserver);

        Cursor cursor = getContentResolver().query(NOTE_CONTENT_URI, null, null,
                null, null);

        List<Note> notes = ConverUtils.convertCursorToNotes(cursor);
        ((NoteAdapter) mListAdapter).addData(notes);
    }

    @Override
    public void onPause(){
        super.onPause();
        getContentResolver().unregisterContentObserver(mContentObserver);
    }

    private void changeUi() {
        Cursor cursor = getContentResolver().query(NOTE_CONTENT_URI, null, null,
                null, null);

        List<Note> notes = ConverUtils.convertCursorToNotes(cursor);
        ((NoteAdapter) mListAdapter).addData(notes);
    }

    private void initView() {
        noteListView = findViewById(R.id.noteListView);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        noteListView.setLayoutManager(mLayoutManager);

        mListAdapter = new NoteAdapter(new ArrayList<Note>());
        noteListView.setAdapter(mListAdapter);
    }

    private class MyObserver extends ContentObserver{

        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange){
            super.onChange(selfChange);
            changeUi();
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            onChange(selfChange);
        }
    }
}
