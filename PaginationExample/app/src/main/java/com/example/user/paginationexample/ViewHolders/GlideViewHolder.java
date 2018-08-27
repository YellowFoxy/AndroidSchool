package com.example.user.paginationexample.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.paginationexample.ImageModels.GlideImageModel;
import com.example.user.paginationexample.R;

public class GlideViewHolder extends BaseImageViewHolder<GlideImageModel> {

    private ImageView glideImageView;

    public GlideViewHolder(View itemView) {
        super(itemView);
        glideImageView = itemView.findViewById(R.id.glideImageView);
    }

    @Override
    public void startInit() {

        Glide.with(this.itemView)
                .load(getImageModel().getUri())
                .apply(new RequestOptions().override(200,200).fitCenter())
                .into(glideImageView);
    }
}
