package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.imf.haryanachi.databinding.ActivityHigherLoginBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.higherAuthoriytyLogin.HigherAuthorityResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HigherLoginActivity extends AppCompatActivity {


    ActivityHigherLoginBinding binding;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_higher_login);

        binding.subbtn.setOnClickListener((View v) -> {
            if (binding.username.getText().toString().isEmpty()) {
                binding.username.setError("Write your username");
            } else if (binding.password.getText().toString().isEmpty()) {
                binding.password.setError("Write your password");
            } else {
                username = binding.username.getText().toString().trim();
                password = binding.password.getText().toString().trim();


                setlogin(username, password);
            }
        });

    }

    private void setlogin(String username, String password) {

        try {
            String pass = password.replace("%40", "@");
            Log.d("logmeee", pass);
            Call<HigherAuthorityResponse> call = ApiClient.getApiClient().others_login(username, pass);
            call.enqueue(new Callback<HigherAuthorityResponse>() {
                @Override
                public void onResponse(Call<HigherAuthorityResponse> call, Response<HigherAuthorityResponse> response) {
                    if (response.body().isStatus()) {

                        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("session", "ok_admin");
                        editor.putString("others_id", response.body().getOthersId());
                        editor.putString("role", response.body().getRole());
                        editor.apply();
                        Intent intent = new Intent(HigherLoginActivity.this, SuperAdminActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<HigherAuthorityResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Toast.makeText(HigherLoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}