package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.imf.haryanachi.adapter.HistoryAdapter;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.history.DataItem;
import com.imf.haryanachi.networkModel.history.HistoryRequest;
import com.imf.haryanachi.networkModel.history.HistoryResponse;
import com.imf.haryanachi.networkModel.status.StatusRequest;
import com.imf.haryanachi.networkModel.status.StatusResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusActivity extends AppCompatActivity {

    //  /*copy code*/
    private CodeScanner mCodeScanner;
    private CodeScannerView scannerView;

    private TextView genderView, agetxt, pnamrtxt;
    private String docname, paitentId, visitId, doctorId, center_name, ward_name, bed_number, patients_status, pname;
    List<StatusResponse> statusResponses;
    private Button button;
    private Button dashButton, statusButton, regButton;
    private String chc_id, phc_id,age,gender;
    private ImageView homeiamge;
    RecyclerView recycerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Intent intent = getIntent();
        docname = intent.getStringExtra("name");
        paitentId = intent.getStringExtra("paitentId");
        chc_id = intent.getStringExtra("chc_id");
        phc_id = intent.getStringExtra("phc_id");
        bed_number = intent.getStringExtra("bed_number");
        visitId = intent.getStringExtra("visitId");
        doctorId = intent.getStringExtra("doctorId");
        pname = intent.getStringExtra("pname");

        Log.d("messageee", visitId);
        Log.d("messageee", chc_id);
        Log.d("messageee", phc_id);
        Log.d("messageee", bed_number);

        Log.d("paitentId", paitentId);
        SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
        chc_id = sharedPref1.getString("chc_id", "");
        phc_id = sharedPref1.getString("phc_id", "");
        age = sharedPref1.getString("page", "");
        gender = sharedPref1.getString("gender", "");
        pname = sharedPref1.getString("pname", "");
        bed_number = sharedPref1.getString("bed_number", "");

        statusResponses = new ArrayList<>();
        pnamrtxt = findViewById(R.id.pname);
        agetxt = findViewById(R.id.p_age);
        genderView = findViewById(R.id.pgender);
        homeiamge = findViewById(R.id.homeiamge);
        agetxt.setText(age);
        genderView.setText(gender);
        pnamrtxt.setText(pname);
//        nametxt.setText(docname);
        scannerView = findViewById(R.id.scanner_view3);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.startPreview();
        statusButton = findViewById(R.id.update);
        recycerview = findViewById(R.id.recycerview);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recycerview.setLayoutManager(layoutManager);
        recycerview.setHasFixedSize(true);
     /*   homeiamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatusActivity.this, EmployeeProfileActivity.class);
                startActivity(intent);
            }
        });*/
        statusButton.setOnClickListener((View v) -> {
            Intent intent1 = new Intent(StatusActivity.this, VitalsActivity.class);
            intent.putExtra("name", docname);
            intent.putExtra("chc_id", chc_id);//doctorId
            intent.putExtra("doctorId", doctorId);//doctorId
            intent.putExtra("phc_id", phc_id);//doctorId
            intent.putExtra("bed_number", bed_number);//doctorId
            intent.putExtra("paitentId", paitentId);//doctorId
            intent.putExtra("visitId", visitId);//doctorId
            startActivity(intent1);
            finish();
        });
        getHistory(paitentId);

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(StatusActivity.this,VitalsActivity.class);
                intent1.putExtra("pname",pname);
                intent1.putExtra("visitId",visitId);
                intent1.putExtra("doctorId",doctorId);
                intent1.putExtra("paitentId",paitentId);
                intent1.putExtra("docname",docname);
                startActivity(intent1);

            }
        });*/


//        /*here value get from qr code */
//        mCodeScanner.setDecodeCallback(new DecodeCallback() {
//            @Override
//            public void onDecoded(@NonNull final Result result) {
//                try {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            String[] temp;
//                            String strMain = result.getText();
//                            Log.d("myDataaaa", strMain);
//                            String delimiter = ",";
//                            temp = strMain.split(delimiter);
//                            StatusRequest request = new StatusRequest();
//                            try {
//                                if (temp[0].isEmpty()) {
//                                } else center_name = temp[0];
//                            } catch (Exception e) {
//                            }
//                            try {
//                                if (temp[1].isEmpty()) {
//                                } else ward_name = temp[1];
//                            } catch (Exception e) {
//                            }
//                            try {
//                                if (temp[2].isEmpty()) {
//                                } else bed_number = temp[2];
//                            } catch (Exception e) {
//                            }
//                            try {
//                                if (temp[3].isEmpty()) {
//                                } else patients_status = temp[3];
//                            } catch (Exception e) {
//                            }
//
//                            request.setVisit_id(visitId);
//                            request.setPatients_id(paitentId);
//                            request.setPatients_status(patients_status);
//                            request.setBed_number(bed_number);
//                            request.setCenter_name(center_name);
//                            request.setWard_name(ward_name);
//
//                            paitentStatus(request);
//                        }
//                    });
//                } catch (Exception e) {
//
//                }
//
//            }
//        });


    }

    private void getHistory(String paitentId) {
        HistoryRequest  request=new HistoryRequest();
        request.setPatients_id(paitentId);
        Call<HistoryResponse> responceCall = ApiClient.getApiClient().mobile_patients_history(request);

    responceCall.enqueue(new Callback<HistoryResponse>() {
        @Override
        public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
            if (response.body().isStatus()){
                List<DataItem> items=response.body().getData();
                HistoryAdapter adapter=new HistoryAdapter(StatusActivity.this,items);
                recycerview.setAdapter(adapter);
            }
        }

        @Override
        public void onFailure(Call<HistoryResponse> call, Throwable t) {

        }
    });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You should update Patient first", Toast.LENGTH_SHORT).show();
    }

    private void paitentStatus(StatusRequest request) {

        try {
            Call<StatusResponse> responceCall = ApiClient.getApiClient().status(request);
            responceCall.enqueue(new Callback<StatusResponse>() {
                @Override
                public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {

//                    mCodeScanner.stopPreview();
//                    mCodeScanner.releaseResources();

                    Log.d("responce", response.body().getMessage() + "  status");
                    Intent intent = new Intent(StatusActivity.this, CallActivity.class);
                    intent.putExtra("doctorId", doctorId);
                    intent.putExtra("visitId", visitId);
                    intent.putExtra("patientId", paitentId);
                    intent.putExtra("docname", docname);
                    intent.putExtra("patients_status", patients_status);
                    intent.putExtra("center_name", response.body().getCenter_name());
                    intent.putExtra("ward_name", response.body().getWard_name());
                    intent.putExtra("bed_number", bed_number);
                    intent.putExtra("pname", pname);
                    startActivity(intent);
                    finish();

                }

                @Override
                public void onFailure(Call<StatusResponse> call, Throwable t) {
//                    mCodeScanner.stopPreview();
//                    mCodeScanner.startPreview();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //  mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        //  mCodeScanner.releaseResources();
        super.onPause();
    }
}