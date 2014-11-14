package lola.giovannini.lola.activite_overview.objets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import lola.giovannini.lola.R;

/**
 * Created by giovannini on 10/17/14.
 */
public class EquipementFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View equipement = inflater.inflate(R.layout.frag_objets, container, false);

        getListeFragment();
        getRichesseFragment();

        Log.i("EquipementFragment", "Le fragment Équipement est créé.");
        return equipement;
    }

    private void getListeFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ObjetFragment objet_frag = new ObjetFragment();
        fragmentTransaction.replace(R.id.objetsContainerListe, objet_frag, "Liste d'objets");
        fragmentTransaction.commit();
    }

    private void getRichesseFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RichesseFragment richesseFragment = new RichesseFragment();
        fragmentTransaction.replace(R.id.objetsContainerRichesse, richesseFragment, "Richesse");
        fragmentTransaction.commit();
    }
}
