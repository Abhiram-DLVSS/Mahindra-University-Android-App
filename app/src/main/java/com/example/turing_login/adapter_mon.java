package com.example.turing_login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import java.util.ArrayList;

public class adapter_mon extends RecyclerView.Adapter<adapter_mon.viewholdermon> {
    ArrayList<datamodel_mon> dataholder_mon;

    public adapter_mon(ArrayList<datamodel_mon> dataholder_mon){this.dataholder_mon=dataholder_mon; }


    @NonNull
    @Override
    public viewholdermon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design_mon,parent,false);
        return new viewholdermon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholdermon holder, int position) {
        holder.header.setText(dataholder_mon.get(position).getHeader());
        holder.desc.setText(dataholder_mon.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return dataholder_mon.size();
    }

    class viewholdermon extends RecyclerView.ViewHolder{

        TextView header,desc;
        public viewholdermon(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.textview_mon_frag);
            desc =itemView.findViewById(R.id.textview2_mon_frag);
        }
    }
}

