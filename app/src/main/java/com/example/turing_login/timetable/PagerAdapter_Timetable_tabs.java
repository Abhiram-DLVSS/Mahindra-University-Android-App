package com.example.turing_login.timetable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.turing_login.timetable.FriFragment;
import com.example.turing_login.timetable.MonFragment;
import com.example.turing_login.timetable.SatFragment;
import com.example.turing_login.timetable.ThuFragment;
import com.example.turing_login.timetable.TueFragment;
import com.example.turing_login.timetable.WedFragment;

public class PagerAdapter_Timetable_tabs extends FragmentPagerAdapter{
    private int numOfTabs;
    public PagerAdapter_Timetable_tabs(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs= numOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MonFragment();
            case 1:
                return new TueFragment();
            case 2:
                return new WedFragment();
            case 3:
                return new ThuFragment();
            case 4:
                return new FriFragment();
            case 5:
                return new SatFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() { return numOfTabs; }
}