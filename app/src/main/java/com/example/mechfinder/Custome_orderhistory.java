package com.example.mechfinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custome_orderhistory extends BaseAdapter {

    ArrayList<String> id,shopname,vehicle_copany,vehicle_model,status1,amount;
    private Context context;

    public Custome_orderhistory(Context appcontext, ArrayList<String>id, ArrayList<String> shopname,ArrayList<String> vehicle_copany,ArrayList<String> status1,ArrayList<String> amount,ArrayList<String> vehicle_model)
    {
        this.context=appcontext;
        this.id=id;
//        this.servicename=servicename;
        this.shopname=shopname;
        this.vehicle_copany=vehicle_copany;
        this.vehicle_model=vehicle_model;
        this.status1=status1;
        this.amount=amount;
//        this.photo=photo;
//        this.discribtion=discribtion;
//        this.distributer_id=distributer_id;
//        this.status=status;
//
//


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
            gridView=inflator.inflate(R.layout.custome_orderhistory,null);

        }
        else
        {
            gridView=(View)view;

        }
//        TextView tvservicename=(TextView)gridView.findViewById(R.id.textView14);
        TextView tvshopname=(TextView)gridView.findViewById(R.id.textView15);
        TextView tvvehicle_company=(TextView)gridView.findViewById(R.id.textView16);
        TextView tvstatus=(TextView)gridView.findViewById(R.id.textView18);
        TextView tvamomunt=(TextView)gridView.findViewById(R.id.textView17);
        TextView tvvehicle_model=(TextView)gridView.findViewById(R.id.textView39);


//        tvservicename.setText(servicename.get(i));
        tvshopname.setText(shopname.get(i));
        tvvehicle_company.setText(vehicle_copany.get(i));
        tvvehicle_model.setText(vehicle_model.get(i));

        tvstatus.setText(status1.get(i));
        tvamomunt.setText(amount.get(i));
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView15);
//
//        tv1.setText(common_name_eng.get(i));
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String url= sh.getString("url","")+ photo.get(i);
//
//        Toast.makeText(context,url,Toast.LENGTH_LONG).show();;
//
//
//
//        Picasso.with(context).load(url).into(im);



//        tv1.setTextColor(Color.BLACK);
//
//
//        tv1.setText(gname[i]);
//
//
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String ip=sh.getString("ip","");
//
//        String url="http://" + ip + ":5000/static/game/"+gamecode[i]+".jpg";
//
//
//        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;

    }
}
