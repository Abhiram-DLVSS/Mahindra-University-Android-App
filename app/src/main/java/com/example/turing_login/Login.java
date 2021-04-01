package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Turing_Login_NoActionBar);
        setContentView(R.layout.login);
        email=findViewById(R.id.ID);
        password=findViewById(R.id.Password);
        fauth=FirebaseAuth.getInstance();
        Login=(Button)findViewById(R.id.Login);
        signup=findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        if(fauth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),FEATURES.class));
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

                if(TextUtils.isEmpty(remail)){
                    email.setError("Please enter your Email-ID");
                    Toast.makeText(com.example.turing_login.Login.this, "Please enter your Email-ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(rpassword)){
                    password.setError("Please enter Password",errorIcon);
                    Toast.makeText(com.example.turing_login.Login.this, "Please enter Password", Toast.LENGTH_SHORT).show();

                    return;
                }
                else{

                    nDialog = new ProgressDialog(com.example.turing_login.Login.this);
                    nDialog.setMessage("Glad to see you again!");
                    nDialog.setIndeterminate(false);
                    nDialog.show();
                }
                fauth.signInWithEmailAndPassword(remail,rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Login and save session

                        if(task.isSuccessful())
                        {
                            Toast.makeText(com.example.turing_login.Login.this, "Turing is ready to serve!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),FEATURES.class));
                            nDialog.dismiss();
                        }
                        else {
                            Toast.makeText(com.example.turing_login.Login.this, "Check the damn password!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });
    }

    private void openRegister() {
        Intent intent=new Intent(this, Register.class);
        startActivity(intent);
    }
}