package com.example.user.contentproviderexamplesender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.contentproviderexamplesender.Managers.DbManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(NoteListActivity.newIntent(this));

    }
}
