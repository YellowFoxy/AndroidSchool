package com.example.user.aidlexample;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.aidlexample.Fragments.EditFragment;
import com.example.user.aidlexample.Fragments.WatchFragment;

public class MainActivity extends AppCompatActivity implements WatchFragment.OnFragmentInteractionListener {

    public static final String ACTION_EDIT_FRAGMENT_KEY = "edit";
    public static final String ACTION_EDIT_CANCEL_FRAGMENT_KEY = "edit_cancel";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(ACTION_EDIT_FRAGMENT_KEY);
    }

    @Override
    public void onFragmentInteraction(String action) {
        setFragment(action);
    }

    private void setFragment(String action) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (action.equals(ACTION_EDIT_FRAGMENT_KEY)) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, EditFragment.newInstance())
                    .commit();

        } else if (action.equals(ACTION_EDIT_CANCEL_FRAGMENT_KEY)) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, WatchFragment.newInstance())
                    .commit();
        }
    }
}
