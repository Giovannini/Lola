package lola.giovannini.lola.activite_creation.race;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class MyRacePagerAdapter extends FragmentStatePagerAdapter {
    int count;
    public MyRacePagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        fragment =  new RaceChooserFragment();

        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt("object", i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}