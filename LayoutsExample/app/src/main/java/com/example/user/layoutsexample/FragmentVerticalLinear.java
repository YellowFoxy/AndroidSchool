package com.example.user.layoutsexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentVerticalLinear extends Fragment {

    private FragmentVerticalLinear.MyBroadcastReceiver broadcastReceiver;

    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        broadcastReceiver=new FragmentVerticalLinear.MyBroadcastReceiver();
        return inflater.inflate(R.layout.fragment_vertical_linear, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(MyService.TEXT_SERVICE_ACTION));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private void initViews(View v) {
        buttonOne = v.findViewById(R.id.buttonOne);
        buttonTwo = v.findViewById(R.id.buttonTwo);
        buttonThree = v.findViewById(R.id.buttonThree);

    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String resultOne = intent.getStringExtra(MyService.TEXT_SERVICE_OUT_ONE);
            String resultTwo = intent.getStringExtra(MyService.TEXT_SERVICE_OUT_TWO);
            String resultThree = intent.getStringExtra(MyService.TEXT_SERVICE_OUT_THREE);

            buttonOne.setText(resultOne);
            buttonTwo.setText(resultTwo);
            buttonThree.setText(resultThree);
        }
    }
}
