package com.example.user.recyclerviewexample;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OperationFailViewHolder extends RecyclerView.ViewHolder {
    private TextView dateText;
    private Button statusButton;

    public OperationFailViewHolder(View itemView) {
        super(itemView);

        dateText = itemView.findViewById(R.id.dateText);
        statusButton = itemView.findViewById(R.id.statusButton);

        statusButton.setBackgroundColor(Color.RED);
    }

    public void setText(String text) {
        dateText.setText(text);
    }

    public void setFailReason(String text){
        statusButton.setText(text);
    }
}
