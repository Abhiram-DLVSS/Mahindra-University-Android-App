package com.example.turing_login;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alespero.expandablecardview.ExpandableCardView;

import java.util.List;

public class AdapterAssign extends RecyclerView.Adapter<AdapterAssign.ViewHolder> {

    private final Context context_assign;
    private final List<Listitem_assign> listitem_assigns;
    public AdapterAssign(List<Listitem_assign> listitem_assigns, Context context_assign) {
        this.listitem_assigns = listitem_assigns;
        this.context_assign = context_assign;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_assign).inflate(R.layout.assignments_list_item
                ,parent
                ,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_assign listitem_assign =listitem_assigns.get(position);
        holder.textview_heading_assign.setText(listitem_assign.getHead());
        holder.textview_desc_assign.setText(listitem_assign.getDesc());
        holder.textview_lect_assign.setText(listitem_assign.getLect());
        holder.link= listitem_assign.getLink();

    }
    @Override
    public int getItemCount() {
        return listitem_assigns.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_heading_assign;
        public TextView textview_desc_assign;
        public TextView textview_lect_assign;
        public RelativeLayout rv;
        public Button upload;
        public CardView cv;
        public ImageView arrow;
        public String link;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_assign=(TextView) itemView.findViewById(R.id.textview_heading_assign);
            textview_desc_assign=(TextView) itemView.findViewById(R.id.textview_desc_assign);
            textview_lect_assign=(TextView) itemView.findViewById(R.id.textview_lecturer_assign);
            upload=itemView.findViewById(R.id.upload_button);
            rv=(RelativeLayout) itemView.findViewById(R.id.assign_rl);
            cv=itemView.findViewById(R.id.assign_card);
            arrow=itemView.findViewById(R.id.arrow_img);
//
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(textview_lect_assign.getVisibility()==View.VISIBLE){
                        textview_lect_assign.setVisibility(View.GONE);
                        upload.setVisibility(View.GONE);
                        arrow.setBackground(null);
                        arrow.setImageResource(R.drawable.ic_baseline_arrow_drop_down);


                    }
                    else if(textview_lect_assign.getVisibility()==View.GONE){
                        textview_lect_assign.setVisibility(View.VISIBLE);
                        upload.setVisibility(View.VISIBLE);
                        arrow.setBackground(null);
                        arrow.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);

                    }
                }
            });
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoUrl(link);
                }
            });
            upload.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Uri copyUri = Uri.parse(link);
                    ClipboardManager clipboard = (ClipboardManager) context_assign.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newUri(itemView.getContext().getContentResolver(), "URI", copyUri);
                    clipboard.setPrimaryClip(clip);
//                    Toast toast=Toast.makeText(itemView.getContext(),copyUri+" Copied to Clipboard",Toast.LENGTH_SHORT);
                    Toast toast=Toast.makeText(itemView.getContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                    toast.show();
                    // Get instance of Vibrator from current Context
                    Vibrator vib = (Vibrator) context_assign.getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 400 milliseconds
                    vib.vibrate(40);
                    return true;
                }
            });
        }
    }
    public void gotoUrl(String s) {
        Log.d("adapter", "onClick: "+s);
        Uri uri = Uri.parse(s);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context_assign.startActivity(intent);
    }
}