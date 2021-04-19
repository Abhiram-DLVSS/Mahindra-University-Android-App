package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

import toan.android.floatingactionmenu.FloatingActionButton;
import toan.android.floatingactionmenu.FloatingActionsMenu;

public class TimeTable extends AppCompatActivity {
    private FloatingActionButton timetable_button,faculty,fee,event;
    private FloatingActionsMenu floatingmenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table);

        //To get custom status bar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.stan));

        TabLayout tabLayout = findViewById(R.id.tab_bar);
        ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter= new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (Calendar.MONDAY == dayOfWeek) {
            viewPager.setCurrentItem(0, true);
        } else if (Calendar.TUESDAY == dayOfWeek) {
            viewPager.setCurrentItem(1, true);
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            viewPager.setCurrentItem(2, true);
        } else if (Calendar.THURSDAY == dayOfWeek) {
            viewPager.setCurrentItem(3, true);
        } else if (Calendar.FRIDAY == dayOfWeek) {
            viewPager.setCurrentItem(4, true);
        } else if (Calendar.SATURDAY == dayOfWeek) {
            viewPager.setCurrentItem(5, true);
        } else if (Calendar.SUNDAY == dayOfWeek) {
            viewPager.setCurrentItem(0, true);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.d("frag", "onTabSelected:"+tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("frag", "onTabUnselected:"+tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.d("frag", "onTabReselect:"+tab.getPosition());
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //floating
        floatingmenu=findViewById(R.id.tt_fm_menu);
        fee=findViewById(R.id.tt_fm_fees);
        faculty=findViewById(R.id.tt_fm_faculty);
        event=findViewById(R.id.tt_fm_events);
        timetable_button=findViewById(R.id.tt_fm_timetable);


        floatingmenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
//                Toast.makeText(TimeTable.this, "test", Toast.LENGTH_SHORT).show();
                timetable_button.setVisibility(View.VISIBLE);
                fee.setVisibility(View.VISIBLE);
                faculty.setVisibility(View.VISIBLE);
                event.setVisibility(View.VISIBLE);
                floatingmenu.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_multip,null));
                floatingmenu.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.menu,null));
                floatingmenu.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            }

            @Override
            public void onMenuCollapsed() {
                floatingmenu.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_hamburger_icon_svg,null));
                timetable_button.setVisibility(View.GONE);
                fee.setVisibility(View.GONE);
                faculty.setVisibility(View.GONE);
                event.setVisibility(View.GONE);
                floatingmenu.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.nth,null));
                floatingmenu.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.tt_main);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.tt_fm_menu,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM,0);
                constraintSet.connect(R.id.tt_fm_menu,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END,0);
                constraintSet.applyTo(constraintLayout);

            }});


        fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
            }
        });
        fee.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Uri copyUri = Uri.parse("https://mahindraecolecentrale.unicampus.in/ERPLogin.aspx?type=std");
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newUri(getContentResolver(), "URI", copyUri);
                clipboard.setPrimaryClip(clip);


                Toast toast=Toast.makeText(getApplicationContext(),"Link Copied to Clipboard",Toast.LENGTH_SHORT);
                toast.show();
                return true;
            }
        });
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFaculty();
            }
        });
        timetable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeTable();

            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEvent();
            }
        });

    }
    private void openTimeTable() {
        Toast.makeText(this, "👀", Toast.LENGTH_SHORT).show();
    }
    private void openFaculty() {
        Intent intent=new Intent(this,FacultyMenu.class);
        startActivity(intent);
    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void openFeature()
    {
        Intent intent=new Intent(this, Forms.class);
        startActivity(intent);
    }
    public void openEvent()
    {
        ProgressDialog nDialog;
        nDialog = new ProgressDialog(com.example.turing_login.TimeTable.this);
        nDialog.setMessage("Loading the Website");
        nDialog.setIndeterminate(false);
        nDialog.show();
        Intent intent=new Intent(this, Event.class);
        startActivity(intent);
        nDialog.dismiss();
    }
}