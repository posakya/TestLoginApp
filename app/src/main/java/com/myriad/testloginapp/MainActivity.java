package com.myriad.testloginapp;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.myriad.testloginapp.adapter_class.CheckGpsEnable;
import com.myriad.testloginapp.adapter_class.GpsCoordinate;
import com.myriad.testloginapp.adapter_class.LoginClass;
import com.myriad.testloginapp.network.CheckInternet;

public class MainActivity extends AppCompatActivity {

    LoginClass loginClass;

    TelephonyManager telephonyManager;
    String deviceId;

    EditText editFirstName, editLastName, editEmail;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);




        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        telephonyManager = (TelephonyManager) getSystemService(MainActivity.this.TELEPHONY_SERVICE);
        deviceId = telephonyManager.getDeviceId();

    }

    public void save(View view) {
        inputData();
    }



    public void inputData(){
        String email = editEmail.getText().toString().trim();
        String fName = editFirstName.getText().toString().trim();
        String lName = editLastName.getText().toString().trim();

        if (fName.isEmpty()){
            Toast.makeText(this, "First name required", Toast.LENGTH_SHORT).show();
        }else if (lName.isEmpty()){
            Toast.makeText(this, "Last name required", Toast.LENGTH_SHORT).show();
        }else if (email.isEmpty()){
            Toast.makeText(this, "Email required", Toast.LENGTH_SHORT).show();
        }else{
            loginClass = new LoginClass(getApplicationContext());
            LoginClass.tree = "User";
            loginClass.postData(LoginClass.tree, FirebaseDatabase.getInstance().getReference().push().getKey(),fName,lName,email, Double.valueOf(deviceId), GpsCoordinate.lat, GpsCoordinate.lng,"imadol");
        }
    }
}
