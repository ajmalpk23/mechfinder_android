package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
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

public class worckshop extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    LinearLayout linearLayout;
    RecyclerView rv;

    ArrayList<String> photo;


    TextView name,amount,place,city,district,email,phone;
    TableRow tableRow,tableRow2;
    ListView listView;
    Button reviews,checkout;
    SharedPreferences sh;
    String url="";
    public static ArrayList<String> id,image;
    public static ArrayList<String> serid,service,seramount;
    public static ArrayList<String> selecteditems;
    String alreadySelected="";
    int totalAmount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worckshop);
        linearLayout=(LinearLayout)findViewById(R.id.listv2);
        name=(TextView)findViewById(R.id.textView);
        amount=(TextView)findViewById(R.id.amount);
        tableRow=(TableRow)findViewById(R.id.tableRow);
        tableRow2=(TableRow)findViewById(R.id.tablerow2);
        listView=(ListView)findViewById(R.id.listview);
        reviews=(Button)findViewById(R.id.reviews);
        checkout=(Button)findViewById(R.id.checkout);
        place=(TextView)findViewById(R.id.textView2);
        city=(TextView)findViewById(R.id.textView6);
        district=(TextView)findViewById(R.id.textView10);
        email=(TextView)findViewById(R.id.textView12);
        phone=(TextView)findViewById(R.id.textView13);
        rv=(RecyclerView)findViewById(R.id.rv);





        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_workshop";
        alreadySelected=sh.getString("nn","");
        selecteditems=new ArrayList<>();
//        Toast.makeText(worckshop.this, "service"+alreadySelected, Toast.LENGTH_SHORT).show();

        reviews.setOnClickListener(this);
        checkout.setOnClickListener(this);
        serid=new ArrayList<>();
        service=new ArrayList<>();
        seramount=new ArrayList<>();
        Custome_workshop cus=new Custome_workshop(getApplicationContext(),service,seramount);
        listView.setAdapter(cus);



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                          Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("gall_status");
                            if(status.equalsIgnoreCase("ok"))
                            {
                                JSONArray ja= jsonObj.getJSONArray("gall_data");
                                photo=new ArrayList<>();
//                                nid=new ArrayList<>();
//                                title=new ArrayList<>();
//                                image=new ArrayList<>();
//                                date=new ArrayList<>();
//                                des=new ArrayList<>();


                                for ( int i=0;i< ja.length(); i++)
                                {
                                    JSONObject jd= ja.getJSONObject(i);
                                    photo.add(jd.getString(("image")));


//                                    nid.add(jd.getString("news_id"));
//                                    title.add(jd.getString("news_title"));
//                                    image.add(jd.getString("image"));
//                                    date.add(jd.getString("date"));
//                                    des.add(jd.getString("news_description"));

                                }
                                LinearLayoutManager layoutManager= new LinearLayoutManager(worckshop.this, LinearLayoutManager.HORIZONTAL, false);
                                rv.setLayoutManager(layoutManager);
                                rv.setAdapter(new gallery(getApplicationContext(),photo));

                            }







                            String statu_shop=jsonObj.getString("shop_status");
                            Toast.makeText(getApplicationContext(), "h"+statu_shop, Toast.LENGTH_LONG).show();

                            if(statu_shop.equalsIgnoreCase("1")){
                                String shop_name=jsonObj.getString("shop_name");
                                String email1=jsonObj.getString("email");
                                String phone1=jsonObj.getString("phone");
                                String place1=jsonObj.getString("place");
                                String city1=jsonObj.getString("city");
                                String district1=jsonObj.getString("district");
                                name.setText(shop_name);
                                email.setText(email1);
                                phone.setText(phone1);
                                place.setText(place1);
                                city.setText(city1);
                                district.setText(district1);

//                                JSONObject mm=jsonObj.getJSONObject("shop_data");
//                                name.setText(mm.getString("shop_name"));
//                                email.setText(mm.getString("email"));
//                                phone.setText(mm.getString("phone"));
//                                place.setText(mm.getString("place"));
//                                city.setText(mm.getString("city"));
//                                district.setText(mm.getString("district"));
                            }

                            String statusa=jsonObj.getString("gall_status");
                            if(statusa.equalsIgnoreCase("1")){
                                JSONArray ja= jsonObj.getJSONArray("gall_data");
                                id=new ArrayList<>();
                                image=new ArrayList<>();

                                for ( int i=0;i< ja.length(); i++){
                                    JSONObject jd= ja.getJSONObject(i);
                                    id.add(jd.getString("gallery_id"));
                                    image.add(jd.getString("image"));
                                }


                            }

                            String status_service=jsonObj.getString("ser_status");
                            if(status_service.equalsIgnoreCase("1")){
                                JSONArray ja= jsonObj.getJSONArray("ser_data");

                                for ( int i=0;i< ja.length(); i++){
                                    JSONObject jd= ja.getJSONObject(i);
                                    serid.add(jd.getString("service_id"));
                                    service.add(jd.getString("service"));
                                    seramount.add(jd.getString("amount"));
                                    if(alreadySelected.equalsIgnoreCase(jd.getString("service"))){
                                        selecteditems.add(i+"");

                                        totalAmount=totalAmount+Integer.parseInt(jd.getString("amount"));
                                        amount.setText(totalAmount+"");
                                    }

                                }

                                cus.notifyDataSetChanged();
                               if(selecteditems.size()>0){

                                   listView.getChildAt(Integer.parseInt(selecteditems.get(0))).setBackgroundColor(Color.GREEN);
                               }



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

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(reviews==v){
            Intent in=new Intent(getApplicationContext(),reviews.class);
            startActivity(in);
        }
        else if(checkout==v){
            sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor edt=sh.edit();
            edt.putString("totalamount",totalAmount+"");
            edt.commit();

            if(selecteditems.size()>0) {

                for(int m=0;m<selecteditems.size();m++)
                {
                    Toast.makeText(getApplicationContext(),selecteditems.get(m),Toast.LENGTH_LONG).show();
                }


                Intent in = new Intent(getApplicationContext(), checkout.class);
                startActivity(in);
            }




        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(selecteditems.contains(position+"")){
            listView.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);

            selecteditems.remove(position+"");
            totalAmount=totalAmount-Integer.parseInt(seramount.get(position));
            amount.setText(totalAmount+"");
        }
        else{
            selecteditems.add(position+"");
            listView.getChildAt(position).setBackgroundColor(Color.GREEN);
            totalAmount=totalAmount+Integer.parseInt(seramount.get(position));
            amount.setText(totalAmount+"");
        }


    }
}