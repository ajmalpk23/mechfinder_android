package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class order_history extends AppCompatActivity implements AdapterView.OnItemClickListener {
//    LinearLayout linearlayout;
    ListView listview1;
    SharedPreferences sh;
    String url="";
    ArrayList<String> service_requestid,shopname,vehicle_company,status1,amount1,vehicle_model1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
//        linearlayout=(LinearLayout)findViewById(R.id.lvs);
        listview1=(ListView)findViewById(R.id.listview1);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_order_history";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("order_history_status");
                            if(status.equalsIgnoreCase("ok"))
                            {
                                JSONArray ja= jsonObj.getJSONArray("order_history_data");
                                service_requestid=new ArrayList<>();
//                                servicename=new ArrayList<>();
                                shopname=new ArrayList<>();
                                amount1=new ArrayList<>();
                                status1=new ArrayList<>();
                                vehicle_company=new ArrayList<>();
                                vehicle_model1=new ArrayList<>();


                                for ( int i=0;i< ja.length(); i++)
                                {
                                    JSONObject jd= ja.getJSONObject(i);
                                    service_requestid.add(jd.getString("service_request_id"));
//                                    servicename.add(jd.getString("service"));
                                    shopname.add(jd.getString("shop_name"));
                                    amount1.add(jd.getString("payment"));
                                    status1.add(jd.getString("status"));
                                    vehicle_company.add(jd.getString("company"));
                                    vehicle_model1.add(jd.getString("model"));


                                }
//                                Custome_orderhistory(Context appcontext, ArrayList<String>id, ArrayList<String>servicename, ArrayList<String> shopname,ArrayList<String> vehicle_copany,ArrayList<String> status1,ArrayList<String> amount,ArrayList<String> vehicle_model)
                                listview1.setAdapter(new Custome_orderhistory(getApplicationContext(),service_requestid,shopname,vehicle_company,status1,amount1,vehicle_model1));
//                                listview1.setAdapter(new Custome_orderhistory(getApplicationContext(),servicename,shopname,amount,status1,vehicle_company,vehi));




                            }
                            else{
                                Toast.makeText(order_history.this, "No data", Toast.LENGTH_SHORT).show();
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
                params.put("lid", sh.getString("lid", ""));
//                params.put("vid",sh.getString("vid",""));




                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

        listview1.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(status1.get(position).equalsIgnoreCase("pending")){
            Intent in = new Intent(getApplicationContext(),orderhistory_proceesing.class);
            in.putExtra("srid",service_requestid.get(position));
            startActivity(in);

        }
        else if(status1.get(position).equalsIgnoreCase("approved")){

            Intent in = new Intent(getApplicationContext(), orderhistory_pending.class);
            in.putExtra("srid",service_requestid.get(position));
            startActivity(in);

        }
        else if(status1.get(position).equalsIgnoreCase("rejected")){
            Intent in = new Intent(getApplicationContext(), orderhistory_rejected.class);
            in.putExtra("srid",service_requestid.get(position));
            startActivity(in);

        }
        else if(status1.get(position).equalsIgnoreCase("done")){
            Intent in = new Intent(getApplicationContext(), orderhistory_approved.class);
            in.putExtra("srid",service_requestid.get(position));
            startActivity(in);

        }
        else if (status1.get(position).equalsIgnoreCase("paid")){
            Intent in = new Intent(getApplicationContext(), orderhistory_success.class);
            in.putExtra("srid",service_requestid.get(position));
            startActivity(in);

        }

    }
}