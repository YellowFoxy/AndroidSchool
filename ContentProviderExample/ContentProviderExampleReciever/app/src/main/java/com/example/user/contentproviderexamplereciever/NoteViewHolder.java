package com.example.user.contentproviderexamplereciever;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NoteViewHolder extends RecyclerView.ViewHolder{

    private TextView dateTextView;
    private TextView headerTextView;
    private Note note;
    OnItemSelectedListener itemSelectedListener;

    public NoteViewHolder(View itemView, OnItemSelectedListener listener) {
        super(itemView);
        initViews(itemView);
        itemSelectedListener = listener;
    }

    private void initViews(View view){
        dateTextView = view.findViewById(R.id.dateTextView);
        headerTextView = view.findViewById(R.id.headerTextView);
    }

    public void setNote(Note insertNote) {
        if(insertNote==null)
            return;

        updateNote(insertNote);
        setDate(note.getCreatedDateTime());
        setHeader(note.getHeader());

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.onItemSelected(note);
            }
        });
        headerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectedListener.onItemSelected(note);
            }
        });
    }

    public void updateNote(Note newNote) {
        note = newNote;
    }

    public void setDate(String date) {
        dateTextView.setText(date);
    }

    public void setHeader(String header){
        headerTextView.setText(header);
    }

    public interface OnItemSelectedListener {

        void onItemSelected(Note item);
    }

}
