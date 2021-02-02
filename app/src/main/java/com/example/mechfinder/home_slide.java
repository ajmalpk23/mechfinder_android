package com.example.mechfinder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class home_slide extends RecyclerView.Adapter<home_slide.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    int[] a;
//    ArrayList<String> photo;

    public home_slide(Context context, int a[]) {
        inflater = LayoutInflater.from(context);
        this.context = context;

        this.a=a;



    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_home_slide, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.serial_number.setText(photo.get(position));


        holder.imageView.setImageResource(a[position]);


//        holder.imageView.setTag(position);
//
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int pos= (int) v.getTag();
//
//                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
//
//                SharedPreferences.Editor ed=sh.edit();
//                ed.putString("date",date.get(pos));
//                ed.putString("title",title.get(pos));
//                ed.putString("photo",photo.get(pos));
//                ed.putString("des",des.get(pos));
//
//
//                ed.commit();
//
//
//                Intent ins= new Intent(context,news.class);
//
//                ins.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(ins);
//
//
//
//
//
//            }
//        });

//        Intent ins= new Intent(context,news.class);
//        ins.putExtra("nid",nid);
//        ins.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(ins);

//        SharedPreferences.Editor edt=sh.edit();
//        edt.putString("fff",url);
//
//        edt.commit();

    }

    @Override
    public int getItemCount() {
        return a.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        //        TextView serial_number;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
//            serial_number = (TextView)itemView.findViewById(R.id.textView47);
            imageView = (ImageView) itemView.findViewById(R.id.imageView16);




        }
    }
}