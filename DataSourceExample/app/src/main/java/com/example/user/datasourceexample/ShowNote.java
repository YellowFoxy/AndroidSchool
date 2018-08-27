package com.example.user.datasourceexample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.datasourceexample.models.Note;


public class ShowNote extends Fragment {
    private static final String ARG_NOTE = "selected_note";

    private Note selectedNote;

    private Button editButton;
    private TextView headerTextView;
    private TextView bodyTextView;


    private OnFragmentInteractionListener mListener;

    public ShowNote() {

    }

    public static ShowNote newInstance(Note note) {
        ShowNote fragment = new ShowNote();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedNote = (Note) getArguments().get(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view){
        headerTextView = view.findViewById(R.id.headerTextView);
        headerTextView.setText(selectedNote.getHeader());
        bodyTextView = view.findViewById(R.id.bodyTextView);
        bodyTextView.setText(selectedNote.getText());
        editButton = view.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFragmentInteraction(SelectedNoteActivity.ACTION_EDIT_FRAGMENT_KEY);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String action);
    }
}
