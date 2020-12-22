package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class registration_home_ocation extends AppCompatActivity implements View.OnClickListener {
    EditText place,city,district,pincode;
    TextView homeloction;
    ImageButton next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_home_ocation);
        place=(EditText)findViewById(R.id.place);
        city=(EditText)findViewById(R.id.city);
        district=(EditText)findViewById(R.id.district);
        pincode=(EditText)findViewById(R.id.pincode);
        homeloction=(TextView)findViewById(R.id.homelocation);
        next=(ImageButton)findViewById(R.id.next);



        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String Place=place.getText().toString();
        String City=city.getText().toString();
        String District=district.getText().toString();
        String Pincode=pincode.getText().toString();
    }
}