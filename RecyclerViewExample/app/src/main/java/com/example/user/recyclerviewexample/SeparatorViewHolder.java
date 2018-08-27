package com.example.user.recyclerviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SeparatorViewHolder extends RecyclerView.ViewHolder {

    private TextView separatorText;

    public SeparatorViewHolder(View itemView) {
        super(itemView);

        separatorText = itemView.findViewById(R.id.separatorText);
    }

    public void setText(String text) {
        separatorText.setText(text);
    }
}
