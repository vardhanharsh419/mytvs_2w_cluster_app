package com.example.tvslauncher.Common;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class LocationTracker {

    public static String TAG = LocationTracker.class.getName();

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    Location location = null;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;
    private static final long MIN_TIME_BW_UPDATES = 1000;
    protected LocationManager locationManager;
    static Context mcontext;
    private static LocationTracker instance;


    public static synchronized LocationTracker getInstance(Context ctx) {
        mcontext = ctx;
        if (instance == null) {
            instance = new LocationTracker();
        }
        return instance;
    }

    myListener listener;
    public void connectToLocation(myListener listener) {
        this.listener=listener;
        stopLocationUpdates();
        displayLocation();
    }

    private void displayLocation() {
        try {
            Log.i(TAG,"displayLocation");
            Location location = getLocation();
            if (location != null) {
                updateLattitudeLongitude(location.getLatitude(), location.getLongitude());
            }
        } catch (SecurityException e) {
//            SecurityException.printStackTrace(e);
        } catch (Exception e) {
//            SecurityException.printStackTrace(e);
        }
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager) mcontext
                    .getSystemService(Context.LOCATION_SERVICE);

            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                this.canGetLocation = false;

            } else {

                this.canGetLocation = true;

                if (isNetworkEnabled) {

                    Log.d(TAG + "-->Network", "Network Enabled");

                    if (locationManager != null) {

                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, locationProviderListener);
                        return location;
                    }

                } else if (isGPSEnabled) {

                    Log.d(TAG + "-->GPS", "GPS Enabled");

                    if (locationManager != null) {

                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, locationProviderListener);

                        return location;
                    }
                }
            }

        } catch (SecurityException e) {
//            Except/ionHandler.printStackTrace(e);
        } catch (Exception e) {
//            ExceptionHandler.printStackTrace(e);
        }
        return location;
    }

    public void updateLattitudeLongitude(double latitude, double longitude) {
        Log.i(TAG, "updated Lat == " + latitude + "  updated long == " + longitude);
//        SharedPreferenceManager sharedPreferenceManager = SharedPreferenceManager.getInstance();
//        sharedPreferenceManager.updateUserDeviceLatLong(latitude, longitude);


        listener.onUpdate(latitude, longitude);
    }

    public void stopLocationUpdates(){
        try {
            if (locationManager != null) {
                Log.i(TAG,"stopLocationUpdates");
                locationManager.removeUpdates(locationProviderListener);
                locationManager = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public LocationListener locationProviderListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            try {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                updateLattitudeLongitude(latitude, longitude);

            } catch (Exception e) {

//                ExceptionHandler.printStackTrace(e);
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {


        }

        @Override
        public void onProviderEnabled(String s) {


        }

        @Override
        public void onProviderDisabled(String s) {


        }
    };

    public interface myListener{
        void onUpdate(double latt, double longg);
    }

}