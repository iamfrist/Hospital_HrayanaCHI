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

import com.imf.haryanachi.PaitentDetailsActivity;
import com.imf.haryanachi.R;
import com.imf.haryanachi.networkModel.patientLogin.PatientsdataItem;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    List<PatientsdataItem> list;
    Context context;

    public UserAdapter(List<PatientsdataItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userlayout, parent, false);
        return new UserAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {

        holder.nameTextView.setText(list.get(position).getPname());
        holder.ageTextView.setText(list.get(position).getPAge());
        holder.genderTextView.setText(list.get(position).getPgender());
        holder.mobileTextView.setText(list.get(position).getPnumber());
        holder.regnoTextView.setText(list.get(position).getRegno());
      //  holder.comoTextView.setText(list.get(position).get());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = context.getSharedPreferences("patient", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("qrcode", list.get(position).getQrcode());
                editor.putString("pId", list.get(position).getPatientsId());
                editor.putString("pname", list.get(position).getPname());
                editor.putString("age", list.get(position).getPAge());
                editor.putString("gender", list.get(position).getPgender());
                editor.putString("mobile", list.get(position).getPnumber());
                editor.putString("regno", list.get(position).getRegno());
                editor.apply();

                Intent intent=new Intent(context, PaitentDetailsActivity.class);
                context.startActivity(intent);



            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView nameTextView, ageTextView, genderTextView, comoTextView, mobileTextView,regnoTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            linearLayout = itemView.findViewById(R.id.linaeruser);
            nameTextView = itemView.findViewById(R.id.name);
            ageTextView = itemView.findViewById(R.id.age);
            genderTextView = itemView.findViewById(R.id.GENDER);
          //  comoTextView = itemView.findViewById(R.id.comor);
            mobileTextView = itemView.findViewById(R.id.mobile);
            regnoTextView = itemView.findViewById(R.id.regno);

        }
    }
}
