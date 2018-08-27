package com.example.user.paginationexample.ImageModels;

public class HttpUrlConnectionImageModel extends ImageModel {
    @Override
    protected ImageModelType getImageType() {
        return ImageModelType.HTTP_URL_CONNECTOR_TYPE;
    }
}
