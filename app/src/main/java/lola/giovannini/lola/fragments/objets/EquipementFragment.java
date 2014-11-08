package lola.giovannini.lola.fragments.objets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Objet;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;
import lola.giovannini.lola.fragments.general.CaracteristiquesFragment;

/**
 * Created by giovannini on 10/17/14.
 */
public class EquipementFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View equipement = inflater.inflate(R.layout.frag_objets, container, false);

        getActivity().getActionBar().setTitle("Équipement");

        getListeFragment();
        getRichesseFragment();

        Log.i("EquipementFragment", "Ce fragment est créé.");
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
