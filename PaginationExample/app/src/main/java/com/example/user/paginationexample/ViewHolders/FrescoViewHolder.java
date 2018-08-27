package com.example.user.paginationexample.ViewHolders;

import android.view.View;

import com.example.user.paginationexample.ImageModels.FrescoImageModel;
import com.example.user.paginationexample.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoViewHolder extends BaseImageViewHolder<FrescoImageModel> {

    private SimpleDraweeView frescoSimpleDraweeView;

    public FrescoViewHolder(View itemView) {

        super(itemView);
        frescoSimpleDraweeView=itemView.findViewById(R.id.frescoSimpleDraweeView);
    }

    @Override
    public void startInit() {
        if (frescoSimpleDraweeView == null)
            return;

        frescoSimpleDraweeView.setImageURI(getImageModel().getUri());
    }
}
