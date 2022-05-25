package mu.timetable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.MU.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class QuoteSheet extends BottomSheetDialogFragment {
    ArrayList<String> quotelist = new ArrayList<>();
    ArrayList<String> authorlist = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.quote_sheet_layout, container, false);

        TextView next = v.findViewById(R.id.arrow_textview);
        String jsonString;
        try {
            InputStream is = getContext().getResources().openRawResource(R.raw.quotes);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
            JSONArray jsonArray=new JSONArray(jsonString);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                quotelist.add(obj.getString("text"));
                authorlist.add(obj.getString("author"));
            }
            int min = 0;
            int max = 1642;
            int rand_num = (int)(Math.random()*(max-min+1)+min);

            TextView quote_textview=v.findViewById(R.id.quote_textview);
            TextView author_textview=v.findViewById(R.id.author_textview);
            quote_textview.setText(quotelist.get(rand_num));
            String author=authorlist.get(rand_num);
            if(author.equals("null"))
                author_textview.setText("~Anonymous");
            else
                author_textview.setText("~"+author);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jsonString;
                try {
                    InputStream is = getContext().getResources().openRawResource(R.raw.quotes);
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    jsonString = new String(buffer, "UTF-8");
                    JSONArray jsonArray=new JSONArray(jsonString);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        quotelist.add(obj.getString("text"));
                        authorlist.add(obj.getString("author"));
                    }
                    int min = 0;
                    int max = 1642;
                    int rand_num = (int)(Math.random()*(max-min+1)+min);

                    TextView quote_textview=v.findViewById(R.id.quote_textview);
                    TextView author_textview=v.findViewById(R.id.author_textview);
                    quote_textview.setText(quotelist.get(rand_num));
                    String author=authorlist.get(rand_num);
                    if(author.equals("null"))
                        author_textview.setText("~Anonymous");
                    else
                        author_textview.setText("~"+author);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }
}
