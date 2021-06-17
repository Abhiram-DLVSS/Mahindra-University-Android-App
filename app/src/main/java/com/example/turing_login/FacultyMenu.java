package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.example.turing_login.timetable.TimeTable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyMenu extends Intents implements SwipeRefreshLayout.OnRefreshListener{
    private int flag=1,count,total;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fac_faculty_menu);
        RecyclerView FacultyMenu = findViewById(R.id.FacultyMenu);
        statusbar();

        mSwipeRefreshLayout =findViewById(R.id.FacultyMenu_re);
        mSwipeRefreshLayout.setOnRefreshListener(this::onRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.stan,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        //Floating button disappears
        FacultyMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 10&&flag==1 ){
                    flag=0;
                    final Animation animation = new TranslateAnimation(0,0,0,250);
                    animation.setDuration(500);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(flag==0)
                                floatingmenu.setVisibility(View.GONE);
                        }
                    }, 500);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);
                } else if (dy < -10&&flag==0){
                    floatingmenu.setVisibility(View.VISIBLE);
                    flag=1;
                    floatingmenu.setVisible(true);
                    final Animation animation = new TranslateAnimation(0,0,250,0);
                    animation.setDuration(500);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);

                }

            }
        });

        fetch();



        //floating
        floatinginit();
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FacultyMenu.this, "👀", Toast.LENGTH_SHORT).show();
            }
        });

    }
    void fetch(){
        RecyclerView FacultyMenu = findViewById(R.id.FacultyMenu);

        List<String>titles = new ArrayList<>();
        List<Integer>images = new ArrayList<>();
        List<String>pos = new ArrayList<>();
        List<Drawable>row= new ArrayList<>();
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);
        reference.keepSynced(false);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rollnumber = snapshot.child("id").getValue().toString();
                String year=rollnumber.substring(0,2);
                String branch=rollnumber.substring(7,8);
                if((Integer.parseInt(year)==19)&&(Integer.parseInt(branch)==5)){

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Faculty").child("CSE");
                    reference.keepSynced(true);
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            total = (int) snapshot.getChildrenCount();
                            int[] free = new int[total];
                            for (count = 0; count < total; count++) {
                                String m1 = snapshot.child(""+count).getValue().toString();
                                free[count]=Integer.parseInt(m1);
                            }

                            GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
                            FacultyMenu.setLayoutManager(gridLayoutManager);
                            titles.add("Prof. Arya Kumar Bhattacharya");
                            titles.add("Prof. Rama Murthy");
                            titles.add("Prof. Raghu Kishore Neelisetti");
                            titles.add("Prof. Prafulla Kalapatapu");
                            titles.add("Prof. Bhanukiran Perabathini");
                            titles.add("Prof. Neha Bharill");
                            titles.add("Prof. Om Prakash Patel");
                            titles.add("Prof. Pavan Kumar Perepu");
                            titles.add("Prof. Praveen Kumar Alapati");
                            titles.add("Prof. Rajesh Tavva");
                            titles.add("Prof. Ravi Kishore");
                            titles.add("Prof. Sanatan Sukhija");
                            titles.add("Prof. Sunny Rai");
                            titles.add("Prof. Veeraiah");
                            titles.add("Prof. Yayati Gupta");
                            titles.add("Prof. Ani Thomas");
                            titles.add("Prof. Raj Narayanan");
                            titles.add("Prof. Ranjith Shankaran");
                            titles.add("Prof. Sayoni Laha");
                            titles.add("Prof. Tamal Kanti Paul");
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
                            pos.add("ACADEMIC ASSOCIATE");


                            for(int i=0;i<=19;i++){
                                if(free[i]==0)
                                    row.add(getResources().getDrawable(R.drawable.round_red_shade));
                                else if(free[i]==1)
                                    row.add(getResources().getDrawable(R.drawable.round_green_shade));
                            }
                            FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos,row));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                else if((Integer.parseInt(year)==19)&&(Integer.parseInt(branch)==1)){

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Faculty").child("CSE");
                    reference.keepSynced(true);
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            total = (int) snapshot.getChildrenCount();
                            int[] free = new int[total];
                            for (count = 0; count < total; count++) {
                                String m1 = snapshot.child(""+count).getValue().toString();
                                free[count]=Integer.parseInt(m1);
                            }
                            GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
                            FacultyMenu.setLayoutManager(gridLayoutManager);
                            titles.add("Prof. Prabhakar Singh");
                            titles.add("Prof. Visalakshi Talakokula");
                            titles.add("Prof. Jayaprakash Vemuri");
                            titles.add("Prof. Ganesh Babu Kodeboyina");
                            titles.add("Prof. Ataullah Khan");
                            titles.add("Prof. Avirneni Deepti");
                            titles.add("Prof. Hari Prasad");
                            titles.add("Prof. Jyoti Kainthola");
                            titles.add("Prof. Saladi S.V. Subbarao");
                            titles.add("Prof. Venkata Dilip Kumar Pasupuleti");
                            titles.add("Prof. Ani Thomas");
                            titles.add("Prof. Raj Narayanan");
                            titles.add("Prof. Ranjith Shankaran");
                            titles.add("Prof. Sayoni Laha");
                            titles.add("Prof. Tamal Kanti Paul");
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
                            pos.add("ACADEMIC ASSOCIATE");
                            for(int i=0;i<=19;i++){
                                if(free[i]==0)
                                    row.add(getResources().getDrawable(R.drawable.round_red_shade));
                                else if(free[i]==1)
                                    row.add(getResources().getDrawable(R.drawable.round_green_shade));
                            }
                            FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos,row));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });                }
                else if((Integer.parseInt(year)==19)&&(Integer.parseInt(branch)==2)){

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Faculty").child("CSE");
                    reference.keepSynced(true);
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            total = (int) snapshot.getChildrenCount();
                            int[] free = new int[total];
                            for (count = 0; count < total; count++) {
                                String m1 = snapshot.child(""+count).getValue().toString();
                                free[count]=Integer.parseInt(m1);
                            }
                            GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
                            FacultyMenu.setLayoutManager(gridLayoutManager);
                            titles.add("Prof. J. L. Bhattacharya");
                            titles.add("Prof. Bhuvaneswari Gurumoorthy");
                            titles.add("Prof. Sunil Bhooshan");
                            titles.add("Prof. Bharghava Rajaram");
                            titles.add("Prof. Sayantan Hazra");
                            titles.add("Prof. K. R. Sarma");
                            titles.add("Prof. Aditya Abburi");
                            titles.add("Prof. Ankita Jain");
                            titles.add("Prof. Gopinath G R");
                            titles.add("Prof. Krishna Chaitanya Bulusu");
                            titles.add("Prof. Pooran Singh");
                            titles.add("Prof. Sreedhar Madichetty");
                            titles.add("Prof. Subbarao Boddu");
                            titles.add("Prof. Ani Thomas");
                            titles.add("Prof. Raj Narayanan");
                            titles.add("Prof. Ranjith Shankaran");
                            titles.add("Prof. Sayoni Laha");
                            titles.add("Prof. Tamal Kanti Paul");

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
                            pos.add("ACADEMIC ASSOCIATE");
                            for(int i=0;i<=19;i++){
                                if(free[i]==0)
                                    row.add(getResources().getDrawable(R.drawable.round_red_shade));
                                else if(free[i]==1)
                                    row.add(getResources().getDrawable(R.drawable.round_green_shade));
                            }
                            FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos,row));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else if((Integer.parseInt(year)==19)&&(Integer.parseInt(branch)==3)){
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Faculty").child("CSE");
                    reference.keepSynced(true);
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            total = (int) snapshot.getChildrenCount();
                            int[] free = new int[total];
                            for (count = 0; count < total; count++) {
                                String m1 = snapshot.child(""+count).getValue().toString();
                                free[count]=Integer.parseInt(m1);
                                Log.d("fac", "onDataChange: "+count+"="+free[count]);
                            }
                            GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
                            FacultyMenu.setLayoutManager(gridLayoutManager);
                            titles.add("Prof. Bhaskar Tamma");
                            titles.add("Prof. Ranjith Kunnath");
                            titles.add("Prof. Abhijit Bhattacharyya");
                            titles.add("Prof. Jagan Mohan Padbidri");
                            titles.add("Prof. Palash Roy Choudhury");
                            titles.add("Prof. Prasad Pokkunuri");
                            titles.add("Prof. Deep Seth");
                            titles.add("Prof. Harshavardhan Kalathur");
                            titles.add("Prof. Kondaiah P");
                            titles.add("Prof. Manish Kumar Agrawal");
                            titles.add("Prof. Ravikiran Bompelly");
                            titles.add("Prof. Sebastian Uppapalli");
                            titles.add("Prof. Senbagaraman Sudarsanam");
                            titles.add("Prof. Ani Thomas");
                            titles.add("Prof. Raj Narayanan");
                            titles.add("Prof. Ranjith Shankaran");
                            titles.add("Prof. Sayoni Laha");
                            titles.add("Prof. Tamal Kanti Paul");

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
                            pos.add("ACADEMIC ASSOCIATE");
                            for(int i=0;i<=19;i++){
                                if(free[i]==0)
                                    row.add(getResources().getDrawable(R.drawable.round_red_shade));
                                else if(free[i]==1)
                                    row.add(getResources().getDrawable(R.drawable.round_green_shade));
                            }
                            FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos,row));

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onRefresh() {
        fetch();
    }
}