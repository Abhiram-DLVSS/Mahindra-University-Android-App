package MU.timetable;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter{
    private final int numOfTabs;
    private static FragMon mon;
    private static FragTue tue;
    private static FragWed wed;
    private static FragThu thu;
    private static FragFri fri;
    private static FragSat sat;
    private static Context context;
    public PagerAdapter(FragmentManager fm, int numOfTabs, Context context){
        super(fm);
        this.numOfTabs= numOfTabs;
        PagerAdapter.context =context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
//    if(mon==null||tue==null||wed==null||thu==null||fri==null||sat==null) {
//        Log.d("frag", "getItem: Check");
//        if (mon == null)
//            mon = new FragMon();
//        if (tue == null)
//            tue = new FragTue();
//        if (wed == null)
//            wed = new FragWed();
//        if (thu == null)
//            thu = new FragThu();
//        if (fri == null)
//            fri = new FragFri();
//        if (sat == null)
//            sat = new FragSat();
//    }
        Log.d("frag", "getItem:"+position);
        switch (position){
            case 0:
                return new FragMon();
            case 1:
                return new FragTue();
            case 2:
                return new FragWed();
            case 3:
                return new FragThu();
            case 4:
                return new FragFri();
            case 5:
                return new FragSat();
            default:
                return null;
        }
    }

    @Override
    public int getCount() { return numOfTabs; }
}