package lola.giovannini.lola.activite_creation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lola.giovannini.lola.R;
import lola.giovannini.lola.activite_main.activite_overview.CompetencesFragment;
import lola.giovannini.lola.activite_main.activite_overview.classe.ClasseFragment;
import lola.giovannini.lola.activite_main.activite_overview.general.OverviewFragment;
import lola.giovannini.lola.activite_main.activite_overview.objets.EquipementFragment;
import lola.giovannini.lola.activite_main.races.Race;
import lola.giovannini.lola.activite_main.races.RaceHalfelin;

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
        System.out.println("Badaboum " + i);
        Fragment fragment;
        fragment =  new RaceChooserFragment();

        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt("object", i + 1);
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