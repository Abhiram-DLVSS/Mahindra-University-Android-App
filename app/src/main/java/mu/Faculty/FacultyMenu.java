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
                    titles.add("Professor 1");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Dean Academics");

                    titles.add("Professor 2");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Sr.Associate Professor");

                    titles.add("Professor 3");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Associate Professor ");

                    titles.add("Professor 4");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Assistant Professor ");

                    titles.add("Professor 5");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Assistant Professor ");

                    titles.add("Professor 6");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Assistant Professor ");

                    for (int i = 0; i <= 6; i++) {
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
                    titles.add("Professor 1 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof1 Professor ");

                    titles.add("Professor 2 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof2 Professor ");

                    titles.add("Professor 3 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof3 Professor ");

                    titles.add("Professor 4 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof4 Professor ");

                    titles.add("Professor 5 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof5 Professor ");

                    titles.add("Professor 6 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof6 Professor ");

                    for (int i = 0; i <= 6; i++) {
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
                    titles.add("Professor 1 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof1 Professor ");

                    titles.add("Professor 2 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof2 Professor ");

                    titles.add("Professor 3 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof3 Professor ");

                    titles.add("Professor 4 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof4 Professor ");

                    titles.add("Professor 5 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof5 Professor ");

                    titles.add("Professor 6 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof6 Professor ");

                    for (int i = 0; i <= 6; i++) {
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
                    titles.add("Professor 1 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof1 Professor ");

                    titles.add("Professor 2 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof2 Professor ");

                    titles.add("Professor 3 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof3 Professor ");

                    titles.add("Professor 4 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof4 Professor ");

                    titles.add("Professor 5 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof5 Professor ");

                    titles.add("Professor 6 Name");
                    images.add(R.drawable.portait_placeholder);
                    pos.add("Prof6 Professor ");

                    for (int i = 0; i <= 6; i++) {
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