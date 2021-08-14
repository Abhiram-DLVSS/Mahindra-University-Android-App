package MU.timetable;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import MU.BottomSheet;
import com.example.turing_login.BuildConfig;
import MU.DatabaseHelper;
import MU.Intents;
import com.example.turing_login.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class TimeTable extends Intents {

    private final int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tt_time_table);

        statusbar();//to change status bar color
        TabLayout tabLayout = findViewById(R.id.tab_bar);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageView imageView = findViewById(R.id.tt_3dot);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Availability");
        ref.keepSynced(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String chk= snapshot.child("Update").getValue().toString();
                String versionName = BuildConfig.VERSION_NAME;
                if(!chk.equals(versionName))
                    imageView.setImageResource(R.drawable.tt_3dotscircle);
                else
                    imageView.setImageResource(R.drawable.ic_three_dot);
                String menu= snapshot.child("Menu").getValue().toString();
                if(menu.equals("0"))
                    floatingmenu.setVisibility(View.INVISIBLE);
                else
                    floatingmenu.setVisibility(View.VISIBLE);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initializing the popup menu and giving the reference as current context
                PopupMenu popupMenu = new PopupMenu(TimeTable.this, imageView);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.menu_items, popupMenu.getMenu());
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Availability");
                ref.keepSynced(true);
                ref.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String chk= snapshot.child("Update").getValue().toString();
                        Menu menu=popupMenu.getMenu();
                        MenuItem update= menu.findItem(R.id.update);
                        String versionName = BuildConfig.VERSION_NAME;

                        update.setVisible(!chk.equals(versionName));
                        if(!chk.equals(versionName))
                            imageView.setImageResource(R.drawable.tt_3dotscircle);
                        else
                            imageView.setImageResource(R.drawable.ic_three_dot);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("chk", "Error: "+error);
                    }
                });

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if(menuItem.getItemId()==R.id.logout_in_menu) {

                            BottomSheet bottomSheet = new BottomSheet();
                            bottomSheet.show(getSupportFragmentManager(),
                                    "ModalBottomSheet");
                        }
                        else if(menuItem.getItemId()==R.id.contact) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            Uri data = Uri.parse("mailto:devturing21@gmail.com?subject=" + Uri.encode("Mahindra University App") + "&body=" + Uri.encode("~Write here~"));
                            intent.setData(data);
                            startActivity(intent);
                        }
                        else if(menuItem.getItemId()==R.id.update) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            Uri data = Uri.parse("https://drive.google.com/drive/folders/1JOOQGWbeiIwJ2jVWP7P9fbNgHXFRPylN");
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

        PagerAdapter pagerAdapter= new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),getApplicationContext());
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
        //Sql data
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        Cursor data = mDatabaseHelper.getData();
        data.moveToNext();
        data.getString(1);
//        Toast.makeText(TimeTable.this, data.getString(1), Toast.LENGTH_SHORT).show();
        timetable_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TimeTable.this, "👀", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);     }
}