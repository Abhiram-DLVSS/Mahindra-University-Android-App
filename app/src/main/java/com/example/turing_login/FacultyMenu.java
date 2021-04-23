package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
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
                    animation.setDuration(1000);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);
                } else if (dy < -10&&flag==0){
                    flag=1;
                    floatingmenu.setVisible(true);
                    final Animation animation = new TranslateAnimation(0,0,250,0);
                    animation.setDuration(1000);
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
                                Log.d("fac", "onDataChange: "+count+"="+free[count]);
                            }

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
                            images.add(R.drawable.fac_arya);
                            images.add(R.drawable.fac_rama);
                            images.add(R.drawable.fac_raghu);
                            images.add(R.drawable.fac_prafulla);
                            images.add(R.drawable.fac_bhanukiran);
                            images.add(R.drawable.fac_neha);
                            images.add(R.drawable.fac_om);
                            images.add(R.drawable.fac_pavan);
                            images.add(R.drawable.fac_praveen);
                            images.add(R.drawable.fac_rajesh);
                            images.add(R.drawable.fac_ravi);
                            images.add(R.drawable.fac_sanathan);
                            images.add(R.drawable.fac_sunny);
                            images.add(R.drawable.fac_veeraiah);
                            images.add(R.drawable.fac_yayati);
                            images.add(R.drawable.fac_ani);
                            images.add(R.drawable.fac_raj);
                            images.add(R.drawable.fac_ranjith);
                            images.add(R.drawable.fac_sayoni);
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


                            for(int i=0;i<=19;i++){
                                if(free[i]==0)
                                    row.add(getResources().getDrawable(R.drawable.round_red));
                                else if(free[i]==1)
                                    row.add(getResources().getDrawable(R.drawable.round_green));
                            }
                            FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos,row));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


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
                    images.add(R.drawable.fac_prabhakar);
                    images.add(R.drawable.fac_visalakshi);
                    images.add(R.drawable.fac_jayaprakash);
                    images.add(R.drawable.fac_ganesh);
                    images.add(R.drawable.fac_ataullah);
                    images.add(R.drawable.fac_avirneni);
                    images.add(R.drawable.fac_hari);
                    images.add(R.drawable.fac_jyoti);
                    images.add(R.drawable.fac_saladi);
                    images.add(R.drawable.fac_venkata);
                    images.add(R.drawable.fac_ani);
                    images.add(R.drawable.fac_raj);
                    images.add(R.drawable.fac_ranjith);
                    images.add(R.drawable.fac_sayoni);
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
                    FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos,row));
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
                    images.add(R.drawable.fac_jl);
                    images.add(R.drawable.fac_bhuvaneswari);
                    images.add(R.drawable.fac_sunil);
                    images.add(R.drawable.fac_bhargav);
                    images.add(R.drawable.fac_sayantan);
                    images.add(R.drawable.fac_kr);
                    images.add(R.drawable.fac_aditya);
                    images.add(R.drawable.fac_ankita);
                    images.add(R.drawable.fac_gopinath);
                    images.add(R.drawable.fac_krishna);
                    images.add(R.drawable.fac_pooran);
                    images.add(R.drawable.fac_sreedhar);
                    images.add(R.drawable.fac_subbarao);
                    images.add(R.drawable.fac_ani);
                    images.add(R.drawable.fac_raj);
                    images.add(R.drawable.fac_ranjith);
                    images.add(R.drawable.fac_sayoni);
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
                    FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos,row));
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
                    images.add(R.drawable.fac_bhaskar);
                    images.add(R.drawable.fac_ranjith1);
                    images.add(R.drawable.fac_abhijit);
                    images.add(R.drawable.fac_jagan);
                    images.add(R.drawable.fac_palash);
                    images.add(R.drawable.fac_prasad);
                    images.add(R.drawable.fac_deep);
                    images.add(R.drawable.fac_harshavardhan);
                    images.add(R.drawable.fac_kondaiah);
                    images.add(R.drawable.fac_manish);
                    images.add(R.drawable.fac_ravikiran);
                    images.add(R.drawable.fac_sebastian);
                    images.add(R.drawable.fac_senbagaraman);
                    images.add(R.drawable.fac_ani);
                    images.add(R.drawable.fac_raj);
                    images.add(R.drawable.fac_ranjith);
                    images.add(R.drawable.fac_sayoni);
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
                    FacultyMenu.setAdapter(new Facultyadapter(images,titles,pos,row));

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