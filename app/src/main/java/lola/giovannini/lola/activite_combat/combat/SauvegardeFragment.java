package lola.giovannini.lola.activite_combat.combat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import lola.giovannini.lola.Caractéristique;
import lola.giovannini.lola.classes.Classe;
import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/14/14.
 */
public class SauvegardeFragment extends Fragment{
    String CLASS_NAME = "ArmeFragment";

    /*Personnage*/
    Personnage perso;
    /*TextView*/
    TextView valueVig, valueRef, valueVol;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View sauvegarde = inflater.inflate(R.layout.frag_combat1_sauvegarde, container, false);
        perso = ((MainActivity) getActivity()).getPerso();

        initViews(sauvegarde);
        getSauvegardesInfos();

        //Log.i(CLASS_NAME, "Le fragment Sauvegarde est créé.");
        return sauvegarde;
    }

    public void initViews(View sauvegarde) {
        valueVig = (TextView) sauvegarde.findViewById(R.id.valueVig);
        valueRef = (TextView) sauvegarde.findViewById(R.id.valueRef);
        valueVol = (TextView) sauvegarde.findViewById(R.id.valueVol);
    }

    private void getSauvegardesInfos(){
        int vig = 0, ref = 0, vol = 0;
        Caractéristique carac = perso.getCaractéristiques();
        Map<String, Integer> divers = perso.getDivers();
        /* Armes */
        List<Classe> classes = perso.getClasses();
        for(Classe c : classes){
            vig += c.getBonusVigueur();
            ref += c.getBonusRéflexes();
            vol += c.getBonusVolonté();
        }
        vig += carac.getModificateur("con");
        ref += carac.getModificateur("dex");
        vol += carac.getModificateur("sag");

        if (divers.containsKey("vig")) vig += divers.get("SAUVVIG");
        if (divers.containsKey("ref")) ref += divers.get("SAUVREF");
        if (divers.containsKey("vol")) vol += divers.get("SAUVVOL");

        valueVig.setText("" + vig);
        valueRef.setText("" + ref);
        valueVol.setText("" + vol);
    }
}