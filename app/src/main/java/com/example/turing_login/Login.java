package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button Login;
    EditText email, password;
    FirebaseAuth fauth;
    TextView signup;
    boolean connected=false;
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
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
                        int bottomDetector = view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY());
                        if(bottomDetector == 0 )
                            Log.d("loginact", "bd="+bottomDetector);
                        else
                        scrollView.fullScroll(View.FOCUS_DOWN);
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
            startActivity(new Intent(getApplicationContext(), Features.class));
            finish();
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog nDialog;
                String remail=email.getText().toString().trim();
                String rpassword=password.getText().toString().trim();

                Drawable errorIcon = getResources().getDrawable(R.drawable.null_layout);
                errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));

//                if(TextUtils.isEmpty(remail)){
//                    email.setError("Please enter your Email-ID");
//                    Toast.makeText(com.example.turing_login.Login.this, "Please enter your Email-ID", Toast.LENGTH_SHORT).show();
//                }
//                else if(TextUtils.isEmpty(rpassword)){
//                    password.setError("Please enter Password",errorIcon);
//                    Toast.makeText(com.example.turing_login.Login.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
//                }
                if(!connected){
                    Toast.makeText(Login.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(remail)||TextUtils.isEmpty(rpassword)){
                    Toast.makeText(com.example.turing_login.Login.this, "Incorrect Username or Password.", Toast.LENGTH_LONG).show();
                }
                else{
                    nDialog = new ProgressDialog(com.example.turing_login.Login.this);
                    nDialog.setMessage("Glad to see you again!");
                    nDialog.setIndeterminate(false);
                    nDialog.show();
                    fauth.signInWithEmailAndPassword(remail,rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Login and save session

                            if(task.isSuccessful()){
                                startActivity(new Intent(getApplicationContext(), Features.class));
                                nDialog.dismiss();
                                finish();
                            }
                            else {
                                nDialog.dismiss();
                                Toast.makeText(com.example.turing_login.Login.this, "Incorrect Username or Password.", Toast.LENGTH_LONG).show();
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