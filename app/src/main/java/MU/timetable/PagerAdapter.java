package MU.timetable;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//Functionality: When selected a Tab, open that tab
public class PagerAdapter extends FragmentPagerAdapter{
    private final int numOfTabs;
    private static Context context;
    public PagerAdapter(FragmentManager fm, int numOfTabs, Context context){
        super(fm);
        this.numOfTabs= numOfTabs;
        PagerAdapter.context =context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d("Tab", "Tab Number:"+position);
        switch (position){
            case 0:
                return new Frag("Monday");
            case 1:
                return new Frag("Tuesday");
            case 2:
                return new Frag("Wednesday");
            case 3:
                return new Frag("Thursday");
            case 4:
                return new Frag("Friday");
            case 5:
                return new Frag("Saturday");
            default:
                return null;
        }
    }
    @Override
    public int getCount() { return numOfTabs; }
}