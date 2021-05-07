package com.example.turing_login.timetable;

import androidx.viewpager.widget.ViewPager;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.turing_login.BottomSheet;
import com.example.turing_login.Features;
import com.example.turing_login.Intents;
import com.example.turing_login.Login;
import com.example.turing_login.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Time;
import java.util.Calendar;

public class TimeTable extends Intents {

    private int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_time_table);

        statusbar();//to change status bar color
        TabLayout tabLayout = findViewById(R.id.tab_bar);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageView imageView = findViewById(R.id.tt_3dot);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initializing the popup menu and giving the reference as current context
                PopupMenu popupMenu = new PopupMenu(TimeTable.this, imageView);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.menu_items, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.logout_in_menu) {

                            BottomSheet bottomSheet = new BottomSheet();
                            bottomSheet.show(getSupportFragmentManager(),
                                    "ModalBottomSheet");
                            // Toast message on menu item clicked
//                            Toast.makeText(TimeTable.this, "Signing out...", Toast.LENGTH_SHORT).show();
//                            FirebaseAuth.getInstance().signOut();
//                            startActivity(new Intent(getApplicationContext(), Login.class));
//                            finish();
                        }
                        else if(menuItem.getItemId()==R.id.feat_menu) {
                            Intent intent=new Intent(TimeTable.this, Features.class);
                            startActivity(intent);
                            finish();
                        }
                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

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
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //floating
        floatinginit();
        timetable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TimeTable.this, "👀", Toast.LENGTH_SHORT).show();
            }
        });
        viewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int dx=scrollX-oldScrollX;
//                Log.d("chk", "onScrollChange: "+(scrollX-oldScrollX));

                if (dx !=0){
//                    final Animation animation = new TranslateAnimation(0,0,0,250);
//                    animation.setDuration(50);
//                    animation.setFillAfter(true);
//                    floatingmenu.startAnimation(animation);
//
//                    new Handler().postDelayed(new Runnable() {
//
//                        @Override
//                        public void run() {
                            final Animation animation1 = new TranslateAnimation(0,0,250,0);
                            animation1.setDuration(500);
                            animation1.setFillAfter(true);
                            floatingmenu.startAnimation(animation1);                        }
//                    }, 50);
//
//                }
            }
        });



//    first_time(0);


    }


}