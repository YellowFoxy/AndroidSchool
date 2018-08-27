package com.example.user.datasourceexample;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.datasourceexample.Managers.DbManager;
import com.example.user.datasourceexample.Managers.ToastManager;
import com.example.user.datasourceexample.models.Note;

public class AddNoteActivity extends AppCompatActivity {

    private EditText headerEditText;
    private EditText noteBodyEditText;
    private Button saveButton;
    private DbManager dbManager;

    private Note newNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        setTitle("Добавление новой заметки");
        initViews();
        dbManager = new DbManager(this);
    }

    private void initViews() {
        headerEditText = findViewById(R.id.headerEditText);
        noteBodyEditText = findViewById(R.id.noteBodyEditText);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewNote();
            }
        });
    }

    private void saveNewNote() {
        String headerText = headerEditText.getText().toString();
        String body = noteBodyEditText.getText().toString();
        if (headerText == null || TextUtils.isEmpty(headerText)) {
            ToastManager.showToast(this, "Пожалуйтса, заполните заголовок заметки", null);
            return;
        }
        if (body == null || TextUtils.isEmpty(body)) {
            ToastManager.showToast(this, "Нельзя сохранить пустую заметку", null);
            return;
        }

        newNote = new Note(headerText, body);
        new Thread(new Runnable() {
            @Override
            public void run() {
                dbManager.addNewNote(newNote);
                handler.sendEmptyMessage(0);
            }
        }).run();
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, AddNoteActivity.class);
        return intent;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            finish();
        }
    };
}
