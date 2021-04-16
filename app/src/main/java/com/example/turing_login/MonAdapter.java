package com.example.turing_login;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MonAdapter extends RecyclerView.Adapter<MonAdapter.ViewHolder> {

    private Context context_mon;
    private List<Listitem_monfrag> listItems_monfrag;
    public MonAdapter(List<Listitem_monfrag> listItems_monfrag, Context context_mon) {
        this.listItems_monfrag = listItems_monfrag;
        this.context_mon = context_mon;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_mon).inflate(R.layout.list_item_monfrag
                ,parent
                ,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_monfrag listitem_monfrag =listItems_monfrag.get(position);
        holder.textview_heading_monfragv.setText(listitem_monfrag.getHead());
        holder.textview_desc_monfragv.setText(listitem_monfrag.getDesc());
        holder.textview_lect_monfragv.setText(listitem_monfrag.getLect());
        holder.link=listitem_monfrag.getLink();
        int color= Integer.parseInt(listitem_monfrag.getCol());
        int colo=ContextCompat.getColor(context_mon, R.color.white);
        Log.d("Abhi",  "value of color is "+colo);
        if (color!=-1) {
            holder.textview_heading_monfragv.setTextColor(-1);
            holder.textview_desc_monfragv.setTextColor(-1);
            holder.textview_lect_monfragv.setTextColor(-1);
            holder.textview_heading_monfragv.setTypeface(Typeface.DEFAULT);
        }
        holder.cv.setCardBackgroundColor(color);
    }
    @Override
    public int getItemCount() {
        return listItems_monfrag.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_heading_monfragv;
        public TextView textview_desc_monfragv;
        public TextView textview_lect_monfragv;
        public RelativeLayout rv;
        public CardView cv;
        public String link;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_monfragv=(TextView) itemView.findViewById(R.id.textview_heading_monfrag);
            textview_desc_monfragv=(TextView) itemView.findViewById(R.id.textview_desc_monfrag);
            textview_lect_monfragv=(TextView) itemView.findViewById(R.id.textview_lecturer_monfrag);
            rv=(RelativeLayout) itemView.findViewById(R.id.mon_rl);
            cv=itemView.findViewById(R.id.mon_card);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Uri copyUri = Uri.parse(link);
                    ClipboardManager clipboard = (ClipboardManager) context_mon.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newUri(itemView.getContext().getContentResolver(), "URI", copyUri);
                    clipboard.setPrimaryClip(clip);
                    Toast toast=Toast.makeText(itemView.getContext(),copyUri+" Copied to Clipboard",Toast.LENGTH_SHORT);
                    toast.show();
                    return true;
                }
            });
        }
    }
}