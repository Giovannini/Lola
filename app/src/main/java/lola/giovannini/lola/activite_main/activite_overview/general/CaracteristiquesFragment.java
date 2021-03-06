package lola.giovannini.lola.activite_main.activite_overview.general;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import lola.giovannini.lola.activite_main.MainActivity;
import lola.giovannini.lola.activite_main.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/5/14.
 */
public class CaracteristiquesFragment extends Fragment {
    String CLASS_NAME = "CaracteristiquesFragment";
    Personnage perso;

    TextView force, dexterite, constitution, intelligence, sagesse, charisme;

    LinearLayout layoutAjoutPointCaractéristique;
    boolean ajoutPointCaractéristiquePossible;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View caractéristiques = inflater.inflate(R.layout.frag_overview_caracteristiques, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();
        this.ajoutPointCaractéristiquePossible = false;

        findViewsPersoCaracteristics(caractéristiques);

        retrieveData();

        if(this.ajoutPointCaractéristiquePossible){
            this.layoutAjoutPointCaractéristique.setVisibility(View.VISIBLE);
        }else{
            this.layoutAjoutPointCaractéristique.setVisibility(View.GONE);
        }

        //Log.i(CLASS_NAME, "Le fragment Caractéristiques est créé.");
        return caractéristiques;
    }

    private void retrieveData() {
         /*Caractéristiques*/
        force.setText("" + perso.getCaractéristiques().getForce() + "\t(" + perso
                .getCaractéristiques().getModificateur("for") + ")");
        dexterite.setText("" + perso.getCaractéristiques().getDextérité()+ "\t(" + perso
                .getCaractéristiques().getModificateur("dex") + ")");
        constitution.setText("" + perso.getCaractéristiques().getConstitution()+ "\t(" + perso
                .getCaractéristiques().getModificateur("con") + ")");
        intelligence.setText("" + perso.getCaractéristiques().getIntelligence()+ "\t(" + perso
                .getCaractéristiques().getModificateur("int") + ")");
        sagesse.setText("" + perso.getCaractéristiques().getSagesse()+ "\t(" + perso
                .getCaractéristiques().getModificateur("sag") + ")");
        charisme.setText("" + perso.getCaractéristiques().getCharisme()+ "\t(" + perso
                .getCaractéristiques().getModificateur("cha") + ")");

        ajoutPointCaractéristiquePossible = (perso.getPointCaractéristiques() > 0);
    }

    public void findViewsPersoCaracteristics(View caractéristiques){
        layoutAjoutPointCaractéristique = (LinearLayout) caractéristiques.findViewById(R.id
                .overview_layout_ajout_caracteristiques);
        force = (TextView) caractéristiques.findViewById(R.id.valueFOR);
        dexterite = (TextView) caractéristiques.findViewById(R.id.valueDEX);
        constitution = (TextView) caractéristiques.findViewById(R.id.valueCON);
        intelligence = (TextView) caractéristiques.findViewById(R.id.valueINT);
        sagesse = (TextView) caractéristiques.findViewById(R.id.valueSAG);
        charisme = (TextView) caractéristiques.findViewById(R.id.valueCHA);

        TextView b;
        for(int i = 0, fini = layoutAjoutPointCaractéristique.getChildCount(); i<fini; i++){
            b = (TextView) layoutAjoutPointCaractéristique.getChildAt(i);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(perso.getPointCaractéristiques() > 0) {
                        int index = layoutAjoutPointCaractéristique.indexOfChild(v);
                        switch (index) {
                            case 0://Force
                                perso.getCaractéristiques().ajoutForce();
                                force.setText("" + perso.getCaractéristiques().getForce());
                                perso.usePointCaracteristique();
                                break;
                            case 1://Dextérité
                                perso.getCaractéristiques().ajoutDexterite();
                                dexterite.setText("" + perso.getCaractéristiques().getDextérité());
                                perso.usePointCaracteristique();
                                break;
                            case 2://Constitution
                                perso.getCaractéristiques().ajoutConstitution();
                                constitution.setText("" + perso.getCaractéristiques().getConstitution());
                                perso.usePointCaracteristique();
                                break;
                            case 3://Intelligence
                                perso.getCaractéristiques().ajoutIntelligence();
                                intelligence.setText("" + perso.getCaractéristiques().getIntelligence());
                                perso.usePointCaracteristique();
                                break;
                            case 4://Sagesse
                                perso.getCaractéristiques().ajoutSagesse();
                                sagesse.setText("" + perso.getCaractéristiques().getSagesse());
                                perso.usePointCaracteristique();
                                break;
                            case 5://Charisme
                                perso.getCaractéristiques().ajoutCharisme();
                                charisme.setText("" + perso.getCaractéristiques().getCharisme());
                                perso.usePointCaracteristique();
                                break;
                            default:
                                Log.e("OverviewFragment.findViewsPersoCaracteristics()",
                                        "Erreur en ajoutant un point de caractéristique: mauvais " +
                                                "index.");
                                break;
                        }
                    }
                    if(perso.getPointCaractéristiques() <= 0){
                        layoutAjoutPointCaractéristique.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}
