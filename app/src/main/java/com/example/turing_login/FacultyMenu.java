package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyMenu extends Intents {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faculty_menu);
        RecyclerView FacultyMenu = findViewById(R.id.FacultyMenu);
        statusbar();
        List<String>titles = new ArrayList<>();
        List<Integer>images = new ArrayList<>();
        List<String>pos = new ArrayList<>();
            final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
            String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
//        reference.keepSynced(true);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);
        reference.keepSynced(false);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    String rollnumber=snapshot.child("Users").child(currentuser).child("id").getValue().toString();
                    String rollnumber = snapshot.child("id").getValue().toString();
                    String year=rollnumber.substring(0,2);
                    String branch=rollnumber.substring(7,8);
                    if((Integer.parseInt(year)==19)&&(Integer.parseInt(branch)==5)){
                        GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
                        FacultyMenu.setLayoutManager(gridLayoutManager);
                        titles.add("Dr. Arya Kumar Bhattacharya");
                        titles.add("Dr. Rama Murthy");
                        titles.add("Dr. Raghu Kishore Neelisetti");
                        titles.add("Dr. Prafulla Kalapatapu");
                        titles.add("Dr. Bhanukiran Perabathini");
                        titles.add("Dr. Neha Bharill");
                        titles.add("Dr. Om Prakash Patel");
                        titles.add("Dr. Pavan Kumar Perepu");
                        titles.add("Dr. Praveen Kumar Alapati");
                        titles.add("Dr. Rajesh Tavva");
                        titles.add("Dr. Ravi Kishore");
                        titles.add("Dr. Sanatan Sukhija");
                        titles.add("Dr. Sunny Rai");
                        titles.add("Dr. Veeraiah");
                        titles.add("Dr. Yayati Gupta");
                        titles.add("Dr. Ani Thomas");
                        titles.add("Dr. Raj Narayanan");
                        titles.add("Dr. Ranjith Shankaran");
                        titles.add("Dr. Sayoni Laha");
                        images.add(R.drawable.arya);
                        images.add(R.drawable.rama);
                        images.add(R.drawable.raghu);
                        images.add(R.drawable.prafulla);
                        images.add(R.drawable.bhanukiran);
                        images.add(R.drawable.neha);
                        images.add(R.drawable.om);
                        images.add(R.drawable.pavan);
                        images.add(R.drawable.praveen);
                        images.add(R.drawable.rajesh);
                        images.add(R.drawable.ravi);
                        images.add(R.drawable.sanathan);
                        images.add(R.drawable.sunny);
                        images.add(R.drawable.veeraiah);
                        images.add(R.drawable.yayati);
                        images.add(R.drawable.ani);
                        images.add(R.drawable.raj);
                        images.add(R.drawable.ranjith);
                        images.add(R.drawable.sayoni);
                        pos.add("DEAN RESEARCH AND HOD");
                        pos.add("PROFESSOR");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos));
                    }
                    else if((Integer.parseInt(year)==19)&&(Integer.parseInt(branch)==1)){
                        GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
                        FacultyMenu.setLayoutManager(gridLayoutManager);
                        titles.add("Dr. Prabhakar Singh");
                        titles.add("Dr. Visalakshi Talakokula");
                        titles.add("Dr. Jayaprakash Vemuri");
                        titles.add("Dr. Ganesh Babu Kodeboyina");
                        titles.add("Dr. Ataullah Khan");
                        titles.add("Dr. Avirneni Deepti");
                        titles.add("Dr. Hari Prasad");
                        titles.add("Dr. Jyoti Kainthola");
                        titles.add("Dr. Saladi S.V. Subbarao");
                        titles.add("Dr. Venkata Dilip Kumar Pasupuleti");
                        titles.add("Dr. Ani Thomas");
                        titles.add("Dr. Raj Narayanan");
                        titles.add("Dr. Ranjith Shankaran");
                        titles.add("Dr. Sayoni Laha");
                        images.add(R.drawable.prabhakar);
                        images.add(R.drawable.visalakshi);
                        images.add(R.drawable.jayaprakash);
                        images.add(R.drawable.ganesh);
                        images.add(R.drawable.ataullah);
                        images.add(R.drawable.avirneni);
                        images.add(R.drawable.hari);
                        images.add(R.drawable.jyoti);
                        images.add(R.drawable.saladi);
                        images.add(R.drawable.venkata);
                        images.add(R.drawable.ani);
                        images.add(R.drawable.raj);
                        images.add(R.drawable.ranjith);
                        images.add(R.drawable.sayoni);
                        pos.add("ASSOCIATE PROFESSOR AND HOD");
                        pos.add("PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ADJUNCT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos));
                    }
                    else if((Integer.parseInt(year)==19)&&(Integer.parseInt(branch)==2)){
                        GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
                        FacultyMenu.setLayoutManager(gridLayoutManager);
                        titles.add("Dr. J. L. Bhattacharya");
                        titles.add("Dr. Bhuvaneswari Gurumoorthy");
                        titles.add("Dr. Sunil Bhooshan");
                        titles.add("Dr. Bharghava Rajaram");
                        titles.add("Dr. Sayantan Hazra");
                        titles.add("Dr. K. R. Sarma");
                        titles.add("Dr. Aditya Abburi");
                        titles.add("Dr. Ankita Jain");
                        titles.add("Dr. Gopinath G R");
                        titles.add("Dr. Krishna Chaitanya Bulusu");
                        titles.add("Dr. Pooran Singh");
                        titles.add("Dr. Sreedhar Madichetty");
                        titles.add("Dr. Subbarao Boddu");
                        titles.add("Dr. Ani Thomas");
                        titles.add("Dr. Raj Narayanan");
                        titles.add("Dr. Ranjith Shankaran");
                        titles.add("Dr. Sayoni Laha");
                        images.add(R.drawable.jl);
                        images.add(R.drawable.bhuvaneswari);
                        images.add(R.drawable.sunil);
                        images.add(R.drawable.bhargav);
                        images.add(R.drawable.sayantan);
                        images.add(R.drawable.kr);
                        images.add(R.drawable.aditya);
                        images.add(R.drawable.ankita);
                        images.add(R.drawable.gopinath);
                        images.add(R.drawable.krishna);
                        images.add(R.drawable.pooran);
                        images.add(R.drawable.sreedhar);
                        images.add(R.drawable.subbarao);
                        images.add(R.drawable.ani);
                        images.add(R.drawable.raj);
                        images.add(R.drawable.ranjith);
                        images.add(R.drawable.sayoni);
                        pos.add("PROFESSOR AND HOD");
                        pos.add("PROFESSOR");
                        pos.add("PROFESSOR");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ADJUNCT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos));
                    }
                    else if((Integer.parseInt(year)==19)&&(Integer.parseInt(branch)==3)){
                        GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
                        FacultyMenu.setLayoutManager(gridLayoutManager);
                        titles.add("Dr. Bhaskar Tamma");
                        titles.add("Dr. Ranjith Kunnath");
                        titles.add("Dr. Abhijit Bhattacharyya");
                        titles.add("Dr. Jagan Mohan Padbidri");
                        titles.add("Dr. Palash Roy Choudhury");
                        titles.add("Dr. Prasad Pokkunuri");
                        titles.add("Dr. Deep Seth");
                        titles.add("Dr. Harshavardhan Kalathur");
                        titles.add("Dr. Kondaiah P");
                        titles.add("Dr. Manish Kumar Agrawal");
                        titles.add("Dr. Ravikiran Bompelly");
                        titles.add("Dr. Sebastian Uppapalli");
                        titles.add("Dr. Senbagaraman Sudarsanam");
                        titles.add("Dr. Ani Thomas");
                        titles.add("Dr. Raj Narayanan");
                        titles.add("Dr. Ranjith Shankaran");
                        titles.add("Dr. Sayoni Laha");
                        images.add(R.drawable.bhaskar);
                        images.add(R.drawable.ranjith1);
                        images.add(R.drawable.abhijit);
                        images.add(R.drawable.jagan);
                        images.add(R.drawable.palash);
                        images.add(R.drawable.prasad);
                        images.add(R.drawable.deep);
                        images.add(R.drawable.harshavardhan);
                        images.add(R.drawable.kondaiah);
                        images.add(R.drawable.manish);
                        images.add(R.drawable.ravikiran);
                        images.add(R.drawable.sebastian);
                        images.add(R.drawable.senbagaraman);
                        images.add(R.drawable.ani);
                        images.add(R.drawable.raj);
                        images.add(R.drawable.ranjith);
                        images.add(R.drawable.sayoni);
                        pos.add("PROFESSOR AND HOD");
                        pos.add("PROFESSOR");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ASSISTANT PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ACADEMIC ASSOCIATE");
                        pos.add("ASSOCIATE PROFESSOR");
                        pos.add("ACADEMIC ASSOCIATE");
                        FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
}