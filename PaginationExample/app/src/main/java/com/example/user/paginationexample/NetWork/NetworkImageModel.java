package com.example.user.paginationexample.NetWork;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NetworkImageModel implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("previewURL")
    private String imageURL;

    @SerializedName("user")
    private String user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL(){
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
