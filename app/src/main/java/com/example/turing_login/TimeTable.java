package com.example.turing_login;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

import toan.android.floatingactionmenu.FloatingActionButton;
import toan.android.floatingactionmenu.FloatingActionsMenu;

public class TimeTable extends Intents {
    private FloatingActionButton timetable_button,faculty,fee,event,grades,assignments,forms;
    private FloatingActionsMenu floatingmenu;
    private ConstraintLayout tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table);

        statusbar();//to change status bar color

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
        floatingmenu=findViewById(R.id.fm_menu);
        fee=findViewById(R.id.fm_fees);
        faculty=findViewById(R.id.fm_faculty);
        event=findViewById(R.id.fm_events);
        timetable_button=findViewById(R.id.fm_timetable);
        tt=findViewById(R.id.tt_fm);
        grades=findViewById(R.id.fm_grades);
        assignments=findViewById(R.id.fm_assignment);
        forms=findViewById(R.id.fm_forms);

        floatingmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingmenu.collapse();
            }
        });

        floatingmenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                timetable_button.setVisibility(View.VISIBLE);
                fee.setVisibility(View.VISIBLE);
                faculty.setVisibility(View.VISIBLE);
                event.setVisibility(View.VISIBLE);
                grades.setVisibility(View.VISIBLE);
                assignments.setVisibility(View.VISIBLE);
                forms.setVisibility(View.VISIBLE);
                floatingmenu.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_multip,null));
                floatingmenu.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.menu,null));
                tt.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                floatingmenu.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            @Override
            public void onMenuCollapsed() {
                floatingmenu.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_hamburger_icon_svg,null));
                timetable_button.setVisibility(View.GONE);
                fee.setVisibility(View.GONE);
                faculty.setVisibility(View.GONE);
                event.setVisibility(View.GONE);
                grades.setVisibility(View.GONE);
                assignments.setVisibility(View.GONE);
                forms.setVisibility(View.GONE);
                floatingmenu.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.nth,null));
                floatingmenu.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.tt_fm);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.fm_menu,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM,0);
                constraintSet.connect(R.id.fm_menu,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END,0);
                constraintSet.applyTo(constraintLayout);

                ConstraintLayout constraintLayout1 = (ConstraintLayout) findViewById(R.id.tt_main);
                ConstraintSet constraintSet1 = new ConstraintSet();
                constraintSet1.clone(constraintLayout1);
                constraintSet1.connect(R.id.tt_fm,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM,0);
                constraintSet1.connect(R.id.tt_fm,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END,0);
                constraintSet1.applyTo(constraintLayout1);
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
        forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openForms();

            }
        });

    }
}