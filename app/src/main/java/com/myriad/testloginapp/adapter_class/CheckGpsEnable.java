package com.myriad.testloginapp.adapter_class;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

public class CheckGpsEnable {


    private GoogleApiClient googleApiClient;
    Context context;

    public CheckGpsEnable(Context context) {
        this.context = context;
    }

    /*

   enabling gps location

   */

    public void enableGps() {


        if (googleApiClient == null) {

            googleApiClient = new GoogleApiClient.Builder(context).addApi(LocationServices.API).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);

            final PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());

            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(@NonNull LocationSettingsResult result1) {

                    final Status status = result1.getStatus();

                    final LocationSettingsStates state = result1.getLocationSettingsStates();


                    switch (status.getStatusCode())

                    {

                        case LocationSettingsStatusCodes.SUCCESS:
                            GpsCoordinate gpsCoordinate = new GpsCoordinate(context);
//                            Toast.makeText(context, "Lat : "+gpsCoordinate.getLatitude(), Toast.LENGTH_SHORT).show();
                            gpsCoordinate.getLocation();
                            break;

                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:


                            try {

                                status.startResolutionForResult((Activity) context, 1000);

                            } catch (IntentSender.SendIntentException e)

                            {

                            }

                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                            break;

                    }

                }
            });

        }else{
            GpsCoordinate gpsCoordinate = new GpsCoordinate(context);
            gpsCoordinate.getLocation();
//            Toast.makeText(context, "Lat : "+gpsCoordinate.getLatitude(), Toast.LENGTH_SHORT).show();
        }
    }
}
