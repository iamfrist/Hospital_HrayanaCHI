package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.imf.haryanachi.adapter.CenterDAdapter;
import com.imf.haryanachi.databinding.ActivityCenterBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.all_center_details_api_statewise.CenterDetailResponse;
import com.imf.haryanachi.networkModel.all_center_details_api_statewise.DataItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CenterActivity extends AppCompatActivity {
    String district_id;
    ActivityCenterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_center);
        Intent intent = getIntent();
        district_id = intent.getStringExtra("district_id");
        Log.d("logmeeee", district_id + "   sada");


        getdata(district_id);
    }

    private void getdata(String district_id) {
        binding.progressBar.setVisibility(View.VISIBLE);
        Call<CenterDetailResponse> call = ApiClient.getApiClient().all_center_details_api_statewise(district_id);
        call.enqueue(new Callback<CenterDetailResponse>() {
                         @Override
                         public void onResponse(Call<CenterDetailResponse> call, Response<CenterDetailResponse> response) {
                             if (response.body().isStatus()){
                                 List<DataItem> data=response.body().getData();

                                 CenterDAdapter adapter=new CenterDAdapter(CenterActivity.this,data);
                                 binding.recyeler.setAdapter(adapter);
                                 binding.progressBar.setVisibility(View.GONE);

                             }
                         }

                         @Override
                         public void onFailure(Call<CenterDetailResponse> call, Throwable t) {

                         }
                     }
        );

    }
}