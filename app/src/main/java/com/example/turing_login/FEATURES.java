package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FEATURES extends AppCompatActivity {
    private Button button,timetable_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_e_a_t_u_r_e_s);
        button=(Button)findViewById(R.id.FORMS);
        timetable_button=(Button)findViewById(R.id.TIME_TABLE);
        button.setOnClickListener(new View.OnClickListener() {
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
    public void openFeature()
    {
        Intent intent=new Intent(this,FORMS.class);
        startActivity(intent);
    }
    public void openTimeTable()
    {
        Intent intent=new Intent(this,TimeTable.class);
        startActivity(intent);
    }
}