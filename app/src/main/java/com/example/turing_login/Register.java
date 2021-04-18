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
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText Name, email, id, password;
    Button register;
    FirebaseAuth fauth;
    private FirebaseDatabase rootnode = FirebaseDatabase.getInstance();
    private DatabaseReference root = rootnode.getReference("Users");
    boolean connected=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Turing_Login_NoActionBar);
        setContentView(R.layout.register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Name = findViewById(R.id.Name);
        email = findViewById(R.id.EmailID);
        id = findViewById(R.id.CollegeID);
        ScrollView scrollView=findViewById(R.id.regsterscroll);
        password = findViewById(R.id.Password);
        fauth = FirebaseAuth.getInstance();
        register = findViewById(R.id.REGISTER);
        ConstraintLayout constraintLayout;


        Drawable errorIcon = getResources().getDrawable(R.drawable.null_layout);
        errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));

        id.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        if (fauth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Features.class));
            finish();
        }
        constraintLayout=findViewById(R.id.registerview);
        constraintLayout.getViewTreeObserver().addOnGlobalLayoutListener(new  ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                int heightDiff = constraintLayout.getRootView().getHeight() - constraintLayout.getHeight();
//
//                if (heightDiff > 100) { // Value should be less than keyboard's height
//                    Log.e("MyActivity", "keyboard opened"+scrollView.getBottom()+" ");
////                    scrollView.scrollTo(0,scrollView.getBottom());
////                    scrollView.fullScroll(View.FOCUS_DOWN);
//                    scrollView.setVerticalScrollbarPosition(scrollView.getBottom());
//                    scrollView.scrollTo(0,scrollView.getBottom());
//                    scrollView.getVerticalScrollbarPosition();
//                    Log.e("MyActivity", "Scrolled");
//                } else {
//                    Log.e("MyActivity", "keyboard closed"+scrollView.getBottom());
//                    scrollView.scrollTo(0,scrollView.getBottom());
////                    scrollView.fullScroll(View.FOCUS_DOWN);
//                }
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.scrollTo(0,scrollView.getBottom());
//                        View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
//                        int bottomDetector = view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY());
//                        if(bottomDetector == 0 )
//                            Log.d("loginact", "bd="+bottomDetector);
//                        else
//                            scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog nDialog = new ProgressDialog(Register.this);

                String remail = email.getText().toString().trim();
                String rpassword = password.getText().toString().trim();
                String rname = Name.getText().toString().trim();
                String rid = id.getText().toString().trim();




                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                else
                    connected = false;


                if (TextUtils.isEmpty(remail)) {
                    email.setError("Email-ID is required!");
                }
                else if (TextUtils.isEmpty(rname)) {
                    Name.setError("Name is required!");
                }
                else if (TextUtils.isEmpty(rid)) {
                    id.setError("ID is required!");
                }
                else if(!rid.contains("XJ1A0")&&rid.length()>10){
                    id.setError("Please Check your ID");
                }
                else if(!rid.contains("19XJ1A05")&&!rid.contains("19XJ1A03")&&!rid.contains("19XJ1A01")&&!rid.contains("19XJ1A02")){
                    id.setError("Please Check your ID");
                }

                else if (TextUtils.isEmpty(rpassword)) {
                    password.setError("Password is required!",errorIcon);
                }
                else if (rpassword.length() < 8) {
                    password.setError("Turing requires 8 or more characters",errorIcon);
                }
                else if(!email.getText().toString().contains("@mechyd.ac.in")){
                    email.setError("Enter your College Email Address");

                }
                else if(!email.getText().toString().contains(rid.substring(0,2)+rid.substring(7,10)+"@mechyd.ac.in")){
                    email.setError("Please check your College Email ID");
                }
                else if(connected==false){
                    Toast.makeText(Register.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    nDialog.setMessage("Welcome!");
                    nDialog.setIndeterminate(false);
                    nDialog.show();

                    fauth.createUserWithEmailAndPassword(remail, rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                nDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), Features.class));

                                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                root.child(currentuser).child("id").setValue(rid);
                                root.child(currentuser).child("name").setValue(rname);
                                finish();
                            } else {
                                Toast.makeText(Register.this, "Initialization Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



            }
        });
    }
}