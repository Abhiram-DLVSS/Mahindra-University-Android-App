package com.example.turing_login;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.firebase.database.core.Tag;

public class PagerAdapter extends FragmentPagerAdapter{
    private int numOfTabs;
    private static MonFragment mon;
    private static TueFragment tue;
    private static WedFragment wed;
    private static  ThuFragment thu;
    private static FriFragment fri;
    private static SatFragment sat;
    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs= numOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                if(mon==null) {
                    mon = new MonFragment();
                    Log.d("A22", "monday is null");
                }
                return mon;
            case 1:
//                if(tue==null)
                    tue =new TueFragment();
                return tue;
            case 2:
                if(wed==null)
                    wed =new WedFragment();
                return wed;
            case 3:
                if(thu==null)
                    thu =new ThuFragment();
                return thu;
            case 4:
                if(fri==null)
                    fri =new FriFragment();
                return fri;
            case 5:
                if(sat==null)
                    sat =new SatFragment();
                return sat;
            default:
                return null;

        }
    }

    @Override
    public int getCount() { return numOfTabs; }
}