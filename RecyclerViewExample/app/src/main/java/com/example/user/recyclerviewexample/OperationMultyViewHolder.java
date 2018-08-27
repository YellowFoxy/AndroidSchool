package com.example.user.recyclerviewexample;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class OperationMultyViewHolder extends RecyclerView.ViewHolder {
    private TextView dateText;
    private RecyclerView includedRecyclerView;

    public OperationMultyViewHolder(View itemView) {
        super(itemView);

        dateText = itemView.findViewById(R.id.dateText);
        includedRecyclerView = itemView.findViewById(R.id.includedRecyclerView);
    }

    public void setText(String text) {
        dateText.setText(text);
    }
}
