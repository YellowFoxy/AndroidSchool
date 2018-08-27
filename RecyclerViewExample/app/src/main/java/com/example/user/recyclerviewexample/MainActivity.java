package com.example.user.recyclerviewexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OperationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MyBroadcastReceiver myBroadcastReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initLayoutManager();
        initAdapters();
        initializeReceiver();


        startService(HistoryIntentService.newIntent(this));
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initLayoutManager() {
        if (recyclerView == null)
            return;

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void initAdapters() {
        if (recyclerView == null)
            return;

        //List<IHistoryCollectionItem> items = new ArrayList<IHistoryCollectionItem>();
        /*for (int i = 0; i < 150; i++) {
            Date newDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(newDate);
            calendar.add(Calendar.DATE, i);
            items.add(new HistoryCollectionSeparatorItem(calendar.getTime()));
        }*/

        mAdapter = new OperationAdapter(new ArrayList<HistoryCollectionItem>());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {

        super.onResume();
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myBroadcastReceiver);
    }

    private void initializeReceiver() {
        myBroadcastReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter(HistoryIntentService.MY_HISTORY_SERVICE_ACTION);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            HistoryCollectionItem newOperation = (HistoryCollectionItem) intent.getSerializableExtra(HistoryIntentService.EXTRA_KEY_OUT);
            mAdapter.addNewItem(newOperation);
        }
    }
}
