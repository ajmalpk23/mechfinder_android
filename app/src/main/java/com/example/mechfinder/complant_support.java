package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class complant_support extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linearLayout;
    EditText complaint;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complant_support);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        complaint=(EditText)findViewById(R.id.complaint);
        send=(Button)findViewById(R.id.send);


        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String Complaint=complaint.getText().toString();
    }
}