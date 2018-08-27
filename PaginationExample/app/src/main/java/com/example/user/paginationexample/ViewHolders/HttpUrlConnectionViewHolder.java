package com.example.user.paginationexample.ViewHolders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.paginationexample.ImageModels.HttpUrlConnectionImageModel;
import com.example.user.paginationexample.R;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionViewHolder extends BaseImageViewHolder<HttpUrlConnectionImageModel> {

    private ImageView httpUrlConnectorImageView;

    public HttpUrlConnectionViewHolder(View itemView) {

        super(itemView);
        httpUrlConnectorImageView = itemView.findViewById(R.id.httpUrlConnectorImageView);
    }

    @Override
    public void startInit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                Bitmap loadedBitmap = getImage(getImageModel().getUri());
                bundle.putParcelable("Bitmap", loadedBitmap);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();

            httpUrlConnectorImageView.setImageBitmap((Bitmap) data.get("Bitmap"));
        }
    };

    private Bitmap getImage(Uri uri){
        try{
            URL url = new URL(uri.toString());
            return loadImage(url);
        }
        catch(Exception e){
            return null;
        }
    }

    private Bitmap loadImage(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responceCode = connection.getResponseCode();
            if (responceCode == 200) {
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else
                return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}
