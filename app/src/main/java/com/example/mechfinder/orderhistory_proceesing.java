package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class orderhistory_proceesing extends AppCompatActivity implements View.OnClickListener {
    ImageView chat;
    String service_requestid,shop_id;
    SharedPreferences sh;
    String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory_proceesing);
        chat=(ImageView)findViewById(R.id.imageView25);


        service_requestid=getIntent().getStringExtra("srid");

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_orderhistory_pending";


        chat.setOnClickListener(this);
        Toast.makeText(getApplicationContext(),"hai"+service_requestid,Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"hai"+url,Toast.LENGTH_SHORT).show();




        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String shop_status1=jsonObj.getString("wokshop_status");
//                            Toast.makeText(getApplicationContext(),shop_status1,Toast.LENGTH_LONG).show();
                            if(shop_status1.equalsIgnoreCase("ok")){
                                JSONObject mm=jsonObj.getJSONObject("workshop_data");
//                                shopname.setText(mm.getString("shop_name"));
//                                email.setText(mm.getString("email"));
//                                phone.setText(mm.getString("phone"));
//                                place.setText(mm.getString("place"));
//                                city.setText(mm.getString("city"));
//                                vehicle_compnay.setText(mm.getString("company"));
//                                vehicle_model.setText(mm.getString("model"));
//                                vehicle_regno.setText(mm.getString("regno"));
//                                totalamount1.setText(mm.getString("payment"));
                                shop_id=(mm.getString("workshop_id"));

//                                String shop_name1=jsonObj.getString("shop_name");
//                                String email1=jsonObj.getString("email");
//                                String phone1=jsonObj.getString("phone");
//                                String place1=jsonObj.getString("place");
//                                String city1=jsonObj.getString("city");
//
//                                name.setText(shop_name1);
//                                email.setText(email1);
//                                phone.setText(phone1);
//                                place.setText(place1);
//                                city.setText(city1);
//

//
                            }
//                            String user_status=jsonObj.getString("user_status");
//                            if(user_status.equalsIgnoreCase("ok")){
//                                JSONObject mm=jsonObj.getJSONObject("user_data");
//                                username.setText(mm.getString("name"));
////
//
////
//
////
//                            }
//                            String status=jsonObj.getString("service_status");
//                            if(status.equalsIgnoreCase("ok"))
//                            {
//                                JSONArray ja= jsonObj.getJSONArray("service_data");
////                                service_requestid=new ArrayList<>();
//////                                servicename=new ArrayList<>();
////                                shopname=new ArrayList<>();
////                                amount=new ArrayList<>();
////                                status1=new ArrayList<>();
////                                vehicle_company=new ArrayList<>();
////                                vehicle_model1=new ArrayList<>();
//                                service_id=new ArrayList<>();
//                                service=new ArrayList<>();
//                                seramount=new ArrayList<>();
//
//
//                                for ( int i=0;i< ja.length(); i++)
//                                {
//                                    JSONObject jd= ja.getJSONObject(i);
//                                    service_id.add(jd.getString("service_request_id"));
//                                    service.add(jd.getString("service"));
//                                    seramount.add(jd.getString("amount"));
////                                    service_requestid.add(jd.getString("service_request_id"));
//////                                    servicename.add(jd.getString("service"));
////                                    shopname.add(jd.getString("shop_name"));
////                                    amount.add(jd.getString("payment"));
////                                    status1.add(jd.getString("status"));
////                                    vehicle_company.add(jd.getString("company"));
////                                    vehicle_model1.add(jd.getString("model"));
//
//
//                                }
////                                Custome_orderhistory(Context appcontext, ArrayList<String>id, ArrayList<String>servicename, ArrayList<String> shopname,ArrayList<String> vehicle_copany,ArrayList<String> status1,ArrayList<String> amount,ArrayList<String> vehicle_model)
//                                listv2.setAdapter(new Custome_orderhistory_pending(getApplicationContext(),service_id,service,seramount));
////                                listview1.setAdapter(new Custome_orderhistory(getApplicationContext(),servicename,shopname,amount,status1,vehicle_company,vehi));
//
//
//
//
//                            }




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
                params.put("sid",service_requestid);
//                params.put("shop_id",sh.getString("shop_id",""));


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

    @Override
    public void onClick(View v) {
        Intent in = new Intent(getApplicationContext(), Chat.class);
        in.putExtra("shop_id",shop_id);
        startActivity(in);
    }
}