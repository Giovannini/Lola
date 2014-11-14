package lola.giovannini.lola.activite_combat.combat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lola.giovannini.lola.Armure;
import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/7/14.
 */
public class Bba_CaFragment  extends Fragment{
    String CLASS_NAME = "BBA et CA Fragment";

    /*Personnage*/
    Personnage perso;
    /*TextView*/
    TextView bba_melee, bba_distance, ca_valeur;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View bba_ca = inflater.inflate(R.layout.frag_combat1_bba_ca, container, false);
        perso = ((MainActivity) getActivity()).getPerso();

        initViews(bba_ca);
        getBbaInfos();
        getCaInfos();

        Log.i(CLASS_NAME, "Le fragment BBA/CA est créé.");
        return bba_ca;
    }

    public void initViews(View bba_ca) {
        bba_melee           = (TextView) bba_ca.findViewById(R.id.bba_melee_value);
        bba_distance        = (TextView) bba_ca.findViewById(R.id.bba_distance_value);
        ca_valeur           = (TextView) bba_ca.findViewById(R.id.valeurCA);
    }

    private void getBbaInfos(){
        int bba = perso.getBba();
        int bonusTaille = perso.getBonusTaille();
        int melee = bba + perso.getCaractéristiques().getModificateur("for") + bonusTaille;
        int distance = bba + perso.getCaractéristiques().getModificateur("dex") + bonusTaille;

        bba_melee.setText(""+melee);
        bba_distance.setText(""+distance);
    }

    private void getCaInfos(){
        int ca = 10;
        for (Armure a : perso.getArmures()){
            ca += Integer.parseInt(a.getCa());
            ca += perso.getCaractéristiques().getModificateur("dex");
            ca += perso.getBonusTaille();
            if(perso.getDivers().containsKey("ca")) {
                ca += perso.getDivers().get("ca");
            }
        }
        ca_valeur.setText(""+ca);
    }
}
