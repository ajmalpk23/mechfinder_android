package com.example.mechfinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Custome_homeservice extends BaseAdapter implements View.OnClickListener {

    ArrayList<String>id,shop_name,place,phone,amount;
    SharedPreferences sh;
    private Context context;

    public Custome_homeservice(Context appcontext, ArrayList<String>id, ArrayList<String>shop_name, ArrayList<String> place, ArrayList<String> phone, ArrayList<String> amount)
    {
        this.context=appcontext;
        this.id=id;
        this.shop_name=shop_name;
        this.place=place;
        this.phone=phone;
        this.amount=amount;





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
            gridView=inflator.inflate(R.layout.custome_home_service,null);

        }
        else
        {
            gridView=(View)view;

        }


        TextView tvshopname=(TextView)gridView.findViewById(R.id.textView7);
        TextView tvplace=(TextView)gridView.findViewById(R.id.textView8);
        TextView tvphone=(TextView)gridView.findViewById(R.id.textView9);
        TextView tvamount=(TextView)gridView.findViewById(R.id.textView27);
        Button go=(Button)gridView.findViewById(R.id.button2);

        tvshopname.setText(shop_name.get(i));
        tvplace.setText(place.get(i));
        tvphone.setText(phone.get(i));
        tvamount.setText(amount.get(i));


        go.setTag(id.get(i));


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "ii="+v,Toast.LENGTH_SHORT).show();

                String shopid= v.getTag().toString();
                sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor edt=sh.edit();
                edt.putString("shop_id",shopid);
                edt.commit();

                Intent ins= new Intent(context,worckshop.class);
                ins.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ins);
            }
        });


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




        return gridView;

    }

    @Override
    public void onClick(View v) {

    }
}
