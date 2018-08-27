package com.example.user.paginationexample.NetWork;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageRequest {

    @GET("api")
    Call<ResponceModel> getImagesList(@Query("key") String key, @Query("page") int page);

}
