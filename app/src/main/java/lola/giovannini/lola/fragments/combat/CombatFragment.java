package lola.giovannini.lola.fragments.combat;

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
        View combat = inflater.inflate(R.layout.frag_combat, container, false);

        getArmeFragment();
        getArmureFragment();
        getInitiativeFragment();
        getPVFragment();
        getBBACAFragment();

        Log.i(CLASS_NAME, "Ce fragment est créé.");
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

    private void getInitiativeFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        InitiativeFragment initiativeFragment = new InitiativeFragment();
        fragmentTransaction.add(R.id.combatContainerInitiative, initiativeFragment, "Armure");
        fragmentTransaction.commit();
    }

    private void getPVFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PVFragment pvFragment = new PVFragment();
        fragmentTransaction.add(R.id.combatContainerPV, pvFragment, "Armure");
        fragmentTransaction.commit();
    }

    private void getBBACAFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bba_CaFragment bba_caFragment = new Bba_CaFragment();
        fragmentTransaction.add(R.id.combatContainerBbaCa, bba_caFragment, "Armure");
        fragmentTransaction.commit();
    }


}