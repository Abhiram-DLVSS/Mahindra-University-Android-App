package MU;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import MU.timetable.TimeTable;

import com.MU.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth fauth;
    private static final int SPLASH_TIME_OUT=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DatabaseHelper mDatabaseHelper = new DatabaseHelper(getApplicationContext());
                Cursor data = mDatabaseHelper.getData();


                if(data.moveToNext()) {

                            Intent homeIntent = new Intent(SplashScreen.this, TimeTable.class);
                            startActivity(homeIntent);
                            finish();
                }
                else{
                            Intent homeIntent = new Intent(SplashScreen.this, Login.class);
                            startActivity(homeIntent);
                            finish();
                }

            }
        },SPLASH_TIME_OUT);
    }
}