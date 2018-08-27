package com.example.user.paginationexample;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.example.user.paginationexample.ImageModels.ImageModel;
import com.example.user.paginationexample.ViewHolders.BaseImageViewHolder;

public class ImagePaggingAdapter extends PagedListAdapter<ImageModel, BaseImageViewHolder> {

    private ViewHolderFactory viewHolderFactory;

    protected ImagePaggingAdapter() {
        super(ImageModel.DIFF_CALLBACK);

        viewHolderFactory = new ViewHolderFactory();
    }

    @Override
    public int getItemViewType(int position) {
        ImageModel currentItem = this.getItem(position);
        return currentItem.getImageTypeId();
    }

    @NonNull
    @Override
    public BaseImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (BaseImageViewHolder) viewHolderFactory.getViewHolder(parent, ImageModel.convertIntToImageModelType(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseImageViewHolder holder, int position) {
        ImageModel imageItemModel = this.getItem(position);
        BaseImageViewHolder castHolder = holder;
        if (castHolder == null || imageItemModel == null)
            return;

        castHolder.setImageModel(imageItemModel);
        castHolder.startInit();
    }
}
