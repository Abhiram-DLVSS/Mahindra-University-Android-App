package MU.timetable;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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


public class AdapterBox extends RecyclerView.Adapter<AdapterBox.ViewHolder> {

    private final Context context_box;
    private final List<Listitem_tt> listItems_boxfrag;
    public AdapterBox(List<Listitem_tt> listItems_boxfrag, Context context_box) {
        this.listItems_boxfrag = listItems_boxfrag;
        this.context_box = context_box;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context_box).inflate(R.layout.tt_list_item_box
                ,parent
                ,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Listitem_tt listitem_boxfrag =listItems_boxfrag.get(position);
        holder.textview_heading_boxfragv.setText(listitem_boxfrag.getHead());
        holder.textview_desc_boxfragv.setText(listitem_boxfrag.getDesc());
        holder.textview_lect_boxfragv.setText(listitem_boxfrag.getLect());
        holder.link=listitem_boxfrag.getLink();
        int color= Integer.parseInt(listitem_boxfrag.getCol());
        int colo=ContextCompat.getColor(context_box, R.color.white);
        if (color!=-1) {
            holder.textview_heading_boxfragv.setTextColor(-1);
            holder.textview_desc_boxfragv.setTextColor(-1);
            holder.textview_lect_boxfragv.setTextColor(-1);
            holder.textview_heading_boxfragv.setTypeface(Typeface.DEFAULT);
        }
        holder.cv.setCardBackgroundColor(color);
    }
    @Override
    public int getItemCount() {
        return listItems_boxfrag.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_heading_boxfragv;
        public TextView textview_desc_boxfragv;
        public TextView textview_lect_boxfragv;
        public RelativeLayout rv;
        public CardView cv;
        public String link;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview_heading_boxfragv=(TextView) itemView.findViewById(R.id.textview_heading_boxfrag);
            textview_desc_boxfragv=(TextView) itemView.findViewById(R.id.textview_desc_boxfrag);
            textview_lect_boxfragv=(TextView) itemView.findViewById(R.id.textview_lecturer_boxfrag);
            rv=(RelativeLayout) itemView.findViewById(R.id.box_rl);
            cv=itemView.findViewById(R.id.box_card);
            textview_heading_boxfragv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri copyUri = Uri.parse(link);
//                    Toast toast=Toast.makeText(itemView.getContext(),copyUri+" Copied to Clipboard",Toast.LENGTH_SHORT);
                    Toast toast;
                    if( copyUri.toString().equals("null")){
                        toast=Toast.makeText(itemView.getContext(),"Recurring Link not found",Toast.LENGTH_SHORT);
                        toast.show();
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_box.getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 400 milliseconds
                        vib.vibrate(40);
                    }
                    else{
                        context_box.startActivity(new Intent(Intent.ACTION_VIEW,copyUri));
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_box.getSystemService(Context.VIBRATOR_SERVICE);
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
                        toast.show();
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_box.getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 400 milliseconds
                        vib.vibrate(40);
                    }
                    else{
                        ClipboardManager clipboard = (ClipboardManager) context_box.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newUri(itemView.getContext().getContentResolver(), "URI", copyUri);
                        clipboard.setPrimaryClip(clip);
                        toast=Toast.makeText(itemView.getContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                        toast.show();
                        // Get instance of Vibrator from current Context
                        Vibrator vib = (Vibrator) context_box.getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 400 milliseconds
                        vib.vibrate(40);
                    }
                    return true;
                }
            });
        }
    }
}