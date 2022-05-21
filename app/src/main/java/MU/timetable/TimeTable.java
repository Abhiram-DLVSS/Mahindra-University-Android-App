package MU.timetable;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.MU.BuildConfig;
import com.MU.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Objects;

import MU.DatabaseHelper;
import MU.Intents;

public class TimeTable extends Intents {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_time_table);

        statusbar();//to change status bar color
        TextView timetable_name=findViewById(R.id.Name_timetable_textview);
        TabLayout tabLayout = findViewById(R.id.tab_bar);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageView threeDot = findViewById(R.id.tt_3dot);

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        Cursor data = mDatabaseHelper.getData();
        data.moveToNext();
        String name=data.getString(2);
        try {
            String strdata = this.getIntent().getExtras().getString("Uniqid");
            if (strdata.equals("Splash")) {
                timetable_name.setText("Welcome " + name.substring(0, 1).toUpperCase() + name.substring(1) + "!");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        timetable_name.setText("Timetable");
                    }
                }, 3000);
            }
        }
        catch (Exception e){

        }

        //Accessing the Availability node in the Firebase Database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Availability");
        ref.keepSynced(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //To check whether there are new updates or not and display a cue to make the users update the app
                String chk = Objects.requireNonNull(snapshot.child("Update").getValue()).toString();
                String versionName = BuildConfig.VERSION_NAME;
                if (!chk.equals(versionName)) {
                    threeDot.setImageResource(R.drawable.tt_3dotscircle);
                    threeDot.setTag("circle");
                } else {
                    threeDot.setImageResource(R.drawable.ic_three_dot);
                    threeDot.setTag(null);
                }
                //To check the database, whether to display the Floating menu or not, if needed
                String menu = Objects.requireNonNull(snapshot.child("Menu").getValue()).toString();
                if (menu.equals("0"))
                    floatingmenu.setVisibility(View.INVISIBLE);
                else
                    floatingmenu.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        threeDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initializing the popup menu and giving the reference as current context
                PopupMenu popupMenu = new PopupMenu(TimeTable.this, threeDot);
                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.menu_items, popupMenu.getMenu());
                Menu menu = popupMenu.getMenu();
                MenuItem update = menu.findItem(R.id.update);
                //If the threeDot Element has a circle(which was based on firebase node), we will enable the Update available option
                update.setVisible(threeDot.getTag() == "circle");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        //Logout
                        if (menuItem.getItemId() == R.id.logout_in_menu) {
                            BottomSheet bottomSheet = new BottomSheet();
                            bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
                        }
                        //Contact Us
                        else if (menuItem.getItemId() == R.id.contact) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            Uri data = Uri.parse("mailto:devturing21@gmail.com?subject=" + Uri.encode("Mahindra University App") + "&body=" + Uri.encode("~Write here~"));
                            intent.setData(data);
                            startActivity(intent);
                        }
                        //Update Available
                        else if (menuItem.getItemId() == R.id.update) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            Uri data = Uri.parse("https://drive.google.com/drive/folders/1EwWLzi3xKLluGMUZB1Qu2ByGqPIoZoAR?usp=sharing");
                            intent.setData(data);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

        //For the Timetable fragments
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        //To display the present day in the Timetable by default
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

        //Navigating between the tabs
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
        //If we click the Timetable button in floating menu when we are in Timetable
        timetable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TimeTable.this, "👀", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //When back button is pressed
    @Override
    public void onBackPressed() {
        if (floatingmenu.isExpanded())
            floatingmenu.collapse();
        else {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }
}