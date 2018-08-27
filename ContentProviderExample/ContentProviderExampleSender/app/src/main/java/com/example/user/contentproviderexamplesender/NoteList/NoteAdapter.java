package com.example.user.contentproviderexamplesender.NoteList;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.contentproviderexamplesender.Models.Note;
import com.example.user.contentproviderexamplesender.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> implements NoteViewHolder.OnItemSelectedListener {

    NoteViewHolder.OnItemSelectedListener listener;
    private ArrayList<Note> mData;

    public NoteAdapter(ArrayList<Note> dataSet, NoteViewHolder.OnItemSelectedListener listener) {
        this.listener = listener;
        if (dataSet == null)
            mData = new ArrayList<>();
        else
            mData = dataSet;
    }

    @Override
    public int getItemViewType(int position) {
        //Note currentNote = mData.get(position);
        return 0;//currentNote;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_layout, parent, false);
        NoteViewHolder newViewHolder = new NoteViewHolder(view, this);
        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note thisNote = mData.get(position);
        if (thisNote == null)
            return;

        holder.setNote(thisNote);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position,
                                 @NonNull List<Object> payloads) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads);
        else {
            Bundle bundle = (Bundle) payloads.get(0);
            holder.updateNote(mData.get(position));
            //holder.setNote(mData.get(position));
            for (String key : bundle.keySet()) {

                if (key.equals("id")) {
                    holder.setNote(mData.get(position));
                    return;
                }
                if (key.equals("header"))
                    holder.setHeader(mData.get(position).getHeader());
                if (key.equals("dateTime")) {
                    holder.setDate(mData.get(position).getCreatedDateTime());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(List<Note> dataSet) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCall(this.mData, dataSet));
        diffResult.dispatchUpdatesTo(this);
        mData.clear();
        mData.addAll(dataSet);
    }

    @Override
    public void onItemSelected(Note item) {
        listener.onItemSelected(item);
    }
}

