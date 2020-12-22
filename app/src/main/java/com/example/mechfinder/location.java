package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class location extends AppCompatActivity implements View.OnClickListener {
    TextView location;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        location=(TextView)findViewById(R.id.location);
        save=(Button)findViewById(R.id.save);


        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String Location=location.getText().toString();
    }
}