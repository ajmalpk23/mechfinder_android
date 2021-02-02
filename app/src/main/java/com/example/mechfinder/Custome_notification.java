package com.example.mechfinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custome_notification extends BaseAdapter {

    ArrayList<String> image,sub,date,des;
    private Context context;

    public Custome_notification(Context appcontext, ArrayList<String>image, ArrayList<String>sub, ArrayList<String> date,ArrayList<String> des)
    {
        this.context=appcontext;
        this.image=image;
        this.sub=sub;
        this.date=date;
        this.des=des;

//






    }

    @Override
    public int getCount() {
        return sub.size();
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
            gridView=inflator.inflate(R.layout.custome_notification,null);

        }
        else
        {
            gridView=(View)view;

        }


        TextView tvsub=(TextView)gridView.findViewById(R.id.textView24);
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView25);
        TextView tvdes=(TextView)gridView.findViewById(R.id.textView26);
        ImageView image1=(ImageView) gridView.findViewById(R.id.imageView11);

        tvsub.setText(sub.get(i));
        tvdate.setText(date.get(i));
        tvdes.setText(des.get(i));
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String url1=sh.getString("url","")+"static/notification/"+(image.get(i));




        Picasso.with(context).load(url1).into(image1);
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
