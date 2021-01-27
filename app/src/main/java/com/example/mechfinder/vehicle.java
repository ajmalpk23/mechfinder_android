package com.example.mechfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.ImageButton;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class vehicle extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ImageButton add;
    ListView lvs;
    BottomNavigationView bottomNavigationView;
    SharedPreferences sh;
    String url="";
    ArrayList<String> vid,vtype,vcompany,vmodel,vreg,vmanyear,vimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        add=(ImageButton)findViewById(R.id.add);
        lvs=(ListView)findViewById(R.id.lvs);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavigationView);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_vehicle";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("veh_status");
                            if(status.equalsIgnoreCase("1"))
                            {
                                JSONArray ja= jsonObj.getJSONArray("veh_data");
                                vid=new ArrayList<>();
                                vtype=new ArrayList<>();
                                vcompany=new ArrayList<>();
                                vmodel=new ArrayList<>();
                                vreg=new ArrayList<>();
                                vmanyear=new ArrayList<>();
                                vimage=new ArrayList<>();

                                for ( int i=0;i< ja.length(); i++)
                                {
                                    JSONObject jd= ja.getJSONObject(i);
                                    vid.add(jd.getString("vehicle_id"));
                                    vtype.add(jd.getString("vehicle_type"));
                                    vcompany.add(jd.getString("company"));
                                    vmodel.add(jd.getString("model"));
                                    vreg.add(jd.getString("regno"));
                                    vmanyear.add(jd.getString("manfctr_year"));
                                    vimage.add(jd.getString("image"));


                                }
                                lvs.setAdapter(new Custome_vehicle(getApplicationContext(),vid,vtype,vcompany,vmodel,vreg,vmanyear,vimage));




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
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.vehicle){
            Toast.makeText(this, "vehicle section", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId()==R.id.home){

            Intent in=new Intent(getApplicationContext(),home.class);
            startActivity(in);
            Toast.makeText(this, "home section 2", Toast.LENGTH_SHORT).show();

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