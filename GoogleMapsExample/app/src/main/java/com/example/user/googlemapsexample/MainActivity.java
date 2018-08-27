package com.example.user.googlemapsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {

    private GoogleMap map;

    private SupportMapFragment mapFragment;
    private EditText fromAddressEditText;
    private EditText toAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        fromAddressEditText = findViewById(R.id.fromAddressEditText);
        toAddressEditText = findViewById(R.id.toAddressEditText);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                mapInit();
            }
        });
    }

    private void mapInit(){
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
