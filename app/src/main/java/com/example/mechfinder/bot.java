package com.example.mechfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class bot extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnLongClickListener {
    LinearLayout linearLayout;
    BottomNavigationView bottomnavigation;


    MessagesAdapter adapterMessages;
    ListView listMessages;
    ImageView bt1;
    EditText edtxttosent;

    SharedPreferences sh;
    String url="",url1="",lid,toid,shop_id;
    String lastid;
    ArrayList<String> from,message,date;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        linearLayout=(LinearLayout)findViewById(R.id.listv2);
        bottomnavigation=(BottomNavigationView)findViewById(R.id.bottomnav);


        bottomnavigation.setOnNavigationItemSelectedListener(this);
        bottomnavigation.setItemIconTintList(null);
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);
        tv4=(TextView)findViewById(R.id.tv4);
        tv5=(TextView)findViewById(R.id.tv5);
        tv6=(TextView)findViewById(R.id.tv6);
        tv7=(TextView)findViewById(R.id.tv7);
        tv8=(TextView)findViewById(R.id.tv8);
        tv9=(TextView)findViewById(R.id.tv9);
        tv10=(TextView)findViewById(R.id.tv10);





        listMessages= (ListView)findViewById(R.id.list_chat);
        bt1= (ImageView) findViewById(R.id.button_chat_send);
        adapterMessages = new MessagesAdapter(bot.this);
        edtxttosent=(EditText)findViewById(R.id.input_chat_message);
        listMessages.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listMessages.setStackFromBottom(true);

        from=new ArrayList<>();
        message=new ArrayList<>();
        date=new ArrayList<>();

        ChatMessage	message = new ChatMessage();
        message.setMessage("Hi");
        message.setUsername("Robo");
        message.setIncomingMessage(true);
        message.setDate("2020-12-12");
        adapterMessages.add(message);
        listMessages.setAdapter(adapterMessages);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_chatbot";

        tv1.setOnLongClickListener(this);
        tv2.setOnLongClickListener(this);
        tv3.setOnLongClickListener(this);
        tv4.setOnLongClickListener(this);
        tv5.setOnLongClickListener(this);
        tv6.setOnLongClickListener(this);
        tv7.setOnLongClickListener(this);
        tv8.setOnLongClickListener(this);
        tv9.setOnLongClickListener(this);
        tv10.setOnLongClickListener(this);






        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String msggg=edtxttosent.getText().toString();
                if(msggg.equalsIgnoreCase(""))
                {
                    edtxttosent.setError("enter message");
                }
                else {

                    ChatMessage	message = new ChatMessage();
                    message.setMessage(msggg);
                    message.setUsername("Me");
                    message.setIncomingMessage(false);
                    message.setDate("2020-12-12");
                    adapterMessages.add(message);
                    listMessages.setAdapter(adapterMessages);


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
                                            ChatMessage	message = new ChatMessage();
                                            message.setMessage(jsonObj.getString("data"));
                                            message.setUsername("Robo");
                                            message.setIncomingMessage(true);
                                            message.setDate("2020-12-12");
                                            adapterMessages.add(message);
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

                            params.put("msg",msggg);

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

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.bot){
            Toast.makeText(this, "bot section 3", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId()==R.id.chat111){

            Intent in=new Intent(getApplicationContext(),home.class);
            startActivity(in);
            Toast.makeText(this, "home section 2", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.vehicle){
            Intent in=new Intent(getApplicationContext(),vehicle.class);
            startActivity(in);
            Toast.makeText(this, "vehicle 3", Toast.LENGTH_SHORT).show();

        }
        else if (item.getItemId()==R.id.my_account){
            Intent in=new Intent(getApplicationContext(),my_account.class);
            startActivity(in);
            Toast.makeText(this, "account clicked 3", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onLongClick(View v) {

        if(v==tv1){
            edtxttosent.setText(tv1.getText().toString());
        }
        else if(v==tv2){

            edtxttosent.setText(tv2.getText().toString());
        }
        else if(v==tv3){

            edtxttosent.setText(tv3.getText().toString());
        }
        else if(v==tv4){

            edtxttosent.setText(tv4.getText().toString());
        }
        else if(v==tv5){

            edtxttosent.setText(tv5.getText().toString());
        }
        else if(v==tv6){

            edtxttosent.setText(tv6.getText().toString());
        }
        else if(v==tv7){

            edtxttosent.setText(tv7.getText().toString());
        }
        else if(v==tv8){

            edtxttosent.setText(tv8.getText().toString());
        }
        else if(v==tv9){

            edtxttosent.setText(tv9.getText().toString());
        }
        else if(v==tv10){

            edtxttosent.setText(tv10.getText().toString());
        }
        return false;
    }
}