package com.imf.haryanachi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.imf.haryanachi.databinding.ActivityShowPatientActivtyBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.updatefinal.UpdateFinalRequest;
import com.imf.haryanachi.networkModel.updatefinal.UpdatefinalResponse;
import com.imf.haryanachi.utils.StaticValues;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShowPatientActivty extends AppCompatActivity {

    ActivityShowPatientActivtyBinding binding;
    SharedPreferences sharedPref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_patient_activty);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_patient_activty);

        sharedPref1 = getSharedPreferences("show", Context.MODE_PRIVATE);
        binding.name.setText(sharedPref1.getString("pname", ""));
        binding.pAge.setText(sharedPref1.getString("p_age", ""));
        binding.pgender.setText(sharedPref1.getString("pgender", ""));
        binding.patientsStatus.setText(sharedPref1.getString("patients_status", ""));
        binding.dayscount.setText(sharedPref1.getString("dayscount", ""));
        binding.homeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowPatientActivty.this, EmployeeProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.contibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if ( binding.patientsStatus.getText().toString().equals("Severe")) {
                    openDilog();
                } else {
                    pupup("PATIENT STATUS UPDATED SUCCESSFULLY");
                }


            }
        });



        binding.viewdashbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowPatientActivty.this, EmployeeProfileActivity.class));
                finish();
            }
        });

        binding.dischangerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);
                update("Discharged");

            }
        });

        binding.trtohospitalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);

                startActivity(new Intent(ShowPatientActivty.this, HopListActivity.class));
                finish();


                // update("Hospitalized");
            }
        });


    }


    private void update(String status) {
        UpdateFinalRequest request = new UpdateFinalRequest();
        request.setPatients_id(sharedPref1.getString("paitentId", ""));
        request.setPatients_status(status);
        request.setVisit_id(sharedPref1.getString("visitId", ""));
        //update_final_status
        Call<UpdatefinalResponse> responceCall = ApiClient.getApiClient().update_final_status(request);
        responceCall.enqueue(new Callback<UpdatefinalResponse>() {
            @Override
            public void onResponse(Call<UpdatefinalResponse> call, Response<UpdatefinalResponse> response) {
                if (response.body().getStatus().equals("Success")) {
                    Log.d("xxxxxxx", "ggggg");
                    if (status.equals("Hospitalized")) {
                        // binding.msg.setText("PATIENT TRANSFER TO HOSPITAL SUCCESSFULLY");
                        binding.progressBar.setVisibility(View.GONE);
                        pupup("PATIENT TRANSFER TO HOSPITAL SUCCESSFULLY");


                    } else {
                        //  binding.msg.setText("PATIENT  DISCHARGE SUCCESSFULLY");
                        pupup("PATIENT  DISCHARGE SUCCESSFULLY");
                        binding.progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdatefinalResponse> call, Throwable t) {
                Log.d("xxxxxxx", "ffffff");
            }
        });
    }

    private void pupup(String status) {
        new AlertDialog.Builder(this)
                .setMessage(status)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ShowPatientActivty.this, EmployeeProfileActivity.class));
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void openDilog() {

        final Dialog dialog = new Dialog(ShowPatientActivty.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(false);
        dialog.setContentView(R.layout.remark_dialog);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        EditText editText = dialog.findViewById(R.id.remarkedt);
        Button button = dialog.findViewById(R.id.button);

        button.setOnClickListener(i -> {
            if (editText.getText().toString().isEmpty()) {
                editText.setError("Add your remark");
            } else {
                update(editText.getText().toString(), dialog);
            }
        });
    }

    private void update(String remark, Dialog dialog) {
        String paitentId = sharedPref1.getString("paitentId", "");
        String visitId = sharedPref1.getString("visitId", "");
        UpdateFinalRequest request = new UpdateFinalRequest();
        request.setPatients_id(paitentId);
        request.setPatients_status("Severe");
        request.setVisit_id(visitId);
        request.setRemark(remark);
        request.setHospital_id("");
        Call<UpdatefinalResponse> responceCall = ApiClient.getApiClient().update_final_status(request);
        responceCall.enqueue(new Callback<UpdatefinalResponse>() {
            @Override
            public void onResponse(Call<UpdatefinalResponse> call, Response<UpdatefinalResponse> response) {
                if (response.body().getStatus().equals("Success")) {
                    Log.d("xxxxxxx", "ggggg");

                    //binding.progressBar.setVisibility(View.GONE);
                    dialog.dismiss();
                    pupup("PATIENT SEVERE");
                    StaticValues.status = "";
                    StaticValues.hospitalId = "";

                }
            }

            @Override
            public void onFailure(Call<UpdatefinalResponse> call, Throwable t) {
                Log.d("xxxxxxx", "ffffff");
            }
        });
    }
}