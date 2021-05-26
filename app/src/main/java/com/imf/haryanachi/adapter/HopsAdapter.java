package com.imf.haryanachi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imf.haryanachi.R;
import com.imf.haryanachi.networkModel.dischaerge.DataItem;

import java.util.List;

public class HopsAdapter extends RecyclerView.Adapter<HopsAdapter.MyViewHolder>{

    List<DataItem> items;
    Context context;

    public HopsAdapter(List<DataItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hops_list, parent, false);
        return new HopsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.srnoView.setText(position+1+"");
        holder.nameView.setText(items.get(position).getPname());
        holder.ageView.setText(items.get(position).getPAge()+"");
        holder.genderView.setText(items.get(position).getPgender());
        holder.vtimeView.setText(items.get(position).getVisitTime());
        holder.vdateView.setText(items.get(position).getVisitDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView srnoView,nameView,ageView,genderView,vtimeView,vdateView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            srnoView=itemView.findViewById(R.id.srno);
            nameView=itemView.findViewById(R.id.name);
            ageView=itemView.findViewById(R.id.age);
            genderView=itemView.findViewById(R.id.gender);
            vdateView=itemView.findViewById(R.id.vdate);
            vtimeView=itemView.findViewById(R.id.vtime);
        }
    }
    //This method will filter the list
    //here we are passing the filtered data
    //and assigning it to the list with notifydatasetchanged method
    public void filterList(List<com.imf.haryanachi.networkModel.dischaerge.DataItem> filterdNames) {
        this.items = filterdNames;
        notifyDataSetChanged();
    }
}
