package com.example.user.paginationexample;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.user.paginationexample.ImageModels.ImageModel;
import com.example.user.paginationexample.NetWork.ImageNetworkServiceHelper;
import com.example.user.paginationexample.NetWork.ResponceModel;
import com.example.user.paginationexample.NetWork.ResponceModelTransform;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class ImageDataSource extends PageKeyedDataSource<Integer,ImageModel> {

    public static final String USER_KEY = "9903263-a1fed464e81cc555feee97d77";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, ImageModel> callback) {

        final int page = 1;
        Call<ResponceModel> call = ImageNetworkServiceHelper.getService().getImagesList(USER_KEY, page);

        Callback<ResponceModel> requestCallback = new Callback<ResponceModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponceModel> call, @NonNull Response<ResponceModel> response) {
                ResponceModel responceModel = response.body();

                if (responceModel == null) {
                    onFailure(call, new HttpException(response));
                    return;
                }

                List<ImageModel> convertedData = ResponceModelTransform.transformResponce(responceModel);
                callback.onResult(
                        convertedData,
                        page,
                        responceModel.getTotalHits(),
                        null,
                        page + 1
                );
            }

            @Override
            public void onFailure(Call<ResponceModel> call, Throwable t) {

            }
        };
        call.enqueue(requestCallback);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ImageModel> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ImageModel> callback) {
        final int page = params.key;
        Call<ResponceModel> call = ImageNetworkServiceHelper.getService().getImagesList(USER_KEY, page);

        Callback<ResponceModel> requestCallback = new Callback<ResponceModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponceModel> call, @NonNull Response<ResponceModel> response) {
                ResponceModel responceModel = response.body();

                if (responceModel == null) {
                    onFailure(call, new HttpException(response));
                    return;
                }

                List<ImageModel> convertedData = ResponceModelTransform.transformResponce(responceModel);
                callback.onResult(
                        convertedData,
                        page + 1
                );
            }

            @Override
            public void onFailure(Call<ResponceModel> call, Throwable t) {

            }
        };
        call.enqueue(requestCallback);
    }
}
