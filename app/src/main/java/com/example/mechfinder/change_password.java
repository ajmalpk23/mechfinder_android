package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class change_password extends AppCompatActivity implements View.OnClickListener {
    EditText cupass,newpass,conpass;
    Button changepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        cupass=(EditText)findViewById(R.id.cupass);
        newpass=(EditText)findViewById(R.id.newpass);
        conpass=(EditText)findViewById(R.id.conpass);
        changepass=(Button)findViewById(R.id.changepass);

        changepass.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String curPass=cupass.getText().toString();
        String newPass=newpass.getText().toString();
        String conPass=conpass.getText().toString();

    }
}