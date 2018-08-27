package com.example.user.datasourceexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.user.datasourceexample.models.Note;

public class SelectedNoteActivity extends AppCompatActivity implements ShowNote.OnFragmentInteractionListener {

    public static final String ACTION_EDIT_FRAGMENT_KEY = "edit";
    public static final String ACTION_EDIT_CANCEL_FRAGMENT_KEY = "edit_cancel";

    private static final String EXTRA_KEY = "NOTE";
    private static final String EXTRA_NOTE_ID_KEY = "NOTE_ID";

    private Note thisNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_note);

        thisNote = (Note) getIntent().getSerializableExtra(EXTRA_KEY);
        FragmentManager fragTransaction = getSupportFragmentManager();

        setTitle(thisNote.getHeader());
        fragTransaction.beginTransaction()
                .add(R.id.container, ShowNote.newInstance(thisNote))
                .commit();
    }

    public static Intent newIntent(Context context, Note note) {
        Intent intent = new Intent(context, SelectedNoteActivity.class);
        intent.putExtra(EXTRA_KEY, note);
        return intent;
    }

    public static Intent newIntent(Context context, int noteId) {
        Intent intent = new Intent(context, SelectedNoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID_KEY, noteId);
        return intent;
    }

    @Override
    public void onFragmentInteraction(String action) {
        switch (action) {
            case ACTION_EDIT_FRAGMENT_KEY:
                FragmentManager fragTransaction = getSupportFragmentManager();
                fragTransaction.beginTransaction()
                        .replace(R.id.container, EditNote.newInstance(thisNote))
                        .commit();
                break;
            case ACTION_EDIT_CANCEL_FRAGMENT_KEY:
                finish();
                break;
        }
    }
}
