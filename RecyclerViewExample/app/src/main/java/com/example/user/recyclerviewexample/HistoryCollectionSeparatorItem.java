package com.example.user.recyclerviewexample;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryCollectionSeparatorItem extends HistoryCollectionItem{

    public HistoryCollectionSeparatorItem(Date date) {
        super(date);
    }

    @Override
    int getTypeId() {
        return 0;
    }

    @Override
    public String dateToString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return "Дата: " + sdf.format(dateOperation);
    }
}
