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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custome_reviews extends BaseAdapter {

    ArrayList<String>id,name,rating,feedback,date;
    private Context context;

    public Custome_reviews(Context appcontext, ArrayList<String>id, ArrayList<String>name, ArrayList<String> rating,ArrayList<String> feedback,ArrayList<String> date)
    {
        this.context=appcontext;
        this.id=id;
        this.name=name;
        this.rating=rating;
        this.feedback=feedback;
        this.date=date;





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
            gridView=inflator.inflate(R.layout.custome_reviews,null);

        }
        else
        {
            gridView=(View)view;

        }

        TextView tvname=(TextView)gridView.findViewById(R.id.textView28);
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView29);
        TextView tvfeedback=(TextView)gridView.findViewById(R.id.textView30);
        RatingBar tvrating=(RatingBar)gridView.findViewById(R.id.ratingBar);
//        TextView tv1=(TextView)gridView.findViewById(R.id.textView44);
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView15);

        tvname.setText(name.get(i));
        tvdate.setText(date.get(i));
        tvfeedback.setText(feedback.get(i));
        tvrating.setRating(Float.parseFloat(rating.get(i)));
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
