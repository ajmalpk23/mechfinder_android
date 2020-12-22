package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class saveip extends AppCompatActivity implements View.OnClickListener {
    EditText ip;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveip);
        ip=(EditText)findViewById(R.id.ip);
        save=(Button)findViewById(R.id.save);


        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String Ip=ip.getText().toString();
    }
}