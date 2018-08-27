package com.example.user.paginationexample.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.paginationexample.ImageModels.ImageModel;


public abstract class BaseImageViewHolder<T extends ImageModel> extends RecyclerView.ViewHolder{

    private T mImageModel;

    public BaseImageViewHolder(View itemView) {
        super(itemView);
    }

    protected T getImageModel() {
        return mImageModel;
    }

    public void setImageModel(@NonNull T model) {
        mImageModel = model;
    }

    public abstract void startInit();
}
