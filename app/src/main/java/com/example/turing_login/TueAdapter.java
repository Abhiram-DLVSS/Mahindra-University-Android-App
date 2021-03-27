package com.example.turing_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TueAdapter extends RecyclerView.Adapter<TueAdapter.ViewHolder> {

    private Context context_tue;
    private List<Listitem_tuefrag> listItems_tuefrag;

    public TueAdapter(List<Listitem_tuefrag> listItems_tuefrag, Context context_tue) {
        this.listItems_tuefrag = listItems_tuefrag;
        this.context_tue = context_tue;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_tue).inflate(R.layout.list_item_tuefrag
                ,parent
                ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_tuefrag listitem_tuefrag =listItems_tuefrag.get(position);

        holder.textview_heading_tuefragv.setText(listitem_tuefrag.getHead());
        holder.textview_desc_tuefragv.setText(listitem_tuefrag.getDesc());
        holder.textview_lect_tuefragv.setText(listitem_tuefrag.getLect());

    }

    @Override
    public int getItemCount() {
        return listItems_tuefrag.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textview_heading_tuefragv;
        public TextView textview_desc_tuefragv;
        public TextView textview_lect_tuefragv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_tuefragv=(TextView) itemView.findViewById(R.id.textview_heading_tuefrag);
            textview_desc_tuefragv=(TextView) itemView.findViewById(R.id.textview_desc_tuefrag);
            textview_lect_tuefragv=(TextView) itemView.findViewById(R.id.textview_lecturer_tuefrag);

        }
    }
}
