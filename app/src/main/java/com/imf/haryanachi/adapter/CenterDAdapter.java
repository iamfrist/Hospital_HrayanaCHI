package com.imf.haryanachi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.imf.haryanachi.CenterActivity;
import com.imf.haryanachi.R;
import com.imf.haryanachi.networkModel.all_center_details_api_statewise.DataItem;

import java.util.List;

public class CenterDAdapter extends RecyclerView.Adapter<CenterDAdapter.MyViewHolder> {
    Context context;
    List<DataItem> list;

    public CenterDAdapter(Context context, List<DataItem> data) {
        this.context = context;
        this.list = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.center_list, parent, false);
        return new CenterDAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rnoTextView.setText(position + 1 + "");
        holder.hcf.setText(list.get(position).getHcfname() + "");
        holder.comor.setText(list.get(position).getComorbcount() + "");
        holder.family.setText(list.get(position).getOverfamily() + "");
        holder.positive.setText(list.get(position).getAdmitlengthcount()+ "");
        holder.mild.setText(list.get(position).getStablecount() + "");
        holder.mod.setText(list.get(position).getCritcalcount() + "");
        holder.severe.setText(list.get(position).getVcriticalcount() + "");
        holder.member.setText(list.get(position).getLastcount() + "");

      /*  holder.distric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(context, CenterActivity.class);
                intent.putExtra("district_id", list.get(position).getDistrictId() + "");
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        HorizontalScrollView horiscroll;
        LinearLayout line;
        TextView rnoTextView, hcf, comor, family, positive, mild, mod, severe,member/*, disc, hopi, nvisit*/;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rnoTextView = itemView.findViewById(R.id.rno);
            line = itemView.findViewById(R.id.line);
            horiscroll = itemView.findViewById(R.id.horiscroll);
            member = itemView.findViewById(R.id.member);
            hcf = itemView.findViewById(R.id.hcf);
            comor = itemView.findViewById(R.id.comor);
            family = itemView.findViewById(R.id.family);
            positive = itemView.findViewById(R.id.positive);
            mild = itemView.findViewById(R.id.mild);
            mod = itemView.findViewById(R.id.mod);
            severe = itemView.findViewById(R.id.severe);
         /*   disc = itemView.findViewById(R.id.disc);
            hopi = itemView.findViewById(R.id.hopi);
            nvisit = itemView.findViewById(R.id.nvisit);*/
        }
    }
}
