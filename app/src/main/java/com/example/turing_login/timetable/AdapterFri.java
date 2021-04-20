package com.example.turing_login.timetable;

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

import com.example.turing_login.R;

import java.util.List;

public class AdapterFri extends RecyclerView.Adapter<AdapterFri.ViewHolder> {

    private Context context_fri;
    private List<Listitem_frifrag> listItems_frifrag;
    public AdapterFri(List<Listitem_frifrag> listItems_frifrag, Context context_fri) {
        this.listItems_frifrag = listItems_frifrag;
        this.context_fri = context_fri;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_fri).inflate(R.layout.tt_list_item_frifrag
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
        holder.link=listitem_frifrag.getLink();
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
        public String link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_frifragv=(TextView) itemView.findViewById(R.id.textview_heading_frifrag);
            textview_desc_frifragv=(TextView) itemView.findViewById(R.id.textview_desc_frifrag);
            textview_lect_frifragv=(TextView) itemView.findViewById(R.id.textview_lecturer_frifrag);
            rv=(RelativeLayout) itemView.findViewById(R.id.fri_rl);
            cv=itemView.findViewById(R.id.fri_card);itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Uri copyUri = Uri.parse(link);
                    ClipboardManager clipboard = (ClipboardManager) context_fri.getSystemService(Context.CLIPBOARD_SERVICE);
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