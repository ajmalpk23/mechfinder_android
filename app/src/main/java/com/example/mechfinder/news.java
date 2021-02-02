package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class news extends AppCompatActivity {

    SharedPreferences sh;
    String url="",path;
    TextView tittle,date,des;
    ImageView image;
    String nnid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        tittle=(TextView)findViewById(R.id.textView46);
        image=(ImageView) findViewById(R.id.imageView14);
        date=(TextView)findViewById(R.id.textView49);
        des=(TextView)findViewById(R.id.textView48);






        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_news";



        String datea=sh.getString("date","");
        String titlea=sh.getString("title","");
        String desa=sh.getString("des","");
        String photoa=sh.getString("photo","");
        date.setText(datea);
        tittle.setText(titlea);
        des.setText(desa);

        String url1=sh.getString("url","")+"static/news/"+photoa;



        Picasso.with(getApplicationContext()).load(url1).into(image);







    }
}