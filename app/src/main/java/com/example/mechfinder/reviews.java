package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.List;

public class reviews extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    RatingBar ratingBar;
    EditText editText;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        listView=(ListView)findViewById(R.id.listview);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        editText=(EditText)findViewById(R.id.edittext);
        send=(Button)findViewById(R.id.send);


        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String review=editText.getText().toString();
        float rating=ratingBar.getRating();
    }
}