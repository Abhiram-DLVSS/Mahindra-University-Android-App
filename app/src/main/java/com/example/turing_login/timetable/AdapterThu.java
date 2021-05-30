package com.example.turing_login.timetable;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Vibrator;
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
import android.os.Vibrator;

public class AdapterThu extends RecyclerView.Adapter<AdapterThu.ViewHolder> {

    private Context context_thu;
    private List<Listitem_thufrag> listItems_thufrag;
    public AdapterThu(List<Listitem_thufrag> listItems_thufrag, Context context_thu) {
        this.listItems_thufrag = listItems_thufrag;
        this.context_thu = context_thu;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_thu).inflate(R.layout.tt_list_item_thufrag
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
        holder.link=listitem_thufrag.getLink();
        int color= Integer.parseInt(listitem_thufrag.getCol());
        int colo= ContextCompat.getColor(context_thu, R.color.white);
        if (color!=-1) {
            holder.textview_heading_thufragv.setTextColor(-1);
            holder.textview_desc_thufragv.setTextColor(-1);
            holder.textview_lect_thufragv.setTextColor(-1);
            holder.textview_heading_thufragv.setTypeface(Typeface.DEFAULT);
        }
        holder.cv.setCardBackgroundColor(color);
    }
    @Override
    public int getItemCount() {
        return listItems_thufrag.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_heading_thufragv;
        public TextView textview_desc_thufragv;
        public TextView textview_lect_thufragv;
        public RelativeLayout rv;
        public CardView cv;
        public String link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_thufragv=(TextView) itemView.findViewById(R.id.textview_heading_thufrag);
            textview_desc_thufragv=(TextView) itemView.findViewById(R.id.textview_desc_thufrag);
            textview_lect_thufragv=(TextView) itemView.findViewById(R.id.textview_lecturer_thufrag);
            rv=(RelativeLayout) itemView.findViewById(R.id.thu_rl);
            cv=itemView.findViewById(R.id.thu_card);
            textview_heading_thufragv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri copyUri = Uri.parse(link);
//                    Toast toast=Toast.makeText(itemView.getContext(),copyUri+" Copied to Clipboard",Toast.LENGTH_SHORT);
                    Toast toast;
                    if( copyUri.toString().equals("null")){
                        toast=Toast.makeText(itemView.getContext(),"Recurring Link not found",Toast.LENGTH_SHORT);
                        toast.show();}
                    else{
//                        ClipboardManager clipboard = (ClipboardManager) context_wed.getSystemService(Context.CLIPBOARD_SERVICE);
//                        ClipData clip = ClipData.newUri(itemView.getContext().getContentResolver(), "URI", copyUri);
//                        clipboard.setPrimaryClip(clip);
//                        toast=Toast.makeText(itemView.getContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
//                        toast.show();
                        context_thu.startActivity(new Intent(Intent.ACTION_VIEW,copyUri));
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_thu.getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 400 milliseconds
                        vib.vibrate(40);}
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Uri copyUri = Uri.parse(link);
//                    Toast toast=Toast.makeText(itemView.getContext(),copyUri+" Copied to Clipboard",Toast.LENGTH_SHORT);
                    Toast toast;
                    if( copyUri.toString().equals("null")){
                        toast=Toast.makeText(itemView.getContext(),"Recurring Link not found",Toast.LENGTH_SHORT);
                        toast.show();}
                    else{
                        ClipboardManager clipboard = (ClipboardManager) context_thu.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newUri(itemView.getContext().getContentResolver(), "URI", copyUri);
                        clipboard.setPrimaryClip(clip);
                        toast=Toast.makeText(itemView.getContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                        toast.show();
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_thu.getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 400 milliseconds
                        vib.vibrate(40);}
                    return true;
                }
            });
        }
    }
}