package com.myriad.testloginapp.adapter_class;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myriad.testloginapp.model_class.Location;
import com.myriad.testloginapp.model_class.User;

public class LoginClass {

    Context context;
    public static String tree;

    public LoginClass(Context context) {
        this.context = context;
    }

    public void postData(String tree, final String id, final String first_name, final String last_name, final String email, final Double device_id, final Double latitude, final Double longitude, final String street_address){

        Location location = new Location(latitude,longitude,street_address);
        final User user = new User();
        user.setDevice_id(device_id);
        user.setEmail(email);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setId(id);
        user.setLocation(location);


        /*

             checking whether the data is written or not in fire base database

         */

        FirebaseDatabase.getInstance().getReference().child(tree).setValue(user, new DatabaseReference.CompletionListener() {
            @SuppressLint("DefaultLocale")
            public void onComplete(DatabaseError error, @NonNull DatabaseReference ref) {


                if (error != null){
                    Toast.makeText(context, "Failed to save data!!", Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(context, "Successfully saved data!!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Response : "+user, Toast.LENGTH_SHORT).show();
                    SharedPreferenceClasses sharedPreferenceClasses = new SharedPreferenceClasses(context);

                    sharedPreferenceClasses.saveData(id,first_name,last_name,email,String.format("%.2f",latitude),String.format("%.2f",longitude),street_address,String.format("%.2f",device_id));

                }
            }
        });

    }

}
