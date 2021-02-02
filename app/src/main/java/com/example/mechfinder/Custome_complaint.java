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

public class Custome_complaint extends BaseAdapter {

    ArrayList<String> comp,cdate,reply,rdate;
    private Context context;

    public Custome_complaint(Context appcontext, ArrayList<String>comp, ArrayList<String>cdate, ArrayList<String> reply,ArrayList<String> replaydate)
    {
        this.context=appcontext;
        this.comp=comp;
        this.cdate=cdate;
        this.reply=reply;
        this.rdate=replaydate;
//        this.common_name_eng=common_name_eng;
//        this.common_name_mal=common_name_mal;
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
        return comp.size();
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
            gridView=inflator.inflate(R.layout.custome_complaint,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvrdate=(TextView)gridView.findViewById(R.id.textView52);
        TextView tvreplay=(TextView)gridView.findViewById(R.id.textView53);
        TextView tvcdate=(TextView)gridView.findViewById(R.id.textView54);
        TextView tvcomplaint=(TextView)gridView.findViewById(R.id.textView55);

        tvrdate.setText(rdate.get(i));
        tvrdate.setText(reply.get(i));
        tvcdate.setText(rdate.get(i));
        tvcomplaint.setText(comp.get(i));
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
