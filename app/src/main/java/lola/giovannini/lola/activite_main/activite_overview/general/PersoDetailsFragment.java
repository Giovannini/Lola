package lola.giovannini.lola.activite_main.activite_overview.general;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import lola.giovannini.lola.activite_main.MainActivity;
import lola.giovannini.lola.activite_main.Personnage;
import lola.giovannini.lola.R;

/**
 * Created by giovannini on 11/5/14.
 */
public class PersoDetailsFragment extends Fragment implements View.OnClickListener{
    String CLASS_NAME = "PersoDetailsFragment";
    Personnage perso;

    TextView valueNom, valueSexe , valuePoids, valueTaille, valueAlignement, valueNiveau, valueExperience, caracTaille;
    Button addXPButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View competences = inflater.inflate(R.layout.frag_overview_details_perso, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();
        findViewsPersoInfos(competences);
        retrieveData();

        //Log.i(CLASS_NAME, "Le fragment Détails est créé.");
        return competences;
    }

    public void findViewsPersoInfos(View competences){
        valueNom        = (TextView) competences.findViewById(R.id.valueNom);
        valueSexe       = (TextView) competences.findViewById(R.id.valueSexe);
        valuePoids      = (TextView) competences.findViewById(R.id.valuePoids);
        valueTaille     = (TextView) competences.findViewById(R.id.valueTaille);
        valueAlignement = (TextView) competences.findViewById(R.id.valueAlignement);
        valueNiveau     = (TextView) competences.findViewById(R.id.valueNiveau);
        valueExperience = (TextView) competences.findViewById(R.id.valueExperience);
        caracTaille     = (TextView) competences.findViewById(R.id.carac_taille);

        addXPButton = (Button) competences.findViewById(R.id.XPupButton);
        addXPButton.setOnClickListener(this);
    }

    public void retrieveData(){
        valueNom.       setText(perso.getNom());
        valueSexe.      setText(perso.getSexe());
        valuePoids.     setText(perso.getPoids() + " kg");
        valueTaille.    setText(perso.getTaille() + " cm");
        caracTaille.    setText("[" + perso.getRace().getTaille() +"]");
        valueAlignement.setText(perso.getAlignement());
        valueNiveau.    setText("" + perso.getNiveau());
        valueExperience.setText("" + perso.getExpérience());
    }

    @Override
    public void onClick(View v) {
        if (v == addXPButton){
            final Context context = getActivity();

            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.prompt_ajout_xp, null);
            final EditText xp = (EditText) promptsView.findViewById(R.id.add_xp_editText);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(true)
                    .setPositiveButton("Ajouter",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Personnage perso = ((MainActivity) getActivity())
                                            .getPerso();
                                    String textXP = xp.getText().toString();
                                    if ((!textXP.equals(""))) {
                                        perso.addXP(Integer.parseInt(textXP));
                                        valueNiveau.setText("" + perso.getNiveau());
                                        valueExperience.setText("" + perso.getExpérience());
                                    }
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
    }
}
