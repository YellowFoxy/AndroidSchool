package com.example.user.paginationexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.paginationexample.ImageModels.ImageModel;
import com.example.user.paginationexample.ViewHolders.FrescoViewHolder;
import com.example.user.paginationexample.ViewHolders.GlideViewHolder;
import com.example.user.paginationexample.ViewHolders.HttpUrlConnectionViewHolder;
import com.example.user.paginationexample.ViewHolders.PicassoViewHolder;

import java.lang.reflect.InvocationTargetException;
import java.util.Dictionary;
import java.util.Hashtable;

public class ViewHolderFactory {
    private Dictionary<ImageModel.ImageModelType, ViewHolderWithLayout> holders;

    public ViewHolderFactory() {
        holders = new Hashtable<>();
        holders.put(ImageModel.ImageModelType.HTTP_URL_CONNECTOR_TYPE, new ViewHolderWithLayout(R.layout.httpurlconnector_image_layout, HttpUrlConnectionViewHolder.class));
        holders.put(ImageModel.ImageModelType.PICASSO_TYPE, new ViewHolderWithLayout(R.layout.picasso_image_layout, PicassoViewHolder.class));
        holders.put(ImageModel.ImageModelType.GLIDE_TYPE, new ViewHolderWithLayout(R.layout.glide_image_layout, GlideViewHolder.class));
        holders.put(ImageModel.ImageModelType.FRESCO_TYPE, new ViewHolderWithLayout(R.layout.fresco_image_layout, FrescoViewHolder.class));
    }

    public RecyclerView.ViewHolder getViewHolder(@NonNull ViewGroup parent, ImageModel.ImageModelType viewType) {
        return holders.get(viewType).getViewHolder(parent);
    }

    class ViewHolderWithLayout {
        int layoutId;
        Class<RecyclerView.ViewHolder> aClass;

        ViewHolderWithLayout(int layoutId, Class aClass) {
            this.layoutId = layoutId;
            this.aClass = aClass;
        }

        View getView(@NonNull ViewGroup parent) {
            return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        }

        RecyclerView.ViewHolder getViewHolder(@NonNull ViewGroup parent) {

            View view = getView(parent);

            try {
                return aClass.getConstructor(View.class).newInstance(view);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
