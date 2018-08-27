package com.example.user.paginationexample;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.paginationexample.ImageModels.ImageModel;
import com.example.user.paginationexample.Util.MainThreadExecutor;

import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewImages;

    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        recyclerViewImages = findViewById(R.id.recyclerViewImages);

        initLayoutManager();
        initAdapter();
    }

    private void initLayoutManager(){
        if (recyclerViewImages == null)
            return;

        mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);//new GridLayoutManager(this,2);
        recyclerViewImages.setLayoutManager(mLayoutManager);
    }

    private void initAdapter() {
        if (recyclerViewImages == null || mLayoutManager == null)
            return;

        ImageDataSource dataSource = new ImageDataSource();
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build();

        PagedList<ImageModel> imageList = new PagedList.Builder<>(dataSource,config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setInitialKey(1)
                .setNotifyExecutor(new MainThreadExecutor())
                .build();

        ImagePaggingAdapter mImagePaggingAdapter = new ImagePaggingAdapter();
        mImagePaggingAdapter.submitList(imageList);
        recyclerViewImages.setAdapter(mImagePaggingAdapter);
    }
}
