package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Turing_Login_NoActionBar);
        setContentView(R.layout.register);
        Name = findViewById(R.id.Name);
        email = findViewById(R.id.EmailID);
        id = findViewById(R.id.CollegeID);
        password = findViewById(R.id.Password);
        fauth = FirebaseAuth.getInstance();
        register = findViewById(R.id.REGISTER);
        id.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        if (fauth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), FEATURES.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog nDialog = new ProgressDialog(Register.this);

                String remail = email.getText().toString().trim();
                String rpassword = password.getText().toString().trim();
                String rname = Name.getText().toString().trim();
                String rid = id.getText().toString().trim();



                if (TextUtils.isEmpty(remail)) {
                    email.setError("Email-ID is required!");
                    return;
                }
                else if (TextUtils.isEmpty(rname)) {
                    Name.setError("Name is required!");
                    return;
                }
                else if (TextUtils.isEmpty(rid)) {
                    id.setError("ID is required!");
                    return;
                }
                else if (TextUtils.isEmpty(rpassword)) {
                    password.setError("Password is required!");
                    return;
                }
                else if (rpassword.length() < 8) {
                    password.setError("Turing requires 8 or more characters");
                    return;
                }
                else if(email.getText().toString().contains("@medhyd.ac.in")){
                    nDialog.setMessage("Welcome!");
                    nDialog.setIndeterminate(false);
                    nDialog.show();

                    fauth.createUserWithEmailAndPassword(remail, rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                nDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), FEATURES.class));

                                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                root.child(currentuser).child("id").setValue(rid);
                                root.child(currentuser).child("name").setValue(rname);


                            } else {
                                Toast.makeText(Register.this, "Initialization Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Register.this, "Enter you College Email Address", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}