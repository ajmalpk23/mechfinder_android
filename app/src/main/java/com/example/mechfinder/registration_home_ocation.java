package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registration_home_ocation extends AppCompatActivity implements View.OnClickListener {
    EditText place,city,district,pincode;

    Button next;
    SharedPreferences sh;
    String url="";
    String lid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_home_ocation);
        place=(EditText)findViewById(R.id.place);
        city=(EditText)findViewById(R.id.city);
        district=(EditText)findViewById(R.id.district);
        pincode=(EditText)findViewById(R.id.pincode);

        next=(Button) findViewById(R.id.next);
        lid=getIntent().getStringExtra("lid");

        next.setOnClickListener(this);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_sinup_locations";
    }

    @Override
    public void onClick(View v) {
        String _Place=place.getText().toString();
        String _City=city.getText().toString();
        String _District=district.getText().toString();
        String _Pincode=pincode.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("status");
                            if(status.equalsIgnoreCase("ok")){
                                Toast.makeText(registration_home_ocation.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent in=new Intent(getApplicationContext(),Registration_vehicle.class);
                                in.putExtra("lid",lid);
                                startActivity(in);
                            }
                            else{
                                Toast.makeText(registration_home_ocation.this, "Try again later", Toast.LENGTH_SHORT).show();
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

                params.put("place",_Place);
                params.put("city",_City);
                params.put("district",_District);
                params.put("pincode",_Pincode);
                params.put("lid", lid);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

        Intent in=new Intent(getApplicationContext(),Login.class);

        startActivity(in);
    }
}