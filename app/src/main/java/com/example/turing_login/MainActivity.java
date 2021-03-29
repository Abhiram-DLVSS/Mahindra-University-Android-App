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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button Login;
    Button Register;
    EditText email, password;
    FirebaseAuth fauth;
    ProgressBar progressBar;
    TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                String remail=email.getText().toString().trim();
                String rpassword=password.getText().toString().trim();

                if(TextUtils.isEmpty(remail)){
                    email.setError("Email-ID is required!");
                    return;
                }
                if(TextUtils.isEmpty(rpassword)){
                    password.setError("Password is required!");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                fauth.signInWithEmailAndPassword(remail,rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //login and save session

                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Turing is ready to serve!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),FEATURES.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Check the damn password!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });



            }
        });
    }

    private void openRegister() {
        Intent intent=new Intent(this,Turing_Register.class);
        startActivity(intent);
    }
}