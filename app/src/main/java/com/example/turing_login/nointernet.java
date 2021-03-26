package com.example.turing_login;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.firebase.database.FirebaseDatabase;

public class nointernet extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
