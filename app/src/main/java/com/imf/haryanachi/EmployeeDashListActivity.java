package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.imf.haryanachi.adapter.FamilyAdapter;
import com.imf.haryanachi.adapter.HopsAdapter;
import com.imf.haryanachi.adapter.PositiveAdapter;
import com.imf.haryanachi.databinding.ActivityEmployeeDashListBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.dischaerge.DischargeRequest;
import com.imf.haryanachi.networkModel.dischaerge.DischargeResponse;
import com.imf.haryanachi.networkModel.familyList.FamilyListResponse;
import com.imf.haryanachi.networkModel.phcDashCount.PhcDashCountRequest;
import com.imf.haryanachi.networkModel.positiveList.DataItem;
import com.imf.haryanachi.networkModel.positiveList.PositiveListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeDashListActivity extends AppCompatActivity {
    private String dname, id, visitId, chc_id, phc_id, team_number, distric, centername, wardname;
    ActivityEmployeeDashListBinding binding;
    Intent intent;
    String valueIntentStr;
    RecyclerView recyclerView;
    List<com.imf.haryanachi.networkModel.familyList.DataItem> family_items_list;
    FamilyAdapter familyAdapter;
    List<DataItem> positive_items_list;
    PositiveAdapter positiveAdapter;
    HopsAdapter hopsadapter;
    List<com.imf.haryanachi.networkModel.dischaerge.DataItem> hops_items_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dash_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_dash_list);

        SharedPreferences sharedPref1 = getSharedPreferences("PGI", Context.MODE_PRIVATE);
        dname = sharedPref1.getString("name", "");
        id = sharedPref1.getString("id", "");
        chc_id = sharedPref1.getString("chc_id", "");
        phc_id = sharedPref1.getString("phc_id", "");
        team_number = sharedPref1.getString("team_member", "");
        distric = sharedPref1.getString("distric", "");
        centername = sharedPref1.getString("centername", "");
        wardname = sharedPref1.getString("wardname", "");

        binding.DISTRICT.setText(distric);
        binding.chc.setText(centername);
        binding.PHC.setText(wardname);
        binding.TEAMNO.setText(team_number);
        binding.name.setText("WELCOME " + dname);
        search();
        recyclerView = findViewById(R.id.recy);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        binding.homeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDashListActivity.this, EmployeeProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        intent = getIntent();
        valueIntentStr = intent.getStringExtra("value");
        if (valueIntentStr.equals("family")) {
            binding.listname.setText("family list");
            binding.familylinear.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
            getFamilyData(phc_id);
        } else if (valueIntentStr.equals("positive")) {
            binding.listname.setText("positive person list");
            binding.positivelinear.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
            getPositiveData(phc_id);
        } else if (valueIntentStr.equals("Discharged")) {
            binding.listname.setText("Discharged person list");
            binding.hopslinear.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
            getHospitalizedData("Discharged", phc_id);
        } else if (valueIntentStr.equals("Hospitalized")) {
            binding.listname.setText("Hospitalized person list");
            binding.hopslinear.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
            getHospitalizedData("Hospitalized", phc_id);
        }

    }

    private void search() {

        binding.searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(binding.searchEdt.getText().toString());
            }
        });

    }

    private void filter(String text) {


        if (valueIntentStr.equals("family")) {

            List<com.imf.haryanachi.networkModel.familyList.DataItem> filterdNames = new ArrayList<>();
            for (com.imf.haryanachi.networkModel.familyList.DataItem s : family_items_list) {
                //if the existing elements contains the search input
                if (s.getFamilyholder().toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames.add(s);
                }
            }
            familyAdapter.filterList(filterdNames);

        }
        else if (valueIntentStr.equals("positive")) {
            List<DataItem> filterdNames = new ArrayList<>();
            Log.d("logmeeee", positive_items_list.size() + "  sdc");
            for (DataItem s : positive_items_list) {
                //if the existing elements contains the search input
                if (s.getPname().toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames.add(s);
                }
            }
            positiveAdapter.filterList(filterdNames);
        }
        else if (valueIntentStr.equals("Discharged")) {
            List<com.imf.haryanachi.networkModel.dischaerge.DataItem> filterdNames = new ArrayList<>();
            for (com.imf.haryanachi.networkModel.dischaerge.DataItem s : hops_items_list) {
                //if the existing elements contains the search input
                if (s.getPname().toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames.add(s);
                }
            }
            hopsadapter.filterList(filterdNames);
        } else if (valueIntentStr.equals("Hospitalized")) {
            List<com.imf.haryanachi.networkModel.dischaerge.DataItem> filterdNames = new ArrayList<>();
            for (com.imf.haryanachi.networkModel.dischaerge.DataItem s : hops_items_list) {
                //if the existing elements contains the search input
                if (s.getPname().toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames.add(s);
                }
            }
            hopsadapter.filterList(filterdNames);
        }


    }

    private void getHospitalizedData(String status, String phc_id) {
        Log.d("logmeee", phc_id);
        DischargeRequest request = new DischargeRequest();
        request.setPhc_id(phc_id);
        request.setPatient_status(status);
        Call<DischargeResponse> responceCall = ApiClient.getApiClient().mobile_discharged_list(request);
        responceCall.enqueue(new Callback<DischargeResponse>() {
            @Override
            public void onResponse(Call<DischargeResponse> call, Response<DischargeResponse> response) {
                if (response.body().isStatus()) {
                    Log.d("logmeee", response.body().getMessage());
                   hops_items_list = response.body().getData();
                     hopsadapter = new HopsAdapter(hops_items_list, EmployeeDashListActivity.this);
                    recyclerView.setAdapter(hopsadapter);
                    binding.progressBar.setVisibility(View.GONE);

                    if (hops_items_list.isEmpty()) {
                        binding.norecord.setVisibility(View.VISIBLE);
                    }


                }
                binding.progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<DischargeResponse> call, Throwable t) {
                Log.d("logmeee", "" + t.getMessage());
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void getPositiveData(String phc_id) {
        PhcDashCountRequest request = new PhcDashCountRequest();
        request.setPhc_id(phc_id);
        Call<PositiveListResponse> responceCall = ApiClient.getApiClient().mobile_positive_list(request);

        responceCall.enqueue(new Callback<PositiveListResponse>() {
            @Override
            public void onResponse(Call<PositiveListResponse> call, Response<PositiveListResponse> response) {
                if (response.body().isStatus()) {
                    binding.progressBar.setVisibility(View.GONE);
                    positive_items_list = response.body().getData();
                    positiveAdapter = new PositiveAdapter(positive_items_list, EmployeeDashListActivity.this);
                    recyclerView.setAdapter(positiveAdapter);
                    if (positive_items_list.isEmpty()) {
                        binding.norecord.setVisibility(View.VISIBLE);
                    }
                }
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PositiveListResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void getFamilyData(String phc_id) {
        PhcDashCountRequest request = new PhcDashCountRequest();
        request.setPhc_id(phc_id);
        Call<FamilyListResponse> responceCall = ApiClient.getApiClient().show_family_list(request);

        responceCall.enqueue(new Callback<FamilyListResponse>() {
            @Override
            public void onResponse(Call<FamilyListResponse> call, Response<FamilyListResponse> response) {
                if (response.body().isStatus()) {
                    family_items_list = response.body().getData();
                    binding.progressBar.setVisibility(View.GONE);
                    familyAdapter = new FamilyAdapter(family_items_list, EmployeeDashListActivity.this);
                    recyclerView.setAdapter(familyAdapter);
                    if (family_items_list.isEmpty()) {
                        binding.norecord.setVisibility(View.VISIBLE);
                    }
                }
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<FamilyListResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EmployeeDashListActivity.this, EmployeeDashboardActivity.class);
        startActivity(intent);
        finish();
    }
}