package com.example.mechfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    LinearLayout linearLayout,linearLayout2,linearLayout3;
    ListView listview;
    BottomNavigationView bottomnavigation;
    SharedPreferences sh;
    String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        linearLayout2=(LinearLayout)findViewById(R.id.linearLayout2);
        linearLayout3=(LinearLayout)findViewById(R.id.linearLayout3);
        listview=(ListView)findViewById(R.id.listview);
        bottomnavigation=(BottomNavigationView)findViewById(R.id.bttomnavigation);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_home";





        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);


                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();



                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
        
        
        bottomnavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home){
            Toast.makeText(this, "home clicked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId()==R.id.vehicle){

            Intent in=new Intent(getApplicationContext(),vehicle.class);
            startActivity(in);
            Toast.makeText(this, "vehicle clicked", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.bot){
            Intent in=new Intent(getApplicationContext(),bot.class);
            startActivity(in);
            Toast.makeText(this, "bot clicked", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.my_account){
            Intent in=new Intent(getApplicationContext(),my_account.class);
            startActivity(in);
            Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}