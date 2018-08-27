package com.example.user.paginationexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.user.paginationexample.ImageModels.ImageModel;
import com.example.user.paginationexample.ViewHolders.BaseImageViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter {

    private ViewHolderFactory viewHolderFactory;

    private List<ImageModel> mData;

    public ImageAdapter() {
        viewHolderFactory = new ViewHolderFactory();
        mData = new ArrayList<>();
    }

    public ImageAdapter(@NonNull List<ImageModel> images) {
        viewHolderFactory = new ViewHolderFactory();
        mData = images;
    }

    @Override
    public int getItemViewType(int position) {
        ImageModel currentItem = mData.get(position);
        return currentItem.getImageTypeId();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewHolderFactory.getViewHolder(parent, ImageModel.convertIntToImageModelType(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageModel imageItemModel = mData.get(position);
        BaseImageViewHolder castHolder = (BaseImageViewHolder) holder;
        if (castHolder == null || imageItemModel == null)
            return;

        castHolder.setImageModel(imageItemModel);
        castHolder.startInit();
    }

    public void setNewDataItems(List<ImageModel> data){
        int oldSize = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(oldSize, data.size());
    }

    public void addNewDataItem(ImageModel newDataItem) {
        mData.add(newDataItem);
        notifyItemInserted(mData.size() - 1);
    }

    @Override
    public int getItemCount() {
        if (mData == null)
            return 0;

        return mData.size();
    }
}
