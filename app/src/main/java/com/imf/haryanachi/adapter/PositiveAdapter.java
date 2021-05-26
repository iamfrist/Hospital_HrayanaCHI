package com.imf.haryanachi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imf.haryanachi.R;
import com.imf.haryanachi.networkModel.positiveList.DataItem;

import java.util.List;

public class PositiveAdapter extends RecyclerView.Adapter<PositiveAdapter.MyViewHolder> {

    List<DataItem> items;
    Context context;

    public PositiveAdapter(List<DataItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.positive_list, parent, false);
        return new PositiveAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.srnoView.setText(position+1+"");
        holder.nameView.setText(items.get(position).getPname());
        holder.durationView.setText(items.get(position).getDayscount()+"");
        holder.comorView.setText(items.get(position).getNoComo());
        holder.vtimeView.setText(items.get(position).getVisitTime());
        holder.vdateView.setText(items.get(position).getVisitDate());
        Log.d("logmeee",items.get(position).getDayscount()+"");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView srnoView,nameView,durationView,comorView,vtimeView,vdateView;
            public MyViewHolder(@NonNull View itemView) {
            super(itemView);

                srnoView=itemView.findViewById(R.id.srno);
                nameView=itemView.findViewById(R.id.name);
                durationView=itemView.findViewById(R.id.duration);
                comorView=itemView.findViewById(R.id.comor);
                vdateView=itemView.findViewById(R.id.vdate);
                vtimeView=itemView.findViewById(R.id.vtime);

            }
    }
    //This method will filter the list
    //here we are passing the filtered data
    //and assigning it to the list with notifydatasetchanged method
    public void filterList(List<DataItem> filterdNames) {
        this.items = filterdNames;
        notifyDataSetChanged();
    }


}
