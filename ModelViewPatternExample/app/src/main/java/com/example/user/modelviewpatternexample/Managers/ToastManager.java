package com.example.user.modelviewpatternexample.Managers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class ToastManager {

    private ToastManager() {
    }

    public static void showToast(Context context, String message, @Nullable Integer toastLenght) {
        if (toastLenght == null)
            toastLenght = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, toastLenght);
        toast.show();
    }

    public static void showToast(Context context, String message) {
        showToast(context, message, null);
    }
}
