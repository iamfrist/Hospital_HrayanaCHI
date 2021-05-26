package com.imf.haryanachi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import static android.Manifest.permission.CAMERA;

public class FlashScreenActivity extends AppCompatActivity {
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    SharedPreferences sharedPreferences;
    String session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        if (ContextCompat.checkSelfPermission(FlashScreenActivity.this, CAMERA) != PackageManager.PERMISSION_GRANTED) {

            checkAndRequestPermissions();

        } else {
            ShowCustomProgressBarAsyncTask sc = new ShowCustomProgressBarAsyncTask();
            sc.execute();
        }

    }

    private boolean checkAndRequestPermissions() {
        int readPhoneState = ContextCompat.checkSelfPermission(FlashScreenActivity.this, Manifest.permission.CAMERA);
        int readlocation = ContextCompat.checkSelfPermission(FlashScreenActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        List listPermissionsNeeded = new ArrayList<>();
        if (readPhoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(FlashScreenActivity.this,
                    (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);

            return false;
        }
        return true;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==REQUEST_ID_MULTIPLE_PERMISSIONS){

        }

        if (grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission is granted. Continue the action or workflow
            // in your app.

            ShowCustomProgressBarAsyncTask sc = new ShowCustomProgressBarAsyncTask();
            sc.execute();
        }  else {
            // Explain to the user that the feature is unavailable because
            // the features requires a permission that the user has denied.
            // At the same time, respect the user's decision. Don't link to
            // system settings in an effort to convince the user to change
            // their decision.
        }

    }

    private class ShowCustomProgressBarAsyncTask extends AsyncTask<Void,Integer,Void> {

        int myProgress;

        @Override
        protected void onPreExecute() {
            myProgress = 0;

             sharedPreferences=getSharedPreferences("session",MODE_PRIVATE);
            session=sharedPreferences.getString("session","");

            try {
                Log.w("session", "" + session);
            } catch (Exception e) {
                Log.w("CatchError", "Null Calue");
            }
        }
        @Override
        protected Void doInBackground(Void... voids) {
            while (myProgress < 50) {
                myProgress++;
                SystemClock.sleep(50);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //progressBar.setVisibility(View.INVISIBLE);
          try{
            if (session.equals("")) {
               try {
                   Intent i = new Intent(FlashScreenActivity.this, AllLognActivity.class);
                   startActivity(i);
                   finish();

               }catch (Exception e){
               }

            }else if(session.equals("cancel"))
            {
                Intent i = new Intent(FlashScreenActivity.this, AllLognActivity.class);
                startActivity(i);
                finish();
            }else if(session.equals("profile"))
            {
                Intent i = new Intent(FlashScreenActivity.this, ProfileActivity.class);
                startActivity(i);
                finish();
            }else if(session.equals("ok")){
                Intent i = new Intent(FlashScreenActivity.this, EmployeeProfileActivity.class);
                startActivity(i);
                finish();
            }else if(session.equals("ok_admin")){
                Intent i = new Intent(FlashScreenActivity.this, SuperAdminActivity.class);
                startActivity(i);
                finish();
            }}catch (Exception e){
          }
        }
    }

}