package mu.Faculty;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.MU.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mu.DatabaseHelper;
import mu.Intents;

public class FacultyMenu extends Intents implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout mSwipeRefreshLayout;
    private int flag = 1, count, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fac_faculty_menu);
        RecyclerView FacultyMenu = findViewById(R.id.FacultyMenu);
        statusbar();

        mSwipeRefreshLayout = findViewById(R.id.FacultyMenu_re);
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
                if (dy > 10 && flag == 1) {
                    flag = 0;
                    final Animation animation = new TranslateAnimation(0, 0, 0, 250);
                    animation.setDuration(500);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (flag == 0)
                                floatingmenu.setVisibility(View.GONE);
                        }
                    }, 500);
                    animation.setFillAfter(true);
                    floatingmenu.startAnimation(animation);
                } else if (dy < -10 && flag == 0) {
                    floatingmenu.setVisibility(View.VISIBLE);
                    flag = 1;
                    floatingmenu.setVisible(true);
                    final Animation animation = new TranslateAnimation(0, 0, 250, 0);
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

    void fetch() {
        RecyclerView FacultyMenu = findViewById(R.id.FacultyMenu);

        List<String> titles = new ArrayList<>();
        List<Integer> images = new ArrayList<>();
        List<String> pos = new ArrayList<>();
        List<Drawable> row = new ArrayList<>();
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        Cursor data = mDatabaseHelper.getData();
        data.moveToNext();
        String rollnumber = data.getString(1);
        String year = rollnumber.substring(0, 2);
        String branch = rollnumber.substring(7, 8);
        if ((Integer.parseInt(year) == 19) && (Integer.parseInt(branch) == 5)) {

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Faculty").child("CSE");
            reference.keepSynced(true);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    total = (int) snapshot.getChildrenCount();
                    int[] free = new int[total];
                    for (count = 0; count < total; count++) {
                        String m1 = snapshot.child("" + count).getValue().toString();
                        free[count] = Integer.parseInt(m1);
                    }

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
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
                    images.add(R.drawable.fac_tamal);
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


                    for (int i = 0; i <= 19; i++) {
                        if (free[i] == 0)
                            row.add(getResources().getDrawable(R.drawable.round_red_shade));
                        else if (free[i] == 1)
                            row.add(getResources().getDrawable(R.drawable.round_green_shade));
                    }
                    FacultyMenu.setAdapter(new Facultyadapter(images, titles, pos, row));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        } else if ((Integer.parseInt(year) == 19) && (Integer.parseInt(branch) == 1)) {

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Faculty").child("CSE");
            reference.keepSynced(true);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    total = (int) snapshot.getChildrenCount();
                    int[] free = new int[total];
                    for (count = 0; count < total; count++) {
                        String m1 = snapshot.child("" + count).getValue().toString();
                        free[count] = Integer.parseInt(m1);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
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
                    images.add(R.drawable.fac_tamal);
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
                    for (int i = 0; i <= 19; i++) {
                        if (free[i] == 0)
                            row.add(getResources().getDrawable(R.drawable.round_red_shade));
                        else if (free[i] == 1)
                            row.add(getResources().getDrawable(R.drawable.round_green_shade));
                    }
                    FacultyMenu.setAdapter(new Facultyadapter(images, titles, pos, row));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else if ((Integer.parseInt(year) == 19) && (Integer.parseInt(branch) == 2)) {

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Faculty").child("CSE");
            reference.keepSynced(true);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    total = (int) snapshot.getChildrenCount();
                    int[] free = new int[total];
                    for (count = 0; count < total; count++) {
                        String m1 = snapshot.child("" + count).getValue().toString();
                        free[count] = Integer.parseInt(m1);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
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
                    images.add(R.drawable.fac_tamal);
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
                    for (int i = 0; i <= 19; i++) {
                        if (free[i] == 0)
                            row.add(getResources().getDrawable(R.drawable.round_red_shade));
                        else if (free[i] == 1)
                            row.add(getResources().getDrawable(R.drawable.round_green_shade));
                    }
                    FacultyMenu.setAdapter(new Facultyadapter(images, titles, pos, row));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else if ((Integer.parseInt(year) == 19) && (Integer.parseInt(branch) == 3)) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Faculty").child("CSE");
            reference.keepSynced(true);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    total = (int) snapshot.getChildrenCount();
                    int[] free = new int[total];
                    for (count = 0; count < total; count++) {
                        String m1 = snapshot.child("" + count).getValue().toString();
                        free[count] = Integer.parseInt(m1);
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
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
                    images.add(R.drawable.fac_tamal);
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
                    for (int i = 0; i <= 19; i++) {
                        if (free[i] == 0)
                            row.add(getResources().getDrawable(R.drawable.round_red_shade));
                        else if (free[i] == 1)
                            row.add(getResources().getDrawable(R.drawable.round_green_shade));
                    }
                    FacultyMenu.setAdapter(new Facultyadapter(images, titles, pos, row));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        fetch();
    }
}