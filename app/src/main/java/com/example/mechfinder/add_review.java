package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class add_review extends AppCompatActivity implements View.OnClickListener {
    Button send;
    RatingBar rating;
    EditText feedback;
    SharedPreferences sh;
    String url="",srid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        send=(Button)findViewById(R.id.button6);
        rating=(RatingBar)findViewById(R.id.ratingBar2);
        feedback=(EditText)findViewById(R.id.editTextTextMultiLine);

        Toast.makeText(this, "insss", Toast.LENGTH_SHORT).show();

        srid=getIntent().getStringExtra("srid");

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_add_review";
        send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        final String fd=feedback.getText().toString();
        final float rtn=rating.getRating();
//


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if(jsonObj.getString("edt_profile_status").equalsIgnoreCase("ok")){
                                Toast.makeText(add_review.this, "success", Toast.LENGTH_SHORT).show();
                                Intent in=new Intent(getApplicationContext(),home.class);
                                startActivity(in);
                            }
                            else{
                                Toast.makeText(add_review.this, "failed to add", Toast.LENGTH_SHORT).show();
                            }


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
                params.put("feedback",fd);
                params.put("rating",rtn+"");
                params.put("srid",srid);


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }
}