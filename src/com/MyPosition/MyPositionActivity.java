package com.MyPosition;


import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MyPositionActivity extends Activity {
    /** Called when the activity is first created. */
	private LocationManager locManager;
	private LocationListener locListener = new MyLocationListener();
	EditText p_lat;
	EditText p_lon;
	private boolean gps_enabled = false;
	private boolean network_enabled = false;

  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	p_lat = (EditText) findViewById(R.id.lat);
    	p_lon = (EditText) findViewById(R.id.lon);
    	TextView error = (TextView) findViewById(R.id.error);
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		try {
			gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
		}
		try {
			network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
			
		}
		if (!gps_enabled && !network_enabled) {
			error.setText("Error per determinar la posicion");
		}

		if (gps_enabled) {
			locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
		}
		if (network_enabled) {
			locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
		}
    }
    
    class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {
				locManager.removeUpdates(locListener); 
				p_lon.setText( ""+location.getLongitude());
				p_lat.setText(""+location.getLatitude());
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}
	}

}