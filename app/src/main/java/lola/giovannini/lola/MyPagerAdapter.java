package lola.giovannini.lola;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lola.giovannini.lola.fragments.classe.ClasseFragment;
import lola.giovannini.lola.fragments.CompetencesFragment;
import lola.giovannini.lola.fragments.objets.EquipementFragment;
import lola.giovannini.lola.fragments.combat.CombatFragment;
import lola.giovannini.lola.fragments.general.OverviewFragment;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    public MyPagerAdapter(FragmentManager fm) {
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
                fragment = new EquipementFragment();
                break;
            case 3:
                fragment = new CombatFragment();
                break;
            case 4:
                fragment = new ClasseFragment();
                break;
            default:
                fragment = new ClasseFragment();
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
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}