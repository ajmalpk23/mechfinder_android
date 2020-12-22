package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText username,password;
    TextView forgot_password,singup;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        forgot_password=(TextView)findViewById(R.id.forgot_password);
        singup=(TextView)findViewById(R.id.singup);


        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        String userName=username.getText().toString();
        String Password=password.getText().toString();

    }
}