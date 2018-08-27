package com.example.user.networkexample.Manager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class ToastManager {

    private ToastManager(){
    }

    public static void showToast(Context context, String message, @Nullable Integer toastLenght) {
        if (toastLenght == null)
            toastLenght = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, toastLenght);
        toast.show();
    }
}