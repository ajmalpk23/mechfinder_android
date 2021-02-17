package com.example.mechfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chat extends AppCompatActivity {



    MessagesAdapter adapterMessages;
    ListView listMessages;
    ImageView bt1;
    EditText edtxttosent;
    Handler hnd;
    Runnable ad;
    SharedPreferences sh;
    String url="",url1="",lid,toid,shop_id;
    String lastid;
    ArrayList<String> chatid,fromid,message,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        hnd=new Handler();
        listMessages= (ListView)findViewById(R.id.list_chat);
        bt1= (ImageView) findViewById(R.id.button_chat_send);
        adapterMessages = new MessagesAdapter(Chat.this);
        edtxttosent=(EditText)findViewById(R.id.input_chat_message);
        listMessages.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listMessages.setStackFromBottom(true);


        shop_id=getIntent().getStringExtra("shop_id");




        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_chat";

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url1=sh.getString("url","")+"and_show_chat";




        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_chat";
        lid=sh.getString("lid","");
        lastid= sh.getString("lastid", "0");
        toid=getIntent().getStringExtra("toid");
//        if (sh.getString("type","").equals("user"))
//        {
//            toid=External_organisation.lid;
//        }
//        else
//        {
//            toid=External_organisation.lid;
//        }


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String msggg=edtxttosent.getText().toString();
                if(msggg.equalsIgnoreCase(""))
                {
                    edtxttosent.setError("enter message");
                }
                else {

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                    // response
                                    try {
                                        JSONObject jsonObj = new JSONObject(response);
                                        String status=jsonObj.getString("status");
                                        if(status.equalsIgnoreCase("ok"))
                                        {

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

                            params.put("lid",sh.getString("lid",""));
                            params.put("message",msggg);
                            params.put("shop_id",shop_id);

//
//                            params.put("username",userName);
//                            params.put("password",Password);

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
            }
        });

        ad=new Runnable() {
            @Override
            public void run() {


                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    String status=jsonObj.getString("status");
                                    if(status.equalsIgnoreCase("ok"))
                                    {
                                        JSONArray ja= jsonObj.getJSONArray("data");
                                        chatid=new ArrayList<>();
                                        fromid=new ArrayList<>();
                                        message=new ArrayList<>();
                                        date=new ArrayList<>();
//

                                        for ( int i=0;i< ja.length(); i++)
                                        {
                                            JSONObject jd= ja.getJSONObject(i);
                                            chatid.add(jd.getString("chat_id"));
                                            fromid.add(jd.getString("from_id"));
                                            message.add(jd.getString("message"));
                                            date.add(jd.getString("date"));




                                            ChatMessage	message = new ChatMessage();
                                            message.setMessage(jd.getString("message"));
                                            String dt=jd.getString("date");


                                            message.setDate(dt);
                                            if(jd.getString("from_id").equalsIgnoreCase(sh.getString("lid",""))){
                                                message.setUsername("Me");
                                                message.setIncomingMessage(false);
                                            }else{
                                                message.setUsername("Other");
                                                message.setIncomingMessage(true);
                                            }
                                            adapterMessages.add(message);
                                            lastid=jd.getString("chat_id");

                                        }

                                        listMessages.setAdapter(adapterMessages);
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

                        params.put("lid",sh.getString("lid",""));

                        params.put("shop_id",shop_id);
                        params.put("lastid",lastid);



                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS=100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);






                hnd.postDelayed(ad, 2000);
            }
        };
        hnd.post(ad);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences.Editor edt= sh.edit();
        edt.putString("lastid", "0");
        edt.commit();
        hnd.removeCallbacks(ad);

//        if (sh.getString("type","").equals("user"))
//        {
//            Intent in=new Intent(Chat.this,User_view_worker.class);
//            startActivity(in);
//
//        }
//        else {
//            Intent in=new Intent(Chat.this,View_request_worker.class);
//            startActivity(in);
//
//        }


    }
}