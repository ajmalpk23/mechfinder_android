package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class app_feedback extends AppCompatActivity implements View.OnClickListener {
    EditText feedback;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_feedback);
        feedback=(EditText)findViewById(R.id.feedback);
        button=(Button)findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        String Feedback=feedback.getText().toString();
    }
}