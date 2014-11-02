package lola.giovannini.lola;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by giovannini on 10/17/14.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    Personnage perso;

    public TabPagerAdapter(FragmentManager fm, Personnage perso){
        super(fm);
        this.perso = perso;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new OverviewFragment();
            case 1:
                return new CompetencesFragment();
            case 2:
                return new EquipementFragment();
            case 3:
                return new CombatFragment();
            case 4:
                return new ClasseFragment();
            case 5:
                return new ClasseFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
