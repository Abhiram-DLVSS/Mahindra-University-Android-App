package com.example.turing_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TimeTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        TabLayout tabLayout = findViewById(R.id.tab_bar);
        TabItem tab_mon= findViewById(R.id.mon_tab);
        TabItem tab_tue= findViewById(R.id.tue_tab);
        TabItem tab_wed= findViewById(R.id.wed_tab);
        TabItem tab_thu= findViewById(R.id.thu_tab);
        TabItem tab_fri= findViewById(R.id.fri_tab);
        TabItem tab_sat= findViewById(R.id.sat_tab);
        ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter= new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}