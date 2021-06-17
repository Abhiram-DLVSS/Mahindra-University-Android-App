package com.example.turing_login;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Assignment1 extends Intents {

    public int flag=1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private int count, total;
    private List<Listitem_assign> listitem_assigns;
    //to fetch data
    DatabaseReference reff;

    public Assignment1() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statusbar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment1);
        floatinginit();
        assignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Assignment1.this, "👀", Toast.LENGTH_SHORT).show();
            }
        });




        recyclerView=findViewById(R.id.assign_re);
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
        Log.d("Assignment", "ReadHeader: ");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitem_assigns=new ArrayList<>();
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);
        reference.keepSynced(false);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listitem_assigns.clear();
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Assignment");
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
                            String m3= snapshot.child(chil).child("by").getValue().toString();
                            String m4 = snapshot.child(chil).child("link").getValue().toString();
                            Log.d("Assignment", "onDataChange: "+m1+m2+m3+m4);
                            Listitem_assign listitem_assign = new Listitem_assign(m1, m2, m3,m4);
                            assert listitem_assign != null;
                            listitem_assigns.add(listitem_assign);
                            adapter = new AdapterAssign(listitem_assigns, getApplicationContext());
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