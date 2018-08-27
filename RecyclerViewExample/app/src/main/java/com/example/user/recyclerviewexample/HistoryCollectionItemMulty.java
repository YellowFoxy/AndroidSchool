package com.example.user.recyclerviewexample;

import java.util.Date;

public class HistoryCollectionItemMulty extends HistoryCollectionItem {

    public HistoryCollectionItemMulty(Date date) {
        super(date);
    }

    @Override
    int getTypeId() {
        return 3;
    }
}
