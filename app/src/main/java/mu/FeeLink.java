package mu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.MU.R;

import mu.timetable.TimeTable;

public class FeeLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_link);

        Intent intent = new Intent(getApplicationContext(), TimeTable.class);
        startActivity(intent);
        finish();
        gotoUrl();
        LinearLayoutCompat l1 = findViewById(R.id.feeLink);
        l1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getApplicationContext(), TimeTable.class);
                startActivity(intent);
                return false;
            }
        });
    }

    private void gotoUrl() {
        Uri uri = Uri.parse("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}