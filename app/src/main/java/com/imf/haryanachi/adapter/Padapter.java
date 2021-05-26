package com.imf.haryanachi.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imf.haryanachi.AddNewPatientActivity;
import com.imf.haryanachi.DashBoardActivity2;
import com.imf.haryanachi.R;
import com.imf.haryanachi.networkModel.addMember.DataItem;


import java.util.List;

public class Padapter extends RecyclerView.Adapter<Padapter.MyViewHolder> {

    Context context;
    List<DataItem> list;

    public Padapter(DashBoardActivity2 regiActivity, List<DataItem> list) {
        context = regiActivity;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.patient_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getFamilyholder());
        holder.linearfamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref1 = context.getSharedPreferences("addnewP", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref1.edit();
                editor.putString("familyholder", list.get(position).getFamilyholder());
                editor.putString("familymember", list.get(position).getFamilymember());
                editor.putString("positivemember", list.get(position).getPositivemember());
                editor.putString("comorbidities", list.get(position).getComorbidities());
                editor.putString("familyaddress", list.get(position).getFamilyaddress());
                editor.putString("familycity", list.get(position).getFamilycity());
                editor.putString("familystate", list.get(position).getFamilystate());
                editor.putString("holdermobile", list.get(position).getHoldermobile());
                editor.putString("password", list.get(position).getPassword());
                editor.putString("district_id", list.get(position).getDistrictId());
                editor.putString("chc_id", list.get(position).getChcId());
                editor.putString("phc_id", list.get(position).getPhcId());
                editor.apply();

                context.startActivity(new Intent(context, AddNewPatientActivity.class));

          /*    "familyholder": "PARIHAR ",
            "familymember": "6",
            "positivemember": "3",
            "comorbidities": "2",
            "familyaddress": "NAGPUR ",
            "familycity": "NAGPUR ",
            "familystate": "",
            "holdermobile": "9422591515",
            "password": "",
            "district_id": "5f795f2caa36e77b8ab6e3fe",
            "chc_id": "5f7960f0aa36e77b8ab6e412",
            "phc_id": "5f796e50aa36e77b8ab6e4ac"*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout linearfamily;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);
            linearfamily = itemView.findViewById(R.id.linearfamily);

        }
    }
}
