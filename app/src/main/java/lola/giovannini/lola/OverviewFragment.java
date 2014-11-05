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
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by giovannini on 10/17/14.
 */
public class OverviewFragment extends Fragment implements View.OnClickListener {

    Personnage perso;

    TextView valueNom, valueSexe , valuePoids, valueTaille, valueAlignement, valueNiveau, valueExperience, caracTaille;

    TextView force, dexterite, constitution, intelligence, sagesse, charisme;
    Button addXPButton;
    LinearLayout layoutAjoutPointCaractéristique;
    boolean ajoutPointCaractéristiquePossible;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View competences = inflater.inflate(R.layout.overview_frag, container, false);

        this.perso = ((MainActivity) getActivity()).getPerso();
        this.ajoutPointCaractéristiquePossible = false;

        findViewsPersoInfos(competences);
        findViewsPersoCaracteristics(competences);

        retrieveData();

        if(this.ajoutPointCaractéristiquePossible){
            this.layoutAjoutPointCaractéristique.setVisibility(View.VISIBLE);
        }else{
            this.layoutAjoutPointCaractéristique.setVisibility(View.GONE);
        }

        Log.i("OverviewFragment", "Le fragment Général est créé.");
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
        layoutAjoutPointCaractéristique = (LinearLayout) competences.findViewById(R.id
                .overview_layout_ajout_caracteristiques);
        force = (TextView) competences.findViewById(R.id.valueFOR);
        dexterite = (TextView) competences.findViewById(R.id.valueDEX);
        constitution = (TextView) competences.findViewById(R.id.valueCON);
        intelligence = (TextView) competences.findViewById(R.id.valueINT);
        sagesse = (TextView) competences.findViewById(R.id.valueSAG);
        charisme = (TextView) competences.findViewById(R.id.valueCHA);

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

        ajoutPointCaractéristiquePossible = (perso.getPointCaractéristiques() > 0);
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
