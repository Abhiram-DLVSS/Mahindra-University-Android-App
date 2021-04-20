package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Forms extends Intents {
    Button REGISTRATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statusbar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forms);
        REGISTRATION=findViewById(R.id.REGISTRATION);
        REGISTRATION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://docs.google.com");
            }
        });
        floatinginit();
        forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Forms.this, "👀", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}