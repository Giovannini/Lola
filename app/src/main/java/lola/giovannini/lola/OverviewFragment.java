package lola.giovannini.lola;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by giovannini on 10/17/14.
 */
public class OverviewFragment extends Fragment implements View.OnClickListener {

    Personnage perso;

    TextView valueNom, valueSexe , valuePoids, valueTaille, valueAlignement, valueNiveau, valueExperience, caracTaille;

    TextView force, dexterite, constitution, intelligence, sagesse, charisme;
    EditText editXP;
    Button addXPButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View competences = inflater.inflate(R.layout.overview_frag, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();

        findViewsPersoInfos(competences);
        findViewsPersoCaracteristics(competences);

        retrieveData();

        Log.i("OverviewFragment", "Ce fragment est créé.");
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

    public void findViewsPersoCaracteristics(View competences){
        force = (TextView) competences.findViewById(R.id.valueFOR);
        dexterite = (TextView) competences.findViewById(R.id.valueDEX);
        constitution = (TextView) competences.findViewById(R.id.valueCON);
        intelligence = (TextView) competences.findViewById(R.id.valueINT);
        sagesse = (TextView) competences.findViewById(R.id.valueSAG);
        charisme = (TextView) competences.findViewById(R.id.valueCHA);
    }

    public void retrieveData(){
        valueNom.       setText(perso.getNom());
        valueSexe.      setText(perso.getSexe());
        valuePoids.     setText(perso.getPoids() + " kg");
        valueTaille.    setText(perso.getTaille() + " cm");
        caracTaille.    setText("[P]");/**TODO change that line*/
        valueAlignement.setText(perso.getAlignement());
        valueNiveau.    setText("" + perso.getNiveau());
        valueExperience.setText("" + perso.getExpérience());

        /*Caractéristiques*/
        force.setText("" + perso.getCaractéristiques().getForce());
        dexterite.setText("" + perso.getCaractéristiques().getDextérité());
        constitution.setText("" + perso.getCaractéristiques().getConstitution());
        intelligence.setText("" + perso.getCaractéristiques().getIntelligence());
        sagesse.setText("" + perso.getCaractéristiques().getSagesse());
        charisme.setText("" + perso.getCaractéristiques().getCharisme());
    }

    @Override
    public void onClick(View v) {
        if (v == addXPButton){
            /*int xp = Integer.parseInt(editXP.getText().toString());
            perso.addXP(xp);
            valueNiveau.setText("" + perso.getNiveau());
            valueExperience.setText("" + perso.getExpérience());*/
            final Context context = getActivity();

            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.ajout_xp_prompt, null);
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
