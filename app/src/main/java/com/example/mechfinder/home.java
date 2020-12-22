package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {
    LinearLayout linearLayout,linearLayout2,linearLayout3;
    ListView listview;
    BottomNavigationView bottomnavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        linearLayout2=(LinearLayout)findViewById(R.id.linearLayout2);
        linearLayout3=(LinearLayout)findViewById(R.id.linearLayout3);
        listview=(ListView)findViewById(R.id.listview);
        bottomnavigation=(BottomNavigationView)findViewById(R.id.bottomNavigationView);

    }
}