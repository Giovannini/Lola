package lola.giovannini.lola.activite_main.activite_combat.combat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import lola.giovannini.lola.activite_main.MainActivity;
import lola.giovannini.lola.activite_main.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/7/14.
 */
public class PVFragment extends Fragment implements View.OnClickListener{
    String CLASS_NAME = "PVFragment";
    /*Personnage*/
    Personnage perso;
    /*Buttons*/
    ImageView bouton_soin, bouton_degat;
    /*TextView*/
    TextView pvValue;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View combat = inflater.inflate(R.layout.frag_combat1_pv, container, false);
        combat.setOnClickListener(this);
        perso = ((MainActivity) getActivity()).getPerso();

        initViews(combat);
        getPVInfos();

        setButtonsAction();

        //Log.i(CLASS_NAME, "Le fragment de gestion des PV est créé.");
        return combat;
    }

    public void initViews(View combat){
        bouton_soin         = (ImageView) combat.findViewById(R.id.healButton);
        bouton_degat        = (ImageView) combat.findViewById(R.id.buttonDegat);
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
                ColorDrawable[] colorBlink = {new ColorDrawable(Color.parseColor("#bf1725")),
                        new ColorDrawable(getResources().getColor(R.color.themecolor))};
                TransitionDrawable trans = new TransitionDrawable(colorBlink);
                getActivity().getActionBar().setBackgroundDrawable(trans);
                trans.startTransition(500);
                try {
                    perso.getObj().put("Pv", perso.getCaractéristiques().getPv());
                    perso.getMain().saveJson(perso.getObj());
                } catch (JSONException e) {
                    Log.e(CLASS_NAME, e.getMessage());
                }
            }
        } else if (v == bouton_soin) {
            if (perso.getCaractéristiques().getPv() < perso.getCaractéristiques().getPvmax()) {
                perso.getCaractéristiques().addPv();
                pvValue.setText("" + perso.getCaractéristiques().getPv());
                ColorDrawable[] colorBlink = {new ColorDrawable(Color.parseColor("#97bf41")),
                        new ColorDrawable(getResources().getColor(R.color.themecolor))};
                TransitionDrawable trans = new TransitionDrawable(colorBlink);
                getActivity().getActionBar().setBackgroundDrawable(trans);
                trans.startTransition(500);
                try {
                    perso.getObj().put("Pv", perso.getCaractéristiques().getPv());
                    perso.getMain().saveJson(perso.getObj());
                } catch (JSONException e) {
                    Log.e(CLASS_NAME, e.getMessage());
                }
            }
        }
    }
}