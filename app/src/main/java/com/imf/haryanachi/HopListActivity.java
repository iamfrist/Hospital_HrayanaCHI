package com.imf.haryanachi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.imf.haryanachi.adapter.HopsListAdapter;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.hopiList.DataItem;
import com.imf.haryanachi.networkModel.hopiList.HopListResponse;
import com.imf.haryanachi.networkModel.hopiList.HopisListRequest;
import com.imf.haryanachi.networkModel.updatefinal.UpdateFinalRequest;
import com.imf.haryanachi.networkModel.updatefinal.UpdatefinalResponse;
import com.imf.haryanachi.utils.RemarrkUpdate;
import com.imf.haryanachi.utils.StaticValues;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HopListActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    List<DataItem> list;
    SharedPreferences sharedPref1;
    String paitentId, visitId;
    ImageView  backImage;
    Button remarkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_list);
        sharedPref1 = getSharedPreferences("show", Context.MODE_PRIVATE);
        paitentId = sharedPref1.getString("paitentId", "");
        visitId = sharedPref1.getString("visitId", "");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HopListActivity.this, LinearLayoutManager.VERTICAL, false);
        backImage = findViewById(R.id.back);
        recyclerView = findViewById(R.id.recyclist);
       // remarkButton = findViewById(R.id.remark_button);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        SharedPreferences sharedPref = getSharedPreferences("PGI", Context.MODE_PRIVATE);
        String distric_id = sharedPref.getString("distric_id", "");
        getHopital(distric_id);


        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HopListActivity.this,ShowPatientActivty.class));
                finish();
            }
        });

     /*   remarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              *//*  if (StaticValues.hospitalId.isEmpty()){
                    Toast.makeText(HopListActivity.this, "Select Hospital", Toast.LENGTH_SHORT).show();
                }else *//*openDilog();
            }
        });
*/

    }

    private void getHopital(String distric_id) {
//hospital_list
        HopisListRequest request = new HopisListRequest();
        request.setDistrict_id(distric_id);
        Call<HopListResponse> responceCall = ApiClient.getApiClient().hospital_list(request);
        responceCall.enqueue(new Callback<HopListResponse>() {
            @Override
            public void onResponse(Call<HopListResponse> call, Response<HopListResponse> response) {
                if (response.body().isStatus()) {

                    list = response.body().getData();
               if (list.size()>0) {
                   HopsListAdapter adapter = new HopsListAdapter(list, HopListActivity.this, paitentId, visitId);
                   recyclerView.setAdapter(adapter);
               }else if (list.size()==0){
                   new AlertDialog.Builder(HopListActivity.this)
                           .setTitle("HOSPITAL NOT AVAILABLE")
                           .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int which) {
                                   startActivity(new Intent(HopListActivity.this,ShowPatientActivty.class));
                                   finish();
                                   dialog.dismiss();
                               }
                           })
                           .setIcon(android.R.drawable.ic_dialog_alert)
                           .show();


               }



                }
            }

            @Override
            public void onFailure(Call<HopListResponse> call, Throwable t) {

            }
        });

    }


    private void openDilog() {

        final Dialog dialog = new Dialog(HopListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(false);
        dialog.setContentView(R.layout.remark_dialog);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        EditText editText=dialog.findViewById(R.id.remarkedt);
        Button button=dialog.findViewById(R.id.button);

        button.setOnClickListener(i->{
            if(editText.getText().toString().isEmpty()) {
                editText.setError("Add your remark");
            }else {
                update(editText.getText().toString(),dialog);
            }
        });
    }

    private void update(String remark,Dialog dialog) {
        UpdateFinalRequest request = new UpdateFinalRequest();
        request.setPatients_id(paitentId);
        request.setPatients_status("SEVERE");
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
                        StaticValues.status="";
                        StaticValues.hospitalId="";

                }
            }

            @Override
            public void onFailure(Call<UpdatefinalResponse> call, Throwable t) {
                Log.d("xxxxxxx", "ffffff");
            }
        });
    }
    private void pupup(String status) {
        new AlertDialog.Builder(HopListActivity.this)
                .setMessage(status)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(HopListActivity.this, EmployeeProfileActivity.class));
                       finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}