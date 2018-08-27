package com.example.user.paginationexample.NetWork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageNetworkServiceHelper {
    private static final String GET_IMAGE_URI = "https://pixabay.com/";

    private static ImageRequest instance;

    private ImageNetworkServiceHelper() {
    }


    public static ImageRequest getService() {
        if (instance != null)
            return instance;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GET_IMAGE_URI )
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return instance = retrofit.create(ImageRequest.class);
    }
}
