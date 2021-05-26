package com.imf.haryanachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.imf.haryanachi.adapter.AllStateAdapter;
import com.imf.haryanachi.databinding.ActivityStateCountBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.all_state_count_api.AllStateResponse;
import com.imf.haryanachi.networkModel.all_state_count_api.DataItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StateCountActivity extends AppCompatActivity {


    ActivityStateCountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_count);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_state_count);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(
                StateCountActivity.this,
                LinearLayoutManager.VERTICAL,
                false));
        binding.progressBar.setVisibility(View.VISIBLE);
        getall_state_count_api();
    }
    private void getall_state_count_api() {
        //recyclerview
        Call<AllStateResponse> call = ApiClient.getApiClient().all_state_count_api();
        call.enqueue(new Callback<AllStateResponse>() {
            @Override
            public void onResponse(Call<AllStateResponse> call, Response<AllStateResponse> response) {
                if (response.body().isStatus()) {
                    List<DataItem> dataItems = response.body().getData();
                    AllStateAdapter adapter = new AllStateAdapter(StateCountActivity.this, dataItems);
                    binding.recyclerview.setAdapter(adapter);

                    binding.progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AllStateResponse> call, Throwable t) {

            }
        });

    }

}