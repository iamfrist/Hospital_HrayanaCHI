package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.imf.haryanachi.databinding.ActivityEmployeeDashboardBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.dashActiveCount.DashActiveCountResponse;
import com.imf.haryanachi.networkModel.phcDashCount.PhcDashCountRequest;
import com.imf.haryanachi.networkModel.phcDashCount.PhcDashCountResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeDashboardActivity extends AppCompatActivity {
    private String dname, id, visitId, chc_id, phc_id, team_number, distric, centername, wardname;
    ActivityEmployeeDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_dashboard);
        binding.progressBar.setVisibility(View.VISIBLE);
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
        getDashCount(phc_id);
        getActiveDashCount(phc_id);
        binding.progressBar.setVisibility(View.GONE);
        binding.homeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeDashListActivity.class);
                intent.putExtra("value", "family");
                startActivity(intent);
                finish();
            }
        });
        binding.positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeDashListActivity.class);
                intent.putExtra("value", "positive");
                startActivity(intent);
                finish();
            }
        });

        binding.disch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeDashListActivity.class);
                intent.putExtra("value", "Discharged");
                startActivity(intent);
                finish();
            }
        });
        binding.hops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeDashboardActivity.this, EmployeeDashListActivity.class);
                intent.putExtra("value", "Hospitalized");
                startActivity(intent);
                finish();
            }
        });


    }

    private void getActiveDashCount(String phc_id) {
        binding.progressBar.setVisibility(View.VISIBLE);
        PhcDashCountRequest request = new PhcDashCountRequest();
        request.setPhc_id(phc_id);
        Call<DashActiveCountResponse> responceCall = ApiClient.getApiClient().dashboard_active_case_count(request);

        responceCall.enqueue(new Callback<DashActiveCountResponse>() {
            @Override
            public void onResponse(Call<DashActiveCountResponse> call, Response<DashActiveCountResponse> response) {
                if (response.body().isStatus()) {
                    binding.families.setText(Integer.toString(response.body().getData().getFamilycountts()));
                    binding.member.setText(Integer.toString(response.body().getData().getMembercount()));
                    binding.comor.setText(Integer.toString(response.body().getData().getComorbcount()));
                    binding.pcase.setText(Integer.toString(response.body().getData().getPositivecount()));
                    binding.mild.setText(Integer.toString(response.body().getData().getUniquemild()));
                    binding.moderate.setText(Integer.toString(response.body().getData().getUniquemoderate()));
                    binding.severe.setText(Integer.toString(response.body().getData().getUniquesevere()));
                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<DashActiveCountResponse> call, Throwable t) {

            }
        });

    }

    private void getDashCount(String phc_id) {
        binding.progressBar.setVisibility(View.VISIBLE);
        PhcDashCountRequest request = new PhcDashCountRequest();
        request.setPhc_id(phc_id);
        Call<PhcDashCountResponse> responceCall = ApiClient.getApiClient().phc_dashboard_count(request);

        responceCall.enqueue(new Callback<PhcDashCountResponse>() {
            @Override
            public void onResponse(Call<PhcDashCountResponse> call, Response<PhcDashCountResponse> response) {
                if (response.body().isStatus()) {
                    binding.familycount.setText(Integer.toString(response.body().getData().getFamiliescount()));
                    binding.positivecount.setText(Integer.toString(response.body().getData().getAllPositive()));
                    binding.dischcount.setText(Integer.toString(response.body().getData().getFinaldischg()));
                    binding.hopscount.setText(Integer.toString(response.body().getData().getFinalhospital()));
                    binding.progressBar.setVisibility(View.GONE);
                    Log.d("familycount", response.body().getData().getFamiliescount() + "");


                }
            }

            @Override
            public void onFailure(Call<PhcDashCountResponse> call, Throwable t) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences preferences = getSharedPreferences("session", MODE_PRIVATE);
        if (preferences.getString("session", "").equals("ok")) {
            startActivity(new Intent(this, EmployeeProfileActivity.class));
            finish();
        }
    }
}