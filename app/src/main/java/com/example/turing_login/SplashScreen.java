package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth fauth;
    private static final int SPLASH_TIME_OUT=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        fauth=FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(fauth.getCurrentUser()!=null) {
                            Intent homeIntent = new Intent(SplashScreen.this, Login.class);
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