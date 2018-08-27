package com.example.user.aidlexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class DataService extends Service {

    private DataStorage dataStorage = new DataStorage(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return new IMyAidlInterface.Stub() {
            @Override
            public String getData() throws RemoteException {
                return dataStorage.getData();
            }

            @Override
            public void setData(String data) throws RemoteException {
                dataStorage.setData(data);
            }
        };
    }
}
