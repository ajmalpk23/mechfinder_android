package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class saveip extends AppCompatActivity implements View.OnClickListener {
    EditText ip;
    ImageView save;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveip);
        ip=(EditText)findViewById(R.id.ip);
        save=(ImageView) findViewById(R.id.save);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip.setText(sh.getString("ip",""));
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String set_ip=ip.getText().toString();
        String url="http://"+set_ip+":5000/";

        SharedPreferences.Editor edt=sh.edit();
        edt.putString("ip",set_ip);
        edt.putString("url",url);
        edt.commit();

        Intent in=new Intent(getApplicationContext(),Login.class);
        startActivity(in);
    }
}