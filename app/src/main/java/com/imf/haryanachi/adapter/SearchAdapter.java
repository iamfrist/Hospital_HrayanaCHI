package com.imf.haryanachi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imf.haryanachi.R;
import com.imf.haryanachi.StatusActivity;
import com.imf.haryanachi.network.ApiClient;
import com.imf.haryanachi.networkModel.paitentReg.PaintentRequest;
import com.imf.haryanachi.networkModel.paitentReg.RegiResponse;
import com.imf.haryanachi.networkModel.search.DataItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyviewHolder> {

    List<DataItem> items;
    Context context;
    private String dname, id, visitId, chc_id, phc_id, bed_number;
    String currentDateTimeString;

    public SearchAdapter(List<DataItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchlayout, parent, false);
        return new SearchAdapter.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {


        holder.lnameTextView.setText(items.get(position).getLname());
        holder.nameTextView.setText(items.get(position).getPatientName());
        holder.mobilenoTextView.setText(items.get(position).getPnumber());
        holder.regnoTextView.setText(items.get(position).getRegno());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration(items.get(position).getCr_no(), items.get(position).getHoldermobile(),items.get(position).getPatientId());
                SharedPreferences sharedPref1 = context.getSharedPreferences("PGI", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref1.edit();
                editor.putString("page", items.get(position).getPgae());
                editor.putString("gender", items.get(position).getPgender());
                editor.putString("pname", items.get(position).getPatientName());

                editor.apply();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView regnoTextView, nameTextView, lnameTextView, mobilenoTextView;
        LinearLayout linearLayout;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name);
            lnameTextView = itemView.findViewById(R.id.lname);
            linearLayout = itemView.findViewById(R.id.linearla);
            mobilenoTextView = itemView.findViewById(R.id.mobileno);
            regnoTextView = itemView.findViewById(R.id.regno);
        }
    }

    private void registration(String crNo, String holdermobile,String pid) {
        SharedPreferences sharedPref1 = context.getSharedPreferences("PGI", Context.MODE_PRIVATE);
        dname = sharedPref1.getString("name", "");
        id = sharedPref1.getString("id", "");
        chc_id = sharedPref1.getString("chc_id", "");
        phc_id = sharedPref1.getString("phc_id", "");
        bed_number = sharedPref1.getString("bed_number", "");
        try {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

            try {
                currentDateTimeString = sdf.format(d);

            } catch (Exception e) {
            }

            PaintentRequest request = new PaintentRequest();
            request.setCr_no(crNo);
            request.setVisit_time(currentDateTimeString + "");
            request.setDoctor_id(id);
            request.setPatient_id(pid);
            request.setChc_id(chc_id);
            request.setPhc_id(phc_id);
           request.setHoldermobile(holdermobile);
     

            Call<RegiResponse> responceCall = ApiClient.getApiClient().regi(request);
            responceCall.enqueue(new Callback<RegiResponse>() {
                @Override
                public void onResponse(Call<RegiResponse> call, Response<RegiResponse> response) {

                    visitId = response.body().getData().getVisitId();
                    Intent intent = new Intent(context, StatusActivity.class);
                    intent.putExtra("name", dname);
                    intent.putExtra("doctorId", id);//doctorId
                    intent.putExtra("visitId", response.body().getData().getVisitId());
                    intent.putExtra("paitentId", response.body().getData().getPatientsId());
                    intent.putExtra("pname", response.body().getData().getPname());
                    intent.putExtra("chc_id", chc_id);//doctorId
                    intent.putExtra("phc_id", phc_id);//doctorId
                    intent.putExtra("bed_number", bed_number);//doctorId
                    context.startActivity(intent);
                    Log.d("myValueeee", items.get(0).getPatientId() + "");
                    Log.d("myValueeee", items.get(0).getPatientName() + "");
                    SharedPreferences sharedPref1 = context.getSharedPreferences("PGI", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref1.edit();
                    editor.putString("visitId", response.body().getData().getVisitId());
                    editor.putString("paitentId", response.body().getData().getPatientsId());
                    editor.putString("pname", response.body().getData().getPname());
                    editor.apply();
                    ((Activity)context).finish();


                }

                @Override
                public void onFailure(Call<RegiResponse> call, Throwable t) {

                    Toast.makeText(context, "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {

        }

    }


}
