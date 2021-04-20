package com.example.turing_login.timetable;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter{
    private int numOfTabs;
    private static FragMon mon;
    private static FragTue tue;
    private static FragWed wed;
    private static FragThu thu;
    private static FragFri fri;
    private static FragSat sat;
    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs= numOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
       // Log.d("frag", "Position "+position);
        switch (position){
            case 0:
                Log.d("frag", "monday is there");
                if(mon==null) {
                    mon = new FragMon();
                    Log.d("frag", "monday is null");
                }
                return mon;
            case 1:
                Log.d("frag", "tuesday is there");
                if(tue==null)
                    tue =new FragTue();
                Log.d("frag", "tuesday is null");
                return tue;
            case 2:
                if(wed==null)
                    wed =new FragWed();
                Log.d("frag", "wednesday is null");
                return wed;
            case 3:
                if(thu==null)
                    thu =new FragThu();
                Log.d("frag", "thursday is null");
                return thu;
            case 4:
                if(fri==null)
                    fri =new FragFri();
                Log.d("frag", "friday is null");
                return fri;
            case 5:
                if(sat==null)
                    sat =new FragSat();
                Log.d("frag", "saturday is null");
                return sat;
            default:
                return null;

        }
    }

    @Override
    public int getCount() { return numOfTabs; }
}