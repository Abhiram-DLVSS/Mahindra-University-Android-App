package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.turing_login.timetable.TimeTable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button Login;
    EditText email, password;
    FirebaseAuth fauth;
    TextView signup;
    LottieAnimationView unlock,rejected;
    boolean connected=false;
    boolean loginanimation =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Turing_Login_NoActionBar);

        setContentView(R.layout.login);
        email=findViewById(R.id.ID);
        password=findViewById(R.id.Password);
        fauth=FirebaseAuth.getInstance();
        Login=findViewById(R.id.Login);
        signup=findViewById(R.id.signup);
        unlock=findViewById(R.id.unlock);
        rejected=findViewById(R.id.rejected);
        ScrollView scrollView=findViewById(R.id.loginscroll);
        ConstraintLayout constraintLayout;


        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        constraintLayout=findViewById(R.id.loginview);
        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new  ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                int heightDiff = constraintLayout.getRootView().getHeight() - constraintLayout.getHeight();
//
//                if (heightDiff > 100) { // Value should be less than keyboard's height
//                    Log.e("MyActivity", "keyboard opened"+scrollView.getBottom());
//                    scrollView.scrollTo(0,scrollView.getBottom());
////                    scrollView.fullScroll(View.FOCUS_DOWN);
//                } else {
//                    Log.e("MyActivity", "keyboard closed"+scrollView.getBottom());
//                    scrollView.scrollTo(0,scrollView.getBottom());
////                    scrollView.fullScroll(View.FOCUS_DOWN);
//                }
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
//                        View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
//                        int bottomDetector = view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY());
//                        if(bottomDetector == 0 )
//                            Log.d("loginact", "bd="+bottomDetector);
//                        else
//                        scrollView.fullScroll(View.FOCUS_DOWN);
                        scrollView.scrollTo(0,scrollView.getBottom());

                    }

                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Select", "Selected Signup");
                openRegister();
            }
        });

        if(fauth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(), TimeTable.class));
            finish();
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remail=email.getText().toString().trim();
                String rpassword=password.getText().toString().trim();

                Drawable errorIcon = getResources().getDrawable(R.drawable.null_layout);
                errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));
                if(!connected){
                    Toast.makeText(Login.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(remail)||TextUtils.isEmpty(rpassword)){
                    loginanimation =true;
                    rejected.playAnimation();
                    loginanimation =false;
                }
                else{
                    fauth.signInWithEmailAndPassword(remail,rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Login and save session

                            if(task.isSuccessful()){
                                loginanimation =true;
                                rejected.setVisibility(View.INVISIBLE);
                                unlock.playAnimation();
                                loginanimation =false;
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(getApplicationContext(), Features.class));
                                        finish();
                                    }
                                },5500);
                            }
                            else {
                                rejected.playAnimation();
                            }
                        }
                    });
                }
            }
        });
    }

    private void openRegister() {
        Intent intent=new Intent(this, Register.class);
        startActivity(intent);
    }
}