package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.imf.haryanachi.databinding.ActivityAllLognBinding;

public class AllLognActivity extends AppCompatActivity {
    //  /*copy code*/
  /*  private CodeScanner mCodeScanner;
    private CodeScannerView scannerView;
  */

  ActivityAllLognBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_logn);
//
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_logn);

        binding.buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.usernameEtd.getText().toString().isEmpty()) {
                    binding.usernameEtd.setError("Write username");
                } else if (binding.passwordEdt.getText().toString().isEmpty()) {
                    binding.passwordEdt.setError("Write password");
                } else {
                    Intent intent = new Intent(AllLognActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                    SharedPreferences sharedPref1 = getSharedPreferences("logindata", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPref1.edit();
                    editor1.putString("username", binding.usernameEtd.getText().toString());
                    editor1.putString("password", binding.passwordEdt.getText().toString());
                    editor1.apply();
                    }
            }
        });//2108745234

        binding.employeeloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllLognActivity.this, MainActivity.class));
                finish();
            }
        });
        binding.newpRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    startActivity(new Intent(AllLognActivity.this, NewPaitentRegActivity.class));
            }
        });
        binding.newpregi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllLognActivity.this, RegiActivity.class));

            }
        });

    }

    /*private void getlogin(String username, String password) {

        PloginRequest ploginRequest = new PloginRequest();
        ploginRequest.setUsername(username);
        ploginRequest.setPassword(password);

        Call<PloginResponse> responceCall = ApiClient.getApiClient().patients_login(ploginRequest);

        responceCall.enqueue(new Callback<PloginResponse>() {
            @Override
            public void onResponse(Call<PloginResponse> call, Response<PloginResponse> response) {
                if (response.body().getStatus().equals("Success")) {

                    //Toast.makeText(AllLognActivity.this, "asd", Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPref1 = getSharedPreferences("profile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPref1.edit();
                    editor1.putString("comorbidities", response.body().getData().getComorbidities());
                    editor1.putString("pnumber", response.body().getData().getPnumber());
                    editor1.putString("patientsaddress", response.body().getData().getPatientsaddress());
                    editor1.apply();


                    SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("session", "profile");
                    editor.apply();

                }
            }

            @Override
            public void onFailure(Call<PloginResponse> call, Throwable t) {
                //Toast.makeText(AllLognActivity.this, "acscascsasd", Toast.LENGTH_SHORT).show();

                Log.d("acscascsasd", t.getMessage());
            }
        });


    }*/
}