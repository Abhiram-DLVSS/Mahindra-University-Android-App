package mu.timetable;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//Functionality: When selected a Tab, open that tab
public class PagerAdapter extends FragmentPagerAdapter {
    private static Context context;
    private final int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs, Context context) {
        super(fm);
        this.numOfTabs = numOfTabs;
        PagerAdapter.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
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
    public int getCount() {
        return numOfTabs;
    }
}