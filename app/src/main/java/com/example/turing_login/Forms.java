package com.example.turing_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.example.turing_login.timetable.AdapterMon;
import com.example.turing_login.timetable.Listitem_monfrag;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Forms extends Intents {

    public int flag=1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private int count, total;
    private List<Listitem_form> listitem_forms;
    //to fetch data
    DatabaseReference reff;

    public Forms() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statusbar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forms);
        floatinginit();
        forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Forms.this, "👀", Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView=findViewById(R.id.forms_re);
        recyclerView.setHasFixedSize(true);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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


        ReadHeader();
    }
    public void ReadHeader(){
        Log.d("Forms", "ReadHeader: ");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitem_forms=new ArrayList<>();
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);
        reference.keepSynced(false);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listitem_forms.clear();
//                String rollnumber = snapshot.child("id").getValue().toString();
//                String year = rollnumber.substring(0, 2);
//                String branch = rollnumber.substring(7, 8);
//                String rno = rollnumber.substring(8, 10);
//                int  batch = 1;
//                if (Integer.parseInt(branch) == 1) {
//                    if (Integer.parseInt(rno) <= 44)
//                        batch = 1;
//                    else
//                        batch = 3;//not there actually
//                } else if (Integer.parseInt(branch) == 2) {
//                    if (Integer.parseInt(rno) <= 35)
//                        batch = 1;
//                    else
//                        batch = 2;
//                } else if (Integer.parseInt(branch) == 3) {
//                    if (Integer.parseInt(rno) <= 35)
//                        batch = 1;
//                    else
//                        batch = 2;
//                } else if (Integer.parseInt(branch) == 5) {
//                    if (Integer.parseInt(rno) <= 42)
//                        batch = 1;
//                    else
//                        batch = 2;
//                }
//                String batnum=""+batch;

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Forms");
                reference1.keepSynced(true);
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        total = (int) snapshot.getChildrenCount();
//                        total=5;
                        for (count = 0; count < total; count++) {
                            String chil = "" + count;
                            String m1 = snapshot.child(chil).child("header").getValue().toString();
                            String m2 = snapshot.child(chil).child("time").getValue().toString();
                            String m3=snapshot.child(chil).child("by").getValue().toString();
                            String m4 = snapshot.child(chil).child("link").getValue().toString();

//                            String m1="hello";
//                            String m2="hello";
//                            String m3="hello";
//                            String m4="hello";

//                            int k;
//                            Date d=new Date();
//                            SimpleDateFormat sdf=new SimpleDateFormat("HHmm");
//                            String currentDateTimeString = sdf.format(d);
//                            int time=Integer.parseInt(currentDateTimeString);
//                            Calendar c = Calendar.getInstance();
//                            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//                            if(time>=Integer.parseInt(m2.substring(0,2)+m2.substring(3,5))&&time<=Integer.parseInt(m2.substring(8,10)+m2.substring(11,13))&&Calendar.MONDAY == dayOfWeek)
//                                k=-7596779;
//                            else
//                                k=-1;//-16777216;
                            Log.d("forms", "onDataChange: "+m1+m2+m3+m4);
                            Listitem_form listitem_form = new Listitem_form(m1, m2, m3,m4);
                            assert listitem_form != null;
                            listitem_forms.add(listitem_form);
                            adapter = new AdapterForms(listitem_forms, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}