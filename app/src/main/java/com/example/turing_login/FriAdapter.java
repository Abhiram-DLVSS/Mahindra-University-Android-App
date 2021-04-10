package com.example.turing_login;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
        int color= Integer.parseInt(listitem_frifrag.getCol());
        int colo= ContextCompat.getColor(context_fri, R.color.white);
        Log.d("Abhi",  "value of color is "+colo);
        if (color!=-1) {
            holder.textview_heading_frifragv.setTextColor(-1);
            holder.textview_desc_frifragv.setTextColor(-1);
            holder.textview_lect_frifragv.setTextColor(-1);
            holder.textview_heading_frifragv.setTypeface(Typeface.DEFAULT);
        }
        holder.cv.setCardBackgroundColor(color);
    }
    @Override
    public int getItemCount() {
        return listItems_frifrag.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_heading_frifragv;
        public TextView textview_desc_frifragv;
        public TextView textview_lect_frifragv;
        public RelativeLayout rv;
        public CardView cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_frifragv=(TextView) itemView.findViewById(R.id.textview_heading_frifrag);
            textview_desc_frifragv=(TextView) itemView.findViewById(R.id.textview_desc_frifrag);
            textview_lect_frifragv=(TextView) itemView.findViewById(R.id.textview_lecturer_frifrag);
            rv=(RelativeLayout) itemView.findViewById(R.id.fri_rl);
            cv=itemView.findViewById(R.id.fri_card);
        }
    }
}