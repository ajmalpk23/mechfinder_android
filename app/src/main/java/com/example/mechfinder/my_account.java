package com.example.mechfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class my_account extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7;
    TextView profile,orderhistoty,chat1,appfeedback,compalint,changepassword,logout;
    BottomNavigationView bottomnav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        imageView1=(ImageView)findViewById(R.id.imageView1);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView7=(ImageView)findViewById(R.id.imageView7);
        profile=(TextView)findViewById(R.id.profile);
        orderhistoty=(TextView)findViewById(R.id.orderhistory);
        chat1=(TextView)findViewById(R.id.chat);
        appfeedback=(TextView)findViewById(R.id.appfeedback);
        compalint=(TextView)findViewById(R.id.complaint);
        changepassword=(TextView)findViewById(R.id.changepassword);
        logout=(TextView)findViewById(R.id.logout);
        bottomnav=(BottomNavigationView)findViewById(R.id.bottomnav);
        bottomnav.setItemIconTintList(null);



        bottomnav.setOnNavigationItemSelectedListener(this);
        appfeedback.setOnClickListener(this);
        compalint.setOnClickListener(this);
        profile.setOnClickListener(this);
        changepassword.setOnClickListener(this);
        logout.setOnClickListener(this);
        orderhistoty.setOnClickListener(this);
        chat1.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.my_account){
            Toast.makeText(this, "bot section 3", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId()==R.id.chat111){

            Intent in=new Intent(getApplicationContext(),home.class);
            startActivity(in);
            Toast.makeText(this, "home section 2", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.vehicle){
            Intent in=new Intent(getApplicationContext(),vehicle.class);
            startActivity(in);
            Toast.makeText(this, "vehicle 3", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.bot){
            Intent in=new Intent(getApplicationContext(),bot.class);
            startActivity(in);
            Toast.makeText(this, "bot clicked 4", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        if(appfeedback==v) {
            Intent in = new Intent(getApplicationContext(), app_feedback.class);
            startActivity(in);
            Toast.makeText(this, "app feed back", Toast.LENGTH_SHORT).show();
        }
        else if(compalint==v){
            Intent in = new Intent(getApplicationContext(), complant_support.class);
            startActivity(in);
            Toast.makeText(this, "complaint", Toast.LENGTH_SHORT).show();

        }
        else if(profile==v){
            Intent in = new Intent(getApplicationContext(), profile_personal.class);
            startActivity(in);
            Toast.makeText(this, "profile personal", Toast.LENGTH_SHORT).show();

        }
        else if(changepassword==v){
            Intent in = new Intent(getApplicationContext(),change_password.class);
            startActivity(in);
            Toast.makeText(this, "change passwoord", Toast.LENGTH_SHORT).show();
        }
        else if(logout==v){
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor edt=sh.edit();
            edt.remove("vid");
            edt.commit();
            Intent in=new Intent(getApplicationContext(),Login.class);
            startActivity(in);
        }
        else if(orderhistoty==v){
            Intent in=new Intent(getApplicationContext(),order_history.class);
            startActivity(in);

        }
        else if(chat1==v){
            Intent in=new Intent(getApplicationContext(),user_chat.class);
            startActivity(in);


        }





    }
}