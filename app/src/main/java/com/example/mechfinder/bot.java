package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bot extends AppCompatActivity {
    LinearLayout linearLayout;
    BottomNavigationView bottomnavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        bottomnavigation=(BottomNavigationView)findViewById(R.id.bottomnav);
    }
}