package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class my_account extends AppCompatActivity {
    ListView listview;
    BottomNavigationView bottomnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        listview=(ListView)findViewById(R.id.listview);
        bottomnav=(BottomNavigationView)findViewById(R.id.bottomnav);
    }
}