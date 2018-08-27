package com.example.user.datasourceexample;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.user.datasourceexample.Managers.DbManager;
import com.example.user.datasourceexample.models.Note;
import com.example.user.datasourceexample.noteList.NoteAdapter;
import com.example.user.datasourceexample.noteList.NoteViewHolder;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity implements NoteViewHolder.OnItemSelectedListener {

    private Button styleButton;
    private Button addNoteButton;
    private RecyclerView noteListView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mListAdapter;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        dbManager = new DbManager(this);

        setTitle("Список заметок");

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Note> notes = dbManager.getNoteList();

                Bundle bundle = new Bundle();
                bundle.putSerializable("Notes", notes);
                Message ms = new Message();
                ms.setData(bundle);
                handler.sendMessage(ms);
            }
        }).run();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();

            ((NoteAdapter) mListAdapter).addData((ArrayList<Note>) bundle.get("Notes"));
        }
    };

    @Override
    public void onItemSelected(Note item) {
        if (item == null)
            return;

        startActivity(SelectedNoteActivity.newIntent(this, item));
    }
}
