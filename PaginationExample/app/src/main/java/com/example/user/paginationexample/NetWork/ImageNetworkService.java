package com.example.user.paginationexample.NetWork;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageNetworkService extends IntentService {

    public static final String LOAD_IMAGE_SERVICE_ACTION = "ru.example.LOADIMAGE";
    public static final String LOAD_IMAGE_MESSAGE_KEY = "load_image_message_key";
    public static final String LOADED_IMAGE_OUT_KEY = "loaded_image_page";

    public static final String USER_KEY = "9903263-a1fed464e81cc555feee97d77";

    public ImageNetworkService() {
        super("ImageNetworkService ");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null)
            return;

        int pageCount = intent.getIntExtra(LOAD_IMAGE_MESSAGE_KEY, 1);
        loadImagePage(pageCount);

    }

    private void loadImagePage(int page) {
        ImageNetworkServiceHelper.getService().getImagesList(USER_KEY, page).enqueue(new Callback<ResponceModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponceModel> call, @NonNull Response<ResponceModel> response) {
                if (!response.isSuccessful())
                    return;

                sendLoadedImagePageBroadcastMessage(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ResponceModel> call, @NonNull Throwable t) {
            }
        });
    }

    private void sendLoadedImagePageBroadcastMessage(ResponceModel images) {
        Intent responseIntent = new Intent();
        responseIntent.setAction(LOAD_IMAGE_SERVICE_ACTION);

        responseIntent.putExtra(LOADED_IMAGE_OUT_KEY, images);
        sendBroadcast(responseIntent);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ImageNetworkService.class);
    }

    public static Intent getIntentForLoadImages(Context context, int page) {
        Intent intent = newIntent(context);
        intent.putExtra(LOAD_IMAGE_MESSAGE_KEY, page);
        return intent;
    }
}
