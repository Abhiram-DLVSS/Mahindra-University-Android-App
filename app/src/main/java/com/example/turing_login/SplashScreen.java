package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth fauth;
    private static int SPLASH_TIME_OUT=100;
//    private LottieAnimationView loading;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        fauth=FirebaseAuth.getInstance();
//        loading=findViewById(R.id.loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(fauth.getCurrentUser()!=null) {
//                    loading.playAnimation();
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
                            Intent homeIntent = new Intent(SplashScreen.this, Login.class);
                            startActivity(homeIntent);
                            finish();
//                        }
//                    },5000);
                }
                else{
//                    loading.playAnimation();
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
                            Intent homeIntent = new Intent(SplashScreen.this, Login.class);
                            startActivity(homeIntent);
                            finish();
//                        }
//                    },5000);
                }
            }
        },SPLASH_TIME_OUT);
    }
}