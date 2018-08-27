package com.example.user.recyclerviewexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OperationAdapter extends RecyclerView.Adapter {

    private ViewHolderFactory viewHolderFactory;

    private List<HistoryCollectionItem> mData;

    public OperationAdapter(List<HistoryCollectionItem> dataSet) {
        if (dataSet == null)
            mData = new ArrayList<>();
        else
            mData = dataSet;

        viewHolderFactory = new ViewHolderFactory();
    }

    @Override
    public int getItemViewType(int position) {
        HistoryCollectionItem currentOperation = mData.get(position);
        return currentOperation.getTypeId();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewHolderFactory.getViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HistoryCollectionItem historyItem = mData.get(position);

        switch (historyItem.getTypeId()) {
            case 0:
                ((SeparatorViewHolder) holder).setText(historyItem.dateToString());
                return;
            case 1:
                ((OperationSuccessViewHolder) holder).setText(historyItem.dateToString());
                return;
            case 2:
                OperationFailViewHolder failHolder = (OperationFailViewHolder) holder;
                failHolder.setText(historyItem.dateToString());
                failHolder.setFailReason(((HistoryCollectionItemFail) historyItem).failReason);
                return;
        }
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    public void addItems(List<HistoryCollectionItem> newData) {
        int oldSize = mData.size();
        mData.addAll(newData);
        notifyItemRangeInserted(oldSize, newData.size());
    }

    public void addNewItem(HistoryCollectionItem newOperationItem) {
        mData.add(newOperationItem);
        notifyItemInserted(mData.size() - 1);
    }
}

