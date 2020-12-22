package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class vehicle extends AppCompatActivity {
    ImageButton add;
    LinearLayout linearLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        add=(ImageButton)findViewById(R.id.add);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavigationView);

    }
}