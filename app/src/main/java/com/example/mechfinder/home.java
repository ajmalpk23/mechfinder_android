package com.example.mechfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    LinearLayout linearLayout;
    ListView listview;
    RecyclerView recView,rvc;
    BottomNavigationView bottomnavigation;
    SharedPreferences sh;
    String url="";
    ImageView paitingpic,batteriespic,waterpic,tyerspic,lightpic,detailedpic,accessoriespic,oilpic;
    TextView paiting,batteries,water,tyers,light,detailed,accessories,oil;
    String[] lll={"asdfghjksdfdsfs","sdaddaDSA","ADsdADsadsad","aDSADDSADSA","DSFSDFD"};
    ArrayList<String> nid,image,title,date,des;
    ArrayList<String> vimage;



    public  void getnews()
    {


        String url2=sh.getString("url","")+"and_home_news";
        Toast.makeText(getApplicationContext(), url2, Toast.LENGTH_LONG).show();




        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("n_status");
                            if(status.equalsIgnoreCase("ok"))
                            {
                                JSONArray ja= jsonObj.getJSONArray("n_data");
                                nid=new ArrayList<>();
                                title=new ArrayList<>();
                                image=new ArrayList<>();
                                date=new ArrayList<>();
                                des=new ArrayList<>();


                                for ( int i=0;i< ja.length(); i++)
                                {
                                    JSONObject jd= ja.getJSONObject(i);
                                    nid.add(jd.getString("news_id"));
                                    title.add(jd.getString("news_title"));
                                    image.add(jd.getString("image"));
                                    date.add(jd.getString("date"));
                                    des.add(jd.getString("news_description"));

                                }
                                LinearLayoutManager layoutManager= new LinearLayoutManager(home.this, LinearLayoutManager.HORIZONTAL, false);
                                recView.setLayoutManager(layoutManager);
                                recView.setAdapter(new home_news(getApplicationContext(),nid,title,image,date,des));

                            }


                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Errorgggggggggggggggggggggggg" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeegggggggggggggge" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
//                params.put("login_id",sh.getString("lid",""));



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
    int [] a= new int[] { R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.customactionbar);
        //getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        TextView name = view.findViewById(R.id.name);
        ImageView noti = view.findViewById(R.id.noti);
        ImageView vehi = view.findViewById(R.id.vehpic);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_home_vehicle";



        noti.setOnClickListener(this);
        name.setText(LocationService.place);


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("v_status");
                            if(status.equalsIgnoreCase("ok"))
                            {
                                JSONArray ja= jsonObj.getJSONArray("v_data");

                                vimage=new ArrayList<>();



                                for ( int i=0;i< ja.length(); i++)
                                {
                                    JSONObject jd= ja.getJSONObject(i);

//                                    vimage.add(jd.getString("image"));
                                    SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    String url1=sh.getString("url","")+"static/user/vehicle/"+(jd.getString("image"));




                                    Picasso.with(getApplicationContext()).load(url1).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(vehi);

//


                                }
                                 LinearLayoutManager layoutManager= new LinearLayoutManager(home.this, LinearLayoutManager.HORIZONTAL, false);
//                                recView.setLayoutManager(layoutManager);
//                                recView.setAdapter(new home_news(getApplicationContext(),nid,title,image,date,des));

                            }


                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("vid",sh.getString("vid",""));



                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);







        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(home.this, "profile personal", Toast.LENGTH_SHORT).show();

                Intent in = new Intent(getApplicationContext(), notification.class);
                startActivity(in);
            }
        });







        linearLayout=(LinearLayout)findViewById(R.id.listv2);


        listview=(ListView)findViewById(R.id.listview);
        rvc=(RecyclerView) findViewById(R.id.rvc);


        recView=(RecyclerView) findViewById(R.id.rec);

        bottomnavigation=(BottomNavigationView)findViewById(R.id.bttomnavigation);

        bottomnavigation.setItemIconTintList(null);


        paitingpic=(ImageView)findViewById(R.id.paintingpic);
        batteriespic=(ImageView)findViewById(R.id.Batteriespic);
        waterpic=(ImageView)findViewById(R.id.WaterServicepic);
        tyerspic=(ImageView)findViewById(R.id.Tyrespic);
        lightpic=(ImageView)findViewById(R.id.Lightspic);
        detailedpic=(ImageView)findViewById(R.id.Deatiledpic);
        accessoriespic=(ImageView)findViewById(R.id.Accessoriespic);
        oilpic=(ImageView)findViewById(R.id.Windsheidspic);

        batteries=(TextView) findViewById(R.id.Batteries);
        paiting=(TextView)findViewById(R.id.painting);
        water=(TextView) findViewById(R.id.WaterService);
        tyers=(TextView) findViewById(R.id.Tyres);
        light=(TextView) findViewById(R.id.Lights);
        detailed=(TextView) findViewById(R.id.Deatiled);
        accessories=(TextView) findViewById(R.id.Accessories);
        oil=(TextView) findViewById(R.id.Windsheids);









        paiting.setOnClickListener(this);
        paitingpic.setOnClickListener(this);
        batteries.setOnClickListener(this);
        batteriespic.setOnClickListener(this);
        water.setOnClickListener(this);
        waterpic.setOnClickListener(this);
        tyers.setOnClickListener(this);
        tyerspic.setOnClickListener(this);
        light.setOnClickListener(this);
        lightpic.setOnClickListener(this);
        detailed.setOnClickListener(this);
        detailedpic.setOnClickListener(this);
        accessories.setOnClickListener(this);
        accessoriespic.setOnClickListener(this);
        oil.setOnClickListener(this);
        oilpic.setOnClickListener(this);


        getnews();


        LinearLayoutManager layoutManager= new LinearLayoutManager(home.this, LinearLayoutManager.HORIZONTAL, false);
        rvc.setLayoutManager(layoutManager);
        rvc.setAdapter(new home_slide(getApplicationContext(),a));















        
        bottomnavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.chat111){
            Toast.makeText(this, "home clicked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId()==R.id.vehicle){

            Intent in=new Intent(getApplicationContext(),vehicle.class);
            startActivity(in);
            Toast.makeText(this, "vehicle clicked", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.bot){
            Intent in=new Intent(getApplicationContext(),bot.class);
            startActivity(in);
            Toast.makeText(this, "bot clicked", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.my_account){
            Intent in=new Intent(getApplicationContext(),my_account.class);
            startActivity(in);
            Toast.makeText(this, "account clicked", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onClick(View v) {


        if(paiting==v){
            String kk=paiting.getText().toString();


            SharedPreferences.Editor edt=sh.edit();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);


        }
        else if (paitingpic==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=paiting.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);


        }
        else if(batteries==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=batteries.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(batteriespic==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=batteries.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(water==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=water.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(waterpic==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=water.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(tyers==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=tyers.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(tyerspic==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=tyers.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(light==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=light.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(lightpic==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=light.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(detailed==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=detailed.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(detailedpic==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=detailed.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(accessories==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=accessories.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(accessoriespic==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=accessories.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(oil==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=oil.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }
        else if(oilpic==v){
            SharedPreferences.Editor edt=sh.edit();
            String kk=oil.getText().toString();

            edt.putString("nn",kk);
            edt.commit();

            Intent in=new Intent(getApplicationContext(),home_services.class);
            startActivity(in);

        }

    }
}