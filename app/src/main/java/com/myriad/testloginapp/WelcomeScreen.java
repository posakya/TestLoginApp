package com.myriad.testloginapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.myriad.testloginapp.adapter_class.CheckGpsEnable;
import com.myriad.testloginapp.adapter_class.GpsCoordinate;
import com.myriad.testloginapp.network.CheckInternet;


public class WelcomeScreen extends AppCompatActivity {

    CheckGpsEnable checkGpsEnable;
    CheckInternet checkInternet;
    int PERMISSION_ALL = 1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        imageView = findViewById(R.id.imageView);

        String[] PERMISSIONS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_PHONE_STATE
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        checkGpsEnable = new CheckGpsEnable(WelcomeScreen.this);
        checkGpsEnable.enableGps();

        checkInternet = new CheckInternet(WelcomeScreen.this);

        if (checkInternet.isNetworkAvailable()) {

            GpsCoordinate gpsCoordinate = new GpsCoordinate(this);
            gpsCoordinate.getLocation();

            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
            imageView.setAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        } else {

            Toast.makeText(this, "Please check your internet connection!!", Toast.LENGTH_SHORT).show();
        }



    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


}
