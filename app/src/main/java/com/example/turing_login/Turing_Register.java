package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Turing_Register extends AppCompatActivity {
    EditText Name, email, id, password;
    Button register;
    FirebaseAuth fauth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turing__register);
        Name =findViewById(R.id.Name);
        email=findViewById(R.id.EmailID);
        id=findViewById(R.id.CollegeID);
        password=findViewById(R.id.Password);
        fauth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        register=findViewById(R.id.REGISTER);
       if(fauth.getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(),FEATURES.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remail=email.getText().toString().trim();
                String rpassword=password.getText().toString().trim();
                String rname=Name.getText().toString().trim();
                String rid=id.getText().toString().trim();

                if(TextUtils.isEmpty(remail)){
                    email.setError("Email-ID is required!");
                    return;
                }
                if(TextUtils.isEmpty(rname)){
                    Name.setError("Name is required!");
                    return;
                }
                if(TextUtils.isEmpty(rid)){
                    id.setError("ID is required!");
                    return;
                }
                if(TextUtils.isEmpty(rpassword)){
                    password.setError("Password is required!");
                    return;
                }
                if(rpassword.length()<8)
                {
                    password.setError("Turing requires 8 or more characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fauth.createUserWithEmailAndPassword(remail,rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Turing_Register.this, "Turing Realm Initiated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),FEATURES.class));
                        }
                        else
                        {
                            Toast.makeText(Turing_Register.this, "Initialization Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
    }
}