package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Features extends AppCompatActivity {
    private Button buttonfor,timetable_button,faculty,fee;
    private TextView welcome;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.features);
        welcome=findViewById(R.id.welcome);

        //To get custom status bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.stan));



        fee=findViewById(R.id.fee);
        faculty=findViewById(R.id.faculty);
        timetable_button=(Button)findViewById(R.id.TIME_TABLE);
        buttonfor=(Button)findViewById(R.id.FORMS);

        fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
            }
        });
        fee.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Uri copyUri = Uri.parse("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
                ClipData clip = ClipData.newUri(getContentResolver(), "URI", copyUri);
                Toast toast=Toast.makeText(getApplicationContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                //toast.setMargin(50,50);
                toast.show();
                return true;
            }
        });



        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.mahindraecolecentrale.edu.in/faculty");
            }
        });
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFaculty();
            }
        });

        buttonfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFeature();

            }
        });
        timetable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeTable();

            }
        });
    }

    private void openFaculty() {
        Intent intent=new Intent(this,FacultyMenu.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
                switch(id){
                    case R.id.logout_in_menu: {
                        Toast.makeText(this, "Signing out...", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();
                        }
                        break;
                }
        return true;
    }

    private void openTimeTable() {
        Intent intent=new Intent(this, TimeTable.class);
        startActivity(intent);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void openFeature()
    {
        Intent intent=new Intent(this, Forms.class);
        startActivity(intent);
    }
    private void moveToMainActivity() {
        Intent intent=new Intent(Features.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
