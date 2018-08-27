package com.example.user.recyclerviewexample;

import java.util.Date;

public class HistoryCollectionItemSuccess extends HistoryCollectionItem{

    public HistoryCollectionItemSuccess(Date date) {
        super(date);
    }

    @Override
    int getTypeId() {
        return 1;
    }
}
