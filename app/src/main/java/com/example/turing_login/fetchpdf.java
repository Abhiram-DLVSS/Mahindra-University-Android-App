package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fetchpdf extends AppCompatActivity {
    ListView pdflist;
    DatabaseReference databaseReference;
    List<UploadPDF> UploadPDFS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchpdf);
        pdflist=findViewById(R.id.list);
        UploadPDFS = new ArrayList<>();
        
        viewfiles();
        pdflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UploadPDF uploadPDF= UploadPDFS.get(position);
                Intent intent=new Intent();
                intent.setData(Uri.parse("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwjklarC35nwAhVZgtgFHYUsDScQPAgI"));
                startActivity(intent);
            }
        });
    }

    private void viewfiles() {
        databaseReference= FirebaseDatabase.getInstance().getReference("Uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot:snapshot.getChildren()){
                    UploadPDF uploadPDF=postSnapshot.getValue(UploadPDF.class);
                    UploadPDFS.add(uploadPDF);
                }
                String[] uploads=new String[UploadPDFS.size()];
                for(int i=0;i<uploads.length;i++){
                    uploads[i]= UploadPDFS.get(i).getName1();
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,uploads);
                pdflist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}