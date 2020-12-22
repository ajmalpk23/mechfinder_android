package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Registration_vehicle extends AppCompatActivity implements View.OnClickListener {
    Spinner type,model,company;
    EditText regno,year;
    ImageView imageView;
    ImageButton next;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_vehicle);
        type=(Spinner)findViewById(R.id.type);
        model=(Spinner)findViewById(R.id.company);
        company=(Spinner)findViewById(R.id.company);
        regno=(EditText)findViewById(R.id.year);
        year=(EditText)findViewById(R.id.year);
        imageView=(ImageView)findViewById(R.id.imageView);
        next=(ImageButton)findViewById(R.id.next);
        skip=(TextView)findViewById(R.id.skip);

        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String Regno=regno.getText().toString();
        String Year=year.getText().toString();
    }
}