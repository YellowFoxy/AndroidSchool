package com.example.user.fragmentexample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class FragmentOne extends Fragment {
    private EditText myEditText;
    private String Value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Value = getArguments().getString("value");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initializeViews(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void initializeViews(View view) {
        myEditText = view.findViewById(R.id.myEditText);
        if (Value != null && Value != "")
            myEditText.setText(Value);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static FragmentOne newInstance(String val) {
        FragmentOne fragment = new FragmentOne();
        Bundle args = new Bundle();
        args.putString("value", val);
        fragment.setArguments(args);
        return fragment;
    }
}
