package com.example.mechfinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custome_vehicle extends BaseAdapter implements View.OnClickListener {

    ArrayList<String>id,vehicle_type,company,model,manfct,image,regno;

    private Context context;
//,vid,vtype,vcompany,vmodel,vreg,vmanyear,vimage
    public Custome_vehicle(Context appcontext, ArrayList<String>id, ArrayList<String>vehicle_type, ArrayList<String> company,ArrayList<String> model,ArrayList<String> manfct,ArrayList<String> image,ArrayList<String> regno)
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
        Button tvbuttn=(Button)gridView.findViewById(R.id.buttn);

        tvcompany.setText(company.get(i));
        tvmodel.setText(model.get(i));
        tvtype.setText(vehicle_type.get(i));
        tvmanf.setText(manfct.get(i));
        tv1reno.setText(regno.get(i));

        tvbuttn.setTag(id.get(i));
        tvbuttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return gridView;

    }

    @Override
    public void onClick(View v) {
        String vid=v.getTag().toString();
        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edt=sh.edit();
        edt.putString("vid",vid);
        edt.commit();

        v.setBackgroundColor(Color.GREEN);
    }
}
