package com.example.user.asynctaskexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class ColorFragment extends Fragment implements LoaderManager.LoaderCallbacks<Integer> {

    private Loader<Integer> mLoader;
    private LinearLayout test;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.color_fragment_layout, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        test = view.findViewById(R.id.test);
        test.setBackgroundColor(Color.RED);
        mLoader = getLoaderManager().initLoader(0,new Bundle(),this);
    }

    public static ColorFragment newInstance(){
        ColorFragment newFragment = new ColorFragment();
        return newFragment;
    }


    @Override
    public Loader<Integer> onCreateLoader(int id, Bundle args) {
        mLoader = new ColorLoader(getContext());
        return mLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer data) {
        test.setBackgroundColor(data);
        mLoader.forceLoad();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {

    }
}
