package MU.Forms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MU.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import MU.Intents;

public class Forms extends Intents {
    public int flag = 1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private int count, total;
    private List<Listitem_form> listitem_forms;

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
        recyclerView = findViewById(R.id.forms_re);
        recyclerView.setHasFixedSize(true);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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


        ReadHeader();
    }

    public void ReadHeader() {
        Log.d("Forms", "ReadHeader: ");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitem_forms = new ArrayList<>();
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Forms");
        reference1.keepSynced(true);
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                total = (int) snapshot.getChildrenCount();
                for (count = 0; count < total; count++) {
                    String chil = "" + count;
                    String m1 = Objects.requireNonNull(snapshot.child(chil).child("header").getValue()).toString();
                    String m2 = Objects.requireNonNull(snapshot.child(chil).child("time").getValue()).toString();
                    String m3 = Objects.requireNonNull(snapshot.child(chil).child("by").getValue()).toString();
                    String m4 = Objects.requireNonNull(snapshot.child(chil).child("loc").getValue()).toString();
                    Log.d("forms", "onDataChange: " + m1 + m2 + m3 + m4);
                    Listitem_form listitem_form = new Listitem_form(m1, m2, m3, m4);
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

    public void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}