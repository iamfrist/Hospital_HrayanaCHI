package com.imf.haryanachi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.login.LoginRequest;
import com.imf.haryanachi.networkModel.login.LoginResponse;
import com.google.zxing.Result;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private CodeScanner mCodeScanner;
    private CodeScannerView scannerView;
    private Button button;
    private String username, password;
    private EditText passEditText, userEditText;
    private  TextView higher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scannerView = findViewById(R.id.scanner_view);
        button = findViewById(R.id.subbtn);
        higher = findViewById(R.id.higher);
        userEditText = findViewById(R.id.username);
        passEditText = findViewById(R.id.password);
        mCodeScanner = new CodeScanner(this, scannerView);
        higher.setOnClickListener(i->{
            Intent intent=new Intent(MainActivity.this,HigherLoginActivity.class);
            startActivity(intent);
            finish();
        });

        ActionBar.LayoutParams p=new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;

        if (ContextCompat.checkSelfPermission(MainActivity.this, CAMERA) == PackageManager.PERMISSION_GRANTED) {
            mCodeScanner.startPreview();
        } else checkAndRequestPermissions();
        try {

            mCodeScanner.setDecodeCallback(new DecodeCallback() {
                @Override
                public void onDecoded(@NonNull final Result result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                            login(result.getText());
                        }
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        button.setOnClickListener((View v) -> {
            if (userEditText.getText().toString().isEmpty()) {
                userEditText.setError("Write your username");
            } else if (passEditText.getText().toString().isEmpty()) {
                passEditText.setError("Write your password");
            } else {
                username = userEditText.getText().toString().trim();
                password = passEditText.getText().toString().trim();
                setlogin(username, password);
            }
        });


    }

    private void setlogin(String username, String password) {
        try {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setDoctor_code("");
            loginRequest.setUsername(username);
            loginRequest.setPassword(password);
            Call<LoginResponse> responceCall = ApiClient.getApiClient().login(loginRequest);


            responceCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //    Toast.makeText(MainActivity.this, "0000vjhvhvh", Toast.LENGTH_SHORT).show();

                    Log.d("responce", response.body().getMessage());
                    Log.d("responce", response.body().getStatus() + "");
                    Log.d("responce", response.body().getData().getDoctorName());
                    mCodeScanner.stopPreview();
                    mCodeScanner.releaseResources();


                    SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPref1.edit();
                    editor1.putString("name", response.body().getData().getDoctorName());
                    editor1.putString("id", response.body().getData().getDoctorId());
                    editor1.putString("chc_id", response.body().getData().getChcId());
                    editor1.putString("phc_id", response.body().getData().getPhcId());
                    editor1.putString("team_member", response.body().getData().getTeamMember());
                    editor1.putString("wardname", response.body().getData().getWardname());
                    editor1.putString("centername", response.body().getData().getCentername());
                    editor1.putString("distric", response.body().getData().getDistrict_name());
                    editor1.putString("distric_id", response.body().getData().getDistrict_id());
                    editor1.apply();

                    Log.d("logmeeeee",response.body().getData().getPhcId());
                    Log.d("logmeeeee",response.body().getData().getChcId());


                    SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("session", "ok");
                    editor.putString("name", response.body().getData().getDoctorName());
                    editor.putString("id", response.body().getData().getDoctorId());
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, EmployeeProfileActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("responce", t.getMessage());
                    mCodeScanner.stopPreview();
                    mCodeScanner.startPreview();
                    Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void login(String idString) {

        try {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setDoctor_code(idString);
            loginRequest.setPassword("");
            loginRequest.setUsername("");
            Call<LoginResponse> responceCall = ApiClient.getApiClient().login(loginRequest);

            responceCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    mCodeScanner.stopPreview();
                    mCodeScanner.releaseResources();
                    Intent intent = new Intent(MainActivity.this, EmployeeProfileActivity.class);
                    intent.putExtra("name", response.body().getData().getDoctorName());
                    intent.putExtra("id", response.body().getData().getDoctorId());
                    intent.putExtra("chc_id", response.body().getData().getChcId());
                    intent.putExtra("phc_id", response.body().getData().getPhcId());
                    startActivity(intent);
                    finish();

                    SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPref1.edit();
                    editor1.putString("name", response.body().getData().getDoctorName());
                    editor1.putString("id", response.body().getData().getDoctorId());
                    editor1.putString("chc_id", response.body().getData().getChcId());
                    editor1.putString("phc_id", response.body().getData().getPhcId());
                    editor1.putString("team_member", response.body().getData().getTeamMember());
                    editor1.putString("wardname", response.body().getData().getWardname());
                    editor1.putString("centername", response.body().getData().getCentername());
                    editor1.putString("distric", response.body().getData().getDistrict_name());
                    editor1.apply();

                    Log.d("logmeeeee",response.body().getData().getPhcId());
                    Log.d("logmeeeee",response.body().getData().getChcId());

                    SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("session", "ok");
                    editor.putString("name", response.body().getData().getDoctorName());
                    editor.putString("id", response.body().getData().getDoctorId());
                    editor.apply();


                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("responce", t.getMessage());
                    mCodeScanner.stopPreview();
                    mCodeScanner.startPreview();
                    Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, AllLognActivity.class);
        startActivity(intent);
        finish();

    }

    private boolean checkAndRequestPermissions() {
        int readPhoneState = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
        List listPermissionsNeeded = new ArrayList<>();
        if (readPhoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;


    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}