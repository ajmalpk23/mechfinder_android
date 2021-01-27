package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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

public class home_services extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    LinearLayout linearLayout;
    SharedPreferences sh;
    String url="";

    ArrayList<String> id,shop_name,place,phone,amount;

    ListView lvs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_services);
        imageView=(ImageView)findViewById(R.id.imageView2);
        textView=(TextView)findViewById(R.id.textView);
        linearLayout=(LinearLayout)findViewById(R.id.linearlayout);
        lvs=(ListView) findViewById(R.id.lvs);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_nearest";




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
                                id=new ArrayList<>();
                                shop_name=new ArrayList<>();
                                place=new ArrayList<>();
                                phone=new ArrayList<>();
                                amount=new ArrayList<>();

                                for ( int i=0;i< ja.length(); i++)
                                {
                                    JSONObject jd= ja.getJSONObject(i);
                                    id.add(jd.getString("id"));
                                    shop_name.add(jd.getString("shop_name"));
                                    place.add(jd.getString("place"));
                                    phone.add(jd.getString("phone"));
                                    amount.add(jd.getString("amount"));


                                }
                                lvs.setAdapter(new Custome_homeservice(getApplicationContext(), id,shop_name,place,phone,amount));




                            }








                            Toast.makeText(home_services.this, status, Toast.LENGTH_SHORT).show();


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
                params.put("lati",LocationService.lati);
                params.put("longi",LocationService.logi);
                params.put("service",sh.getString("nn","")  );



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