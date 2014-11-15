package lola.giovannini.lola.activite_combat.combat_armes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lola.giovannini.lola.R;
import lola.giovannini.lola.activite_combat.combat.Bba_CaFragment;
import lola.giovannini.lola.activite_combat.combat.InitiativeFragment;
import lola.giovannini.lola.activite_combat.combat.PVFragment;

/**
 * Created by giovannini on 11/14/14.
 */
public class CombatArmesFragment extends Fragment {
    String CLASS_NAME = "CombatFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View combat = inflater.inflate(R.layout.frag_combat2, container, false);

        getArmeFragment();
        getArmureFragment();

        Log.i(CLASS_NAME, "Le fragment combat est créé.");
        return combat;
    }

    private void getArmeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ArmeFragment armeFragment = new ArmeFragment();
        fragmentTransaction.add(R.id.combatContainerArme, armeFragment, "Arme");
        fragmentTransaction.commit();
    }

    private void getArmureFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ArmureFragment armeFragment = new ArmureFragment();
        fragmentTransaction.add(R.id.combatContainerArmure, armeFragment, "Armure");
        fragmentTransaction.commit();
    }


}
