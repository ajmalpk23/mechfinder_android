package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class profile_location extends AppCompatActivity implements View.OnClickListener {
    EditText place,city,district,pincode;
    Button save;
    SharedPreferences sh;
    String url1="",url2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_location);
        place=(EditText) findViewById(R.id.place);
        city=(EditText) findViewById(R.id.city);
        district=(EditText) findViewById(R.id.district);
        pincode=(EditText) findViewById(R.id.pincode);
        save=(Button)findViewById(R.id.button11);


        save.setOnClickListener(this);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        url1=sh.getString("url","")+"and_profile_loc";
        url2=sh.getString("url","")+"and_edit_profile_loc";
        viewprofilelocation();
    }

    @Override
    public void onClick(View v) {
        String Place=place.getText().toString();
        String City=city.getText().toString();
        String District=district.getText().toString();
        String Pincode=pincode.getText().toString();


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url1=sh.getString("ur2","")+"and_edit_profile_loc";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("edt_profile_loc_status");
                            if(status.equalsIgnoreCase("ok")){
                                Toast.makeText(profile_location.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(profile_location.this, "Failed", Toast.LENGTH_SHORT).show();
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

                params.put("place",Place);
                params.put("city",City);
                params.put("district",District);
                params.put("pincode",Pincode);
                params.put("lid", sh.getString("lid", ""));

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
    private  void viewprofilelocation(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("profile_loc_status");

                            if(status.equalsIgnoreCase("ok")){
                                JSONObject mm=jsonObj.getJSONObject("profile_loc_vlid");
                                place.setText(mm.getString("place"));
                                city.setText(mm.getString("city"));
                                district.setText(mm.getString("district"));
                                pincode.setText(mm.getString("pincode"));

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

                params.put("lid",sh.getString("lid",""));

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