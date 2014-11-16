package lola.giovannini.lola.activite_overview.general;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;
import lola.giovannini.lola.fragments.MySpinnerAdapter;

/**
 * Created by giovannini on 10/17/14.
 */
public class OverviewFragment extends Fragment implements View.OnClickListener{
    String CLASS_NAME = "OverviewFragment";
    Personnage perso;
    Spinner spinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View competences = inflater.inflate(R.layout.frag_overview, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();

        spinner = (Spinner) competences.findViewById(R.id.overviewSpinner);
        List<String> frag_names = new ArrayList<String>();
        frag_names.add("CARACTÉRISTIQUES");
        frag_names.add("RACE");
        frag_names.add("DONS");
        MySpinnerAdapter adapter = new MySpinnerAdapter(getActivity().getApplicationContext());
        adapter.update_frag_names(frag_names);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                changeBottomView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CaracteristiquesFragment carac_frag = new CaracteristiquesFragment();
        fragmentTransaction.add(R.id.overviewFragmentContainer, carac_frag, "Caractéristiques");
        fragmentTransaction.commit();

        getFragmentDetail();

        Log.i(CLASS_NAME, "Le fragment Général est créé.");
        return competences;
    }

    private void getCarac() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CaracteristiquesFragment carac_frag = new CaracteristiquesFragment();
        fragmentTransaction.replace(R.id.overviewFragmentContainer, carac_frag, "Caractéristiques");
        fragmentTransaction.commit();
    }

    private void getRace() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RaceFragment race_frag = new RaceFragment();
        fragmentTransaction.replace(R.id.overviewFragmentContainer, race_frag,
                "Race");
        fragmentTransaction.commit();
    }

    private void getDons() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DonsFragment dons_frag = new DonsFragment();
        fragmentTransaction.replace(R.id.overviewFragmentContainer, dons_frag,
                "Dons");
        fragmentTransaction.commit();
    }

    private void getFragmentDetail() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PersoDetailsFragment detailsFragment = new PersoDetailsFragment();
        fragmentTransaction.add(R.id.overviewFragmentContainer2, detailsFragment, "Détails");
        fragmentTransaction.commit();
    }

    public void changeBottomView(int position){
        switch (position) {
            case 0:
                getCarac();
                break;
            case 1:
                getRace();
                break;
            case 2:
                getDons();
                break;
        }
    }

    @Override
    public void onClick(View v) {
    }
}