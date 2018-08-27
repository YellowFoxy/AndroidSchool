package com.example.user.paginationexample.ImageModels;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.net.URL;

public abstract class ImageModel {

    private Uri mUri;

    protected abstract ImageModelType getImageType();

    public int getImageTypeId() {
        switch (getImageType()) {
            case HTTP_URL_CONNECTOR_TYPE:
                return 1;
            case PICASSO_TYPE:
                return 2;
            case GLIDE_TYPE:
                return 3;
            case FRESCO_TYPE:
                return 4;
            default:
                return 0;
        }
    }

    public Uri getUri(){
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }

    @Nullable
    public static ImageModelType convertIntToImageModelType(int val) {
        switch (val) {
            case 1:
                return ImageModelType.HTTP_URL_CONNECTOR_TYPE;
            case 2:
                return ImageModelType.PICASSO_TYPE;
            case 3:
                return ImageModelType.GLIDE_TYPE;
            case 4:
                return ImageModelType.FRESCO_TYPE;
            default:
                return null;
        }
    }

    public static final DiffUtil.ItemCallback<ImageModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<ImageModel>() {

        // Check if items represent the same thing.
        @Override
        public boolean areItemsTheSame(ImageModel oldItem, ImageModel newItem) {
            return oldItem.getUri().equals(newItem.getUri());
        }

        // Checks if the item contents have changed.
        @Override
        public boolean areContentsTheSame(ImageModel oldItem, ImageModel newItem) {
            return true; // Assume Repository details don't change
        }
    };

    public enum ImageModelType {
        HTTP_URL_CONNECTOR_TYPE,
        PICASSO_TYPE,
        GLIDE_TYPE,
        FRESCO_TYPE
    }
}
