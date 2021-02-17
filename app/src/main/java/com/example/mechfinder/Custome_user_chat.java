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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custome_user_chat extends BaseAdapter {

    ArrayList<String> sid,shop_name,place,city,date,status;
    private Context context;

    public Custome_user_chat(Context appcontext, ArrayList<String>sid, ArrayList<String>shop_name, ArrayList<String> place,ArrayList<String> city,ArrayList<String> date,ArrayList<String> status)
    {
        this.context=appcontext;
        this.sid=sid;
        this.shop_name=shop_name;
        this.place=place;
        this.city=city;
        this.date=date;
        this.status=status;




    }

    @Override
    public int getCount() {
        return sid.size();
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
            gridView=inflator.inflate(R.layout.custome_user_chat,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvshop_name=(TextView)gridView.findViewById(R.id.textView50);
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView51);
        TextView tvpalce=(TextView)gridView.findViewById(R.id.textView56);
        TextView tvcity=(TextView)gridView.findViewById(R.id.textView57);
        TextView tvstatus=(TextView)gridView.findViewById(R.id.textView58);
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView15);
//
        tvshop_name.setText(shop_name.get(i));
        tvdate.setText(date.get(i));
        tvpalce.setText(place.get(i));
        tvcity.setText(city.get(i));
        tvstatus.setText(status.get(i));
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
