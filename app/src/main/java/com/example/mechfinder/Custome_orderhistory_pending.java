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

public class Custome_orderhistory_pending extends BaseAdapter {

    ArrayList<String>serid,service,seramount;
    private Context context;

    public Custome_orderhistory_pending(Context appcontext, ArrayList<String>serid, ArrayList<String>service, ArrayList<String> seramount)
    {
        this.context=appcontext;
        this.serid=serid;
        this.service=service;
        this.seramount=seramount;
//        this.scientific_name=scientific_name;
//        this.disease=disease;
//        this.fertilizer=fertilizer;
//        this.photo=photo;
//        this.discribtion=discribtion;
//        this.distributer_id=distributer_id;
//        this.status=status;




    }

    @Override
    public int getCount() {
        return serid.size();
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
            gridView=inflator.inflate(R.layout.custome_orderhistory_pending,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvservice=(TextView)gridView.findViewById(R.id.textView40);
        TextView tvamount=(TextView)gridView.findViewById(R.id.textView41);
        tvservice.setText(service.get(i));
        tvamount.setText(seramount.get(i));
//        TextView tv1=(TextView)gridView.findViewById(R.id.textView44);
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
