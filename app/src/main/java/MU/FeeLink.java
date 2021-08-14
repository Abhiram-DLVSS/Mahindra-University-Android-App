package MU;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import MU.timetable.TimeTable;

import com.example.turing_login.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.view.MotionEvent;
import android.view.View;

public class FeeLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_link);

        Intent intent=new Intent(getApplicationContext(), TimeTable.class);
        startActivity(intent);
        finish();
        gotoUrl("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
        LinearLayoutCompat l1=findViewById(R.id.feeLink);
        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                        Intent intent=new Intent(getApplicationContext(), TimeTable.class);
                        startActivity(intent);
                return false;
            }
        });



    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}