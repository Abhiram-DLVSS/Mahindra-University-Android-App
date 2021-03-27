package com.example.turing_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriAdapter extends RecyclerView.Adapter<FriAdapter.ViewHolder> {

    private Context context_fri;
    private List<Listitem_frifrag> listItems_frifrag;

    public FriAdapter(List<Listitem_frifrag> listItems_frifrag, Context context_fri) {
        this.listItems_frifrag = listItems_frifrag;
        this.context_fri = context_fri;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_fri).inflate(R.layout.list_item_frifrag
                ,parent
                ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_frifrag listitem_frifrag =listItems_frifrag.get(position);

        holder.textview_heading_frifragv.setText(listitem_frifrag.getHead());
        holder.textview_desc_frifragv.setText(listitem_frifrag.getDesc());
        holder.textview_lect_frifragv.setText(listitem_frifrag.getLect());

    }

    @Override
    public int getItemCount() {
        return listItems_frifrag.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textview_heading_frifragv;
        public TextView textview_desc_frifragv;
        public TextView textview_lect_frifragv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_frifragv=(TextView) itemView.findViewById(R.id.textview_heading_frifrag);
            textview_desc_frifragv=(TextView) itemView.findViewById(R.id.textview_desc_frifrag);
            textview_lect_frifragv=(TextView) itemView.findViewById(R.id.textview_lecturer_frifrag);

        }
    }
}
