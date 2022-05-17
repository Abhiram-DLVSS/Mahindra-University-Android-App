package MU;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import MU.timetable.TimeTable;

import com.MU.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.view.MotionEvent;
import android.view.View;

public class GradesLink extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_link);

        Intent intent=new Intent(getApplicationContext(), TimeTable.class);
        startActivity(intent);
        finish();
        gotoUrl("http://mu-parentsportal.com/grade");
        LinearLayoutCompat l1=findViewById(R.id.gradesLink);
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