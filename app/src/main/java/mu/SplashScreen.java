package mu;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.MU.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import mu.MSAuth.Login;
import mu.timetable.TimeTable;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 1000;

    ArrayList<String> quotelist = new ArrayList<>();
    ArrayList<String> authorlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        String jsonString;
        try {
            InputStream is = getApplicationContext().getResources().openRawResource(R.raw.quotes);
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
            Calendar calendar = Calendar.getInstance();
            int rand_num = calendar.get(Calendar.DAY_OF_YEAR);
            TextView quote_textview=findViewById(R.id.quote_textview);
            TextView author_textview=findViewById(R.id.author_textview);
            quote_textview.setText(quotelist.get(rand_num));
            String author=authorlist.get(rand_num);
            if(author.equals("null"))
                author_textview.setText("~Anonymous");
            else
                author_textview.setText("~"+author);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DatabaseHelper mDatabaseHelper = new DatabaseHelper(getApplicationContext());
                Cursor data = mDatabaseHelper.getData();
                if (data.moveToNext()) {

                    Intent homeIntent = new Intent();
                    homeIntent.setClass(SplashScreen.this,TimeTable.class);
                    homeIntent.putExtra("Uniqid","Splash");
                    startActivity(homeIntent);
                    finish();
                } else {
                    Intent homeIntent = new Intent(SplashScreen.this, Login.class);
                    startActivity(homeIntent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}