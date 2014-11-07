package lola.giovannini.lola.fragments.combat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lola.giovannini.lola.Armure;
import lola.giovannini.lola.MainActivity;
import lola.giovannini.lola.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/7/14.
 */
public class PVFragment extends Fragment implements View.OnClickListener{
    String CLASS_NAME = "CombatFragment";
    /*Personnage*/
    Personnage perso;
    /*Buttons*/
    Button bouton_soin, bouton_degat;
    /*TextView*/
    TextView pvValue;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View combat = inflater.inflate(R.layout.frag_combat_pv, container, false);
        combat.setOnClickListener(this);
        perso = ((MainActivity) getActivity()).getPerso();

        initViews(combat);
        getPVInfos();

        setButtonsAction();

        Log.i("CombatFragment", "Ce fragment est créé.");
        return combat;
    }

    public void initViews(View combat){
        bouton_soin         = (Button) combat.findViewById(R.id.healButton);
        bouton_degat        = (Button) combat.findViewById(R.id.buttonDegat);
        pvValue             = (TextView) combat.findViewById(R.id.PVTextView);
    }

    private void getPVInfos(){
        pvValue.setText("" + perso.getCaractéristiques().getPv());
    }

    private void setButtonsAction(){
        bouton_soin.setOnClickListener(this);
        bouton_degat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == this.bouton_degat) {
            if (perso.getCaractéristiques().getPv() > -10) {
                perso.getCaractéristiques().removePv();
                pvValue.setText("" + perso.getCaractéristiques().getPv());
                try {
                    perso.getObj().put("Pv", perso.getCaractéristiques().getPv());
                    perso.getMain().saveJson(perso.getObj());
                } catch (JSONException e) {
                    Log.e("CombatFragment", e.getMessage());
                }
            }
        } else if (v == bouton_soin) {
            if (perso.getCaractéristiques().getPv() < perso.getCaractéristiques().getPvmax()) {
                perso.getCaractéristiques().addPv();
                pvValue.setText("" + perso.getCaractéristiques().getPv());
                try {
                    perso.getObj().put("Pv", perso.getCaractéristiques().getPv());
                    perso.getMain().saveJson(perso.getObj());
                } catch (JSONException e) {
                    Log.e("CombatFragment", e.getMessage());
                }
            }
        }
    }
}