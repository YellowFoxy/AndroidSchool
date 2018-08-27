package com.example.user.paginationexample.NetWork;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.user.paginationexample.ImageModels.FrescoImageModel;
import com.example.user.paginationexample.ImageModels.GlideImageModel;
import com.example.user.paginationexample.ImageModels.HttpUrlConnectionImageModel;
import com.example.user.paginationexample.ImageModels.ImageModel;
import com.example.user.paginationexample.ImageModels.PicassoImageModel;

import java.util.ArrayList;
import java.util.List;

public class ResponceModelTransform {

    @NonNull
    public static List<ImageModel> transformResponce(ResponceModel responce) {
        List<ImageModel> images = new ArrayList<>();
        if (responce == null || responce.getImages().size() == 0)
            return images;

        List<NetworkImageModel> dataImages = responce.getImages();
        for (int i = 0; i < dataImages.size(); i++) {
            ImageModel newModel;
            if (i % 4 == 0) {
                newModel = new PicassoImageModel();
            } else if (i % 3 == 0) {
                newModel = new GlideImageModel();
            } else if (i % 2 == 0) {
                newModel = new FrescoImageModel();
            } else {
                newModel = new HttpUrlConnectionImageModel();
            }
            newModel.setUri(Uri.parse(dataImages.get(i).getImageURL()));
            images.add(newModel);
        }
        return images;
    }
}
