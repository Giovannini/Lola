package lola.giovannini.lola;


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
import android.widget.TextView;

/**
 * Created by giovannini on 10/17/14.
 */
public class OverviewFragment extends Fragment implements View.OnClickListener {

    Personnage perso;

    TextView valueNom, valueSexe , valuePoids, valueTaille, valueAlignement, valueNiveau, valueExperience, caracTaille;
    Button addXPButton;

    TextView carac, race, image;
    String currentTab;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View competences = inflater.inflate(R.layout.frag_overview, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();

        carac           = (TextView) competences.findViewById(R.id.overviewCaracTextView);
        carac.setOnClickListener(this);
        race            = (TextView) competences.findViewById(R.id.overviewRaceTextView);
        race.setOnClickListener(this);
        image           = (TextView) competences.findViewById(R.id.overviewImageTextView);
        image.setOnClickListener(this);

        getCarac();
        getFragmentDetail();

        Log.i("OverviewFragment", "Le fragment Général est créé.");
        return competences;
    }

    private void getCarac(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CaracteristiquesFragment carac_frag = new CaracteristiquesFragment();
        fragmentTransaction.add(R.id.overviewFragmentContainer, carac_frag, "Caractéristiques");
        fragmentTransaction.commit();
        currentTab = "carac";
        carac.setBackgroundColor(Color.parseColor("#9d7b62"));
    }

    private void getFragmentDetail(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PersoDetailsFragment detailsFragment = new PersoDetailsFragment();
        fragmentTransaction.add(R.id.overviewFragmentContainer2, detailsFragment, "Détails");
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v == race){
            if( ! currentTab.equals("race")) {
                race.setBackgroundColor(Color.parseColor("#9d7b62"));
                carac.setBackgroundColor(Color.parseColor("#8e282b"));
                image.setBackgroundColor(Color.parseColor("#8e282b"));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RaceFragment race_frag = new RaceFragment();
                fragmentTransaction.replace(R.id.overviewFragmentContainer, race_frag,
                        "Caractéristiques");
                fragmentTransaction.commit();
                currentTab = "race";
            }
        }else if(v == carac){
            if( ! currentTab.equals("carac")) {
                race.setBackgroundColor(Color.parseColor("#8e282b"));
                carac.setBackgroundColor(Color.parseColor("#9d7b62"));
                image.setBackgroundColor(Color.parseColor("#8e282b"));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CaracteristiquesFragment carac_frag = new CaracteristiquesFragment();
                fragmentTransaction.replace(R.id.overviewFragmentContainer, carac_frag,
                        "Caractéristiques");
                fragmentTransaction.commit();
                currentTab = "carac";
            }
        }else if (v == image){

        }
    }
}
