package com.example.mechfinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Custome_vehicle extends BaseAdapter {

    ArrayList<String>id,vehicle_type,company,model,manfct,image,regno;

    private Context context;
//,vid,vtype,vcompany,vmodel,vreg,vmanyear,vimage
    public Custome_vehicle(Context appcontext, ArrayList<String>id, ArrayList<String>vehicle_type, ArrayList<String> company,ArrayList<String> model,ArrayList<String> regno,ArrayList<String> manfct,ArrayList<String> image)
    {
        this.context=appcontext;
        this.id=id;
        this.vehicle_type=vehicle_type;
        this.company=company;
        this.model=model;
        this.manfct=manfct;
        this.image=image;
        this.regno=regno;





    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custome_vehicle,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvcompany=(TextView)gridView.findViewById(R.id.textView19);
        TextView tvmodel=(TextView)gridView.findViewById(R.id.textView20);
        TextView tvtype=(TextView)gridView.findViewById(R.id.textView21);
        TextView tvmanf=(TextView)gridView.findViewById(R.id.textView22);
        TextView tv1reno=(TextView)gridView.findViewById(R.id.textView23);
        ImageView tvimage=(ImageView)gridView.findViewById(R.id.imageView10);
        ImageView tvbuttn=(ImageView)gridView.findViewById(R.id.buttn);
        ImageView tvbuttn2=(ImageView)gridView.findViewById(R.id.button7);

        tvcompany.setText(company.get(i));
        tvmodel.setText(model.get(i));
        tvtype.setText(vehicle_type.get(i));

        tv1reno.setText(regno.get(i));
        tvmanf.setText(manfct.get(i));

        tvbuttn.setTag(id.get(i));

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String url1=sh.getString("url","")+"static/user/vehicle/"+(image.get(i));
        Toast.makeText(context, "url=="+url1, Toast.LENGTH_LONG).show();
        Log.d("======>",url1);



        Picasso.with(context).load(url1).into(tvimage);





        tvbuttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vid=v.getTag().toString();
                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor edt=sh.edit();
                edt.putString("vid",vid);
                edt.commit();

                v.setBackgroundColor(Color.GREEN);
            }
        });

        tvbuttn2.setTag(id.get(i));
        tvbuttn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vid=v.getTag().toString();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String url=sh.getString("url","")+"and_delete_vehicle";

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

                                Intent in=new Intent(context,vehicle.class);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(in);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<>();

                        params.put("id",vid);


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
        });
        return gridView;

    }

}
