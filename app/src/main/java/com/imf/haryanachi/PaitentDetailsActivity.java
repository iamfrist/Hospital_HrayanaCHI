package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.imf.haryanachi.databinding.ActivityPaitentDetailsBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.detailsPatient.DetailsRequest;
import com.imf.haryanachi.networkModel.detailsPatient.DetaislResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaitentDetailsActivity extends AppCompatActivity {
    TextView nameTextView, ageTextView, genderTextView, comoTextView, mobileTextView,regnoTextView;
    ImageView qrcodeView;
    ActivityPaitentDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitent_details);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_paitent_details);

        binding.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaitentDetailsActivity.this, CallActivity.class));
            }
        });
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaitentDetailsActivity.this, ProfileActivity.class));
                finish();
            }
        });

        nameTextView = findViewById(R.id.name);
        ageTextView = findViewById(R.id.age);
        genderTextView = findViewById(R.id.GENDER);
        //  comoTextView = findViewById(R.id.comor);
        mobileTextView = findViewById(R.id.mobile);
        qrcodeView = findViewById(R.id.qrcode);
        regnoTextView = findViewById(R.id.regno);


        SharedPreferences sharedPref = getSharedPreferences("patient", Context.MODE_PRIVATE);
        sharedPref.getString("pname", "");
        sharedPref.getString("age", "");
        sharedPref.getString("gender","");
        sharedPref.getString("mobile","");
        sharedPref.getString("regno","");


       nameTextView.setText(  sharedPref.getString("pname", ""));
       ageTextView.setText(  sharedPref.getString("age", ""));
       genderTextView.setText(  sharedPref.getString("gender", ""));
        mobileTextView.setText(  sharedPref.getString("mobile", ""));
        regnoTextView.setText(  sharedPref.getString("regno", ""));
        Picasso.get()
                .load( sharedPref.getString("qrcode", ""))
                .error(R.drawable.ic_launcher_foreground)
                .into(qrcodeView);

        sharedPref.getString("pId", "");
        data(  sharedPref.getString("pId", ""));
    }


    void data(String id){

        DetailsRequest request=new DetailsRequest();
        request.setPatients_id(id);

        Call<DetaislResponse> responceCall = ApiClient.getApiClient().view_patients_profile(request);

        responceCall.enqueue(new Callback<DetaislResponse>() {
            @Override
            public void onResponse(Call<DetaislResponse> call, Response<DetaislResponse> response) {
                if (response.body().isStatus()){

                    binding.vdate.setText(response.body().getData().getVisitDate());
                    binding.vtime.setText(response.body().getData().getVisitTime());
                    binding.temp.setText(response.body().getData().getTemperature());
                    binding.pulse.setText(response.body().getData().getPulse());
                    binding.spo2.setText(response.body().getData().getSpo2());
                    binding.rr.setText(response.body().getData().getRate());
                    binding.sbp.setText(response.body().getData().getSystolicBp());
                    binding.dbp.setText(response.body().getData().getDiastolicBp());
                    binding.status.setText(response.body().getData().getPatientsStatus());

                }
            }

            @Override
            public void onFailure(Call<DetaislResponse> call, Throwable t) {

            }
        });


    }
}