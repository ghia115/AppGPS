package com.example.luis.appgps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    TextView tv;
    Button b;
    LocationManager mlocManager;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Use the LocationManager class to obtain GPS locations */
        tv = (TextView) findViewById(R.id.editTextShowLocation);
        b = (Button) findViewById(R.id.buttonGetLocation);
        b.setOnClickListener(this);

        LocationManager_check locationManagerCheck = new LocationManager_check(this);
        Location location = null;

        if(locationManagerCheck.isLocationServiceAvailable()){

            if (locationManagerCheck.getProviderType() == 1)
                location = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            else if (locationManagerCheck.getProviderType() == 2)
                location = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }else{
            locationManagerCheck.createLocationServiceError(MainActivity.this);
        }

    }


    /* Class My Location Listener */

    public class MyLocationListener implements LocationListener{
        @Override

        public void onLocationChanged(Location loc){

            String Text = "location: " +
                    "Latitud = " + loc.getLatitude() +
                    "Longitud = " + loc.getLongitude();

            tv.setText(Text);

        }
        public void onProviderDisabled(String provider){

            //nothin
        }


        public void onProviderEnabled(String provider){

            //nothin
        }

        public void onStatusChanged(String provider, int status, Bundle extras){
            //nothin
        }
    }/* End of Class MyLocationListener */


    @Override
    public void onClick(View v) {
        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, mlocListener);

        Location lastLocation = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        String locationInfo = "Location: " +
                "\nlatitude = " + lastLocation.getLatitude() +
                "\nlongitude = " + lastLocation.getLongitude();

        tv.setText(locationInfo);

    }
}/* End of UseGps Activity */