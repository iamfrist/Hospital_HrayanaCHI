package com.imf.haryanachi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.imf.haryanachi.adapter.AllStateAdapter;
import com.imf.haryanachi.databinding.ActivitySuperAdminBinding;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.all_state_count_api.AllStateResponse;
import com.imf.haryanachi.networkModel.all_state_count_api.DataItem;
import com.imf.haryanachi.networkModel.overall_state_count.OveralStateResponse;
import com.imf.haryanachi.networkModel.todays_state_count.TodaysStateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuperAdminActivity extends AppCompatActivity {


    private int Y_BUFFER = 10;
    private float preX = 0f;
    private float preY = 0f;
    ActivitySuperAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_admin);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_super_admin);

        binding.imglogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilog();
            }
        });

        binding.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(SuperAdminActivity.this,StateCountActivity.class));

            }
        });

        binding.progressBar.setVisibility(View.VISIBLE);
        getOverall();
        getTodat();
     //
    }
void dilog(){
    new AlertDialog.Builder(SuperAdminActivity.this)
            .setTitle("LOGOUT")
            .setMessage("Do You Want To Logout ?")

            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Continue with delete operation

                    SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("session", "cancel");
                    editor.putString("name", "");
                    editor.putString("id", "");
                    editor.apply();
                    Intent intent = new Intent(SuperAdminActivity.this, AllLognActivity.class);
                    startActivity(intent);
                    finish();
                    dialog.dismiss();
                }
            })

            // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();

}

    private void getTodat() {

        Call<TodaysStateResponse> call = ApiClient.getApiClient().todays_state_count();
        call.enqueue(new Callback<TodaysStateResponse>() {
            @Override
            public void onResponse(Call<TodaysStateResponse> call, Response<TodaysStateResponse> response) {
                if (response.body().isStatus()) {
                    com.imf.haryanachi.networkModel.todays_state_count.Data data = response.body().getData();
                    binding.progressBar.setVisibility(View.GONE);
                    Log.d("jnknjknjnj", response.body().getData().getTodayscount() + "   lml,l,ml");
                    binding.familyTxt.setText("" + response.body().getData().getTodayscount());
                    binding.positiveTxt.setText("" + response.body().getData().getTodaysPositive());
                    binding.mildTxt.setText("" + response.body().getData().getUniquemild());
                    binding.modTxt.setText("" + response.body().getData().getUniquemoderate());
                    binding.severTxt.setText("" + response.body().getData().getUniquesevere());
                }
            }

            @Override
            public void onFailure(Call<TodaysStateResponse> call, Throwable t) {

            }
        });

    }

    private void getOverall() {
        Call<OveralStateResponse> call = ApiClient.getApiClient().overall_state_count();
        call.enqueue(new Callback<OveralStateResponse>() {
            @Override
            public void onResponse(Call<OveralStateResponse> call, Response<OveralStateResponse> response) {
                if (response.body().isStatus()) {
                    Log.d("jnknjknjnj", response.body().getData().getFinaldischg() + "   lml,l,ml");
                    binding.familycount.setText("" + response.body().getData().getUniqueadmit());
                    binding.positivecount.setText("" + response.body().getData().getAllPositive());
                    binding.dischcount.setText("" + response.body().getData().getFinaldischg());
                    binding.hopscount.setText("" + response.body().getData().getFinalhospital());
                    binding.progressBar.setVisibility(View.GONE);
               }
            }

            @Override
            public void onFailure(Call<OveralStateResponse> call, Throwable t) {

            }
        });

    }

}