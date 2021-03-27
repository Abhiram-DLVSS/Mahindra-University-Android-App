package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FEATURES extends AppCompatActivity {
    private Button buttonfor,timetable_button,faculty,fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_e_a_t_u_r_e_s);
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
        Intent intent=new Intent(this,FORMS.class);
        startActivity(intent);
    }
    public void logout(View view)
    {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    private void moveToMainActivity() {
        Intent intent=new Intent(FEATURES.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
