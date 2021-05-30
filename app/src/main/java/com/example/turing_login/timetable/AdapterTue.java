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

public class AdapterTue extends RecyclerView.Adapter<AdapterTue.ViewHolder> {

    private Context context_tue;
    private List<Listitem_tuefrag> listItems_tuefrag;
    public AdapterTue(List<Listitem_tuefrag> listItems_tuefrag, Context context_tue) {
        this.listItems_tuefrag = listItems_tuefrag;
        this.context_tue = context_tue;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_tue).inflate(R.layout.tt_list_item_tuefrag
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
        holder.link=listitem_tuefrag.getLink();
        int color= Integer.parseInt(listitem_tuefrag.getCol());
        int colo= ContextCompat.getColor(context_tue, R.color.white);
        if (color!=-1) {
            holder.textview_heading_tuefragv.setTextColor(-1);
            holder.textview_desc_tuefragv.setTextColor(-1);
            holder.textview_lect_tuefragv.setTextColor(-1);
            holder.textview_heading_tuefragv.setTypeface(Typeface.DEFAULT);
        }
        holder.cv.setCardBackgroundColor(color);
    }
    @Override
    public int getItemCount() {
        return listItems_tuefrag.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_heading_tuefragv;
        public TextView textview_desc_tuefragv;
        public TextView textview_lect_tuefragv;
        public RelativeLayout rv;
        public CardView cv;
        public String link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_tuefragv=(TextView) itemView.findViewById(R.id.textview_heading_tuefrag);
            textview_desc_tuefragv=(TextView) itemView.findViewById(R.id.textview_desc_tuefrag);
            textview_lect_tuefragv=(TextView) itemView.findViewById(R.id.textview_lecturer_tuefrag);
            rv=(RelativeLayout) itemView.findViewById(R.id.tue_rl);
            cv=itemView.findViewById(R.id.tue_card);
            textview_heading_tuefragv.setOnClickListener(new View.OnClickListener() {
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
                        context_tue.startActivity(new Intent(Intent.ACTION_VIEW,copyUri));
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_tue.getSystemService(Context.VIBRATOR_SERVICE);
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
                        ClipboardManager clipboard = (ClipboardManager) context_tue.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newUri(itemView.getContext().getContentResolver(), "URI", copyUri);
                        clipboard.setPrimaryClip(clip);
                        toast=Toast.makeText(itemView.getContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                        toast.show();
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_tue.getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 400 milliseconds
                        vib.vibrate(40);}
                    return true;
                }
            });
        }
    }
}