package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class checkout extends AppCompatActivity {
    LinearLayout linearLayout;
    Button chat,placeorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        chat=(Button)findViewById(R.id.chat);
        placeorder=(Button)findViewById(R.id.placeorder);
    }
}