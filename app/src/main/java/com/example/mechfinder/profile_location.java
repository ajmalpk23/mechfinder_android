package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class profile_location extends AppCompatActivity implements View.OnClickListener {
    EditText place,city,district,pincode;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_location);
        place=(EditText) findViewById(R.id.place);
        city=(EditText) findViewById(R.id.city);
        district=(EditText) findViewById(R.id.district);
        pincode=(EditText) findViewById(R.id.pincode);
        save=(Button)findViewById(R.id.save);


        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String Place=place.getText().toString();
        String City=city.getText().toString();
        String District=district.getText().toString();
        String Pincode=pincode.getText().toString();
    }
}