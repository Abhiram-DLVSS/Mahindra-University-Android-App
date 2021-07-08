package MU;

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
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.airbnb.lottie.LottieAnimationView;
import MU.timetable.TimeTable;

import com.example.turing_login.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button Login;
    EditText email, password;
    FirebaseAuth fauth;
    LottieAnimationView unlock,rejected,checkmark;
    DatabaseHelper mDatabaseHelper;
    boolean loginanimation =true;
    Snackbar snackbar_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Turing_Login_NoActionBar);

        setContentView(R.layout.login);
        email=findViewById(R.id.ID);
        password=findViewById(R.id.Password);
        fauth=FirebaseAuth.getInstance();
        Login=findViewById(R.id.Login);
        unlock=findViewById(R.id.unlock);
        checkmark=findViewById(R.id.checkmark);
        rejected=findViewById(R.id.rejected);
        ScrollView scrollView=findViewById(R.id.loginscroll);
        ConstraintLayout constraintLayout;
        checkmark.setVisibility(View.INVISIBLE);

        email.setFilters(new InputFilter[] {new InputFilter.AllCaps()});


        constraintLayout=findViewById(R.id.loginview);
        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new  ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.scrollTo(0,scrollView.getBottom());
                    }
                });
            }
        });

        snackbar_id = Snackbar
                .make(constraintLayout, "Please Check your College ID", Snackbar.LENGTH_LONG)
                .setAction("Edit", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        email.requestFocus();
                    }
                });
        snackbar_id.setActionTextColor(getResources().getColor(R.color.mu));

//        if(fauth.getCurrentUser()!=null)
//        {
//            startActivity(new Intent(getApplicationContext(), TimeTable.class));
//            finish();
//        }
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rejected.setVisibility(View.INVISIBLE);
                Login.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Login.setEnabled(true);
                rejected.setVisibility(View.INVISIBLE);
                Login.setVisibility(View.VISIBLE);

                return false;
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login.setEnabled(false);
                String remail=email.getText().toString().trim();
                String rpassword=password.getText().toString().trim();



                Drawable errorIcon = getResources().getDrawable(R.drawable.null_layout);
                errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            if(TextUtils.isEmpty(remail)){
                rejected();

            }
            else if(!remail.contains("XJ1A0")&&email.length()>10){
                rejected();
            }
            else if(!remail.contains("19XJ1A05")&&!remail.contains("19XJ1A03")&&!remail.contains("19XJ1A01")&&!remail.contains("19XJ1A02")){
                rejected();
            }
            else{
                mDatabaseHelper = new DatabaseHelper(Login.this);
                mDatabaseHelper.addData(email.getText().toString());
                            loginanimation =true;
                            rejected.setVisibility(View.INVISIBLE);
                            checkmark.setVisibility(View.VISIBLE);
                            Login.setVisibility(View.INVISIBLE);
                            Login.setBackground(getResources().getDrawable(R.drawable.round3_green));
                            Login.setTextColor(getResources().getColor(R.color.green));
//                            unlock.playAnimation();
                            checkmark.playAnimation();
                            loginanimation =false;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(getApplicationContext(), TimeTable.class));
                                    finish();
                                    Log.d("TT", "run: ");
                                }
                            },2000);
            }
        }
        else{

//                    Toast.makeText(Login.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
            Snackbar snackbar = Snackbar
                    .make(constraintLayout, "Check your Internet Connection", Snackbar.LENGTH_LONG)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Login.performClick();
                        }
                    });
            snackbar.setActionTextColor(getResources().getColor(R.color.mu));
            snackbar.show();
            Login.setEnabled(true);
        }


            }
        });
    }

    private void rejected() {
        loginanimation =true;
        Login.setVisibility(View.INVISIBLE);
        rejected.setVisibility(View.VISIBLE);
        rejected.playAnimation();
        loginanimation =false;
        Login.setEnabled(true);
        snackbar_id.show();

    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);     }
}