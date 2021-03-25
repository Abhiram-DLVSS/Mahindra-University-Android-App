package com.example.turing_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MonAdapter extends RecyclerView.Adapter<MonAdapter.ViewHolder> {

    private List<Listitem_monfrag> listItems_monfrag;

    public MonAdapter(List<Listitem_monfrag> listItems_monfrag, Context context_mon) {
        this.listItems_monfrag = listItems_monfrag;
        this.context_mon = context_mon;
    }

    private Context context_mon;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_monfrag,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_monfrag listitem_monfrag =listItems_monfrag.get(position);

        holder.textview_heading_monfragv.setText(listitem_monfrag.getHead());
        holder.textview_desc_monfragv.setText(listitem_monfrag.getDesc());

    }

    @Override
    public int getItemCount() {
        return listItems_monfrag.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textview_heading_monfragv;
        public TextView textview_desc_monfragv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_monfragv=(TextView) itemView.findViewById(R.id.textview_heading_monfrag);
            textview_desc_monfragv=(TextView) itemView.findViewById(R.id.textview_desc_monfrag);
        }
    }
}
