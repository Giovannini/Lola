package lola.giovannini.lola.activite_overview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lola.giovannini.lola.activite_combat.combat.CombatFragment;
import lola.giovannini.lola.activite_overview.classe.ClasseFragment;
import lola.giovannini.lola.activite_overview.general.OverviewFragment;
import lola.giovannini.lola.activite_overview.objets.EquipementFragment;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class MyOverviewPagerAdapter extends FragmentStatePagerAdapter {
    public MyOverviewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        switch (i){
            case 0:
                fragment =  new OverviewFragment();
                break;
            case 1:
                fragment = new CompetencesFragment();
                break;
            case 2:
                fragment = new ClasseFragment();
                break;
            case 3:
                fragment = new EquipementFragment();
                break;
            default:
                fragment = new OverviewFragment();
                break;
        }
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt("object", i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}