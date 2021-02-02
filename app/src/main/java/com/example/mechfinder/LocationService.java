package com.example.mechfinder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LocationService extends Service {

	private LocationManager locationManager;
	private Boolean locationChanged;

	private Handler handler = new Handler();
	public static Location curLocation;
	public static boolean isService = true;
	String ip = "";
	String[] zone;
	String pc = "";
	SharedPreferences sh;
	String userType = "";
	public static String place = "Kozhikode", address = "", lati = "", logi = "";


	LocationListener locationListener = new LocationListener() {

		public void onLocationChanged(Location location) {
			if (curLocation == null) {
				curLocation = location;
				locationChanged = true;
			} else if (curLocation.getLatitude() == location.getLatitude() && curLocation.getLongitude() == location.getLongitude()) {
				locationChanged = false;
				return;
			} else
				locationChanged = true;
			curLocation = location;

			if (locationChanged)
				locationManager.removeUpdates(locationListener);
		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}


		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			if (status == 0)// UnAvailable
			{
			} else if (status == 1)// Trying to Connect
			{
			} else if (status == 2) {// Available
			}
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();


		sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		userType = sh.getString("type", "");
		curLocation = getBestLocation();

		if (curLocation == null) {
			System.out.println("starting problem.........3...");
			Toast.makeText(this, "GPS problem..........", Toast.LENGTH_SHORT).show();
		}
		isService = true;
	}

	final String TAG = "LocationService";

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override

	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		//Toast.makeText(this, "Start services", Toast.LENGTH_SHORT).show();

		String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		if (!provider.contains("gps")) { //if gps is disabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			sendBroadcast(poke);
		}
		handler.postDelayed(GpsFinder, 100);

	}


	@Override
	public void onDestroy() {

		String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		if (provider.contains("gps")) { //if gps is enabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			sendBroadcast(poke);
		}

		handler.removeCallbacks(GpsFinder);
		handler = null;
		Toast.makeText(this, "Service Stopped..!!", Toast.LENGTH_SHORT).show();
		isService = false;
	}


	public Runnable GpsFinder = new Runnable() {


		public void run() {

			String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

			if (!provider.contains("gps")) { //if gps is disabled
				final Intent poke = new Intent();
				poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
				poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
				poke.setData(Uri.parse("3"));
				sendBroadcast(poke);
			}


			Location tempLoc = getBestLocation();

			if (tempLoc != null) {

				//Toast.makeText(getApplicationContext(), phoneid, Toast.LENGTH_LONG).show();

				curLocation = tempLoc;
				// Log.d("MyService", String.valueOf("latitude"+curLocation.getLatitude()));

				lati = String.valueOf(curLocation.getLatitude());
				logi = String.valueOf(curLocation.getLongitude());

				// Toast.makeText(getApplicationContext(),URL+" received", Toast.LENGTH_SHORT).show();
				//Toast.makeText(getApplicationContext(),"\nlat.. and longi.."+ lati+"..."+logi, Toast.LENGTH_SHORT).show();


				String loc = "";

				Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
				try {
					List<Address> addresses = geoCoder.getFromLocation(curLocation.getLatitude(), curLocation.getLongitude(), 1);
					if (addresses.size() > 0) {
						for (int index = 0; index < addresses.get(0).getMaxAddressLineIndex(); index++)
							address += addresses.get(0).getAddressLine(index) + " ";
						//Log.d("get loc...", address);

						place = addresses.get(0).getFeatureName().toString();

						if (userType.equalsIgnoreCase("driver")) {
							updateBusLocation();
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				//    Toast.makeText(getBaseContext(), "locality-"+place, Toast.LENGTH_SHORT).show();


			}
			handler.postDelayed(GpsFinder, 5000);// register again to start after 20 seconds...
		}

	};

	private Location getBestLocation() {
		Location gpslocation = null;
		Location networkLocation = null;
		if (locationManager == null) {
			locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
		}
		try {
			if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
					// TODO: Consider calling
					//    ActivityCompat#requestPermissions
					// here to request the missing permissions, and then overriding
					//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
					//                                          int[] grantResults)
					// to handle the case where the user grants the permission. See the documentation
					// for ActivityCompat#requestPermissions for more details.
//					return TODO;
				}
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);// here you can set the 2nd argument time interval also that after how much time it will get the gps location
	                gpslocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	             //  System.out.println("starting problem.......7.11....");
	              
	            }
	            if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
	                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000, 0, locationListener);
	                networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); 
	            }
	        } catch (IllegalArgumentException e) {
	            Log.e("error", e.toString());
	        }
	        if(gpslocation==null && networkLocation==null)
	            return null;

	        if(gpslocation!=null && networkLocation!=null){
	            if(gpslocation.getTime() < networkLocation.getTime()){
	                gpslocation = null;
	                return networkLocation;
	            }else{
	                networkLocation = null;
	                return gpslocation;
	            }
	        }
	        if (gpslocation == null) {
	            return networkLocation;
	        }
	        if (networkLocation == null) {
	            return gpslocation;
	        }
	        return null;
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateBusLocation(){

		String ip = sh.getString("ip", "");


		String url = "http://" + ip + ":5000/dri_add_loc";

		RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

		StringRequest postRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						// response
						try {
							JSONObject jsonObj = new JSONObject(response);
							String sucs = jsonObj.getString("status");
							if (sucs.equalsIgnoreCase("ok")) {
							//	Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

//								Intent i = new Intent(getApplicationContext(), Dri_home.class);
//
//								startActivity(i);


							}


						} catch (Exception e) {
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


				SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				Map<String, String> params = new HashMap<String, String>();

				params.put("lid", sh.getString("lid",""));
				params.put("lati", lati);
				params.put("longi", logi);


				return params;
			}
		};

		requestQueue.add(postRequest);

	}
}
