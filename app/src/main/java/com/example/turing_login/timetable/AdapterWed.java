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

public class AdapterWed extends RecyclerView.Adapter<AdapterWed.ViewHolder> {

    private Context context_wed;
    private List<Listitem_wedfrag> listItems_wedfrag;
    public AdapterWed(List<Listitem_wedfrag> listItems_wedfrag, Context context_wed) {
        this.listItems_wedfrag = listItems_wedfrag;
        this.context_wed = context_wed;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_wed).inflate(R.layout.tt_list_item_wedfrag
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
        holder.link=listitem_wedfrag.getLink();
        int color= Integer.parseInt(listitem_wedfrag.getCol());
        int colo= ContextCompat.getColor(context_wed, R.color.white);
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
        public String link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_wedfragv=(TextView) itemView.findViewById(R.id.textview_heading_wedfrag);
            textview_desc_wedfragv=(TextView) itemView.findViewById(R.id.textview_desc_wedfrag);
            textview_lect_wedfragv=(TextView) itemView.findViewById(R.id.textview_lecturer_wedfrag);
            rv=(RelativeLayout) itemView.findViewById(R.id.wed_rl);
            cv=itemView.findViewById(R.id.wed_card);
            textview_heading_wedfragv.setOnClickListener(new View.OnClickListener() {
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
                        context_wed.startActivity(new Intent(Intent.ACTION_VIEW,copyUri));
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_wed.getSystemService(Context.VIBRATOR_SERVICE);
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
                        ClipboardManager clipboard = (ClipboardManager) context_wed.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newUri(itemView.getContext().getContentResolver(), "URI", copyUri);
                        clipboard.setPrimaryClip(clip);
                        toast=Toast.makeText(itemView.getContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                        toast.show();
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_wed.getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 400 milliseconds
                        vib.vibrate(40);}
                    return true;
                }
            });
        }
    }
}