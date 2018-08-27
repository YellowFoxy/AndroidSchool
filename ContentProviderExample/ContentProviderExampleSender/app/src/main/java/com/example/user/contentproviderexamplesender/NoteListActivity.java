package com.example.user.contentproviderexamplesender;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.user.contentproviderexamplesender.Managers.DbManager;
import com.example.user.contentproviderexamplesender.Models.Note;
import com.example.user.contentproviderexamplesender.NoteList.NoteAdapter;
import com.example.user.contentproviderexamplesender.NoteList.NoteViewHolder;
import com.example.user.contentproviderexamplesender.Provider.ConverUtils;
import com.example.user.contentproviderexamplesender.Provider.NoteProvider;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity implements NoteViewHolder.OnItemSelectedListener {

    private Button styleButton;
    private Button addNoteButton;
    private RecyclerView noteListView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        setTitle("Список заметок");

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, NoteListActivity.class);
        return intent;
    }

    private void initViews() {
        styleButton = findViewById(R.id.styleButton);
        addNoteButton = findViewById(R.id.addNoteButton);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddNoteActivity.newIntent(v.getContext()));
            }
        });

        noteListView = findViewById(R.id.noteListView);
        if (noteListView == null)
            return;

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        noteListView.setLayoutManager(mLayoutManager);

        mListAdapter = new NoteAdapter(new ArrayList<Note>(), this);
        noteListView.setAdapter(mListAdapter);
    }

    private void loadNotes() {
        Cursor cursor = getContentResolver().query(NoteProvider.NOTE_CONTENT_URI, null, null,
                null, null);

        List<Note> notes = ConverUtils.convertCursorToNotes(cursor);
        ((NoteAdapter) mListAdapter).addData(notes);
    }

    @Override
    public void onItemSelected(Note item) {
        if (item == null)
            return;

        startActivity(SelectedNoteActivity.newIntent(this, item));
    }
}
