package com.example.user.aidlexample.Fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.aidlexample.DataService;
import com.example.user.aidlexample.IMyAidlInterface;
import com.example.user.aidlexample.MainActivity;
import com.example.user.aidlexample.R;

import org.w3c.dom.Text;

public class WatchFragment extends Fragment {

    private Button editButton;
    private TextView showDataTextView;

    private IMyAidlInterface myAidlInterface;

    private OnFragmentInteractionListener mListener;

    public WatchFragment() {
    }

    public static WatchFragment newInstance() {
        WatchFragment fragment = new WatchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showDataTextView = view.findViewById(R.id.showDataTextView);
        editButton = view.findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(MainActivity.ACTION_EDIT_FRAGMENT_KEY);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watch, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

            Intent intent = new Intent(getContext(), DataService.class);
            intent.setAction(IMyAidlInterface.class.getName());
            getActivity().bindService(intent, mConnection, getContext().BIND_AUTO_CREATE);
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

        getActivity().unbindService(mConnection);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String action);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);

            try {
                String data = myAidlInterface.getData();
                if(TextUtils.isEmpty(data))
                    return;

                showDataTextView.setText(data);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlInterface = null;
        }
    };
}
