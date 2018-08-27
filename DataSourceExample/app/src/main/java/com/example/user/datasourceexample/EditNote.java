package com.example.user.datasourceexample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.datasourceexample.Managers.DbManager;
import com.example.user.datasourceexample.Managers.ToastManager;
import com.example.user.datasourceexample.models.Note;
import com.example.user.datasourceexample.noteList.NoteAdapter;

import java.util.ArrayList;
import java.util.Date;


public class EditNote extends Fragment {
    private static final String ARG_NOTE = "selected_note";

    private Note selectedNote;

    private EditText headerEditText;
    private EditText noteBodyEditText;
    private DbManager dbManager;

    private Button saveButton;


    private ShowNote.OnFragmentInteractionListener mListener;

    public EditNote() {
    }

    public static EditNote newInstance(Note note) {
        EditNote fragment = new EditNote();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DbManager(getContext());
        if (getArguments() != null) {
            selectedNote = (Note) getArguments().get(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view){
        headerEditText = view.findViewById(R.id.headerEditText);
        headerEditText.setText(selectedNote.getHeader());
        noteBodyEditText = view.findViewById(R.id.noteBodyEditText);
        noteBodyEditText.setText(selectedNote.getText());
        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick();
            }
        });
    }

    private void buttonClick() {
        String headerText = headerEditText.getText().toString();
        String body = noteBodyEditText.getText().toString();
        if (headerText == null || TextUtils.isEmpty(headerText)) {
            ToastManager.showToast(getContext(), "Пожалуйтса, заполните заголовок заметки", null);
            return;
        }
        if (body == null || TextUtils.isEmpty(body)) {
            ToastManager.showToast(getContext(), "Нельзя сохранить пустую заметку", null);
            return;
        }
        selectedNote.setHeader(headerText);
        selectedNote.setText(body);
        selectedNote.setCreatedDateTime(new Date());

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result = dbManager.editNote(selectedNote);
                Bundle bundle = new Bundle();
                bundle.putBoolean("result",result );
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }).run();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShowNote.OnFragmentInteractionListener) {
            mListener = (ShowNote.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Bundle bundle = msg.getData();
            if(!bundle.getBoolean("result")){
                ToastManager.showToast(getContext(), "Неполучилось сохранить заметку", null);
                return;
            }
            if (mListener != null) {
                ToastManager.showToast(getContext(), "Заметка успешно отредактирована", null);
                mListener.onFragmentInteraction(SelectedNoteActivity.ACTION_EDIT_CANCEL_FRAGMENT_KEY);
            }
        }
    };
}
