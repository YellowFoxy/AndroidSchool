package com.example.user.recyclerviewexample;

import java.util.Date;

public class HistoryCollectionItemFail extends HistoryCollectionItem {

    public HistoryCollectionItemFail(Date date) {
        super(date);
    }

    public String failReason;

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    @Override
    int getTypeId() {
        return 2;
    }
}
