package lola.giovannini.lola.activite_main.activite_combat.combat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lola.giovannini.lola.R;

public class CombatFragment extends Fragment {
    String CLASS_NAME = "CombatFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View combat = inflater.inflate(R.layout.frag_combat1, container, false);

        getInitiativeFragment();
        getPVFragment();
        getBBACAFragment();
        getSauvegardeFragment();

        Log.i(CLASS_NAME, "Le fragment combat est créé.");
        return combat;
    }

    private void getInitiativeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        InitiativeFragment initiativeFragment = new InitiativeFragment();
        fragmentTransaction.add(R.id.combatContainerInitiative, initiativeFragment, "Initiative");
        fragmentTransaction.commit();
    }

    private void getPVFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PVFragment pvFragment = new PVFragment();
        fragmentTransaction.add(R.id.combatContainerPV, pvFragment, "PV");
        fragmentTransaction.commit();
    }

    private void getBBACAFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bba_CaFragment bba_caFragment = new Bba_CaFragment();
        fragmentTransaction.add(R.id.combatContainerBbaCa, bba_caFragment, "BbaCa");
        fragmentTransaction.commit();
    }

    private void getSauvegardeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SauvegardeFragment sauvegardeFragment = new SauvegardeFragment();
        fragmentTransaction.add(R.id.combatContainerSauvegarde, sauvegardeFragment, "Sauvegarde");
        fragmentTransaction.commit();
    }


}