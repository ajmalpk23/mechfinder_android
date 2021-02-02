package com.example.mechfinder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class upi extends AppCompatActivity {

    EditText amount, note, name, upivirtualid;
    Button send;
    String TAG ="main",l_id,p_id,am,qnt,am1,srid,username,payment;
    final int UPI_PAYMENT = 0;
    SharedPreferences sh;
    String url="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi_pay);

        send = (Button) findViewById(R.id.send);
        amount = (EditText)findViewById(R.id.amount_et);
        note = (EditText)findViewById(R.id.note);
        name = (EditText) findViewById(R.id.name);
        upivirtualid =(EditText) findViewById(R.id.upi_id);

        srid=getIntent().getStringExtra("srid");
        username=getIntent().getStringExtra("name");
        payment=getIntent().getStringExtra("payment");



        name.setText("name");
        name.setText(username);
        amount.setText(payment);
        upivirtualid.setText("@okaxis");
//        am="1000";
//        amount.setText(am);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_paid";

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the values from the EditTexts
                if (TextUtils.isEmpty(name.getText().toString().trim())){
                    Toast.makeText(upi.this," Name is invalid", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(upivirtualid.getText().toString().trim())){
                    Toast.makeText(upi.this," UPI ID is invalid", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(note.getText().toString().trim())){
                    Toast.makeText(upi.this," Note is invalid", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(amount.getText().toString().trim())){
                    Toast.makeText(upi.this," Amount is invalid", Toast.LENGTH_SHORT).show();
                }else{
                    payUsingUpi(name.getText().toString(), upivirtualid.getText().toString(),
                            note.getText().toString(), amount.getText().toString());
                }
            }
        });


    }
    void payUsingUpi(  String name,String upiId, String note, String amount) {
        Log.e("main ", "name "+name +"--up--"+upiId+"--"+ note+"--"+amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                //.appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                //.appendQueryParameter("tr", "25584584")
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(upi.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response "+resultCode );

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    //when user simply back without payment
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }
    private void upiPaymentDataOperation(ArrayList<String> data) {

        String str = data.get(0);
        Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
        String paymentCancel = "";
        if(str == null) str = "discard";
        String status = "";
        String approvalRefNo = "";
        String response[] = str.split("&");
        for (int i = 0; i < response.length; i++) {
            String equalStr[] = response[i].split("=");
            if(equalStr.length >= 2) {
                if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                    status = equalStr[1].toLowerCase();
                }
                else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                    approvalRefNo = equalStr[1];
                }
            }
            else {
                paymentCancel = "Payment cancelled by user.";
            }
        }
        if (status.equals("success")) {

        }
        else if("Payment cancelled by user.".equals(paymentCancel)) {
            Toast.makeText(upi.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            Log.e("UPI", "Cancelled by user: "+approvalRefNo);
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
                                Intent in=new Intent(getApplicationContext(),add_review.class);
                                in.putExtra("srid",srid);
                                startActivity(in);

//                            String status=jsonObj.getString("status");
//                            if(status.equalsIgnoreCase("valid")){
//                                String lid=jsonObj.getString("lid");
//
//                                SharedPreferences.Editor edt=sh.edit();
//                                edt.putString("lid",lid);
//                                edt.commit();
//
//                                Intent in=new Intent(getApplicationContext(),home.class);
//                                startActivity(in);
//                                Intent in2=new Intent(getApplicationContext(),LocationService.class);
//                                startService(in2);
//                                String aaa=LocationService.lati;
////                                Toast.makeText(getApplicationContext(), "lati="+aaa, Toast.LENGTH_LONG).show();
//
//
//
//                            }
//                            else{
//                                Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
//                            }


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
                    params.put("srid",srid);
//

                    return params;
                }
            };

            int MY_SOCKET_TIMEOUT_MS=100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);





            Toast.makeText(upi.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            Log.e("UPI", "failed payment: "+approvalRefNo);
        }

    }

    public void onBackPressed(){ {
        AlertDialog.Builder builder=new AlertDialog.Builder(upi.this);
        builder.setTitle("CANCEL")
                .setMessage("Are You Sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        upi.super.onBackPressed();;
                    }

                }).setNegativeButton("No", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    }


}