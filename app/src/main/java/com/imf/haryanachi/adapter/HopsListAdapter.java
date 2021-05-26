package com.imf.haryanachi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.imf.haryanachi.EmployeeProfileActivity;
import com.imf.haryanachi.R;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.hopiList.DataItem;
import com.imf.haryanachi.networkModel.updatefinal.UpdateFinalRequest;
import com.imf.haryanachi.networkModel.updatefinal.UpdatefinalResponse;
import com.imf.haryanachi.utils.StaticValues;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HopsListAdapter extends RecyclerView.Adapter<HopsListAdapter.MyViewHolder> {
    List<DataItem> list;
    Context context;
    String paitentId, visitId;



    public HopsListAdapter(List<DataItem> list, Context context, String paitentId, String visitId) {
        this.list = list;
        this.context = context;
        this.paitentId = paitentId;
        this.visitId = visitId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hospitals_list, parent, false);
        return new HopsListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.indexTextView.setText(position+1+"");
    holder.nameTextView.setText(list.get(position).getHospitalName());
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            holder.linearLayout.setBackgroundColor(Color.CYAN);
            update("Hospitalized",list.get(position).getHospitalId());


        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView indexTextView,nameTextView;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        linearLayout=itemView.findViewById(R.id.linHops);
        nameTextView=itemView.findViewById(R.id.hopname);
        indexTextView=itemView.findViewById(R.id.index);


        }
    }

    private void update(String status,String hospitalId) {
        UpdateFinalRequest request = new UpdateFinalRequest();
        request.setPatients_id(paitentId);
        request.setPatients_status(status);
        request.setVisit_id(visitId);
        request.setHospital_id(hospitalId);
        Call<UpdatefinalResponse> responceCall = ApiClient.getApiClient().update_final_status(request);
        responceCall.enqueue(new Callback<UpdatefinalResponse>() {
            @Override
            public void onResponse(Call<UpdatefinalResponse> call, Response<UpdatefinalResponse> response) {
                if (response.body().getStatus().equals("Success")) {
                    Log.d("xxxxxxx", "ggggg");
                    if (status.equals("Hospitalized")) {
                        //binding.progressBar.setVisibility(View.GONE);
                        pupup("PATIENT TRANSFER TO HOSPITAL SUCCESSFULLY");
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
        new AlertDialog.Builder(context)
                .setMessage(status)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(context, EmployeeProfileActivity.class));
                        ((Activity)context).finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
