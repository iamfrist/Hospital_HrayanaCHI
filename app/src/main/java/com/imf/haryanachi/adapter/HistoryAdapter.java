package com.imf.haryanachi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.imf.haryanachi.R;
import com.imf.haryanachi.networkModel.history.DataItem;

import java.util.List;

public class HistoryAdapter  extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>{
    Context context;
    List<DataItem> list;

    public HistoryAdapter(Context context, List<DataItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_list, parent, false);
        return new HistoryAdapter.MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rnoTextView.setText(position+1+"");
        holder.tempTextView.setText(list.get(position).getTemperature()+"");
        holder.pulseTextView.setText(list.get(position).getPulse()+"");
        holder.spo2TextView.setText(list.get(position).getSpo2()+"");
        holder.rrTextView.setText(list.get(position).getRate()+"");
        holder.sbpTextView.setText(list.get(position).getSystolicBp()+"");
        holder.dbpTextView.setText(list.get(position).getDiastolicBp()+"");
        holder.statusTextView.setText(list.get(position).getPatientsStatus()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

     TextView rnoTextView,tempTextView,pulseTextView,spo2TextView,rrTextView,sbpTextView,dbpTextView,statusTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rnoTextView=itemView.findViewById(R.id.rno);
            tempTextView=itemView.findViewById(R.id.temp);
            pulseTextView=itemView.findViewById(R.id.pulse);
            spo2TextView=itemView.findViewById(R.id.spo2);
            rrTextView=itemView.findViewById(R.id.rr);
            sbpTextView=itemView.findViewById(R.id.sbp);
            dbpTextView=itemView.findViewById(R.id.dbp);
            statusTextView =itemView.findViewById(R.id.status);
        }
    }
}
