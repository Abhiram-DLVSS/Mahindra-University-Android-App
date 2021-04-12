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
    private static ThuFragment thu;
    private static FriFragment fri;
    private static SatFragment sat;
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
                    mon = new MonFragment();
                    Log.d("frag", "monday is null");
                }
                return mon;
            case 1:
                Log.d("frag", "tuesday is there");
                if(tue==null)
                    tue =new TueFragment();
                Log.d("frag", "tuesday is null");
                return tue;
            case 2:
                if(wed==null)
                    wed =new WedFragment();
                Log.d("frag", "wednesday is null");
                return wed;
            case 3:
                if(thu==null)
                    thu =new ThuFragment();
                Log.d("frag", "thursday is null");
                return thu;
            case 4:
                if(fri==null)
                    fri =new FriFragment();
                Log.d("frag", "friday is null");
                return fri;
            case 5:
                if(sat==null)
                    sat =new SatFragment();
                Log.d("frag", "saturday is null");
                return sat;
            default:
                return null;

        }
    }

    @Override
    public int getCount() { return numOfTabs; }
}