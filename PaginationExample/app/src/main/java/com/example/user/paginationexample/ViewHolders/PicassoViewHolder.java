package com.example.user.paginationexample.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import com.example.user.paginationexample.ImageModels.PicassoImageModel;
import com.example.user.paginationexample.R;
import com.squareup.picasso.Picasso;

public class PicassoViewHolder extends BaseImageViewHolder<PicassoImageModel> {

    private ImageView picassoImageView;

    public PicassoViewHolder(View itemView) {
        super(itemView);

        picassoImageView = itemView.findViewById(R.id.picassoImageView);
    }

    @Override
    public void startInit() {
        Picasso.get()
                .load(getImageModel().getUri())
                .into(picassoImageView);
    }
}
