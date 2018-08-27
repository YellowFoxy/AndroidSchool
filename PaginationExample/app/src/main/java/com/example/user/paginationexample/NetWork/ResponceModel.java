package com.example.user.paginationexample.NetWork;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponceModel implements Serializable {

    @SerializedName("total")
    private int total;

    @SerializedName("totalHits")
    private int totalHits;

    @SerializedName("hits")
    private List<NetworkImageModel> images;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<NetworkImageModel> getImages() {
        return images;
    }

    public void setImages(List<NetworkImageModel> images) {
        this.images = images;
    }
}
