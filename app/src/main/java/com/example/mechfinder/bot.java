package com.example.mechfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bot extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    LinearLayout linearLayout;
    BottomNavigationView bottomnavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        bottomnavigation=(BottomNavigationView)findViewById(R.id.bottomnav);


        bottomnavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.bot){
            Toast.makeText(this, "bot section 3", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId()==R.id.home){

            Intent in=new Intent(getApplicationContext(),home.class);
            startActivity(in);
            Toast.makeText(this, "home section 2", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.vehicle){
            Intent in=new Intent(getApplicationContext(),vehicle.class);
            startActivity(in);
            Toast.makeText(this, "vehicle 3", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.my_account){
            Intent in=new Intent(getApplicationContext(),my_account.class);
            startActivity(in);
            Toast.makeText(this, "account clicked 3", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}