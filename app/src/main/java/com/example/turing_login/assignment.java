package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class assignment extends AppCompatActivity {

    Button uploadfile,fileselect,fetchfile;
    EditText filename;
    Uri pdfuri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        storageReference=FirebaseStorage.getInstance().getReference();
        databaseReference=FirebaseDatabase.getInstance().getReference("Uploads");
        fetchfile=findViewById(R.id.fetchfile);
        fileselect=findViewById(R.id.fileselect);
        uploadfile=findViewById(R.id.uploadfile);
        filename=findViewById(R.id.filename);
        fetchfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),fetchpdf.class));
            }
        });
        fileselect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(assignment.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectPDF();
                }
                else
                    ActivityCompat.requestPermissions(assignment.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
            }
        });
        uploadfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(pdfuri!=null)
                uploadFile(pdfuri);
            }
        });
    }

    private void uploadFile(Uri pdfuri) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading File...");
        progressDialog.show();
        StorageReference reference=storageReference.child("File Uploads"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri=taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        while(!uri.isComplete());
                            Uri url=uri.getResult();
                        uploadPDF uploadPDF=new uploadPDF(filename.getText().toString(),url.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
                        Toast.makeText(assignment.this,"File Successfully Uploaded",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(assignment.this,"File Not Uploaded",Toast.LENGTH_LONG).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int currentprogress= (int) (100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                progressDialog.setMessage("Uploaded: "+currentprogress+"%");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==9&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectPDF();
        }
        else
            Toast.makeText(assignment.this,"Please provide the permission",Toast.LENGTH_LONG).show();
    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,23);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==23&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
        {
            pdfuri=data.getData();
            Toast.makeText(assignment.this,"File Successfully Selected",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(assignment.this,"Please select a valid file",Toast.LENGTH_LONG).show();
    }
}