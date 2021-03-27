package com.example.turing_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThuAdapter extends RecyclerView.Adapter<ThuAdapter.ViewHolder> {

    private Context context_thu;
    private List<Listitem_thufrag> listItems_thufrag;

    public ThuAdapter(List<Listitem_thufrag> listItems_thufrag, Context context_thu) {
        this.listItems_thufrag = listItems_thufrag;
        this.context_thu = context_thu;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_thu).inflate(R.layout.list_item_thufrag
                ,parent
                ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_thufrag listitem_thufrag =listItems_thufrag.get(position);

        holder.textview_heading_thufragv.setText(listitem_thufrag.getHead());
        holder.textview_desc_thufragv.setText(listitem_thufrag.getDesc());
        holder.textview_lect_thufragv.setText(listitem_thufrag.getLect());

    }

    @Override
    public int getItemCount() {
        return listItems_thufrag.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textview_heading_thufragv;
        public TextView textview_desc_thufragv;
        public TextView textview_lect_thufragv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_thufragv=(TextView) itemView.findViewById(R.id.textview_heading_thufrag);
            textview_desc_thufragv=(TextView) itemView.findViewById(R.id.textview_desc_thufrag);
            textview_lect_thufragv=(TextView) itemView.findViewById(R.id.textview_lecturer_thufrag);

        }
    }
}
