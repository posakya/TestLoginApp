package com.myriad.testloginapp.adapter_class;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceClasses {

    Context context;
    SharedPreferences sp;

    public SharedPreferenceClasses(Context context) {
        this.context = context;
    }

    public void saveData(String id,String firstname,String lastname,String email,String lat,String lng,String street_address,String device_id){

        sp = context.getSharedPreferences("UserData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("id",id);
        editor.putString("first_name",firstname);
        editor.putString("last_name",lastname);
        editor.putString("email",email);
        editor.putString("street_address",street_address);
        editor.putString("latitude",lat);
        editor.putString("longitude",lng);
        editor.putString("device_id",device_id);
        editor.apply();

    }



}
