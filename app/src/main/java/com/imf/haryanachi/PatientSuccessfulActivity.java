package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.imf.haryanachi.databinding.ActivityPatientSuccessfulBinding;

public class PatientSuccessfulActivity extends AppCompatActivity {


    ActivityPatientSuccessfulBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_successful);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_successful);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientSuccessfulActivity.this,AllLognActivity.class));
                finish();
            }
        });
        binding.viewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientSuccessfulActivity.this,AllLognActivity.class));
                finish();
            }
        });
        binding.addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientSuccessfulActivity.this,AllLognActivity.class));
                finish();
            }
        });
    }
}