package com.example.user.layoutsexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentRelative extends Fragment {


    private Messenger myService;
    private ServiceConnection serviceConnection;
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;
    Button centerButton;
    Button buttonSix;
    Button buttonSeven;
    Button buttonEight;
    Button buttonNine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myService = new Messenger(service);

                try {
                    Message msg = Message.obtain(null, BinderService.MSG_REGISTER_CLIENT);
                    msg.replyTo = mMessenger;
                    myService.send(msg);
                } catch (RemoteException e) {

                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        getActivity().bindService(BinderService.newIntent(getActivity()), serviceConnection, getActivity().BIND_AUTO_CREATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_relative, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initButtons(view);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == BinderService.MSG_SET_VALUE_RELATIVE)
                centerButton.setText(msg.getData().getString("value"));
        }
    }

    private void initButtons(View v) {
        buttonOne = v.findViewById(R.id.buttonOne);
        buttonTwo = v.findViewById(R.id.buttonTwo);
        buttonThree = v.findViewById(R.id.buttonThree);
        buttonFour = v.findViewById(R.id.buttonFour);
        centerButton = v.findViewById(R.id.centerButton);
        buttonSix = v.findViewById(R.id.buttonSix);
        buttonSeven = v.findViewById(R.id.buttonSeven);
        buttonEight = v.findViewById(R.id.buttonEight);
        buttonNine = v.findViewById(R.id.buttonNine);

        centerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onCenterButtonClickFromRelativeFragment();
            }
        });
    }
}
