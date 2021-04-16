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

public class FacultyMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faculty_menu);
        RecyclerView FacultyMenu = findViewById(R.id.FacultyMenu);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.stan));
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
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
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
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
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
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        pos.add("PROFESSOR AND HOD");
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
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
                        images.add(R.drawable.ic_person);
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