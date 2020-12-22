package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class profile_personal extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    EditText name,phone,email;
    Button save,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_personal);
        imageView=(ImageView)findViewById(R.id.imageView);
        name=(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        phone=(EditText) findViewById(R.id.phone);
        save=(Button)findViewById(R.id.save);
        location=(Button)findViewById(R.id.location);

        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String Name=name.getText().toString();
        String Phone=phone.getText().toString();
        String Email=email.getText().toString();
    }
}