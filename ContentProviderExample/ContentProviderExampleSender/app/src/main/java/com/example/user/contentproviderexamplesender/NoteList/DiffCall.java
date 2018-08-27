package com.example.user.contentproviderexamplesender.NoteList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.user.contentproviderexamplesender.Models.Note;

import java.util.List;

public class DiffCall extends DiffUtil.Callback {

    private List<Note> mOldList;
    private List<Note> mNewList;

    public DiffCall(List<Note> oldList, List<Note> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId() == mNewList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Note oldItem = mOldList.get(oldItemPosition);
        Note newItem = mNewList.get(newItemPosition);

        boolean result = oldItem.getHeader() == newItem.getHeader();
        result = result && (oldItem.getText() == newItem.getText());
        result = result && (oldItem.getCreatedDateTime() == newItem.getCreatedDateTime());

        return result;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Note oldItem = mOldList.get(oldItemPosition);
        Note newItem = mNewList.get(newItemPosition);
        Bundle diff = new Bundle();
        if (!oldItem.getHeader().equals(newItem.getHeader()))
            diff.putString("header", newItem.getHeader());
        if (!oldItem.getCreatedDateTime().equals(newItem.getCreatedDateTime()))
            diff.putString("dateTime", newItem.getCreatedDateTime());

        if (!areItemsTheSame(oldItemPosition, newItemPosition))
            diff.putInt("id", newItem.getId());

        if (diff.size() == 0)
            return null;

        return diff;
    }
}

