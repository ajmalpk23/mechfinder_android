package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class user_chat extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SharedPreferences sh;
    String url="";
    ListView lv3;
    ArrayList<String> sid,shop_id,shop_name,place,city,date,sta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chat);
        lv3=(ListView)findViewById(R.id.lv3);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_user_chat";








        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("status");
                            if(status.equalsIgnoreCase("ok"))
                            {
                                JSONArray ja= jsonObj.getJSONArray("data");
                                sid=new ArrayList<>();
                                shop_id=new ArrayList<>();
                                shop_name=new ArrayList<>();
                                place=new ArrayList<>();
                                city=new ArrayList<>();
                                date=new ArrayList<>();
                                sta=new ArrayList<>();


//                                amount=new ArrayList<>();

                                for ( int i=0;i< ja.length(); i++)
                                {
                                    JSONObject jd= ja.getJSONObject(i);
                                    sid.add(jd.getString("service_request_id"));
                                    shop_id.add(jd.getString("workshop_id"));
                                    shop_name.add(jd.getString("shop_name"));
                                    place.add(jd.getString("place"));
                                    city.add(jd.getString("city"));
                                    date.add(jd.getString("request_date"));
                                    sta.add(jd.getString("status"));



                                }
                                lv3.setAdapter(new Custome_user_chat(getApplicationContext(), sid,shop_name,place,city,date,sta));




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

        lv3.setOnItemClickListener(this);




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent(getApplicationContext(), Chat.class);
        in.putExtra("shop_id",shop_id.get(position));
        startActivity(in);
    }
}