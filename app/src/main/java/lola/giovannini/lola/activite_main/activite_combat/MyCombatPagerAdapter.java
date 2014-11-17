package lola.giovannini.lola.activite_main.activite_combat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lola.giovannini.lola.activite_main.activite_combat.combat.CombatFragment;
import lola.giovannini.lola.activite_main.activite_combat.combat_armes.CombatArmesFragment;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class MyCombatPagerAdapter extends FragmentStatePagerAdapter {
    public MyCombatPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        switch (i){
            case 0:
                fragment = new CombatFragment();
                break;
            case 1:
                fragment = new CombatArmesFragment();
                break;
            default:
                fragment = new CombatFragment();
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
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}