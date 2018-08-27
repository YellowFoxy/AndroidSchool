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
import android.widget.EditText;

import com.example.user.aidlexample.DataService;
import com.example.user.aidlexample.IMyAidlInterface;
import com.example.user.aidlexample.MainActivity;
import com.example.user.aidlexample.R;

public class EditFragment extends Fragment {

    private Button saveButton;
    private EditText addSomeDataEditText;

    private IMyAidlInterface myAidlInterface;

    private WatchFragment.OnFragmentInteractionListener mListener;

    public EditFragment() {
    }

    public static EditFragment newInstance() {
        EditFragment fragment = new EditFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view){
        addSomeDataEditText = view.findViewById(R.id.addSomeDataEditText);

        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
    }

    private void onButtonClick(){
        String text = addSomeDataEditText.getText().toString();
        try {
            myAidlInterface.setData(text);
            mListener.onFragmentInteraction(MainActivity.ACTION_EDIT_CANCEL_FRAGMENT_KEY);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WatchFragment.OnFragmentInteractionListener) {
            mListener = (WatchFragment.OnFragmentInteractionListener) context;

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

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlInterface = null;
        }
    };
}
