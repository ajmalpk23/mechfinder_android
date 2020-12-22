package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

public class worckshop extends AppCompatActivity {
    LinearLayout linearLayout;
    TextView textView,amount;
    TableRow tableRow,tableRow2;
    ListView listView;
    Button reviews,checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worckshop);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        textView=(TextView)findViewById(R.id.textView);
        amount=(TextView)findViewById(R.id.amount);
        tableRow=(TableRow)findViewById(R.id.tableRow);
        tableRow2=(TableRow)findViewById(R.id.tablerow2);
        listView=(ListView)findViewById(R.id.listview);
        reviews=(Button)findViewById(R.id.reviews);
        checkout=(Button)findViewById(R.id.checkout);

    }
}