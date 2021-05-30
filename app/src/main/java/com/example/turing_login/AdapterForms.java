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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterForms extends RecyclerView.Adapter<AdapterForms.ViewHolder> {

    private Context context_form;
    private List<Listitem_form> listItems_forms;
    public AdapterForms(List<Listitem_form> listItems_forms, Context context_form) {
        this.listItems_forms = listItems_forms;
        this.context_form = context_form;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_form).inflate(R.layout.forms_list_item
                ,parent
                ,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_form listitem_form =listItems_forms.get(position);
        holder.textview_heading_form.setText(listitem_form.getHead());
        holder.textview_desc_form.setText(listitem_form.getDesc());
        holder.textview_lect_form.setText(listitem_form.getLect());
        holder.link= listitem_form.getLink();

    }
    @Override
    public int getItemCount() {
        return listItems_forms.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_heading_form;
        public TextView textview_desc_form;
        public TextView textview_lect_form;
        public RelativeLayout rv;
        public CardView cv;
        public String link;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_form=(TextView) itemView.findViewById(R.id.textview_heading_assign);
            textview_desc_form=(TextView) itemView.findViewById(R.id.textview_desc_assign);
            textview_lect_form=(TextView) itemView.findViewById(R.id.textview_lecturer_assign);
            rv=(RelativeLayout) itemView.findViewById(R.id.assign_rl);
            cv=itemView.findViewById(R.id.assign_card);
            textview_heading_form.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoUrl(link);
                }
            });
            textview_heading_form.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Uri copyUri = Uri.parse(link);
                    ClipboardManager clipboard = (ClipboardManager) context_form.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newUri(itemView.getContext().getContentResolver(), "URI", copyUri);
                    clipboard.setPrimaryClip(clip);
                    Toast toast=Toast.makeText(itemView.getContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                    toast.show();
                    // Get instance of Vibrator from current Context
                    Vibrator vib = (Vibrator) context_form.getSystemService(Context.VIBRATOR_SERVICE);
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
        context_form.startActivity(intent);
    }
}