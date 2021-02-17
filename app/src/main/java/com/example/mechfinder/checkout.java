package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class checkout extends AppCompatActivity implements View.OnClickListener {
//    LinearLayout linearLayout;
    ImageView placeorder;
    SharedPreferences sh;
    String url="",url1="";
    TextView name,city,place,phone,email,username,vehicle,totalAmount1;
    ArrayList<String> id,image;
    ArrayList<String> serid,service,seramount;
    ArrayList<String> selecteditems;
    ListView lvs;
    String services="",amount_place="",total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
//        linearLayout=(LinearLayout)findViewById(R.id.lvs);

        placeorder=(ImageView) findViewById(R.id.placeorder);
        name=(TextView)findViewById(R.id.textView31);
        city=(TextView)findViewById(R.id.textView33);
        place=(TextView)findViewById(R.id.textView32);
        phone=(TextView)findViewById(R.id.textView37);
        email=(TextView)findViewById(R.id.textView36);
        username=(TextView)findViewById(R.id.textView34);
        vehicle=(TextView)findViewById(R.id.textView35);
        totalAmount1=(TextView)findViewById(R.id.textView38);
        lvs=(ListView)findViewById(R.id.listv2);



        placeorder.setOnClickListener(this);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_checkout_invoice";
        url1=sh.getString("url","")+"and_checkout";

        totalAmount1.setText(sh.getString("totalamount",""));
        total=sh.getString("totalamount","");
//
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
                            String shop_status1=jsonObj.getString("wokshop_status");
//                            Toast.makeText(getApplicationContext(),shop_status1,Toast.LENGTH_LONG).show();
                            if(shop_status1.equalsIgnoreCase("ok")){
                                JSONObject mm=jsonObj.getJSONObject("workshop_data");
                                name.setText(mm.getString("shop_name"));
                                email.setText(mm.getString("email"));
                                phone.setText(mm.getString("phone"));
                                place.setText(mm.getString("place"));
                                city.setText(mm.getString("city"));

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
                            String user_status=jsonObj.getString("user_status");
                            if(user_status.equalsIgnoreCase("ok")){
                                JSONObject mm=jsonObj.getJSONObject("user_data");
                                username.setText(mm.getString("name"));
//

//

//
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
                params.put("shop_id",sh.getString("shop_id",""));


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
//

//
        serid=new ArrayList<String>();
        service=new ArrayList<String>();
        seramount=new ArrayList<String>();
        try {


            for (int i = 0; i < worckshop.selecteditems.size(); i++) {
//                Toast.makeText(getApplicationContext(),worckshop.id.get(Integer.parseInt(worckshop.selecteditems.get(i)))+worckshop.service.get(Integer.parseInt(worckshop.selecteditems.get(i)))+worckshop.seramount.get(Integer.parseInt(worckshop.selecteditems.get(i))),Toast.LENGTH_LONG).show();


//
            serid.add(worckshop.serid.get(Integer.parseInt(worckshop.selecteditems.get(i))));
            service.add(worckshop.service.get(Integer.parseInt(worckshop.selecteditems.get(i))));
            seramount.add(worckshop.seramount.get(Integer.parseInt(worckshop.selecteditems.get(i))));
            services=services+"#"+worckshop.serid.get(Integer.parseInt(worckshop.selecteditems.get(i)));
            amount_place=amount_place+"#"+worckshop.seramount.get(Integer.parseInt(worckshop.selecteditems.get(i)));

            }
            lvs.setAdapter(new Custome_checkout(getApplicationContext(), serid,service,seramount));

        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onClick(View v) {


        if(placeorder==v){

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url1,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
//                            String status=jsonObj.getString("add_complaint_status");
//                            if(status.equalsIgnoreCase("ok")){
////                                Toast.makeText(complant_support.this, "success", Toast.LENGTH_SHORT).show();
//                            }
//                            else{
////                                Toast.makeText(complant_support.this, "try again later", Toast.LENGTH_SHORT).show();
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

//                params.put("complaint",Complaint);
                    params.put("lid",sh.getString("lid",""));
                    params.put("Vid",sh.getString("vid",""));
                    params.put("shop_id",sh.getString("shop_id",""));
                    params.put("servicecs",services);
                    params.put("amount_place",amount_place);
                    params.put("total",total);



                    return params;
                }
            };

            int MY_SOCKET_TIMEOUT_MS=100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);


            Intent in = new Intent(getApplicationContext(), order_history.class);
            startActivity(in);

        }



    }
}