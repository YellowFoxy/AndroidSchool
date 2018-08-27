package com.example.user.recyclerviewexample;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class HistoryCollectionItem implements Serializable {

    abstract int getTypeId();

    protected Date dateOperation;

    public void setDate(Date date) {
        dateOperation = date;
    }

    public Date getDate() {
        return dateOperation;
    }

    public String dateToString() {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return "Время: " + sdf.format(dateOperation);
    }

    public HistoryCollectionItem(Date date) {
        dateOperation = date;
    }
}

