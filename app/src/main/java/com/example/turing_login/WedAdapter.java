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

public class WedAdapter extends RecyclerView.Adapter<WedAdapter.ViewHolder> {

    private Context context_wed;
    private List<Listitem_wedfrag> listItems_wedfrag;
    public WedAdapter(List<Listitem_wedfrag> listItems_wedfrag, Context context_wed) {
        this.listItems_wedfrag = listItems_wedfrag;
        this.context_wed = context_wed;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_wed).inflate(R.layout.list_item_wedfrag
                ,parent
                ,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_wedfrag listitem_wedfrag =listItems_wedfrag.get(position);
        holder.textview_heading_wedfragv.setText(listitem_wedfrag.getHead());
        holder.textview_desc_wedfragv.setText(listitem_wedfrag.getDesc());
        holder.textview_lect_wedfragv.setText(listitem_wedfrag.getLect());
        int color= Integer.parseInt(listitem_wedfrag.getCol());
        int colo= ContextCompat.getColor(context_wed, R.color.white);
        Log.d("Abhi",  "value of color is "+colo);
        if (color!=-1) {
            holder.textview_heading_wedfragv.setTextColor(-1);
            holder.textview_desc_wedfragv.setTextColor(-1);
            holder.textview_lect_wedfragv.setTextColor(-1);
            holder.textview_heading_wedfragv.setTypeface(Typeface.DEFAULT);
        }
        holder.cv.setCardBackgroundColor(color);
    }
    @Override
    public int getItemCount() {
        return listItems_wedfrag.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_heading_wedfragv;
        public TextView textview_desc_wedfragv;
        public TextView textview_lect_wedfragv;
        public RelativeLayout rv;
        public CardView cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_wedfragv=(TextView) itemView.findViewById(R.id.textview_heading_wedfrag);
            textview_desc_wedfragv=(TextView) itemView.findViewById(R.id.textview_desc_wedfrag);
            textview_lect_wedfragv=(TextView) itemView.findViewById(R.id.textview_lecturer_wedfrag);
            rv=(RelativeLayout) itemView.findViewById(R.id.wed_rl);
            cv=itemView.findViewById(R.id.wed_card);
        }
    }
}