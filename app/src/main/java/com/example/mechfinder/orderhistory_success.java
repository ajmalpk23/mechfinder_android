package com.example.mechfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class orderhistory_success extends AppCompatActivity {

    SharedPreferences sh;
    String url="";
    TextView shopname,place,city,username,vehicle_compnay,vehicle_model,vehicle_regno,email,phone,totalamount1,discount,parts;
    ImageView download;
    ArrayList<String> service_id,service,seramount;
    ListView listv2;
    String service_requestid;
    ConstraintLayout cn_download;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory_success);
        shopname=(TextView)findViewById(R.id.textView31);
        place=(TextView)findViewById(R.id.textView32);
        city=(TextView)findViewById(R.id.textView33);
        username=(TextView)findViewById(R.id.textView34);
        vehicle_compnay=(TextView)findViewById(R.id.textView35);
        vehicle_model=(TextView)findViewById(R.id.textView42);
        vehicle_regno=(TextView)findViewById(R.id.textView43);
        parts=(TextView)findViewById(R.id.textView44);
        discount=(TextView)findViewById(R.id.textView45);
        listv2=(ListView) findViewById(R.id.listv2);

        email=(TextView)findViewById(R.id.textView36);
        phone=(TextView)findViewById(R.id.textView37);
        totalamount1=(TextView)findViewById(R.id.textView38);
        download=(ImageView) findViewById(R.id.button4);
        cn_download=(ConstraintLayout)findViewById(R.id.to_download);





        service_requestid=getIntent().getStringExtra("srid");





        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sh.getString("url","")+"and_orderhistory_success";




//        totalamount1.setText(sh.getString("totalamount",""));



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String shop_status1=jsonObj.getString("wokshop_status");
//                            Toast.makeText(getApplicationContext(),shop_status1,Toast.LENGTH_LONG).show();
                            if(shop_status1.equalsIgnoreCase("ok")){
                                JSONObject mm=jsonObj.getJSONObject("workshop_data");
                                shopname.setText(mm.getString("shop_name"));
                                email.setText(mm.getString("email"));
                                phone.setText(mm.getString("phone"));
                                place.setText(mm.getString("place"));
                                city.setText(mm.getString("city"));
                                vehicle_compnay.setText(mm.getString("company"));
                                vehicle_model.setText(mm.getString("model"));
                                vehicle_regno.setText(mm.getString("regno"));
                                totalamount1.setText(mm.getString("payment"));
                                parts.setText(mm.getString("parts"));
                                discount.setText(mm.getString("discount"));

//                                String shop_name1=jsonObj.getString("shop_name");
//                                String email1=jsonObj.getString("email");
//                                String phone1=jsonObj.getString("phone");
//                                String place1=jsonObj.getString("place");
//                                String city1=jsonObj.getString("city");
//
//                                name.setText(shop_name1);
//                                email.setText(email1);
//                                phone.setText(phone1);
//                                place.setText(place1);
//                                city.setText(city1);
//

//
                            }
                            String user_status=jsonObj.getString("user_status");
                            if(user_status.equalsIgnoreCase("ok")){
                                JSONObject mm=jsonObj.getJSONObject("user_data");
                                username.setText(mm.getString("name"));
//

//

//
                            }
                            String status=jsonObj.getString("service_status");
                            if(status.equalsIgnoreCase("ok"))
                            {
                                JSONArray ja= jsonObj.getJSONArray("service_data");
//                                service_requestid=new ArrayList<>();
////                                servicename=new ArrayList<>();
//                                shopname=new ArrayList<>();
//                                amount=new ArrayList<>();
//                                status1=new ArrayList<>();
//                                vehicle_company=new ArrayList<>();
//                                vehicle_model1=new ArrayList<>();
                                service_id=new ArrayList<>();
                                service=new ArrayList<>();
                                seramount=new ArrayList<>();


                                for ( int i=0;i< ja.length(); i++)
                                {
                                    JSONObject jd= ja.getJSONObject(i);
                                    service_id.add(jd.getString("service_request_id"));
                                    service.add(jd.getString("service"));
                                    seramount.add(jd.getString("amount"));
//                                    service_requestid.add(jd.getString("service_request_id"));
////                                    servicename.add(jd.getString("service"));
//                                    shopname.add(jd.getString("shop_name"));
//                                    amount.add(jd.getString("payment"));
//                                    status1.add(jd.getString("status"));
//                                    vehicle_company.add(jd.getString("company"));
//                                    vehicle_model1.add(jd.getString("model"));


                                }
//                                Custome_orderhistory(Context appcontext, ArrayList<String>id, ArrayList<String>servicename, ArrayList<String> shopname,ArrayList<String> vehicle_copany,ArrayList<String> status1,ArrayList<String> amount,ArrayList<String> vehicle_model)
                                listv2.setAdapter(new Custome_orderhistory_pending(getApplicationContext(),service_id,service,seramount));
//                                listview1.setAdapter(new Custome_orderhistory(getApplicationContext(),servicename,shopname,amount,status1,vehicle_company,vehi));




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
                params.put("sid",service_requestid);
//                params.put("shop_id",sh.getString("shop_id",""));


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bn=viewToBitmap(cn_download);
                try {
                    File getImage = getExternalFilesDir("");
                    File f=new File(getImage.getPath(),  service_requestid+".jpeg");
                    FileOutputStream output = new FileOutputStream(f);
                    bn.compress(Bitmap.CompressFormat.PNG, 100, output);
                    output.close();
                    Toast.makeText(orderhistory_success.this, "success", Toast.LENGTH_SHORT).show();
                    viewGallery(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(orderhistory_success.this, e.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(orderhistory_success.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Bitmap viewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
    private void viewGallery(File file) {

        Uri mImageCaptureUri = FileProvider.getUriForFile(
                getApplicationContext(),
                getApplicationContext().getApplicationContext()
                        .getPackageName() + ".provider", file);

        Intent view = new Intent();
        view.setAction(Intent.ACTION_VIEW);
        view.setData(mImageCaptureUri);
        List<ResolveInfo> resInfoList =
                getApplicationContext().getPackageManager()
                        .queryIntentActivities(view, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo: resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            getApplicationContext().grantUriPermission(packageName, mImageCaptureUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        view.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(mImageCaptureUri, "image/*");
        getApplicationContext().startActivity(intent);
    }
}