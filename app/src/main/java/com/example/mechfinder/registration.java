package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import javax.microedition.khronos.egl.EGLDisplay;

public class registration extends AppCompatActivity implements View.OnClickListener {
    EditText name,password,conpassword,email,phone;
    ImageView userimage;
    ImageButton next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        conpassword=(EditText)findViewById(R.id.conpassword);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        next=(ImageButton)findViewById(R.id.next);
        userimage=(ImageView)findViewById(R.id.userimage);


        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String userName=name.getText().toString();
        String Password=password.getText().toString();
        String conPass=conpassword.getText().toString();
        String Email=email.getText().toString();
        String Phone=phone.getText().toString();
    }
}