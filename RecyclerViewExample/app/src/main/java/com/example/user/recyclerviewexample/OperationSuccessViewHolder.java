package com.example.user.recyclerviewexample;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OperationSuccessViewHolder extends RecyclerView.ViewHolder {

    private TextView dateText;
    private Button statusButton;

    public OperationSuccessViewHolder(View itemView) {
        super(itemView);

        dateText = itemView.findViewById(R.id.dateText);
        statusButton = itemView.findViewById(R.id.statusButton);

        statusButton.setBackgroundColor(Color.GREEN);
    }

    public void setText(String text) {
        dateText.setText(text);
    }
}
