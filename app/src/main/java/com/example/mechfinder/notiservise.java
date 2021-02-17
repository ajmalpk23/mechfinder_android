package com.example.mechfinder;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.preference.PreferenceManager;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.TaskStackBuilder;

import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class notiservise extends Service {
Handler hd;
String url="";

String []nid,noti,time;
String lastID="0";
SharedPreferences sh;
public static final String CHANNEL_ONE_ID = "com.example.mridul.distr_mgt.ONE";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
try {
			
			if(Build.VERSION.SDK_INT>9) {
				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String hu = sh.getString("ip", "");
		url = "http://" + hu + ":5000/and_ord_notification";

		hd=new Handler();
		hd.post(r);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		hd.removeCallbacks(r);
	}
	
	public Runnable r=new Runnable() {
		
		@Override
		public void run() {
         try
         {
    		RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    	    //    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
    	        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
    	                new Response.Listener<String>()
    	                {
    	                    @Override
    	                    public void onResponse(String response) {




    	                        // response
    	                        try {

    	                            JSONObject jsonObj = new JSONObject(response);
    	                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

    	                            	String jjs=jsonObj.getString("nots");


    	                            	if(jjs.equalsIgnoreCase("approved")) {
											showSmallNotification("Your Order is Approved");
										}

										if(jjs.equalsIgnoreCase("done")) {
											showSmallNotification("Ready For Payment");
										}
										if(jjs.equalsIgnoreCase("rejected")) {
											showSmallNotification("Your order is rejected");
										}

									}

    	                        } catch (Exception e) {
    	                        	 Toast.makeText(getApplicationContext(),"eeeee"+e.toString(),Toast.LENGTH_LONG).show();
    	                       }


    	                    }
    	                },
    	                new Response.ErrorListener()
    	                {
    	                    @Override
    	                    public void onErrorResponse(VolleyError error) {
    	                        // error
    	                        Toast.makeText(getApplicationContext(),"eeeee"+error.toString(),Toast.LENGTH_SHORT).show();
    	                    }
    	                }
    	        ) {
    	            @Override
    	            protected Map<String, String> getParams()
    	            {
    	                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    	                Map<String, String>  params = new HashMap<String, String>();

//
						params.put("lid",sh.getString("lid",""));


//
    	                return params;
    	            }
    	        };
			 postRequest.setRetryPolicy
					 (new DefaultRetryPolicy(60000,
							 DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
							 DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


			 requestQueue.add(postRequest);



    						
			//hd.postDelayed(r, 5000);
         }
         catch(Exception e){

		 }
			hd.postDelayed(r, 60000);

		}
	};
	
//	public void notis(String a) {
//		// TODO Auto-generated method stub
//		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
//        .setSmallIcon(R.drawable.speech_bubble)
//        .setContentTitle("You have new message")
//        .setContentText(a)
//        .setAutoCancel(true);
//
//
//
//		//
////Creates an explicit intent for an Activity in your app
//
//Intent resultIntent = new Intent(getApplicationContext(), Login.class);
//
//// The stack builder object will contain an artificial back stack for the
//// started Activity.
//// This ensures that navigating backward from the Activity leads out of
//// your application to the Home screen.
//TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
//// Adds the back stack for the Intent (but not the Intent itself)
//
//stackBuilder.addParentStack(Login.class);
//
//// Adds the Intent that starts the Activity to the top of the stack
//stackBuilder.addNextIntent(resultIntent);
//PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
//mBuilder.setContentIntent(resultPendingIntent);
////mBuilder.setVibrate();
//Notification note=mBuilder.build();
//note.defaults |= Notification.DEFAULT_VIBRATE;
//note.defaults |= Notification.DEFAULT_SOUND;
//
//NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//// mId allows you to update the notification later on.
//mNotificationManager.notify(100,note);// mBuilder.build());
//	}
	private void showSmallNotification(String message){
		String CHANNEL_ID = CHANNEL_ONE_ID;
		String CHANNEL_NAME = "Notification";
		Intent intent = new Intent(this, Login.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

		inboxStyle.addLine(message);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			// I would suggest that you use IMPORTANCE_DEFAULT instead of IMPORTANCE_HIGH
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
			channel.enableVibration(true);
			channel.setLightColor(Color.BLUE);
			channel.enableLights(true);
			channel.setShowBadge(true);
			notificationManager.createNotificationChannel(channel);
		}

		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
				.setVibrate(new long[]{0, 100})
				.setPriority(Notification.PRIORITY_MAX)
				.setLights(Color.BLUE, 3000, 3000)
				.setAutoCancel(true)
				.setContentTitle("Notification")
				.setContentIntent(pendingIntent)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setStyle(inboxStyle)
				.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.logo_checkout))
				.setContentText(message);

		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
			notificationBuilder.setChannelId(CHANNEL_ONE_ID);
		}
		notificationManager.notify(CHANNEL_ID, 1, notificationBuilder.build());
	}
}
